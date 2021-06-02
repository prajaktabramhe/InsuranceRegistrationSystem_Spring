package com.bridgelabz.imp.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

import lombok.Data;

@Entity 
@Table(name="insurance_create")
@Data
public class InsuranceCreateModel 
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	public int tokenId;
	public int insuranceId;
	public int monthPeriod;
	public String status;
	public LocalDateTime registereddate=LocalDateTime.now();
	public LocalDateTime updateddate;
	
	public InsuranceCreateModel() {}

	public InsuranceCreateModel(Long id, int tokenId, int insuranceId, int monthPeriod, String status,
			LocalDateTime registereddate, LocalDateTime updateddate) {
		super();
		this.id = id;
		this.tokenId = tokenId;
		this.insuranceId = insuranceId;
		this.monthPeriod = monthPeriod;
		this.status = status;
		this.registereddate = registereddate;
		this.updateddate = updateddate;
	}

	


	
	
}
