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
@Table(name="machine_check_point")
public class MachineCheckPoint {
	@Id
	@GeneratedValue
	@Column(name="id")
    private int id;
	
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine machine;
	
	/*@ManyToOne
	@JoinColumn(name="check_point_id")
	private CheckPoint checkPoint;*/
	@Column(name="check_point_name")
	private String checkPointName;
	
	
	@Column(name="frequency")
	private String frequency;
	
	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	@Column(name="defaultValue")
	private String defaultValue;
	
	@Column(name="operation")
	private String operation;
	
	
	
	@Column(name="added_date")
	private Date addedDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="added_by")
	private String addedBy;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="active")
	private int active;
	
	@Column(name="delete_bit")
	private int deleteBit;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

//	public CheckPoint getCheckPoint() {
//		return checkPoint;
//	}
//
//	public void setCheckPoint(CheckPoint checkPoint) {
//		this.checkPoint = checkPoint;
//	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}


	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getDeleteBit() {
		return deleteBit;
	}

	public void setDeleteBit(int deleteBit) {
		this.deleteBit = deleteBit;
	}

	public String getCheckPointName() {
		return checkPointName;
	}

	public void setCheckPointName(String checkPointName) {
		this.checkPointName = checkPointName;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	

}
