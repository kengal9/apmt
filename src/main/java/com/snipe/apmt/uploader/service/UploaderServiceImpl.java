package com.snipe.apmt.uploader.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.snipe.apmt.admin.service.AdminOperationsService;
import com.snipe.apmt.dao.CartDAORepository;
import com.snipe.apmt.dao.RoleRepository;
import com.snipe.apmt.dao.UserDAORepository;
import com.snipe.apmt.dao.UserLogRepository;
import com.snipe.apmt.domain.CartDomain;
import com.snipe.apmt.domain.RoleDomain;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.domain.UserLogDomain;
import com.snipe.apmt.exception.APMTRestException.BACKEND_SERVER_ERROR;
import com.snipe.apmt.exception.APMTRestException.SESSION_TIMEOUT;
import com.snipe.apmt.filter.APMTFilter;
import com.snipe.apmt.mapper.UserMapper;
import com.snipe.apmt.model.RoleModel;
import com.snipe.apmt.model.UserModel;
import com.snipe.apmt.service.SendUploadedEmailTemplate;
import com.snipe.apmt.service.UserService;
import com.snipe.apmt.uploader.dao.UploaderRepository;
import com.snipe.apmt.uploader.domain.UploaderArticleDomain;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import com.snipe.apmt.uploader.mapper.UploaderMapper;
import com.snipe.apmt.uploader.model.UploaderArticleModel;
import com.snipe.apmt.uploader.model.UploaderModel;
import com.snipe.apmt.utils.CommonUtils;
import com.snipe.apmt.utils.IDGeneration;
import com.snipe.apmt.verification.constant.ArticleStatusCode;
import com.snipe.apmt.verification.service.IEmailService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UploaderServiceImpl implements UploaderService {

	@Autowired
	UploaderRepository uploaderRepository;
	
	@Autowired
    UserDAORepository userDaoRepository;

	@Autowired
	UploaderMapper uploaderMapper;

	@Autowired
	IDGeneration idGeneration;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	UserService userService;
	
	@Autowired
	private AmazonS3 s3Client;
	
	@Autowired
	AdminOperationsService adminOperationsService;
	
	@Autowired
	UserDAORepository userDAORepository;
	
	@Autowired
	UserMapper userMapper;
	
	
	@Autowired
	IEmailService emailService;
	
	@Autowired 
	CartDAORepository cartDAORepository;
	
	private static final Logger logger = LoggerFactory.getLogger(APMTFilter.class);

	private static final String String = null;

	//final String uploadDirectory = System.getProperty("user.dir") + "/src/main/resources/Images";
	
	
	@Value("${application.bucket.name}")
	private String bucketName;
	
	@Value("${apmt.apmtUrl}")
	private String url;
	
	
	@Override
	public String saveProject(UploaderModel UploaderModel,MultipartFile image,MultipartFile video) throws Exception {
		
		
		//Validations
		
				if(UploaderModel.getTechnologyUsed().isEmpty())
					throw new Exception(" Please Enter Technology Used ");
				if(UploaderModel.getCategoryId()== 0)
					throw new Exception(" Please select Caegory  ");
				if(UploaderModel.getDescription().isEmpty())
					throw new Exception(" Please Enter Description ");
				if(UploaderModel.getTitle().isEmpty())
					throw new Exception(" Please Enter Title ");	
				
		
		UploaderDomain uploaderDomain = new UploaderDomain();
		// UploaderModel.setProjectId(idGeneration.generateRandomId());
		BeanUtils.copyProperties(UploaderModel, uploaderDomain);
		int projectId=uploaderRepository.createProjectId();
		
		uploaderDomain.setProjectId(projectId);
		 
		
		

		// Capturing User information to store userid in project table
		// Inserting a record in user log table
		
//		UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
//		Optional<UserDomain> checkNull = Optional.ofNullable(userDomain);
//
//		if (checkNull.isPresent()) {
//			userService.saveUserLogDetails(userDomain, "saveProject",UploaderModel.getTitle());
//			uploaderDomain.setUploaderId(userDomain.getId());
//		} else {
//			throw new SESSION_TIMEOUT();
//		}
		/*/////////////
		File fileObj=convertMultiPartFileToFile(image);
		
		String fileName="Project/Image/"+System.currentTimeMillis()+"_"+image.getOriginalFilename();
		s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj));
		fileObj.delete();
		
		//String videoName=uploaderDomain.getProjectId()+"_video_"+image.getOriginalFilename().substring(image.getOriginalFilename().length()-4);
		
		/////////////////////////////////*/
		String imgName="Project/Image/"+projectId+"_prj_img_"+image.getOriginalFilename();
		String videoName="Project/Videos/"+projectId+"_prj_video_"+video.getOriginalFilename();
		
		
		
		UploadFileInStorageService(image,imgName);
		
		UploadFileInStorageService(video,videoName);
		
		
		uploaderDomain.setImage(imgName);
		//uploaderDomain.setImagePath(imgName);
		
		uploaderDomain.setVideo(videoName);

		uploaderRepository.save(uploaderDomain);

        //	For Sending Email TO Uploader and Internal Users	
		// UserDomain userDomain= (UserDomain) CommonUtils.geSsession().getAttribute("userModel");			
		 String ToEmail=uploaderDomain.getEmailId();
		 String[] Cc = userDAORepository.getMailIdByRoleId();
		 emailService.sendUploadSuccessMessage("From", ToEmail,Cc);
		 return "Project Saved";
	}
	
	
//	@Override
//	public List<UploaderModel> getProjectList() throws Exception {
//		List<UploaderDomain> uploaderDomain = uploaderRepository.findAll();
//		return uploaderMapper.entityList(uploaderDomain);
//
//	}
	
	@Override
	public List<UploaderModel>  getProjectList() {

		List<UploaderDomain> ListOfProjectDomin = uploaderRepository.findAll();
		List<UploaderModel> ListOfProjectModel = uploaderMapper.entityList(ListOfProjectDomin);

		List<UploaderModel> listUploaderProjectModel = ListOfProjectModel.stream().map((project) -> {
			try {
//				S3Object object = s3Client.getObject(bucketName, article.getImage());
//				S3ObjectInputStream stream = object.getObjectContent();
//				article.setImage1(IOUtils.toByteArray(stream));
				project.setImage2(url+"/download?fileName="+project.getImage());
				project.setVideo2(url+"/download?fileName="+project.getVideo());
			} 
			catch (AmazonS3Exception e2) {
				project.setImage2(null);
			}
			return project;
		}).collect(Collectors.toList());

		return listUploaderProjectModel;
	}

	/*@Override
	public UploaderModel getProjectListById(long projectId) throws Exception {
		UploaderModel uploaderModel = new UploaderModel();
		UploaderDomain uploaderDomain = uploaderRepository.findByProjectId(projectId);
		BeanUtils.copyProperties(uploaderDomain, uploaderModel);
		return uploaderModel;
	}*/
	
	@Override
	public List<UploaderModel> getProjectListById(long projectId) throws Exception {
		
		List<UploaderDomain> uploaderDomain = uploaderRepository.findByProjectId(projectId);
		return uploaderMapper.entityList(uploaderDomain);
	}

	@Override
	public UploaderDomain updateProject(UploaderModel uploaderModel) throws Exception {
		UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(uploaderModel.getProjectId());
		BeanUtils.copyProperties(uploaderModel, uploaderDomain);
		//Insert into user activity log table
		UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
		userService.saveUserLogDetails(userDomain, "updateProject",uploaderModel.getTitle());
		return uploaderRepository.save(uploaderDomain);

	}

	@Override
	public String deleteByProjectId(long projectId) throws Exception {
		UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(projectId);
		uploaderRepository.deleteByProjectId(projectId);
		//Insert into user activity log table
		UserDomain userDomain = (UserDomain) CommonUtils.geSsession().getAttribute("userModel");
		userService.saveUserLogDetails(userDomain, "deleteProject",uploaderDomain.getTitle());
		return "Project Deleted";
	}

	/*
	@Override
	public String uploadFile(@RequestParam("File") MultipartFile file) throws Exception {

		boolean Upath = new File(uploadDirectory).mkdir();
		StringBuilder fileNames = new StringBuilder();

		String filename = file.getOriginalFilename();

		Path fileNameAndPath = Paths.get(uploadDirectory, filename);

		fileNames.append(file.getOriginalFilename());
		Files.write(fileNameAndPath, file.getBytes());

		return "Image is saved";
	}*/

	@Override
	public String UploadFileInStorageService(MultipartFile file,String fileName) throws Exception{
		File fileObj=convertMultiPartFileToFile(file);
		//String fileName=System.currentTimeMillis()+"_"+file.getOriginalFilename();
		s3Client.putObject(new PutObjectRequest(bucketName,fileName,fileObj));
		fileObj.delete();
		return "File uploaded :"+fileName;
	}
	//To only File Download not data from the database
	@Override
	public byte[] downloadFile(String fileName) {
		S3Object s3Object= s3Client.getObject(bucketName, fileName);
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
	@Override
	public String deleteFile(String fileName) {
		s3Client.deleteObject(bucketName, fileName);
		return fileName+ " removed....";
		
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
	public UploaderModel getProjectByProjectId(long projectId) throws Exception {
		
		UploaderDomain uploaderDomain = uploaderRepository.findProjectByProjectId(projectId);
		 UploaderModel uploaderModel=new UploaderModel();
		 
		 uploaderModel.setId(uploaderDomain.getId());
		 uploaderModel.setDescription(uploaderDomain.getDescription());
		 uploaderModel.setImage(uploaderDomain.getImage());
		 uploaderModel.setModules(uploaderDomain.getModules());
		 

		 uploaderModel.setPrice(uploaderDomain.getPrice());
		 uploaderModel.setProjectId(uploaderDomain.getProjectId());
		 

		 uploaderModel.setSynopsis(uploaderDomain.getSynopsis());
		 uploaderModel.setTechnologyUsed(uploaderDomain.getTechnologyUsed());
		 

		 uploaderModel.setTitle(uploaderDomain.getTitle());
		 uploaderModel.setVideo(uploaderDomain.getVideo());
		 

		 uploaderModel.setCategoryId(uploaderDomain.getCategoryId());
		 uploaderModel.setEditPrice(uploaderDomain.getEditPrice());
		 
		 
		 
		 uploaderModel.setProjectCode(uploaderDomain.getProjectCode());
		 
		 uploaderModel.setStatus(uploaderDomain.getStatus());
		 
		 uploaderModel.setUploaderId(uploaderDomain.getUploaderId());
		 
		 
		 S3Object object =s3Client.getObject(bucketName, uploaderDomain.getImage());
	     S3ObjectInputStream  stream  =  object.getObjectContent();
	     uploaderModel.setImage1(IOUtils.toByteArray(stream));
	    uploaderModel.setImage2(url+"/download?fileName="+uploaderModel.getImage());
	     
	     S3Object object2 =s3Client.getObject(bucketName, uploaderDomain.getVideo());
	     S3ObjectInputStream  stream2  =  object2.getObjectContent();
	     
	     uploaderModel.setVideo1(IOUtils.toByteArray(stream2));
	     uploaderModel.setVideo2(url+"/download?fileName="+uploaderDomain.getVideo());                
	    
			
		return uploaderModel;
	}

	@Override
	public List<UploaderModel> getProjectListByUploaderId(String uploaderId) {
		List<UploaderDomain> listUploaderDomain = uploaderRepository.findByUploaderId(uploaderId);
		List<UploaderModel> listUploaderModel = uploaderMapper.entityList(listUploaderDomain);
		return listUploaderModel;
	}


	public String getEmailId() {
		UploaderDomain uploaderDomain = new UploaderDomain();
		String mailId = uploaderDomain.getEmailId();
		return mailId;
	}


	@Override
	public List<UploaderModel> getApprovedProjects() {
		
		List<UploaderDomain> uploaderDomain = uploaderRepository.getApprovedProjects();
		List<UploaderModel> ListOfProjectModel = uploaderMapper.entityList(uploaderDomain);
		//return uploaderMapper.entityList(uploaderDomain);
		List<UploaderModel> listUploaderProjectModel = ListOfProjectModel.stream().map((project) -> {
			try {
//				S3Object object = s3Client.getObject(bucketName, article.getImage());
//				S3ObjectInputStream stream = object.getObjectContent();
//				article.setImage1(IOUtils.toByteArray(stream));
				project.setImage2(url+"/download?fileName="+project.getImage());
				project.setVideo2(url+"/download?fileName="+project.getVideo());
			} 
			catch (AmazonS3Exception e2) {
				project.setImage2(null);
			}
			return project;
		}).collect(Collectors.toList());

		return listUploaderProjectModel;
	}
	}
















