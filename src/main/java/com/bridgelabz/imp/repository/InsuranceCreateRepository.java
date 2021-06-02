package com.bridgelabz.imp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.imp.model.InsuranceCreateModel;

public interface InsuranceCreateRepository extends JpaRepository<InsuranceCreateModel, Long>
{
	
		Optional<InsuranceCreateModel> findByinsuranceId(int insuranceId);

		List<InsuranceCreateModel> findBystatusStartsWith(String status);

		List<InsuranceCreateModel> findBymonthPeriodEquals(String monthPeriod);

}
