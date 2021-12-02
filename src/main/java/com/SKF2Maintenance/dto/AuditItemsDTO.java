package com.SKF2Maintenance.dto;

import javax.persistence.Column;

public class AuditItemsDTO {
	private int auditStagesItemId;

	private String itemName;
	
	private String fullScore;


	private String description;


	public int getAuditStagesItemId() {
		return auditStagesItemId;
	}


	public void setAuditStagesItemId(int auditStagesItemId) {
		this.auditStagesItemId = auditStagesItemId;
	}


	public String getItemName() {
		return itemName;
	}


	public void setItemName(String itemName) {
		this.itemName = itemName;
	}


	public String getFullScore() {
		return fullScore;
	}


	public void setFullScore(String fullScore) {
		this.fullScore = fullScore;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
	
}
