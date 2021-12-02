package com.SKF2Maintenance.dto;

import java.util.List;

import javax.persistence.Column;

import com.SKF2Maintenance.model.AuditItems;

public class AuditStageDto {
	private int auditStageId;
	private int stageNo;
	
	private String stageName;
	
	private int maximumScore;
	
	private List<AuditItemsDTO> items;

	public int getAuditStageId() {
		return auditStageId;
	}

	public void setAuditStageId(int auditStageId) {
		this.auditStageId = auditStageId;
	}

	public int getStageNo() {
		return stageNo;
	}

	public void setStageNo(int stageNo) {
		this.stageNo = stageNo;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public int getMaximumScore() {
		return maximumScore;
	}

	public void setMaximumScore(int maximumScore) {
		this.maximumScore = maximumScore;
	}

	public List<AuditItemsDTO> getItems() {
		return items;
	}

	public void setItems(List<AuditItemsDTO> items) {
		this.items = items;
	}
	
	
	

}
