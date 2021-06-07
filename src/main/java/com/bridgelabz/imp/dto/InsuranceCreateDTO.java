package com.bridgelabz.imp.dto;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.boot.context.properties.bind.DefaultValue;

import lombok.Data;

@Data
public class InsuranceCreateDTO 
{
	
	@NotNull(message = "tokenId cannot be null")
	private List<Long> tokenid;
	
	@NotNull(message = "InsuranceId cannot be null")
	private List<Long> insuranceid;
	
	@NotNull(message = "MonthPeriod cannot be null")	
	private int monthperiod;
	
	@NotEmpty(message = "Status cannot be null")
	private String status;
		
	@NotNull(message = "Claim cannot be null")
	private Boolean claim;
}
