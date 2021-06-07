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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.bridgelabz.imp.dto.InsuranceCreateDTO;
import com.bridgelabz.imp.dto.InsuranceGetStatusDTO;
import com.bridgelabz.imp.model.InsuranceCreateModel;
import com.bridgelabz.imp.service.IInsuranceService;
import com.bridgelabz.imp.util.InsuranceResponse;
import com.bridgelabz.imp.util.Response;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/insuraceCreate")
@Slf4j
public class InsuranceCreateController 
{
	@Autowired
	IInsuranceService insuranceService;

	/** To add new insurance
	 * To create Insurance create with tokenid and insuranceid
	 * @param userInsuranceDTO :To get data from InsuranceCreateDTO
	 * @return :ResponseEntity<Response>
	 */
	@PostMapping("/addUserInsurance")
	ResponseEntity<Response> CreateInsurance(@Valid @RequestBody InsuranceCreateDTO userInsuranceDTO)
	{
		Response response=insuranceService.CreateInsurance(userInsuranceDTO);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	/**
	 * To update insurance data
	 * @param userInsuranceDTO : To get data from InsuranceCreateDTO
	 * @return : ResponseEntity<Response>
	 */
	@PutMapping("/updateInsurance/{token}")
	ResponseEntity<Response> updateInsurance(@PathVariable String token, @RequestBody InsuranceCreateDTO userInsuranceDTO)
	{
		Response response=insuranceService.updateInsurance(token,userInsuranceDTO);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	/**
	 * To get entire data of users and insurance
	 *  @param token : JWT token with id
	 *  return : ResponseEntity<List<?>>
	 */
	@GetMapping("/get/{token}")
	ResponseEntity<List<?>> getAllData(@PathVariable String token)
	{
		List<InsuranceResponse> response = insuranceService.getData(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	/**
	 * To get insurance data by status
	 * @param token : JWT token with id
	 * @param status
	 * @return : ResponseEntity<>
	 */
	@GetMapping("/getallbyStatus/{token}")
	ResponseEntity getallbyStatus(@PathVariable String token,@RequestParam String status)
	{
		InsuranceGetStatusDTO response = insuranceService.getallbyStatus(token,status);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	/**
	 * To get insurance data by month period
	 * @param token : JWT token with id
	 * @param monthperiod :Month period of insurance
	 * @return :ResponseEntity<List<?>>
	 */
	@GetMapping("/getAllbyMonthPeriod/{token}")
	ResponseEntity<List<?>> getAllbyMonthPeriod(@PathVariable String token, @RequestHeader int monthperiod)
	{
		List<InsuranceCreateModel> response = insuranceService.getAllbyMonthPeriod(token, monthperiod);
		return new ResponseEntity<List<?>>(response,HttpStatus.OK);
	}

	/**
	 * To delete insurance data
	 * @param token : JWT token with id
	 * @return : ResponseEntity<Response>
	 */
	@DeleteMapping("/deleteInsuarance/{token}")
	ResponseEntity<Response> deleteInsuarance(@PathVariable String token)
	{
		Response response=insuranceService.deleteInsuarance(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	/**
	 * To getallInsuranceforUser data
	 * @return : ResponseEntity<>
	 */
	@GetMapping("/getallInsuranceforUser")
	ResponseEntity getAllByUserId(@RequestHeader Long userid) {
		return new ResponseEntity(insuranceService.getallInsuarnce(userid), HttpStatus.OK);

	}
	
	/**
	 * To get insurance data by claim
	 * @param claim : True/False
	 * @return : ResponseEntity<>
	 */

	@GetMapping("/getInsuranceByClaim/{token}")
	ResponseEntity getInsuranceByClaim(@PathVariable String token,@RequestParam Boolean claim)
	{
		log.debug("Get InsuranceByClaim By Claim");
		InsuranceGetStatusDTO response = insuranceService.getInsuranceByClaim(token,claim);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	/**
	 * To update new insurance claim 
	 * @param token
	 * @param userInsuranceDTO : To get data from InsuranceCreateDTO
	 * @param claim : True/False
	 * @return : ResponseEntity<Response>
	 */
	
	@PutMapping("/updateClaim/{token}")
	ResponseEntity<Response> updateInsuranceClaim(@PathVariable String token, @RequestParam boolean claim)
	{
//		log.debug("Update Insurance Claim: " + userInsuranceDTO);
		Response response=insuranceService.updateInsuranceClaim(token,claim);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}


}
