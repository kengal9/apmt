package com.snipe.apmt.model;

import java.io.Serializable;

import com.snipe.apmt.admin.model.CountryModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectModel implements Serializable{

	private static final long serialVersionUID = -5880660832445645759L;
	
	
	private String projectId;
	private String title;
	private String description;
	private String image;
	private String video;
	private String synopsis;
	private String price;
	private String editPrice;
	private String modules;
	private String technologyUsed;
	
	
	
	
}
