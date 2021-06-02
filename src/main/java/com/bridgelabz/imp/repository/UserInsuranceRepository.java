package com.bridgelabz.imp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.imp.model.UserInsuranceModel;

public interface UserInsuranceRepository extends JpaRepository<UserInsuranceModel, Long>
{
	
		Optional<UserInsuranceModel> findByinsuranceId(int insuranceId);

		List<UserInsuranceModel> findBystatusStartsWith(String status);

		List<UserInsuranceModel> findBymonthPeriodEquals(String monthPeriod);

}
