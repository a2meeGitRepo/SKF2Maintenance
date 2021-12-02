package com.SKF2Maintenance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="audit_stages_items")
public class AuditItems {
	
	@Id
	@GeneratedValue
	@Column(name="audit_stages_item_id")
	private int auditStagesItemId;
	
	@ManyToOne
	@JoinColumn(name="audit_stage_id")
	private AuditStages auditStage;
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine machine;
	
	@Column(name="item_name")
	private String itemName;
	
	
	@Column(name="description")
	private String description;
	
	@Column(name="full_score")
	private String fullScore;
	
	
	@Column(name="addec_date")
	private Date addecDate;
	
	
	
	@Column(name="active")
	private int active;



	public int getAuditStagesItemId() {
		return auditStagesItemId;
	}



	public void setAuditStagesItemId(int auditStagesItemId) {
		this.auditStagesItemId = auditStagesItemId;
	}



	public AuditStages getAuditStage() {
		return auditStage;
	}



	public void setAuditStage(AuditStages auditStage) {
		this.auditStage = auditStage;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getFullScore() {
		return fullScore;
	}



	public void setFullScore(String fullScore) {
		this.fullScore = fullScore;
	}



	public Date getAddecDate() {
		return addecDate;
	}



	public void setAddecDate(Date addecDate) {
		this.addecDate = addecDate;
	}



	public int getActive() {
		return active;
	}



	public void setActive(int active) {
		this.active = active;
	}



	public Machine getMachine() {
		return machine;
	}



	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
	
	

}
