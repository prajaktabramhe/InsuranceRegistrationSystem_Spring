package com.bridgelabz.imp.service;

import java.util.List;

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

	List<InsuranceCategoryModel> getInsuranceForCategory(String token, String category);
	
}
