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
@Table(name="maintenance_task")
public class Task {
	
	
	
	@Id
	@GeneratedValue
	@Column(name="task_id")
	private int taskId;
	

	
	@Column(name="task_head_id")
	private int taskHeadId;
	
	@Column(name="machine_id")
	private int machineId;
	
	
	@Column(name="sr_no")
	private String srNo;
	
	@Column(name="task_type")
	private String taskType;
	
	@Column(name="frequency")
	private String frequency;
	
	@Column(name="task_name")
	private String taskName;
	
	
	@Column(name="active")
	private int active;
	
	@Column(name="added_date")
	private Date addedDate;

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	

	public int  getTaskHeadId() {
		return taskHeadId;
	}

	public void setTaskHeadId(int taskHeadId) {
		this.taskHeadId = taskHeadId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public String getSrNo() {
		return srNo;
	}

	public void setSrNo(String srNo) {
		this.srNo = srNo;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public int getMachineId() {
		return machineId;
	}

	public void setMachineId(int machineId) {
		this.machineId = machineId;
	}
	
	

}
