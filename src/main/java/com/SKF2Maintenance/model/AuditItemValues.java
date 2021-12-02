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
@Table(name="audit_items_value")
public class AuditItemValues {
	@Id
	@GeneratedValue
	@Column(name="audit_items_value_id")
	private int auditItemsValueId;
	
	
	@ManyToOne
	@JoinColumn(name="audit_stages_item_id")
	private AuditItems items;
	
	
	
	@ManyToOne
	@JoinColumn(name="audit_id")
	private Audit audit;
	
	@Column(name="not_started")
	private int notStarted;
	
	@Column(name="major_dev")
	private int majorDev;
	
	@Column(name="minor_dev")
	private int minorDev;
	
	@Column(name="no_dev")
	private int noDev;
	
	@Column(name="description")
	private String description;
	
	
	@Column(name="added_date")
	private Date addedDate;
	
	
	@Column(name="active")
	private int active;


	public int getAuditItemsValueId() {
		return auditItemsValueId;
	}


	public void setAuditItemsValueId(int auditItemsValueId) {
		this.auditItemsValueId = auditItemsValueId;
	}


	public AuditItems getItems() {
		return items;
	}


	public void setItems(AuditItems items) {
		this.items = items;
	}


	public int getNotStarted() {
		return notStarted;
	}


	public void setNotStarted(int notStarted) {
		this.notStarted = notStarted;
	}


	public int getMajorDev() {
		return majorDev;
	}


	public void setMajorDev(int majorDev) {
		this.majorDev = majorDev;
	}


	public int getMinorDev() {
		return minorDev;
	}


	public void setMinorDev(int minorDev) {
		this.minorDev = minorDev;
	}


	public int getNoDev() {
		return noDev;
	}


	public void setNoDev(int noDev) {
		this.noDev = noDev;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
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


	public Audit getAudit() {
		return audit;
	}


	public void setAudit(Audit audit) {
		this.audit = audit;
	}
	
	
}
