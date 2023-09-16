package com.snipe.apmt.uploader.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import org.apache.http.HttpEntity;
import org.hibernate.SessionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.HttpMethod;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.net.HttpHeaders;
import com.google.common.net.MediaType;
import com.snipe.apmt.domain.UserDomain;
import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.uploader.model.UploaderModel;
import com.snipe.apmt.uploader.service.UploaderService;
import com.snipe.apmt.utils.CommonUtils;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")

public class UploaderController {
	

	@Autowired
	UploaderService uploaderService;
	
	@Value("${apmt.apmtUrl}")
	private String uriPath;
	
	public UploaderModel getJson(String uploaderModel) {
		UploaderModel uploaderJson=new UploaderModel();
		try {
			ObjectMapper objectMapper=new ObjectMapper();
			objectMapper.registerModule(new JavaTimeModule());
			uploaderJson=objectMapper.readValue(uploaderModel, UploaderModel.class);
			
			
		}
		catch(Exception e) {
			System.out.println("Exception : "+e);
			
		}
		return uploaderJson;
	}
	
	
	@PostMapping(value = "/save/Project", consumes = { "application/json", "multipart/form-data" })
	public ResponseEntity<GenericRes> saveProject(@RequestPart("uploaderModel") String uploaderModel,@RequestParam("Image") MultipartFile file,@RequestParam("Video") MultipartFile video) throws Exception {
		
		UploaderModel  uploaderModel1= getJson(uploaderModel);
			
		return prepareSuccessResponse(uploaderService.saveProject(uploaderModel1,file,video));
	}
	
	
	@GetMapping(value = "/projectList")
	public ResponseEntity<GenericRes> getProjectList() throws Exception {
		return prepareSuccessResponse(uploaderService.getProjectList());
	}
	
	@GetMapping(value = "/projectList/{projectId}")
	public ResponseEntity<GenericRes> getUploadedProjectListById(@PathVariable("projectId") long projectId) throws Exception {
		return prepareSuccessResponse(uploaderService.getProjectByProjectId(projectId));
	}
	
	@GetMapping(value = "/projectListByUserId")
	public ResponseEntity<GenericRes> getUploadedProjectListByUserId() throws Exception {
		UserDomain userDomain=(UserDomain)CommonUtils.geSsession().getAttribute("userModel");
		if(userDomain==null) {
			throw new SessionException("Session expired");
		}
		return prepareSuccessResponse(uploaderService.getProjectListByUploaderId(userDomain.getId()));
	}
	
	@PutMapping(value = "/updateProject")
	public ResponseEntity<GenericRes> updateProject(@RequestBody UploaderModel uploaderModel) throws Exception {
		return prepareSuccessResponse(uploaderService.updateProject(uploaderModel));

	}
	
	@DeleteMapping(value = "/deleteProject/{projectId}")
	public ResponseEntity<GenericRes> deleteByProjectId(@PathVariable("projectId") long projectId) throws Exception {
		return prepareSuccessResponse(uploaderService.deleteByProjectId(projectId));
	}
	/*
	@PostMapping("/uploadFile")
	public ResponseEntity<GenericRes> fileUpload(@RequestParam("File") MultipartFile file)throws Exception {
		return prepareSuccessResponse(uploaderService.uploadFile(file));
	}
	*/
	
	@PostMapping("/upload")
	public ResponseEntity<GenericRes>  uploadFileInStorage(@RequestParam("File") MultipartFile file,String fileNameAndPath)throws Exception {
		return prepareSuccessResponse(uploaderService.UploadFileInStorageService(file,fileNameAndPath));
    }
	
	@GetMapping("/download")
	public ResponseEntity<?>  downloadFile(@RequestParam String fileName)throws Exception {
		byte[] data =uploaderService.downloadFile(fileName);
		//ByteArrayResource resource=new ByteArrayResource(data);
		
		return ResponseEntity.status(HttpStatus.OK)
				.contentLength(data.length)
				.header("Content-type","application/octet-stream")
				.header("Content-disposition", "attachment; filename=\""+fileName +"\"")
				.body(data);			
				
    }
	
	//@GetMapping("/projectID/{uploader_id}")
	//public ResponseEntity<GenericRes> getAllProjectsByUserId()throws Exception {
	//	return prepareSuccessResponse(uploaderService.getProjectListByUploaderId) ;
		
	//}
	@GetMapping(value = "/approvedProjects")
	public ResponseEntity<GenericRes> getApprovedProjects() throws Exception {
		return prepareSuccessResponse(uploaderService.getApprovedProjects());
	}
	
	
	}
	
