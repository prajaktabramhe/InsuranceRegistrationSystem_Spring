package com.bridgelabz.imp.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class InsuranceDTO 
{
	
	@NotEmpty(message = "insurancename cannot be null")
	private String insurancename;
		
	@NotEmpty(message = "insuarncestatus cannot be null")
	private String insuarncestatus;
	
	@NotEmpty(message = "insurancescheme cannot be null")	
	private String insurancescheme;
	
	@NotNull(message = "insurancecode cannot be null")
	private int insurancecode;
	
}
