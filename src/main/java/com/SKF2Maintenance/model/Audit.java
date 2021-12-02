package com.SKF2Maintenance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name="audit_tr")
public class Audit {
	@Id
	@GeneratedValue
	@Column(name="audit_id")
	private int auditId;
	
	@Column(name="audit_name")
	private String auditName;
	
	
	@Column(name="factory")
	private String factory;
	
	
	@Column(name="channel")
	private String channel;
	
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine machine; 
	
	@ManyToOne
	@JoinColumn(name="machine_owner_id")
	private MachineOwner machineOwner; 
	
	@Column(name="am_coordinator")
	private String amCoordinator;
	
	@Column(name="pm_coordinator")
	private String pmCoordinator;
	
	
	@Column(name="self_audit")
	private String selfAudit;
	
	
	
	
	@Column(name="maintenance_ex_sme")
	private String maintenanceExSme;
	
	@Column(name="top_management")
	private String topManagement;

	@Column(name="additional_comment")
	private String additionalComment;

	
	
	@Column(name="phase_cirtification")
	private String phaseCirtification;

	
	@Column(name="audit_by")
	private String auditBy;
	
	
	
	@Column(name="aduit_date")
	private Date aduitDate;
	
	
	
	@Column(name="updated_by")
	private String updatedBy;
	
	
	
	@Column(name="updated_date")
	private Date updatedDate;



	public int getAuditId() {
		return auditId;
	}



	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}



	public String getAuditName() {
		return auditName;
	}



	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}



	public String getFactory() {
		return factory;
	}



	public void setFactory(String factory) {
		this.factory = factory;
	}



	public String getChannel() {
		return channel;
	}



	public void setChannel(String channel) {
		this.channel = channel;
	}



	public Machine getMachine() {
		return machine;
	}



	public void setMachine(Machine machine) {
		this.machine = machine;
	}



	public MachineOwner getMachineOwner() {
		return machineOwner;
	}



	public void setMachineOwner(MachineOwner machineOwner) {
		this.machineOwner = machineOwner;
	}



	public String getAmCoordinator() {
		return amCoordinator;
	}



	public void setAmCoordinator(String amCoordinator) {
		this.amCoordinator = amCoordinator;
	}



	public String getPmCoordinator() {
		return pmCoordinator;
	}



	public void setPmCoordinator(String pmCoordinator) {
		this.pmCoordinator = pmCoordinator;
	}



	public String getSelfAudit() {
		return selfAudit;
	}



	public void setSelfAudit(String selfAudit) {
		this.selfAudit = selfAudit;
	}



	public String getMaintenanceExSme() {
		return maintenanceExSme;
	}



	public void setMaintenanceExSme(String maintenanceExSme) {
		this.maintenanceExSme = maintenanceExSme;
	}



	public String getTopManagement() {
		return topManagement;
	}



	public void setTopManagement(String topManagement) {
		this.topManagement = topManagement;
	}



	public String getAdditionalComment() {
		return additionalComment;
	}



	public void setAdditionalComment(String additionalComment) {
		this.additionalComment = additionalComment;
	}



	public String getPhaseCirtification() {
		return phaseCirtification;
	}



	public void setPhaseCirtification(String phaseCirtification) {
		this.phaseCirtification = phaseCirtification;
	}



	public String getAuditBy() {
		return auditBy;
	}



	public void setAuditBy(String auditBy) {
		this.auditBy = auditBy;
	}



	public Date getAduitDate() {
		return aduitDate;
	}



	public void setAduitDate(Date aduitDate) {
		this.aduitDate = aduitDate;
	}



	public String getUpdatedBy() {
		return updatedBy;
	}



	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}



	public Date getUpdatedDate() {
		return updatedDate;
	}



	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	
	

}
