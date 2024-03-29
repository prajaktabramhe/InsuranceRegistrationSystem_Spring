package com.bridgelabz.imp.repository;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.imp.model.InsuranceCategoryModel;
import com.bridgelabz.imp.model.InsuranceCreateModel;


public interface InsuranceCategoryRepository extends JpaRepository<InsuranceCategoryModel, Long>{

	Optional<InsuranceCategoryModel> findByInsurancecode(int insurancecode);

	List<InsuranceCategoryModel> findByInsurancename(String category);

	List<InsuranceCategoryModel> findByRegistereddateBetween(LocalDate startDate, LocalDate endDate);

}
