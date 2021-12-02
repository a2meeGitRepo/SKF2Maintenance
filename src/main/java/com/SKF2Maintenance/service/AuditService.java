package com.SKF2Maintenance.service;

import java.util.List;

import com.SKF2Maintenance.model.Audit;
import com.SKF2Maintenance.model.AuditItemValues;
import com.SKF2Maintenance.model.AuditItems;
import com.SKF2Maintenance.model.AuditStages;

public interface AuditService {

	List<AuditStages> getAuditStagesByMachine(int machineId);

	List<AuditItems> getAuditStagesItemByStage(int stageId);

	Audit saveAudit(Audit audit);

	void saveAuditItemValue(AuditItemValues itemValues);

	List<AuditStages> getAuditStages();

	void saveAudiitStage(AuditStages auditStages);

	List<AuditItems> getAuditItems();

	void saveAuditItems(AuditItems auditItems);

	List<Audit> getAudits();

}
