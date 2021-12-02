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
@Table(name="maintenance_task_head")
public class TaskHead {
	@Id
	@GeneratedValue
	@Column(name="task_head_id")
	private int taskHeadId;
	
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine machine;
	
	
	@Column(name="task_head_name")
	private String taskHeadName;
	
	
	@Column(name="sr_no")
	private String srNo;
	
	@Column(name="active")
	private int active;
	
	@Column(name="added_date")
	private Date addedDate;

	public int getTaskHeadId() {
		return taskHeadId;
	}

	public void setTaskHeadId(int taskHeadId) {
		this.taskHeadId = taskHeadId;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getTaskHeadName() {
		return taskHeadName;
	}

	public void setTaskHeadName(String taskHeadName) {
		this.taskHeadName = taskHeadName;
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
	
	
	
	
	
	
	
	
}
