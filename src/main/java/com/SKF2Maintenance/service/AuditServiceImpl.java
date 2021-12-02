package com.SKF2Maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.Audit;
import com.SKF2Maintenance.model.AuditItemValues;
import com.SKF2Maintenance.model.AuditItems;
import com.SKF2Maintenance.model.AuditStages;
import com.SKF2Maintenance.repository.AuditItemValuesRepo;
import com.SKF2Maintenance.repository.AuditItemsRepo;
import com.SKF2Maintenance.repository.AuditRepo;
import com.SKF2Maintenance.repository.AuditStagesRepo;

@Service
public class AuditServiceImpl implements AuditService {

	@Autowired
	AuditStagesRepo auditStagesRepo;
	@Autowired
	AuditItemsRepo auditItemsRepo;
	@Autowired
	AuditRepo auditRepo;
	
	@Autowired
	AuditItemValuesRepo auditItemValuesRepo;
	
	@Override
	public List<AuditStages> getAuditStagesByMachine(int machineId) {
		// TODO Auto-generated method stub
		return auditStagesRepo.getAuditStagesByMachine(machineId);
	}

	@Override
	public List<AuditItems> getAuditStagesItemByStage(int stageId) {
		// TODO Auto-generated method stub
		return auditItemsRepo.getAuditStagesItemByStage(stageId);
	}

	@Override
	public Audit saveAudit(Audit audit) {
		// TODO Auto-generated method stub
		return auditRepo.save(audit);
	}

	@Override
	public void saveAuditItemValue(AuditItemValues itemValues) {
		// TODO Auto-generated method stub
		auditItemValuesRepo.save(itemValues);
	}

	@Override
	public List<AuditStages> getAuditStages() {
		// TODO Auto-generated method stub
		return auditStagesRepo.findAll();
	}

	@Override
	public void saveAudiitStage(AuditStages auditStages) {
		// TODO Auto-generated method stub
		auditStagesRepo.save(auditStages);
	}

	@Override
	public List<AuditItems> getAuditItems() {
		// TODO Auto-generated method stub
		return auditItemsRepo.findAll();
	}

	@Override
	public void saveAuditItems(AuditItems auditItems) {
		// TODO Auto-generated method stub
		auditItemsRepo.save(auditItems);
	}

	@Override
	public List<Audit> getAudits() {
		// TODO Auto-generated method stub
		return auditRepo.findAll();
	}

}
