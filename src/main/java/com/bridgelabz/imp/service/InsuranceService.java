package com.bridgelabz.imp.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.imp.dto.InsuranceCreateDTO;
import com.bridgelabz.imp.exception.UserException;
import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.model.InsuranceCreateModel;
import com.bridgelabz.imp.model.UserData;
import com.bridgelabz.imp.repository.InsuranceCategoryRepository;
import com.bridgelabz.imp.repository.InsuranceCreateRepository;
import com.bridgelabz.imp.repository.UserRepository;
import com.bridgelabz.imp.util.InsuranceResponse;
import com.bridgelabz.imp.util.Response;
import com.bridgelabz.imp.util.TokenUtil;

@Service
public class InsuranceService  implements IInsuranceService 
{

	
	@Autowired
	InsuranceCreateRepository insuranceCreateRepository;
	
	@Autowired
	InsuranceCategoryRepository userInsuranceCategory;
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	ModelMapper modelmapper;
	@Autowired
	TokenUtil tokenutil;
	
	@Override
	public Response CreateInsurance(InsuranceCreateDTO userInsuranceDTO) 
	{
			InsuranceCreateModel user=modelmapper.map(userInsuranceDTO, InsuranceCreateModel.class);
			insuranceCreateRepository.save(user);
			System.out.println(user);
			String token=tokenutil.createToken(user.getId());
			return new Response(200, "Insurance Succefully Added", token);
	}

	@Override
	public Response updateInsurance(String token, InsuranceCreateDTO userInsuranceDTO) 
	{
		Long id = tokenutil.decodeToken(token);
		Optional<InsuranceCreateModel>isInsurancePresent=insuranceCreateRepository.findById(id);
		if(isInsurancePresent.isPresent()) 
		{
			isInsurancePresent.get().setInsuranceid(userInsuranceDTO.getInsuranceid());
			isInsurancePresent.get().setUpdateddate(LocalDateTime.now());
			isInsurancePresent.get().setMonthperiod(userInsuranceDTO.getMonthPeriod());
			isInsurancePresent.get().setStatus(userInsuranceDTO.getStatus());
			isInsurancePresent.get().setTokenid(userInsuranceDTO.getTokenid());
			
			insuranceCreateRepository.save(isInsurancePresent.get());
			return new Response(200, "Insurance Succefully Updated", null);
		}else 
		{
			throw new UserException(400, "Insurance is not saved!");
	    }
	}

//	@Override
//	public List<InsuranceCreateModel> getallInsuarnce(String token) {
//		Long id = tokenutil.decodeToken(token);
//		Optional<InsuranceCreateModel>isInsurancePresent=insuranceCreateRepository.findById(id);
//		if(isInsurancePresent.isPresent()) 
//		{
//			List<InsuranceCreateModel> getallInsuarnce = insuranceCreateRepository.findAll();
//			return getallInsuarnce;
//		}else 
//		{
//			throw new UserException(400, "Token is not valid!");
//		}
//	}

	@Override
	public Response deleteInsuarance(String token) 
	{
		long user_id = tokenutil.decodeToken(token);
		Optional<InsuranceCreateModel> isThere = insuranceCreateRepository.findById(user_id);
		if (isThere.isPresent())
		{
			insuranceCreateRepository.delete(isThere.get());
			return new Response(400,"Deleted Successfully" , HttpStatus.ACCEPTED);
		}else 
		{
			throw new UserException(500 , "Insurance could not be found to be deleted");
		}
	}


	@Override
	public List<InsuranceCreateModel> getallbyStatus(String token, String status) 
	{
		Long user_id = tokenutil.decodeToken(token);
		Optional<InsuranceCreateModel>isInsurancePresent=insuranceCreateRepository.findById(user_id);
		if(isInsurancePresent.isPresent()) 
		{
			List<InsuranceCreateModel> getallbyStatus = insuranceCreateRepository.findByStatusStartsWith(status);
			return getallbyStatus;
		}else 
		{
			throw new UserException(400, "Token is not valid!");
		}
	}

	@Override
	public List<InsuranceCreateModel> getAllbyMonthPeriod(String token) 
	{
		Long user_id = tokenutil.decodeToken(token);
		Optional<InsuranceCreateModel>isInsurancePresent=insuranceCreateRepository.findById(user_id);
		if(isInsurancePresent.isPresent()) 
		{
			List<InsuranceCreateModel> getAllbyMonthPeriod = insuranceCreateRepository.findAll();
			return getAllbyMonthPeriod;
		}else 
		{
			throw new UserException(400, "Token is not valid!");
		}
	}

	@Override
	public List<InsuranceResponse> getData(String token) {
		long user_id = tokenutil.decodeToken(token);
		System.out.println(user_id + "check id");
		Optional<InsuranceCreateModel> b = insuranceCreateRepository.findById(user_id);
		
		Long BId=0L;
		Long insuranceId=0L;
		UserData a =null;
		InsuranceCategoryModel c =null;
		List<InsuranceResponse> insuranceCreateList = new ArrayList<>();
		
		
		//Start
		/*
		List<InsuranceCreateModel> insuranceCreateModelList =  insuranceCreateRepository.findAll();
		if(!insuranceCreateModelList.isEmpty()) 
		{
			Optional<UserData> userModel = null;
			Optional<InsuranceCategoryModel> insuranceCategoryModel = null;
			for(InsuranceCreateModel b1 : insuranceCreateModelList) {
				
				for(Long insurancecreateModelObject : b1 .getTokenid()) {
					 userModel = userrepository.findById(insurancecreateModelObject);
				}
				for(Long insurancecreateModelObject : b1 .getInsuranceid()) {
					 insuranceCategoryModel = userInsuranceCategory.findById(insurancecreateModelObject);
				}
				a = userModel.get();
				c = insuranceCategoryModel.get(); 
				insuranceCreateList.add(new InsuranceResponse(a, c, id));
			}
		}
		else 
		{
			throw new UserException(404,"No Data found in Insurance Create Table");
		}*/
		//end
		if(b.isPresent()) 
		{
			List<InsuranceCreateModel> blist =  insuranceCreateRepository.findAll();
			for(InsuranceCreateModel b1 : blist) {
				System.out.println(b1);
				System.out.println("Line 179");
				System.out.println();
				List<Long> b1Id = b1.getTokenid();
				List<Long> insuranceIds = b1.getInsuranceid();
				for(Long b1IdX : b1Id) {
					BId=b1IdX;
					System.out.println(BId);
					System.out.println("Line 186");
				}
				for(Long iid : insuranceIds) {
					insuranceId=iid;
					System.out.println(insuranceId);
					System.out.println("Line 191");
				}
				
				Optional<UserData> a1 = userrepository.findById(BId);
				System.out.println(a1);
				if(a1.isPresent()) {
					a = a1.get();
				}
				else {
					
					throw new UserException(404,"user Not found inside");
				}
				Optional<InsuranceCategoryModel> c1 = userInsuranceCategory.findById(insuranceId);
				System.out.println(c1);
				if(c1.isPresent()) {
					c = c1.get();
				}
				else {
					
					throw new UserException(404,"Category Not found");
				}
				
				
				insuranceCreateList.add(new InsuranceResponse(a, c, user_id));
			} // Outside FOR					
			return insuranceCreateList;
			
		}
		else {
			
			throw new UserException(404,"user Not found outside");
		}
		
	}

	}

	


