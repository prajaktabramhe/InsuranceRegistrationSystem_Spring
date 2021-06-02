package com.bridgelabz.imp.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
@Entity 
@Table(name="insurancecategory")
@Data
public class InsuranceCategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	public String insurancename;
	public String insuarncestatus;
	public String insurancescheme;
	public LocalDateTime registereddate=LocalDateTime.now();
	public LocalDateTime updateddate;
	public int insurancecode;
	
	public InsuranceCategoryModel() {}

	public InsuranceCategoryModel(Long id, String insurancename, String insuarncestatus, String insurancescheme,
			LocalDateTime registereddate, LocalDateTime updateddate, int insurancecode) {
		super();
		this.id = id;
		this.insurancename = insurancename;
		this.insuarncestatus = insuarncestatus;
		this.insurancescheme = insurancescheme;
		this.registereddate = registereddate;
		this.updateddate = updateddate;
		this.insurancecode = insurancecode;
	}
	
	
}