package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.ChangeManagement;

public interface ChangeManagementRepo extends JpaRepository<ChangeManagement, Integer> {
	@Query("from ChangeManagement c")
	List<ChangeManagement> getOpenChangeManagement();

}
