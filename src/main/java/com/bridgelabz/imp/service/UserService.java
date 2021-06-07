package com.bridgelabz.imp.service;

import java.time.LocalDate;
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
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService implements IUserService 
{

	@Autowired
	UserRepository userrepository;

	@Autowired
	ModelMapper modelmapper;
	@Autowired
	TokenUtil tokenutil;

	/**
	 * To add user data
	 */
	@Override
	public Response createUser(UserDTO userdto) 
	{
		Optional<UserData> isPresent=userrepository.findByFullname(userdto.getFullname());
		if(isPresent.isPresent()) 
		{
			log.error("User exists already.");
			throw new UserException(400, "User Already Added");
		}else 
		{
			UserData user=modelmapper.map(userdto, UserData.class);
			userrepository.save(user);
			String token=tokenutil.createToken(user.getUserid());
			log.debug("User added.");
			return new Response(200, "User Succefully Added", token);
		}
	}

	/**
	 * To update user data
	 */
	@Override
	public Response updateUser(String token, UserDTO userdto) 
	{
		Long userid = tokenutil.decodeToken(token);
		Optional<UserData>isContactPresent=userrepository.findById(userid);
		if(isContactPresent.isPresent()) 
		{
			isContactPresent.get().setFullname(userdto.getFullname());
			isContactPresent.get().setAge(userdto.getAge());
			isContactPresent.get().setUpdateddate(LocalDate.now());
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
			log.error("User not found.");
			throw new UserException(400, "User not found!");
		}
	}

	/**
	 * To get all user data
	 */
	@Override
	public List<UserData> getallUser(String token) 
	{
		Long id = tokenutil.decodeToken(token);
		Optional<UserData>isContactPresent=userrepository.findById(id);
		if(isContactPresent.isPresent()) 
		{
			log.debug("Get all users");
			List<UserData> getallUser = userrepository.findAll();
			return getallUser;
		}
		else 
		{
			log.error("Token not valid");
			throw new UserException(400, "Token is not valid!");
		}
	}

	/**
	 * To delete user data
	 */
	@Override
	public Response deleteUser(String token) 
	{
		long id = tokenutil.decodeToken(token);
		Optional<UserData> isThere = userrepository.findById(id);
		if (isThere.isPresent())
		{
			userrepository.delete(isThere.get());
			log.debug("Deleted Successfully");
			return new Response(400,"Deleted Successfully" , HttpStatus.ACCEPTED);
		}else 
		{
			log.error("User could not be found to be deleted");
			throw new UserException(500 , "User could not be found to be deleted");
		}
	}

}
