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

import com.bridgelabz.imp.dto.UserDTO;
import com.bridgelabz.imp.model.UserData;
import com.bridgelabz.imp.service.IUserService;
import com.bridgelabz.imp.util.Response;

@RestController
@RequestMapping("/user")
public class UserController 
{
	@Autowired
	IUserService userService;

	/**
	 * To add new user
	 * @param userdto : To get user data from UserDTO
	 * @return: ResponseEntity<Response>
	 */
	@PostMapping("/addnewuser")
	ResponseEntity<Response> createUser(@Valid @RequestBody UserDTO userdto)
	{
		Response response=userService.createUser(userdto);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	
	/**
	 * To update user data
	 * @param token :JWT data with userid
	 * @param userdto : To get user data from UserDTO
	 * @return : ResponseEntity<Response>
	 */
	@PutMapping("/updateuser/{token}")
	ResponseEntity<Response> updateUser(@PathVariable String token, @RequestBody UserDTO userdto)
	{
		Response response=userService.updateUser(token,userdto);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

		
	/**
	 * To get all users
	 * @param token :JWT data with userid
	 * @return: ResponseEntity<Response>
	 */
	@GetMapping("/getallUser/{token}")
	ResponseEntity<List<?>> getallUser(@PathVariable String token)
	{
		List<UserData> response = userService.getallUser(token);
		return new ResponseEntity<List<?>>(response,HttpStatus.OK);
	}

	/**
	 * To delete user
	 * @param token : JWT data with userid
	 * @return : ResponseEntity<Response>
	 */
	@DeleteMapping("/deletcontact/{token}")
	ResponseEntity<Response> deleteUser(@PathVariable String token)
	{
		Response response=userService.deleteUser(token);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
}
