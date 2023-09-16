package com.snipe.apmt.service;

import java.util.List;

import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.model.ProjectModel;

public interface ProjectService {

	public String saveProject(ProjectModel ProjectModel) throws Exception;

	public List<ProjectModel> getProjectList() throws Exception;

	public ProjectModel getProjectListById(long projectId) throws Exception;

	public ProjectDomain updateProject(ProjectModel projectModel) throws Exception;

	public String deleteByProjectId(long projectId) throws Exception;

}
