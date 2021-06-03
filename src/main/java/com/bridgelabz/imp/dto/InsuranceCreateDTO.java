package com.bridgelabz.imp.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsuranceCreateDTO {
	
	@NotNull(message = "tokenId cannot be null")
	private List<Long> tokenid;
	
	@NotNull(message = "InsuranceId cannot be null")
	private List<Long> insuranceid;
	
	@NotNull(message = "MonthPeriod cannot be null")	
	private int monthPeriod;
	
	@NotEmpty(message = "Status cannot be null")
	private String status;
	
	
}
