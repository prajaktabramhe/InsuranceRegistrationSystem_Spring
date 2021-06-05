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
import com.bridgelabz.imp.dto.InsuranceGetStatusDTO;
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

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
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

	/**
	 * To add insurance data
	 */
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
			isInsurancePresent.get().setMonthperiod(userInsuranceDTO.getMonthperiod());
			isInsurancePresent.get().setStatus(userInsuranceDTO.getStatus());
			isInsurancePresent.get().setTokenid(userInsuranceDTO.getTokenid());

			insuranceCreateRepository.save(isInsurancePresent.get());
			return new Response(200, "Insurance Succefully Updated", null);
		}else 
		{
			throw new UserException(400, "Insurance is not saved!");
		}
	}

	/**
	 * To delete insurance data
	 */
	@Override
	public Response deleteInsuarance(String token) 
	{
		long user_id = tokenutil.decodeToken(token);
		Optional<InsuranceCreateModel> isThere = insuranceCreateRepository.findById(user_id);
		if (isThere.isPresent())
		{
			insuranceCreateRepository.deleteById(isThere.get().id);
			return new Response(400,"Deleted Successfully" , HttpStatus.ACCEPTED);
		}else 
		{
			throw new UserException(500 , "Insurance could not be found to be deleted");
		}
	}

	
	/**
	 * To get insurance data by status
	 */
	@Override
	public InsuranceGetStatusDTO getallbyStatus(String token, String status) 
	{
		InsuranceGetStatusDTO response = new InsuranceGetStatusDTO();
		Long user_id = tokenutil.decodeToken(token);
		Optional<InsuranceCreateModel>isInsurancePresent=insuranceCreateRepository.findById(user_id);
		if(isInsurancePresent.isPresent())
		{
			List<InsuranceCreateModel> getallbyStatus = insuranceCreateRepository.findByStatusStartsWith(status);
			response.setId(getallbyStatus.get(0).getId());
			response.setTokenid(userrepository.findById(getallbyStatus.get(0).getTokenid().get(0)));
			response.setInsuranceid(userInsuranceCategory.findById(getallbyStatus.get(0).getInsuranceid().get(0)));
			response.setMonthperiod(getallbyStatus.get(0).getMonthperiod());
			response.setRegistereddate(getallbyStatus.get(0).getRegistereddate());
			System.out.println(response);
			return response;
		}else 
		{
			throw new UserException(400, "Token is not valid!");
		}
	}

	/**
	 * To get insurance data by month
	 */
	@Override
	public List<InsuranceCreateModel> getAllbyMonthPeriod(String token, int monthperiod) 
	{
		Long user_id = tokenutil.decodeToken(token);
		Optional<InsuranceCreateModel>isInsurancePresent=insuranceCreateRepository.findById(user_id);
		if(isInsurancePresent.isPresent()) 
		{
			List<InsuranceCreateModel> getAllbyMonthPeriod = insuranceCreateRepository.findByMonthperiod(monthperiod);
			return getAllbyMonthPeriod;
		}else 
		{
			throw new UserException(400, "Token is not valid!");
		}
	}


	/**
	 * To get insurance data and user data 
	 */
	@Override
	public List<InsuranceResponse> getData(String token) 
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


	/**
	 * To get all insurance data 
	 */
	@Override
	public List<InsuranceCreateModel> getallInsuarnce(Long userid)
	{
		return insuranceCreateRepository.getByUserid(userid);
	}


	/**
	 * To get insurance data by claim
	 */
	@Override
	public InsuranceGetStatusDTO getInsuranceByClaim(String token, boolean claim) 
	{
		InsuranceGetStatusDTO response = new InsuranceGetStatusDTO();
		Long user_id = tokenutil.decodeToken(token);
		Optional<InsuranceCreateModel>isInsurancePresent=insuranceCreateRepository.findById(user_id);
		if(isInsurancePresent.isPresent())
		{
			List<InsuranceCreateModel> getallbyClaim = insuranceCreateRepository.findByClaim(claim);
			response.setId(getallbyClaim.get(0).getId());
			response.setTokenid(userrepository.findById(getallbyClaim.get(0).getTokenid().get(0)));
			response.setInsuranceid(userInsuranceCategory.findById(getallbyClaim.get(0).getInsuranceid().get(0)));
			response.setMonthperiod(getallbyClaim.get(0).getMonthperiod());
			response.setRegistereddate(getallbyClaim.get(0).getRegistereddate());
			System.out.println(response);
			return response;
		}else 
		{
			log.error("Token not found");
			throw new UserException(400, "Token is not valid!");
		}
	}

	/**
	 * To update insurance data by claim/**
	 */
	@Override
	public Response updateInsuranceClaim(String token, InsuranceCreateDTO userInsuranceDTO, boolean claim) 
	{
		Long id = tokenutil.decodeToken(token);
		Optional<InsuranceCreateModel>isDataPresent =insuranceCreateRepository.findById(id);
		if(isDataPresent .isPresent()) 
		{
			isDataPresent .get().setInsuranceid(userInsuranceDTO.getInsuranceid());
			isDataPresent .get().setUpdateddate(LocalDateTime.now());
			isDataPresent .get().setMonthperiod(userInsuranceDTO.getMonthperiod());
			isDataPresent .get().setStatus(userInsuranceDTO.getStatus());
			isDataPresent .get().setTokenid(userInsuranceDTO.getTokenid());
			isDataPresent .get().setClaim(claim);
			insuranceCreateRepository.save(isDataPresent .get());
			return new Response(200, "Data Succefully Updated", null);
		}else 
		{
			log.error("User not found.");
			throw new UserException(400, "user Not found");
		}
	}

}	





