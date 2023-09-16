package com.snipe.apmt.verification.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.snipe.apmt.model.ProjectModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VerificationModel implements Serializable {

	private static final long serialVersionUID = 3300840423303133760L;

	private long id;
	private long projectId;
	private String verifierId;
	private int statusCode;
	private String statusDesc;
	private LocalDateTime projectVerifiedDate;
	private LocalDateTime projectApprovedDate;
	private LocalDateTime projectRejectedDate;

}
