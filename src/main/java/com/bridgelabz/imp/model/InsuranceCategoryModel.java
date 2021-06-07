package com.bridgelabz.imp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity 
@Table(name="insurance_category")
@Data
public class InsuranceCategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	public Long categoryid;
	public String insurancename;
	public String insuarncestatus;
	public String insurancescheme;
	public LocalDate registereddate=LocalDate.now();
	public LocalDate updateddate;
	public int insurancecode;

	public InsuranceCategoryModel() {}

	public InsuranceCategoryModel(Long categoryid, String insurancename, String insuarncestatus, String insurancescheme,
			LocalDate registereddate, LocalDate updateddate, int insurancecode) {
		super();
		this.categoryid = categoryid;
		this.insurancename = insurancename;
		this.insuarncestatus = insuarncestatus;
		this.insurancescheme = insurancescheme;
		this.registereddate = registereddate;
		this.updateddate = updateddate;
		this.insurancecode = insurancecode;
	}


}
