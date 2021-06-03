package com.bridgelabz.imp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.bridgelabz.imp.dto.InsuranceDTO;
import com.bridgelabz.imp.exception.UserException;
import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.model.UserData;
import com.bridgelabz.imp.repository.InsuranceCategoryRepository;
import com.bridgelabz.imp.util.Response;
import com.bridgelabz.imp.util.TokenUtil;

@Service
public class InsuranceCategoryService implements IInsuranceCategoryService 
{

	@Autowired
	InsuranceCategoryRepository insuranceCategoryRepository;
	
	@Autowired
	ModelMapper modelmapper;
	
	@Autowired
	TokenUtil tokenutil;

	@Override
	public Response addInsurance(@Valid InsuranceDTO insuarancedto) {
		Optional<InsuranceCategoryModel> isPresent=insuranceCategoryRepository.findByInsurancecode(insuarancedto.getInsurancecode());
   		if(isPresent.isPresent()) 
		{
			throw new UserException(400, "Insurance Already Added");
		}else 
		{
			InsuranceCategoryModel user=modelmapper.map(insuarancedto, InsuranceCategoryModel.class);
			insuranceCategoryRepository.save(user);
			String token=tokenutil.createToken(user.getCategoryid());
			return new Response(200, "Insurance Succefully Added", token);
		}
	}

	@Override
	public Response updateInsuarance(String token, InsuranceDTO insuarancedto) {
		Long id = tokenutil.decodeToken(token);
		Optional<InsuranceCategoryModel>isContactPresent=insuranceCategoryRepository.findById(id);
		if(isContactPresent.isPresent()) 
		{
			isContactPresent.get().setInsurancename(insuarancedto.getInsurancename());
			isContactPresent.get().setInsurancescheme(insuarancedto.getInsurancescheme());
			isContactPresent.get().setInsuarncestatus(insuarancedto.getInsuarncestatus());
			isContactPresent.get().setUpdateddate(LocalDateTime.now());
			isContactPresent.get().setInsurancecode(insuarancedto.getInsurancecode());
			insuranceCategoryRepository.save(isContactPresent.get());
			return new Response(200, "Insurance Succefully Updated", null);
		}
		else 
		{
			throw new UserException(400, "Insurance is not saved!");
	    }
	}

	@Override
	public List<InsuranceCategoryModel> getallInsurance(String token) 
	{
		Long id = tokenutil.decodeToken(token);
		Optional<InsuranceCategoryModel>isContactPresent=insuranceCategoryRepository.findById(id);
		if(isContactPresent.isPresent()) 
		{
			List<InsuranceCategoryModel> getallInsurance = insuranceCategoryRepository.findAll();
			return getallInsurance;
		}
		else 
		{
			throw new UserException(400, "Token is not valid!");
		}
	}

	@Override
	public Response deleteInsuarance(String token) {
		long id = tokenutil.decodeToken(token);
		Optional<InsuranceCategoryModel> isThere = insuranceCategoryRepository.findById(id);
		if (isThere.isPresent())
		{
			insuranceCategoryRepository.delete(isThere.get());
			return new Response(400,"Deleted Successfully" , HttpStatus.ACCEPTED);
		}else 
		{
			throw new UserException(500 , "Insurance could not be found to be deleted");
		}
	}

	
	

}
