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
@Table(name="maintenance")
public class Maintenance {
	
	@Id
	@GeneratedValue
	@Column(name="maintenance_id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine machine;
	
	
	@ManyToOne
	@JoinColumn(name="task_id")
	private Task task;
	
	
	
	@ManyToOne
	@JoinColumn(name="task_head_id")
	private TaskHead taskHead;
	
	
	
	@Column(name="maintenance_mode")
	private String maintenanceMode;
	
	@Column(name="frequency")
	private String frequency;
	
	@Column(name="shedule_date")
	private Date sheduleDate;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="maintenance_done_by")
	private String maintenanceDoneBy;
	
	@Column(name="overall_observation")
	private String overallObservation;
	
	@Column(name="maintenance_close_date")
	private Date maintenanceCloseDate;
	
	@Transient
	private List<MaintenanceDoneCheckPoint> maintenanceCheckPoints;
	
	@Column(name="status")
	private String status;
	
	
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	
	
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

	public String getMaintenanceMode() {
		return maintenanceMode;
	}

	public void setMaintenanceMode(String maintenanceMode) {
		this.maintenanceMode = maintenanceMode;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public Date getSheduleDate() {
		return sheduleDate;
	}

	public void setSheduleDate(Date sheduleDate) {
		this.sheduleDate = sheduleDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getMaintenanceDoneBy() {
		return maintenanceDoneBy;
	}

	public void setMaintenanceDoneBy(String maintenanceDoneBy) {
		this.maintenanceDoneBy = maintenanceDoneBy;
	}

	public String getOverallObservation() {
		return overallObservation;
	}

	public void setOverallObservation(String overallObservation) {
		this.overallObservation = overallObservation;
	}

	public Date getMaintenanceCloseDate() {
		return maintenanceCloseDate;
	}

	public void setMaintenanceCloseDate(Date maintenanceCloseDate) {
		this.maintenanceCloseDate = maintenanceCloseDate;
	}

	public List<MaintenanceDoneCheckPoint> getMaintenanceCheckPoints() {
		return maintenanceCheckPoints;
	}

	public void setMaintenanceCheckPoints(List<MaintenanceDoneCheckPoint> maintenanceCheckPoints) {
		this.maintenanceCheckPoints = maintenanceCheckPoints;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public TaskHead getTaskHead() {
		return taskHead;
	}

	public void setTaskHead(TaskHead taskHead) {
		this.taskHead = taskHead;
	}


}
