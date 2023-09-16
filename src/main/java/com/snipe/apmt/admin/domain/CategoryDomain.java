package com.snipe.apmt.admin.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryDomain implements Serializable {

	private static final long serialVersionUID = -4101345190838958733L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	@Column(name = "categoryId", unique = true, nullable = false)
	private int categoryId;

	@Column(name = "categoryName", unique = true, nullable = false)
	private String categoryName;

	@Column(name = "dateCreated")
	@CreationTimestamp
	private LocalDateTime dateCreated;
	@Column(name = "modificationDate")
	@UpdateTimestamp
	private LocalDateTime modificationDate;

}
