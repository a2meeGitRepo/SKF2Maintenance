package com.SKF2Maintenance.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SKF2Maintenance.model.Spare;

public interface SparesRepo extends JpaRepository<Spare, Integer>,SparesRepoCustome{

	@Query("select count(s)from Spare s where s.deleteBit=0")
	long getSparesCount();

	@Query("select count(s)from Spare s where s.deleteBit=0")
	long getSpareCountAndSerach(@Param("searchText") String searchText);
	
	@Query("from Spare s where s.deleteBit=0 and s.active=1")
	List<Spare> getAllActiveSpares();
	
	@Query("select count(s)from Spare s where s.deleteBit=0 and s.spareName=?1")
	Optional<Spare> getSpareBySpareName(String spareName);

}
