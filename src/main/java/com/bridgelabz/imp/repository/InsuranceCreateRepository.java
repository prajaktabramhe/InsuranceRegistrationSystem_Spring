package com.bridgelabz.imp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bridgelabz.imp.model.InsuranceCreateModel;

public interface InsuranceCreateRepository extends JpaRepository<InsuranceCreateModel, Long>
{
	
		List<InsuranceCreateModel> findByStatusStartsWith(String status);

		List<InsuranceCreateModel> findByMonthperiod(int monthperiod);

		@Query(value = "select * from insurance_create where id = (select userid from insurance_token where tokenid= :userid)",
				nativeQuery = true)
		List<InsuranceCreateModel> getByUserid(@Param("userid") Long userid);
}
