package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import com.SKF2Maintenance.model.ChangeManagement;
import com.SKF2Maintenance.model.ChangeManagementImage;
import com.SKF2Maintenance.model.ChangeManagementSpare;

public interface ChangeMangementService {

	void addChangeMangement(ChangeManagement changeManagement);

	Optional<ChangeManagement> getChangemangementById(int changeManagementId);

	void saveChangeManagementImage(ChangeManagementImage changeManagementImage);

	void saveChangemangementSpare(ChangeManagementSpare changeManagementSpare);

	List<ChangeManagement> getAllChangeManagement();

	List<ChangeManagement> getOpenChangeManagement();

	List<ChangeManagementSpare> getSpareByChangeManagement(int changemanagementId);

	List<ChangeManagementImage> getImageByChangeManagement(int changemanagementId);

}
