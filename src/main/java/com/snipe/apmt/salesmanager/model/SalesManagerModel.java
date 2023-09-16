package com.snipe.apmt.salesmanager.model;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("serial")

public class SalesManagerModel implements Serializable {
	
	private long id;
	private long projectId;
	private String NewProject;
	private long price;
	private long editPrice;
	private String modules;
	private String technologyUsed;
	
	



}
