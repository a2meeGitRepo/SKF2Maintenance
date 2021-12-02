package com.SKF2Maintenance.dto;

import java.util.List;

import com.SKF2Maintenance.model.AuditItems;
import com.SKF2Maintenance.model.AuditStages;
import com.SKF2Maintenance.model.Machine;

public class AuditItemsSaveDto {
private Machine machine;
private AuditStages auditStage;
private List<AuditItems> items;
public Machine getMachine() {
	return machine;
}
public void setMachine(Machine machine) {
	this.machine = machine;
}
public AuditStages getAuditStage() {
	return auditStage;
}
public void setAuditStage(AuditStages auditStage) {
	this.auditStage = auditStage;
}
public List<AuditItems> getItems() {
	return items;
}
public void setItems(List<AuditItems> items) {
	this.items = items;
}





}
