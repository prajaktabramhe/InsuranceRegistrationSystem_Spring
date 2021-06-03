package com.bridgelabz.imp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bridgelabz.imp.dto.UserDTO;
import com.bridgelabz.imp.exception.UserException;
import com.bridgelabz.imp.model.UserData;
import com.bridgelabz.imp.repository.UserRepository;
import com.bridgelabz.imp.util.Response;
import com.bridgelabz.imp.util.TokenUtil;

@Service
public class UserService implements IUserService 
{

	@Autowired
	UserRepository userrepository;
	
	@Autowired
	ModelMapper modelmapper;
	@Autowired
	TokenUtil tokenutil;
	
	@Override
	public Response createUser(UserDTO userdto) 
	{
		Optional<UserData> isPresent=userrepository.findByFullname(userdto.getFullname());
   		if(isPresent.isPresent()) 
		{
			throw new UserException(400, "User Already Added");
		}else 
		{
			UserData user=modelmapper.map(userdto, UserData.class);
			userrepository.save(user);
			String token=tokenutil.createToken(user.getUserid());
			return new Response(200, "User Succefully Added", token);
		}
	}

	@Override
	public Response updateUser(String token, UserDTO userdto) 
	{
		Long userid = tokenutil.decodeToken(token);
		Optional<UserData>isContactPresent=userrepository.findById(userid);
		if(isContactPresent.isPresent()) 
		{
			isContactPresent.get().setFullname(userdto.getFullname());
			isContactPresent.get().setAge(userdto.getAge());
			isContactPresent.get().setUpdateddate(LocalDateTime.now());
			isContactPresent.get().setMobilenumber(userdto.getMobilenumber());
			isContactPresent.get().setFamilybackground(userdto.getFamilybackground());
			isContactPresent.get().setPermanentAddress(userdto.getPermanentAddress());
			isContactPresent.get().setTemporaryAddress(userdto.getTemporaryAddress());
			isContactPresent.get().setHealthcondition(userdto.getHealthcondition());
			isContactPresent.get().setOccupation(userdto.getOccupation());
			isContactPresent.get().setKyc(userdto.getKyc());
			
			userrepository.save(isContactPresent.get());
			return new Response(200, "User Succefully Updated", null);
		}
		else 
		{
			throw new UserException(400, "User is not saved!");
	    }

	}

	@Override
	public List<UserData> getallUser(String token) 
	{
		Long id = tokenutil.decodeToken(token);
		Optional<UserData>isContactPresent=userrepository.findById(id);
		if(isContactPresent.isPresent()) 
		{
			List<UserData> getallUser = userrepository.findAll();
			return getallUser;
		}
		else 
		{
			throw new UserException(400, "Token is not valid!");
		}
	}

	@Override
	public Response deleteUser(String token) 
	{
		long id = tokenutil.decodeToken(token);
		Optional<UserData> isThere = userrepository.findById(id);
		if (isThere.isPresent())
		{
			userrepository.delete(isThere.get());
			return new Response(400,"Deleted Successfully" , HttpStatus.ACCEPTED);
		}else 
		{
			throw new UserException(500 , "User could not be found to be deleted");
		}
	}

	
}
