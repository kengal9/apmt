package com.snipe.apmt.salesmanager.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.snipe.apmt.uploader.domain.UploaderDomain;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class PriceModel implements Serializable{
	
	private long id;
	//private UploaderDomain uploaderDomain;
	private long projectId;
	private long price;
	private int Discount;
	private long editPrice;
	private LocalDateTime creationDate;
	private LocalDateTime modificationDate;
	

}
