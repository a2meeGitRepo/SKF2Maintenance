package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.ChangeManagementSpare;

public interface ChangeManagementSpareRepo extends JpaRepository<ChangeManagementSpare, Integer> {
	@Query("from ChangeManagementSpare c where c.changeManagement.id=?1")
	List<ChangeManagementSpare> getSpareByChangeManagement(int changemanagementId);

}
