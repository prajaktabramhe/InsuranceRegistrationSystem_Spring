package com.bridgelabz.imp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bridgelabz.imp.exception.UserException;
import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.model.InsuranceCreateModel;
import com.bridgelabz.imp.model.UserData;
import com.bridgelabz.imp.repository.InsuranceCategoryRepository;
import com.bridgelabz.imp.repository.InsuranceCreateRepository;
import com.bridgelabz.imp.repository.UserRepository;
import com.bridgelabz.imp.util.InsuranceResponse;
import com.bridgelabz.imp.util.TokenUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminService implements IAdminService
{
	@Autowired
	UserRepository userrepository;

	@Autowired
	InsuranceCategoryRepository userInsuranceCategory;

	@Autowired
	InsuranceCreateRepository insuranceCreateRepository;

	@Autowired
	TokenUtil tokenutil;

	/**
	 * To get all user data for admin
	 * @param token: JWT with userid
	 * @return : List<UserData>
	 */
	@Override
	public List<UserData> getUsers(String token) 
	{
		Long userid = tokenutil.decodeToken(token);
		Optional<UserData>isContactPresent=userrepository.findById(userid);
		if(isContactPresent.isPresent()) {
			log.debug("Get all users");
			List<UserData> getUsers=userrepository.findAll();
			return getUsers;
		}
		else {
			log.error("Token not valid");
			throw new UserException(400,"Token not valid");
		}
	}

	/**
	 * To get insurance data for admin
	 * @param token: JWT with insuranceid
	 * @return : List<InsuranceCategoryModel>
	 */
	@Override
	public List<InsuranceCategoryModel> getAllInsuranceData(String token) 
	{
		Long userid = tokenutil.decodeToken(token);
		Optional<InsuranceCategoryModel> isEntryPresent = userInsuranceCategory.findById(userid);
		if(isEntryPresent.isPresent()) {
			log.debug("get");
			List<InsuranceCategoryModel> getInsuranceData=userInsuranceCategory.findAll();
			return getInsuranceData;
		}
		else {
			log.error("Token not valid");
			throw new UserException(400,"Token not valid");
		}
	}

	@Override
	public List<InsuranceResponse> getallcreatedinsurance(String token) 
	{
		long user_id = tokenutil.decodeToken(token);
		Optional<InsuranceCreateModel> isDataPresent = insuranceCreateRepository.findById(user_id);

		Long CreateId=0L;
		Long insuranceId=0L;
		UserData userdata =null;
		InsuranceCategoryModel insurancecategory =null;
		List<InsuranceResponse> insuranceCreateList = new ArrayList<>();

		if(isDataPresent.isPresent()) 
		{
			List<InsuranceCreateModel> createlistB =  insuranceCreateRepository.findAll();
			for(InsuranceCreateModel crreatemodel : createlistB) 
			{
				List<Long> userIds  = crreatemodel.getTokenid();
				List<Long> insuranceIds = crreatemodel.getInsuranceid();
				for(Long createId : userIds ) 
				{
					CreateId=createId;
				}
				for(Long iid : insuranceIds) 
				{
					insuranceId=iid;
				}
				Optional<UserData> isUserPresent  = userrepository.findById(CreateId);
				if(isUserPresent.isPresent()) 
				{
					userdata = isUserPresent.get();
				}
				else 
				{
					throw new UserException(404,"user Not found inside");
				}
				Optional<InsuranceCategoryModel> isCategoryPresent  = userInsuranceCategory.findById(insuranceId);

				if(isCategoryPresent.isPresent()) {
					insurancecategory = isCategoryPresent.get();
				}
				else 
				{
					throw new UserException(404,"Category Not found");
				}
				insuranceCreateList.add(new InsuranceResponse(userdata, insurancecategory, user_id));
			} // Outside FOR					
			return insuranceCreateList;
		}
		else 
		{
			throw new UserException(404,"user Not found outside");
		}
	}
}
