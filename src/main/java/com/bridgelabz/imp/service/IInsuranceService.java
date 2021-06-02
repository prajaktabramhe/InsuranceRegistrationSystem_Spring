package com.bridgelabz.imp.service;


import java.util.List;

import com.bridgelabz.imp.dto.UserInsuranceDTO;
import com.bridgelabz.imp.model.UserInsuranceModel;
import com.bridgelabz.imp.util.Response;

public interface IInsuranceService {

	Response CreateInsurance(UserInsuranceDTO userInsuranceDTO);

	Response updateInsurance(String token, UserInsuranceDTO userInsuranceDTO);

	List<UserInsuranceModel> getallInsuarnce(String token);

	Response deleteInsuarance(String token);

	List<UserInsuranceModel> getallbyStatus(String token, String status);

	
	List<UserInsuranceModel> getAllbyMonthPeriod(String token);






}
