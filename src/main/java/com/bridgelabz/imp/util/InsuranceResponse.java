package com.bridgelabz.imp.util;

import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.model.InsuranceCreateModel;
import com.bridgelabz.imp.model.UserData;

import lombok.Data;

@Data
public class InsuranceResponse {

	UserData userData;
	InsuranceCategoryModel insuranceCategoryModel;
	InsuranceCreateModel insuranceCreate;

	public InsuranceResponse(UserData userData, InsuranceCategoryModel insuranceCategoryModel, InsuranceCreateModel insuranceCreate) {
		this.userData = userData;
		this.insuranceCategoryModel = insuranceCategoryModel;
		this.insuranceCreate = insuranceCreate;
	}


}
