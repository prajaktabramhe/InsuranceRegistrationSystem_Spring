package com.bridgelabz.imp.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.bridgelabz.imp.model.UserData;

//@Repository
public interface UserRepository extends JpaRepository<UserData, Long> 
{



	 Optional<UserData> findByFullname(String fullname);

	

}
