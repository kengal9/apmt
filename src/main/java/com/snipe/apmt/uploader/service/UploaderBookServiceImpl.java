package com.snipe.apmt.uploader.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.snipe.apmt.exception.APMTRestException.SESSION_TIMEOUT;
import com.snipe.apmt.filter.APMTFilter;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.service.SendUploadedEmailTemplate;
import com.snipe.apmt.service.UserService;
import com.snipe.apmt.uploader.dao.UploaderBookRepository;
import com.snipe.apmt.uploader.dao.UploaderRepository;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.domain.UploaderBookDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.mapper.UploaderBookMapper;
import com.snipe.apmt.uploader.mapper.UploaderMapper;
import com.snipe.apmt.uploader.model.UploaderBookModel;
import com.snipe.apmt.uploader.model.UploaderBookModel;
import com.snipe.apmt.uploader.model.UploaderModel;
import com.snipe.apmt.utils.CommonUtils;
import com.snipe.apmt.utils.IDGeneration;
import com.snipe.apmt.verification.service.IEmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UploaderBookServiceImpl implements UploaderBookService {

	@Autowired
	UploaderBookRepository uploaderBookRepository;

	@Autowired
	UploaderBookMapper uploaderBookMapper;

	@Autowired
	IDGeneration idGeneration;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	UserService userService;
	
	@Autowired
	private AmazonS3 s3Client;
	
	//@Autowired
	//public EmailService emailService;
	
	@Autowired
	IEmailService emailService;
	
	@Autowired
	UserDAORepository userDAORepository;
	
		
	private static final Logger logger = LoggerFactory.getLogger(APMTFilter.class);

	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Value("${apmt.apmtUrl}")
	private String url;
	
	
//	@Override
//	public List<UploaderBookModel> getBooksList() throws Exception {
//		List<UploaderBookDomain> uploaderBookDomain = uploaderBookRepository.findAll();
//		return uploaderBookMapper.entityList(uploaderBookDomain);
//	}
	
	@Override
	public List<UploaderBookModel> getBooksList() {

		List<UploaderBookDomain> ListOfBookDomin = uploaderBookRepository.findAll();
		List<UploaderBookModel> ListOfBookModel = uploaderBookMapper.entityList(ListOfBookDomin);

		List<UploaderBookModel> listUploaderBookModel = ListOfBookModel.stream().map((book) -> {
			try {
				book.setImage2(url+"/download?fileName="+book.getImage());
			} 
			catch (AmazonS3Exception e2) {
				book.setImage2(null);
			}
			return book;
		}).collect(Collectors.toList());

		return listUploaderBookModel;
	}

	@Override
	public List<UploaderBookModel> getBooksListById(long bookId) throws Exception {
		List<UploaderBookDomain>uploaderBookDomain=uploaderBookRepository.findByBookId(bookId);
		return uploaderBookMapper.entityList(uploaderBookDomain);
	}

	@Override
	public String deleteByBookId(long bookId) throws Exception {
		UploaderBookDomain uploaderBookDomain=uploaderBookRepository.findBookByBookId(bookId);
		uploaderBookRepository.deleteByBookId(bookId);
	    UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
		userService.saveUserLogDetails(userDomain, "deleteBook",uploaderBookDomain.getTitle());
		return "Book Deleted";
	}

	@Override
	public String UploadBookInStorageService(MultipartFile file, String fileNameAndPath) throws Exception {
		File fileObj=convertMultiPartFileToFile(file);
		s3Client.putObject(new PutObjectRequest(bucketName,fileNameAndPath,fileObj));
		fileObj.delete();
		return "File uploaded :"+fileNameAndPath;
	}
	

	@Override
	public String deleteBook(String bookName) throws Exception {
		s3Client.deleteObject(bucketName, bookName);
		return bookName+ " removed....";
	}

	@Override
	public UploaderBookModel getBookByBookId(long bookId) throws Exception {
		 UploaderBookDomain uploaderBookDomain = uploaderBookRepository.findBookByBookId(bookId);
		 UploaderBookModel uploaderBookModel=new UploaderBookModel();
		 
		 uploaderBookModel.setId(uploaderBookDomain.getId());
		 uploaderBookModel.setDescription(uploaderBookDomain.getDescription());
		 uploaderBookModel.setImage(uploaderBookDomain.getImage());
		 uploaderBookModel.setPrice(uploaderBookDomain.getPrice());
		 uploaderBookModel.setBookId(uploaderBookDomain.getBookId());
		 uploaderBookModel.setSynopsis(uploaderBookDomain.getSynopsis());
		 uploaderBookModel.setTechnology(uploaderBookDomain.getTechnology());
		 uploaderBookModel.setTitle(uploaderBookDomain.getTitle());
		 uploaderBookModel.setCategoryId(uploaderBookDomain.getCategoryId());
		 uploaderBookModel.setPrice(uploaderBookDomain.getPrice());		 
		 uploaderBookModel.setBookCode(uploaderBookDomain.getBookCode());		 
		 uploaderBookModel.setStatus(uploaderBookDomain.getStatus());		 
		 uploaderBookModel.setUploaderId(uploaderBookDomain.getUploaderId());
		 uploaderBookModel.setEditPrice(uploaderBookDomain.getEditPrice());
		 S3Object object =s3Client.getObject(bucketName, uploaderBookDomain.getImage());
	     S3ObjectInputStream  stream  =  object.getObjectContent();
	     uploaderBookModel.setImage1(IOUtils.toByteArray(stream));                         
		 
		return uploaderBookModel;

	}

	@Override
	public byte[] downloadBook(String bookName) throws Exception {
		S3Object s3Object= s3Client.getObject(bucketName, bookName);
		S3ObjectInputStream inputStream=s3Object.getObjectContent();
		byte[] content;
		try {
			content = IOUtils.toByteArray(inputStream);
			return content;
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return null;
	}
	
	
	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile=new File(file.getOriginalFilename());
		try(FileOutputStream fos=new FileOutputStream(convertedFile))
		{
			fos.write(file.getBytes());
		}
		catch(IOException e) {
			log.error("Error converting multipartFile to file",e);
			
		}
		return convertedFile;
	}

	@Override
    public String saveBook(UploaderBookModel uploaderBookModel, MultipartFile image)
			throws Exception {
	
	UploaderBookDomain uploaderBookDomain = new UploaderBookDomain();
	BeanUtils.copyProperties(uploaderBookModel, uploaderBookDomain);
	
	int bookId=uploaderBookRepository.createBookId();
	
	uploaderBookDomain.setBookId(bookId);

	// Capturing User information to store userid in project table
	// Inserting a record in user log table
	
//	UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
//	Optional<UserDomain> checkNull = Optional.ofNullable(userDomain);
//
//	if (checkNull.isPresent()) {
//		userService.saveUserLogDetails(userDomain, "saveBook",uploaderBookModel.getTitle());
//		uploaderBookDomain.setUploaderId(userDomain.getId());
//	} else {
//		throw new SESSION_TIMEOUT();
//	}
	
	String imgName="Book/Image/"+bookId+"_book_img_"+image.getOriginalFilename();
	//String videoName="Project/Videos/"+uploaderDomain.getProjectId()+"_video_"+video.getOriginalFilename();
	
	
	
	UploadBookInStorageService(image,imgName);
	
	//UploadFileInStorageService(video,videoName);
	
	
	uploaderBookDomain.setImage(imgName);
	//uploaderDomain.setImagePath(imgName);
	
	//uploaderDomain.setVideo(videoName);
	uploaderBookRepository.save(uploaderBookDomain);
	
	//Sending mail to uploader and Internal users
	 //UserDomain userDomain= (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
	 String ToEmail=uploaderBookDomain.getEmailId();	
	 String[] Cc = userDAORepository.getMailIdByRoleId();
	 emailService.sendUploadBookSuccessMessage("From", ToEmail,Cc);
	 return "Book Saved";
	}

	@Override
	public List<UploaderBookModel> getApprovedBooks() {
		List<UploaderBookDomain> uploaderBookDomain = uploaderBookRepository.getApprovedBooks();
		
		List<UploaderBookModel> ListOfBookModel = uploaderBookMapper.entityList(uploaderBookDomain);
		
		List<UploaderBookModel> listUploaderBookModel = ListOfBookModel.stream().map((book) -> {
			try {
//				S3Object object = s3Client.getObject(bucketName, article.getImage());
//				S3ObjectInputStream stream = object.getObjectContent();
//				article.setImage1(IOUtils.toByteArray(stream));
				book.setImage2(url+"/download?fileName="+book.getImage());
				//book.setVideo2(url+"/download?fileName="+book.getVideo());
			} 
			catch (AmazonS3Exception e2) {
				book.setImage2(null);
			}
			return book;
		}).collect(Collectors.toList());

		return listUploaderBookModel;
	}
	}

	
	

