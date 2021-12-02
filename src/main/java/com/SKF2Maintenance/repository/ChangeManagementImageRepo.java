package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.ChangeManagementImage;

public interface ChangeManagementImageRepo extends JpaRepository<ChangeManagementImage, Integer>{
	@Query("from ChangeManagementImage c where c.changeManagement.id=?1")
	List<ChangeManagementImage> getImageByChangeManagement(int changemanagementId);

}
