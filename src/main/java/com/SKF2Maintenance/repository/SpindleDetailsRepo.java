package com.SKF2Maintenance.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SKF2Maintenance.model.SpindleDetails;

public interface SpindleDetailsRepo extends JpaRepository<SpindleDetails, Integer>,SpindleDetailsRepoCustome {

	@Query("select count(s)from SpindleDetails s where s.deleteBit=0")
	long getSpindleCount();
	
	@Query("select count(*)from SpindleDetails s where s.spindleName LIKE %:searchText% OR  s.spindleType LIKE %:searchText% and  s.deleteBit=0")
	int getSpindleCoundAndSerach(@Param("searchText")String searchText);
	@Query("from SpindleDetails s where s.deleteBit=0 and s.active=1")

	List<SpindleDetails> getAllActiveSpindle();

}
