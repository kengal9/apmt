package com.snipe.apmt.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.snipe.apmt.dao.ProjectRepository;
import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.mapper.ProjectMapper;
import com.snipe.apmt.model.ProjectModel;
import com.snipe.apmt.utils.IDGeneration;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	

@Autowired
ProjectRepository projectRepository; 

@Autowired
ProjectMapper  projectMapper;

@Autowired
IDGeneration idGeneration;




	@Override
	public String saveProject(ProjectModel projectModel) throws Exception {
		ProjectDomain projectDomain = new ProjectDomain();
		projectModel.setProjectId(idGeneration.generateRandomId());
		BeanUtils.copyProperties(projectModel, projectDomain);
		 projectRepository.save(projectDomain);
		return "Project Saved";
	}
	@Override
	public List<ProjectModel> getProjectList() throws Exception {
		List<ProjectDomain> projectDomain = projectRepository.findAll();
		return projectMapper.entityList(projectDomain);
		
	}


	@Override
	public ProjectModel getProjectListById(long projectId) throws Exception {
		ProjectModel projectModel = new ProjectModel();
		ProjectDomain projectDomain = projectRepository.findByProjectId(projectId);
		BeanUtils.copyProperties(projectDomain, projectModel);
		return projectModel;
	}


	@Override
	public ProjectDomain updateProject(ProjectModel projectModel) throws Exception {
		ProjectDomain projectDomain = new ProjectDomain();
		BeanUtils.copyProperties(projectModel, projectDomain);
		return projectRepository.save(projectDomain) ;
	}


	@Override
	public String deleteByProjectId(long projectId) throws Exception {
		projectRepository.deleteByProjectId(projectId);
		return "Project Deleted";
	}
	
	
	

}
