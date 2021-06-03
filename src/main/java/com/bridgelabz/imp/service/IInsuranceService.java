package com.bridgelabz.imp.service;


import java.util.List;

import com.bridgelabz.imp.dto.InsuranceCreateDTO;
import com.bridgelabz.imp.model.InsuranceCreateModel;
import com.bridgelabz.imp.util.InsuranceResponse;
import com.bridgelabz.imp.util.Response;

public interface IInsuranceService {

	Response CreateInsurance(InsuranceCreateDTO userInsuranceDTO);

	Response updateInsurance(String token, InsuranceCreateDTO userInsuranceDTO);

//	List<InsuranceCreateModel> getallInsuarnce(String token);

	Response deleteInsuarance(String token);

	List<InsuranceCreateModel> getallbyStatus(String token, String status);

	
	List<InsuranceCreateModel> getAllbyMonthPeriod(String token);

	
	List<InsuranceResponse> getData(String token);






}
