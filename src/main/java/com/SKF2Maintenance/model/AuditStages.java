package com.SKF2Maintenance.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
@Entity
@Table(name="audit_stages")
public class AuditStages {
	
	
	@Id
	@GeneratedValue
	@Column(name="audit_stage_id")
	private int auditStageId;
	
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine machine;
	
	
	@Column(name="stage_no")
	private int stageNo;
	
	@Column(name="stage_name")
	private String stageName;
	
	
	@Column(name="maximum_score")
	private int maximumScore;
	
	
	@Column(name="added_date")
	private Date addedDate;
	
	
	@Column(name="active")
	private int active;
	
	@Transient
	private List<AuditItems> items;


	public int getAuditStageId() {
		return auditStageId;
	}


	public void setAuditStageId(int auditStageId) {
		this.auditStageId = auditStageId;
	}


	public Machine getMachine() {
		return machine;
	}


	public void setMachine(Machine machine) {
		this.machine = machine;
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


	public Date getAddedDate() {
		return addedDate;
	}


	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}


	public int getActive() {
		return active;
	}


	public void setActive(int active) {
		this.active = active;
	}


	public List<AuditItems> getItems() {
		return items;
	}


	public void setItems(List<AuditItems> items) {
		this.items = items;
	}
	
	
	

}
