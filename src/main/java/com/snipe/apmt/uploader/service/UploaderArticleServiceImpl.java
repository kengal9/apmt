package com.snipe.apmt.uploader.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.filter.APMTFilter;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.service.SendUploadedEmailTemplate;
import com.snipe.apmt.service.UserService;
import com.snipe.apmt.uploader.dao.UploaderArticleRepository;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.DUPLIACATE_ENTRY_FOUND;
import com.snipe.apmt.exception.APMTRestException.SESSION_TIMEOUT;
import com.snipe.apmt.exception.APMTRestException.VALIDATE;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.mapper.UploaderArticleMapper;
import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.utils.CommonUtils;
import com.snipe.apmt.verification.service.IEmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UploaderArticleServiceImpl implements UploaderArticleService {

	@Autowired
	UploaderArticleRepository articleRepository;
	
	@Autowired
	IEmailService emailService;

	@Autowired
	UserService userService;

	@Autowired
	UploaderArticleMapper articleMapper;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	private AmazonS3 s3Client;
	
	@Autowired
	UserDAORepository userDAORepository;
		

	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Value("${apmt.apmtUrl}")
	private String url;

	private static final Logger logger = LoggerFactory.getLogger(APMTFilter.class);

//	final String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/Images";

	@Override
	public String saveArticle(UploaderArticleModel uploderArticleModel, MultipartFile image) throws Exception {

		if (uploderArticleModel.getTechnologyUsed().isEmpty())
			throw new Exception(" Please Enter Technology Used ");
		if (uploderArticleModel.getCategoryId() == 0)
			throw new Exception(" Please select Caegory  ");
		if (uploderArticleModel.getDescription().isEmpty())
			throw new Exception(" Please Enter Description ");
		if (uploderArticleModel.getTitle().isEmpty())
			throw new Exception(" Please Enter Title ");

		UploaderArticleDomain uploaderArticleDomain = new UploaderArticleDomain();
		BeanUtils.copyProperties(uploderArticleModel, uploaderArticleDomain);

		int articleId = articleRepository.createArticleId();

		uploaderArticleDomain.setArticleId(articleId);

//		UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
//		Optional<UserDomain> checkNull = Optional.ofNullable(userDomain);
//
//		if (checkNull.isPresent()) {
//			userService.saveUserLogDetails(userDomain, "saveArticle", uploderArticleModel.getTitle());
//			uploaderArticleDomain.setUploaderId(userDomain.getId());
//		} else {
//			throw new SESSION_TIMEOUT();
//		}

		String filenameImage = "Article/Image/" + articleId + "_art_img_" + image.getOriginalFilename();
//		String videoName = "Article/Videos/" + articleId  + "Art_video_" + video.getOriginalFilename();
		uploaderArticleDomain.setImageName(filenameImage);
//			uploaderArticleDomain.setVideoName(videoName);


		UploadFileInStorageService(image, filenameImage);
//			UploadFileInStorageService(video, videoName);
//			uploadFile(image);
//			uploadFile(video);
		//Email for uploader and internal users
		articleRepository.save(uploaderArticleDomain);
		// UserDomain userDomain= (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
		 String ToEmail=uploaderArticleDomain.getEmailId();
		 String[] Cc = userDAORepository.getMailIdByRoleId();
		 emailService.sendUploadArticleSuccessMessage("From", ToEmail,Cc);
		return "Article saved";
	}

	
//	public String uploadFile(MultipartFile file) throws Exception {
//
//		boolean Upath = new File(uploadDirectory).mkdir();
//		StringBuilder fileNames = new StringBuilder();
//
//		String filename = file.getOriginalFilename();
//
//		Path fileNameAndPath = Paths.get(uploadDirectory, filename);
//
//		fileNames.append(file.getOriginalFilename());
//		Files.write(fileNameAndPath, file.getBytes());
//
//		return "Image is saved";
//	}

	@Override
	public String UploadFileInStorageService(MultipartFile file, String fileName) throws Exception {
		File fileObj = convertMultiPartFileToFile(file);
		// String fileName=System.currentTimeMillis()+"_"+file.getOriginalFilename();
		s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
		fileObj.delete();
		return "File uploaded :" + fileName;
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());
		try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
			fos.write(file.getBytes());
		} catch (IOException e) {
			log.error("Error converting multipartFile to file", e);

		}
		return convertedFile;
	}

	@Override
	public byte[] downloadFile(String fileName) {
		S3Object s3Object = s3Client.getObject(bucketName, fileName);
		S3ObjectInputStream inputStream = s3Object.getObjectContent();
		byte[] content;
		try {
			content = IOUtils.toByteArray(inputStream);
			return content;
		} catch (IOException e) {
			e.printStackTrace();
			throw new BACKEND_SERVER_ERROR();
		}
	}

	@Override
	public UploaderArticleModel getArticleByArticleId(long articleId) throws Exception {
		try {
			UploaderArticleDomain uploaderArticalDomine = articleRepository.getByArticleId(articleId);
			UploaderArticleModel uploaderArticleModel = articleMapper.entity(uploaderArticalDomine);
//			System.out.println(uploaderArticalDomine.getImageName());
			S3Object object = s3Client.getObject(bucketName, uploaderArticalDomine.getImageName());
			S3ObjectInputStream stream = object.getObjectContent();

			uploaderArticleModel.setImage1(IOUtils.toByteArray(stream));


//			S3Object object2 = s3Client.getObject(bucketName, uploaderArticalDomine.getVideoName());
//			S3ObjectInputStream stream2 = object2.getObjectContent();
//			uploaderArticleModel.setVideo1(IOUtils.toByteArray(stream2));
			return uploaderArticleModel;
		} catch (Exception e) {
			logger.error("Found Exaption in UploaderArticleServiceImplementation: ", e.getMessage());
			e.printStackTrace();
			throw new BACKEND_SERVER_ERROR();

		}
//		return null;
	}

	@Override
	public String deleteFile(String fileName) {
		s3Client.deleteObject(bucketName, fileName);
		return fileName + " removed....";

	}

	@Override
	public String updateArticle(UploaderArticleModel uploderArticleModel) throws Exception {
		try {
			UploaderArticleDomain uploaderArticleDomain = articleMapper.model(uploderArticleModel);
			articleRepository.save(uploaderArticleDomain);
			UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
			userService.saveUserLogDetails(userDomain, "updateArticle", uploderArticleModel.getTitle());

		} catch (Exception e) {
			logger.error("Exception created in UploaderArticleServiceImplementation: ", e.getMessage());
			e.printStackTrace();
			throw new BACKEND_SERVER_ERROR();
		}
		return "article updated";
	}

	@Override
	public String deleteByArticleId(long articleId) {
		UploaderArticleDomain byArticleId = articleRepository.getByArticleId(articleId);
		if (byArticleId == null) {
			throw new VALIDATE("Please Enter Valid Article Id");
		}

		UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
		userService.saveUserLogDetails(userDomain, "deleteArticle", byArticleId.getTitle());
		articleRepository.deleteByArticleId(articleId);
		return "deleted";
	}

	@Override
	public List<UploaderArticleModel> getListByArticleId() {

		List<UploaderArticleDomain> ListOfArticleDomin = articleRepository.findAll();
		List<UploaderArticleModel> ListOfArticleModel = articleMapper.entityList(ListOfArticleDomin);

		List<UploaderArticleModel> listUploaderArticleModel = ListOfArticleModel.stream().map((article) -> {
			try {
//				S3Object object = s3Client.getObject(bucketName, article.getImage());
//				S3ObjectInputStream stream = object.getObjectContent();
//				article.setImage1(IOUtils.toByteArray(stream));
				article.setImage2(url+"/download?fileName="+article.getImage());
			} 
			catch (AmazonS3Exception e2) {
				article.setImage1(null);
			}
			return article;
		}).collect(Collectors.toList());

		return listUploaderArticleModel;
	}


	@Override
	public List<UploaderArticleModel> getApprovedArticles() {
		List<UploaderArticleDomain> uploaderArticleDomain = articleRepository.getApprovedArticles();
		//return articleMapper.entityList(uploaderArticleDomain);
		List<UploaderArticleModel> ListOfArticleModel = articleMapper.entityList(uploaderArticleDomain);
		//return uploaderMapper.entityList(uploaderDomain);
		List<UploaderArticleModel> listUploaderArticleModel = ListOfArticleModel.stream().map((article) -> {
			try {
//				S3Object object = s3Client.getObject(bucketName, article.getImage());
//				S3ObjectInputStream stream = object.getObjectContent();
//				article.setImage1(IOUtils.toByteArray(stream));
				article.setImage2(url+"/download?fileName="+article.getImage());
				//article.setVideo2(url+"/download?fileName="+article.getVideo());
			} 
			catch (AmazonS3Exception e2) {
				article.setImage2(null);
			}
			return article;
		}).collect(Collectors.toList());

		return listUploaderArticleModel;
	}
	}


	

