package com.bridgelabz.imp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.bridgelabz.imp.dto.UserInsuranceDTO;
import com.bridgelabz.imp.model.UserData;
import com.bridgelabz.imp.model.UserInsuranceModel;
import com.bridgelabz.imp.service.IInsuranceService;
import com.bridgelabz.imp.util.Response;


@RestController
@RequestMapping("/insuranceDetails")
public class UserInsuranceController 
{
	@Autowired
	IInsuranceService insuranceService;
	
	@PostMapping("/addUserInsurance")
	ResponseEntity<Response> CreateInsurance(@Valid @RequestBody UserInsuranceDTO userInsuranceDTO)
	{
		Response response=insuranceService.CreateInsurance(userInsuranceDTO);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateInsurance/{token}")
	ResponseEntity<Response> updateInsurance(@PathVariable String token, @RequestBody UserInsuranceDTO userInsuranceDTO)
	{
		Response response=insuranceService.updateInsurance(token,userInsuranceDTO);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getallInsuarnce/{token}")
	ResponseEntity<List<?>> getallInsuarnce(@PathVariable String token)
	{
		List<UserInsuranceModel> response = insuranceService.getallInsuarnce(token);
		return new ResponseEntity<List<?>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getallbyStatus/{token}")
	ResponseEntity<List<?>> getallbyStatus(@PathVariable String token,@RequestParam String status)
	{
		List<UserInsuranceModel> response = insuranceService.getallbyStatus(token,status);
		return new ResponseEntity<List<?>>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getAllbyMonthPeriod/{token}")
	ResponseEntity<List<?>> getAllbyMonthPeriod(@PathVariable String token)
	{
		List<UserInsuranceModel> response = insuranceService.getAllbyMonthPeriod(token);
		return new ResponseEntity<List<?>>(response,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteInsuarance/{token}")
	ResponseEntity<Response> deleteInsuarance(@PathVariable String token)
	{
		Response response=insuranceService.deleteInsuarance(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
