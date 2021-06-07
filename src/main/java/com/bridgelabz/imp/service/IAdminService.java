package com.bridgelabz.imp.service;

import java.util.List;

import com.bridgelabz.imp.dto.DateSearchDTO;
import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.model.UserData;
import com.bridgelabz.imp.util.InsuranceResponse;

public interface IAdminService 
{

	// To get all users
	List<UserData> getUsers(String token);
	
	// To get entire insurance data
	List<InsuranceCategoryModel> getallinsurancecategory(String token);
	
	// To get entire data of users and insurances
	List<InsuranceResponse> getallcreatedinsurance(String token);
	
	// To get user with specified health conditions
	List<UserData> getUsersWithHealthCondition(String token, String healthcondition);
	
	// To get user with specified vehicle data
	List<UserData> getUsersWithVehicleData(String token, String vehicledata);

	// To get Insurance data with specified category
	List<InsuranceCategoryModel> getInsuranceForCategory(String token, String category);
	
	// To get users between mentioned dates
	List<UserData> getalluserbetweenregistereddate(String token, DateSearchDTO dateSearchDTO);

	// To get insurance details between mentioned dates
	List<InsuranceCategoryModel> allInsuranceBetweenDates(String token, DateSearchDTO dateSearchDTO);
	
}
