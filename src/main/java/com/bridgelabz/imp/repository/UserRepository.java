package com.bridgelabz.imp.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bridgelabz.imp.model.UserData;

//@Repository
public interface UserRepository extends JpaRepository<UserData, Long> 
{
	/**
	 * To find data by fullname with value passed as parameter
	 * @param fullname : To search for is already present or not
	 * @return : Optional<UserData>
	 */

	Optional<UserData> findByFullname(String fullname);

}
