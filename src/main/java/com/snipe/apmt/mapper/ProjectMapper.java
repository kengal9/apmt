package com.snipe.apmt.mapper;
import org.springframework.stereotype.Component;

import com.snipe.apmt.domain.ProjectDomain;
import com.snipe.apmt.model.ProjectModel;


	@Component
	public class ProjectMapper extends AbstractModelMapper<ProjectModel, ProjectDomain>{

		@Override
		public Class<ProjectModel> entityType() {
			
			return ProjectModel.class;
		}

		@Override
		public Class<ProjectDomain> modelType() {
			
			return ProjectDomain.class;
		}

}
