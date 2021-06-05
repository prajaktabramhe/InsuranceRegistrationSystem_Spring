package com.bridgelabz.imp.service;


import java.util.List;

import com.bridgelabz.imp.dto.InsuranceCreateDTO;
import com.bridgelabz.imp.dto.InsuranceGetStatusDTO;
import com.bridgelabz.imp.model.InsuranceCreateModel;
import com.bridgelabz.imp.util.InsuranceResponse;
import com.bridgelabz.imp.util.Response;

public interface IInsuranceService {

	Response CreateInsurance(InsuranceCreateDTO userInsuranceDTO);

	Response updateInsurance(String token, InsuranceCreateDTO userInsuranceDTO);

	List<InsuranceCreateModel> getallInsuarnce(Long userid);

	Response deleteInsuarance(String token);

	InsuranceGetStatusDTO getallbyStatus(String token, String status);
		
	List<InsuranceResponse> getData(String token);
	
	List<InsuranceCreateModel> getAllbyMonthPeriod(String token, int monthperiod);

	InsuranceGetStatusDTO getInsuranceByClaim(String token, boolean claim);

	Response updateInsuranceClaim(String token, InsuranceCreateDTO userInsuranceDTO, boolean claim);









}
