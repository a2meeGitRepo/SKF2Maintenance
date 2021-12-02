package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.ChangeManagement;
import com.SKF2Maintenance.model.ChangeManagementImage;
import com.SKF2Maintenance.model.ChangeManagementSpare;
import com.SKF2Maintenance.repository.ChangeManagementImageRepo;
import com.SKF2Maintenance.repository.ChangeManagementRepo;
import com.SKF2Maintenance.repository.ChangeManagementSpareRepo;

@Service
public class ChangeMangementServiceimpl implements ChangeMangementService {
	@Autowired
	ChangeManagementRepo  changeManagementRepo;
	@Autowired
	ChangeManagementSpareRepo changeManagementSpareRepo;
	@Autowired
	ChangeManagementImageRepo changeManagementImageRepo;
	
	
	
	public void addChangeMangement(ChangeManagement changeManagement) {
		// TODO Auto-generated method stub
		changeManagementRepo.save(changeManagement);
	}

	public Optional<ChangeManagement> getChangemangementById(int changeManagementId) {
		// TODO Auto-generated method stub
		return changeManagementRepo.findById(changeManagementId);
	}

	public void saveChangeManagementImage(ChangeManagementImage changeManagementImage) {
		// TODO Auto-generated method stub
	}

	public void saveChangemangementSpare(ChangeManagementSpare changeManagementSpare) {
		// TODO Auto-generated method stub
		changeManagementSpareRepo.save(changeManagementSpare);
	}

	@Override
	public List<ChangeManagement> getAllChangeManagement() {
		// TODO Auto-generated method stub
		return changeManagementRepo.findAll();
	}

	@Override
	public List<ChangeManagement> getOpenChangeManagement() {
		// TODO Auto-generated method stub
		return changeManagementRepo.getOpenChangeManagement();
	}

	@Override
	public List<ChangeManagementSpare> getSpareByChangeManagement(int changemanagementId) {
		// TODO Auto-generated method stub
		return changeManagementSpareRepo.getSpareByChangeManagement(changemanagementId);
	}

	@Override
	public List<ChangeManagementImage> getImageByChangeManagement(int changemanagementId) {
		// TODO Auto-generated method stub
		return changeManagementImageRepo.getImageByChangeManagement(changemanagementId);
	}

}
