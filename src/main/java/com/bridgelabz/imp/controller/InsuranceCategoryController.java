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

import com.bridgelabz.imp.dto.InsuranceDTO;
import com.bridgelabz.imp.dto.UserDTO;
import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.model.UserData;
import com.bridgelabz.imp.service.IInsuranceCategoryService;
import com.bridgelabz.imp.util.Response;

@RestController
@RequestMapping("/InsuarnceCategory")

public class InsuranceCategoryController 
{

	@Autowired
	IInsuranceCategoryService insuranceCategoryService;
	
	@PostMapping("/addnewInsuranceCategory")
	ResponseEntity<Response> addInsurance(@Valid @RequestBody InsuranceDTO insuarancedto)
	{
		Response response=insuranceCategoryService.addInsurance(insuarancedto);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@PutMapping("/updateInsuarance/{token}")
	ResponseEntity<Response> updateInsuarance(@PathVariable String token, @RequestBody InsuranceDTO insuarancedto)
	{
		Response response=insuranceCategoryService.updateInsuarance(token,insuarancedto);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	@GetMapping("/getallInsurance/{token}")
	ResponseEntity<List<?>> getallInsurance(@PathVariable String token)
	{
		List<InsuranceCategoryModel> response = insuranceCategoryService.getallInsurance(token);
		return new ResponseEntity<List<?>>(response,HttpStatus.OK);
	}
	@DeleteMapping("/deleteInsurance/{token}")
	ResponseEntity<Response> deleteInsuarance(@PathVariable String token)
	{
		Response response=insuranceCategoryService.deleteInsuarance(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
