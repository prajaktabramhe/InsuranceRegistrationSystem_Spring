package com.bridgelabz.imp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsuranceCreateDTO {
	
	@NotNull(message = "tokenId cannot be null")
	private int tokenId;
		
	@NotNull(message = "InsuranceId cannot be null")
	private int insuranceId;
	
	@NotNull(message = "MonthPeriod cannot be null")	
	private int monthPeriod;
	
	@NotEmpty(message = "Status cannot be null")
	private String status;
	
}
