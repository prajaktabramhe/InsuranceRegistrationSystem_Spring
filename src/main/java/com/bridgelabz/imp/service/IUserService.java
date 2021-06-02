package com.bridgelabz.imp.service;

import java.util.List;

import javax.validation.Valid;

import com.bridgelabz.imp.dto.UserDTO;
import com.bridgelabz.imp.model.UserData;
import com.bridgelabz.imp.util.Response;


public interface IUserService {

	Response createUser(@Valid UserDTO userdto);

	Response updateUser(String token, UserDTO userdto);

	List<UserData> getallUser(String token);

	List<UserData> getUser();

	Response deleteUser(String token);

}
