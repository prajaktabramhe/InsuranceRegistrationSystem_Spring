package com.bridgelabz.imp.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.bridgelabz.imp.model.InsuranceCategoryModel;


public interface InsuranceCategoryRepository extends JpaRepository<InsuranceCategoryModel, Long>{

	Optional<InsuranceCategoryModel> findByInsurancecode(int insurancecode);

}
