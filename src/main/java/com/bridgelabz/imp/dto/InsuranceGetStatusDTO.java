package com.bridgelabz.imp.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.model.UserData;

import lombok.Data;

@Data
public class InsuranceGetStatusDTO 
{
	public Long id;

	public Optional<UserData> tokenid;
	
	public Optional<InsuranceCategoryModel> insuranceid;
	
	public int monthperiod;
	public String status;
	public LocalDateTime registereddate;
	public LocalDateTime updateddate;
	public boolean claim;

	public InsuranceGetStatusDTO() {}
}
