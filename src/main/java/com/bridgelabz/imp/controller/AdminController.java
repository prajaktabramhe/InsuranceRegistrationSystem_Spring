package com.bridgelabz.imp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.imp.dto.DateSearchDTO;
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
	@GetMapping("/getallinsuranceCategory/{token}")
	public ResponseEntity<List<?>> getallinsurancecategory(@PathVariable String token){
		log.debug("Get all Insurance data");
		List<InsuranceCategoryModel> response = adminService.getallinsurancecategory(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	/**
	 * To get entire data about users and insurance
	 * @param token :JWT token with id
	 * @return :ResponseEntity<List<?>>
	 */
	@GetMapping("/getallcreatedInsurance/{token}")
	public ResponseEntity<List<?>> getallcreatedinsurance(@PathVariable String token){
		log.debug("Get all InsuranceCreate: ");
		List<InsuranceResponse> response = adminService.getallcreatedinsurance(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get all users with specific health condition from admin controller
	 * @param token :JWT data with tokenid
	 * @param healthcondition
	 * @return : ResponseEntity<List<?>>
	 */
	@GetMapping("/getUserHealth/{token}")
	public ResponseEntity<List<?>> getUsersWithHealthCondition(@PathVariable String token,@RequestParam("health") String healthcondition){
		log.debug("Get all Users with health : " + healthcondition);
		List<UserData> response = adminService.getUsersWithHealthCondition(token,healthcondition);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get all users with specific vehicle data from admin controller
	 * @param token : tokenid
	 * @param vehicledata
	 * @return: ResponseEntity<List<?>>
	 */
	@GetMapping("/getUserVehicle/{token}")
	public ResponseEntity<List<?>> getUsersWithVehicleData(@PathVariable String token,@RequestParam("vehicle") String vehicledata){
		log.debug("Get all Users with vehicle data : " + vehicledata);
		List<UserData> response = adminService.getUsersWithVehicleData(token,vehicledata);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get all insurance data for particular category from admin controller
	 */
	@GetMapping("/getInsuranceCategory/{token}")
	public ResponseEntity<List<?>> getInsuranceForCategory(@PathVariable String token,@RequestParam("category") String category){
		log.debug("Get Insurance data for category: " + category);
		List<InsuranceCategoryModel> response = adminService.getInsuranceForCategory(token,category);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get all user Register between start and end dates mentioned
	 */
	@PostMapping("/getalluserbetweenregistereddate/{token}")
	public ResponseEntity<List<?>> getalluserbetweenregistereddate(@PathVariable String token,@RequestBody DateSearchDTO  dateSearchDTO){
		log.debug("Get all user between start date and end date");
		List<UserData> response = adminService.getalluserbetweenregistereddate(token,dateSearchDTO);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To get all insurance category between start and end dates mentioned
	 */
	@PostMapping("/getallInsuranceBetweenDates/{token}")
	public ResponseEntity<List<?>> allInsuranceBetweenDates(@PathVariable String token,@RequestBody DateSearchDTO dateSearchDTO){
		log.debug("Get user between start date and end date");
		List<InsuranceCategoryModel> response = adminService.allInsuranceBetweenDates(token,dateSearchDTO);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
