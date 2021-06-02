package com.bridgelabz.imp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.imp.dto.UserInsuranceDTO;
import com.bridgelabz.imp.exception.UserException;
import com.bridgelabz.imp.model.UserData;
import com.bridgelabz.imp.model.UserInsuranceModel;
import com.bridgelabz.imp.repository.UserInsuranceRepository;
import com.bridgelabz.imp.util.Response;
import com.bridgelabz.imp.util.TokenUtil;

@Service
public class InsuranceService  implements IInsuranceService 
{

	
	@Autowired
	UserInsuranceRepository userInsuranceRepository;
	
	@Autowired
	ModelMapper modelmapper;
	@Autowired
	TokenUtil tokenutil;
	
	@Override
	public Response CreateInsurance(UserInsuranceDTO userInsuranceDTO) 
	{
		Optional<UserInsuranceModel> isPresent=userInsuranceRepository.findByinsuranceId(userInsuranceDTO.getInsuranceId());
   		if(isPresent.isPresent()) 
		{
			throw new UserException(400, "Insurance Already Added");
		}else 
		{
			UserInsuranceModel user=modelmapper.map(userInsuranceDTO, UserInsuranceModel.class);
			userInsuranceRepository.save(user);
			String token=tokenutil.createToken(user.getId());
			return new Response(200, "Insurance Succefully Added", token);
		}
	}

	@Override
	public Response updateInsurance(String token, UserInsuranceDTO userInsuranceDTO) 
	{
		Long id = tokenutil.decodeToken(token);
		Optional<UserInsuranceModel>isInsurancePresent=userInsuranceRepository.findById(id);
		if(isInsurancePresent.isPresent()) 
		{
			isInsurancePresent.get().setInsuranceId(userInsuranceDTO.getInsuranceId());
			isInsurancePresent.get().setUpdateddate(LocalDateTime.now());
			isInsurancePresent.get().setMonthPeriod(userInsuranceDTO.getMonthPeriod());
			isInsurancePresent.get().setStatus(userInsuranceDTO.getStatus());
			isInsurancePresent.get().setTokenId(userInsuranceDTO.getTokenId());
			
			userInsuranceRepository.save(isInsurancePresent.get());
			return new Response(200, "Insurance Succefully Updated", null);
		}else 
		{
			throw new UserException(400, "Insurance is not saved!");
	    }
	}

	@Override
	public List<UserInsuranceModel> getallInsuarnce(String token) {
		Long id = tokenutil.decodeToken(token);
		Optional<UserInsuranceModel>isInsurancePresent=userInsuranceRepository.findById(id);
		if(isInsurancePresent.isPresent()) 
		{
			List<UserInsuranceModel> getallInsuarnce = userInsuranceRepository.findAll();
			return getallInsuarnce;
		}else 
		{
			throw new UserException(400, "Token is not valid!");
		}
	}

	@Override
	public Response deleteInsuarance(String token) 
	{
		long id = tokenutil.decodeToken(token);
		Optional<UserInsuranceModel> isThere = userInsuranceRepository.findById(id);
		if (isThere.isPresent())
		{
			userInsuranceRepository.delete(isThere.get());
			return new Response(400,"Deleted Successfully" , HttpStatus.ACCEPTED);
		}else 
		{
			throw new UserException(500 , "Insurance could not be found to be deleted");
		}
	}


	@Override
	public List<UserInsuranceModel> getallbyStatus(String token, String status) 
	{
		Long id = tokenutil.decodeToken(token);
		Optional<UserInsuranceModel>isInsurancePresent=userInsuranceRepository.findById(id);
		if(isInsurancePresent.isPresent()) 
		{
			List<UserInsuranceModel> getallbyStatus = userInsuranceRepository.findBystatusStartsWith(status);
			return getallbyStatus;
		}else 
		{
			throw new UserException(400, "Token is not valid!");
		}
	}

	@Override
	public List<UserInsuranceModel> getAllbyMonthPeriod(String token) 
	{
		Long id = tokenutil.decodeToken(token);
		Optional<UserInsuranceModel>isInsurancePresent=userInsuranceRepository.findById(id);
		if(isInsurancePresent.isPresent()) 
		{
			List<UserInsuranceModel> getAllbyMonthPeriod = userInsuranceRepository.findAll();
			return getAllbyMonthPeriod;
		}else 
		{
			throw new UserException(400, "Token is not valid!");
		}
	}

}
