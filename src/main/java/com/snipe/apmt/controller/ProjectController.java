package com.snipe.apmt.controller;

import static com.snipe.apmt.exception.HttpResponseUtils.prepareSuccessResponse;

import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.snipe.apmt.exception.GenericRes;
import com.snipe.apmt.model.ProjectModel;
 import com.snipe.apmt.service.ProjectService;

@RestController
@RequestMapping("/v1")
@SuppressWarnings("rawtypes")
public class ProjectController {
/*
	@Autowired
	ProjectService projectService;

	@PostMapping(value = "/save/Project")
	public ResponseEntity<GenericRes> saveProject(@RequestBody ProjectModel projectModel) throws Exception {
		return prepareSuccessResponse(projectService.saveProject(projectModel));
	}
	
	
	/*
	 * @GetMapping(value = "/projectList") public ResponseEntity<GenericRes>
	 * getProjectList() throws Exception { return
	 * prepareSuccessResponse(projectService.getProjectList()); }
	 */
	
	/*@GetMapping(value = "/projectList/{projectId}")
	public ResponseEntity<GenericRes> getProjectListById(@PathVariable("projectId") long projectId) throws Exception {
		return prepareSuccessResponse(projectService.getProjectListById(projectId));
	}
	
	@PutMapping(value = "/updateProject")
	public ResponseEntity<GenericRes> updateProject(@RequestBody ProjectModel projectModel) throws Exception {
		return prepareSuccessResponse(projectService.updateProject(projectModel));

	}
	
	@DeleteMapping(value = "/deleteProject/{projectId}")
	public ResponseEntity<GenericRes> deleteByProjectId(@PathVariable("projectId") long projectId) throws Exception {
		return prepareSuccessResponse(projectService.deleteByProjectId(projectId));
	}
	*/ 
	
}
