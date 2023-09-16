package com.snipe.apmt.admin.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.snipe.apmt.admin.domain.StateDomain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryModel implements Serializable{
	
	private static final long serialVersionUID = 7691380477627826985L;
	
	private  long id;
	private int categoryId;
	private String categoryName;
	private LocalDateTime dateCreated;
	private LocalDateTime lastUpdated;
	

}
