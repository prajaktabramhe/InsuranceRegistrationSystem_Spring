package com.bridgelabz.imp.repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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

	/**
	 * To find user with healthcondition  passed as parameter
	 */
	List<UserData> findByhealthcondition(String healthcondition);

	/**
	 * To find user with vehicledata data passed as parameter
	 */
	List<UserData> findByVehicledata(String vehicledata);
	
	/**
	 * To find users between start and end dates
	 */
	List<UserData> findByRegistereddateBetween(LocalDate localDate, LocalDate localDate2);


}
