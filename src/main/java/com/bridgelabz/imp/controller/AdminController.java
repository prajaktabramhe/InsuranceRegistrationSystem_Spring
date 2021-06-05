package com.bridgelabz.imp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.model.UserData;
import com.bridgelabz.imp.service.IAdminService;
import com.bridgelabz.imp.util.InsuranceResponse;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController 
{
	@Autowired
	IAdminService adminService;

	/**
	 * To get all users data from admin controller
	 * * @return : ResponseEntity<List<?>>
	 */
	@GetMapping("/getUsers/{token}")
	public ResponseEntity<List<?>> getUsers(@PathVariable String token){
		log.debug("Get all Users");
		List<UserData> response = adminService.getUsers(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	/**
	 * To get all insurance data from admin controller
	 * @param token : JWT data with insuranceid
	 * @return : ResponseEntity<List<?>>
	 */
	@GetMapping("/getInsuranceData/{token}")
	public ResponseEntity<List<?>> getInsuranceData(@PathVariable String token){
		log.debug("Get all Insurance data");
		List<InsuranceCategoryModel> response = adminService.getAllInsuranceData(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	/**
	 * To get entire data about users and insurance
	 * @param token :JWT token with id
	 * @return :ResponseEntity<List<?>>
	 */
	@GetMapping("/getallcreatedinsurance/{token}")
	public ResponseEntity<List<?>> getallcreatedinsurance(@PathVariable String token){
		log.debug("Get all InsuranceCreate: ");
		List<InsuranceResponse> response = adminService.getallcreatedinsurance(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
