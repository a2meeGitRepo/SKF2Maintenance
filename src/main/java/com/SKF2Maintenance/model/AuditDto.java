package com.SKF2Maintenance.model;

import java.util.List;

public class AuditDto {
private Audit  audit;
private List<AuditItemValues> itemValues;
public Audit getAudit() {
	return audit;
}
public void setAudit(Audit audit) {
	this.audit = audit;
}
public List<AuditItemValues> getItemValues() {
	return itemValues;
}
public void setItemValues(List<AuditItemValues> itemValues) {
	this.itemValues = itemValues;
}




}
