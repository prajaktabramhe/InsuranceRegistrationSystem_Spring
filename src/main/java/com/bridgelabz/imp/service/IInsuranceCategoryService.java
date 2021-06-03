package com.bridgelabz.imp.service;


import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.imp.dto.InsuranceDTO;
import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.util.Response;

public interface IInsuranceCategoryService {

	Response addInsurance(@Valid InsuranceDTO insuarancedto);

	Response updateInsuarance(String token, InsuranceDTO insuarancedto);
	
	Response deleteInsuarance(String token);
	
	List<InsuranceCategoryModel> getallInsurance(String token);



	

	



	

}
