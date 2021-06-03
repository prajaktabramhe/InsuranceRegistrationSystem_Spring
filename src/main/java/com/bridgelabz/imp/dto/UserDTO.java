package com.bridgelabz.imp.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Data
public class UserDTO 
{
	@Pattern(regexp =  "^[A-Z]{1}[a-zA-Z\\s]{2,}$", message = "Invalid name")
	private String fullname;
		
	@NotEmpty(message = "Mobile number cannot be null")
	private String mobilenumber;
	

	@NotBlank(message = "Permanent address cannot be blank")
	public String permanentAddress;
	
	@NotBlank(message = "Temporary address cannot be blank")
	public String temporaryAddress;
	
	@NotEmpty(message = "Vehicledata cannot be null")	
	private String vehicledata;
	
	@NotNull(message = "Age cannot be null")
	private int age;
	
	@NotEmpty(message = "Occupation cannot be null")	
	private String occupation;
	
	@NotEmpty(message = "HealthCondition cannot be null")	
	private String healthcondition;
	
	@NotEmpty(message = "familyBackground cannot be null")	
	private String familybackground;
	
	@NotEmpty(message = "kyc cannot be null")	
	private String kyc;
	
	
}

