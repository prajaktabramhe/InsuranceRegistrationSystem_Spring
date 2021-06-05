package com.bridgelabz.imp.util;

import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.model.UserData;

import lombok.Data;

@Data
public class InsuranceResponse {

	UserData userData;
	InsuranceCategoryModel insuranceCategoryModel;
	long id;

	public InsuranceResponse(UserData userData, InsuranceCategoryModel insuranceCategoryModel, long id) {
		this.userData = userData;
		this.insuranceCategoryModel = insuranceCategoryModel;
		this.id = id;
	}


}
