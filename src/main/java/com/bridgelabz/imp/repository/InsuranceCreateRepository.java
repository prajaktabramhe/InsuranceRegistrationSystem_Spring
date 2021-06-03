package com.bridgelabz.imp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.model.InsuranceCreateModel;

public interface InsuranceCreateRepository extends JpaRepository<InsuranceCreateModel, Long>
{
	
		List<InsuranceCreateModel> findByStatusStartsWith(String status);

//		List<InsuranceCreateModel> findByMonthPeriodEquals(String monthPeriod);

//		Optional<InsuranceCategoryModel> findByInsuranceCode(int insurancecode);

}
