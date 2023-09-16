package com.snipe.apmt.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "gender")
@JsonInclude(Include.NON_DEFAULT)
public class GenderDomain implements Serializable{

	
	private static final long serialVersionUID = -8466107542640118910L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private int id;

	@Column(unique=true,name ="genderId")
	private String genderId;
	
	@Column(name ="genderName")
	private String genderName;
	

}
