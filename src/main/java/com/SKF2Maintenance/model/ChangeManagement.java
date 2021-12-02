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
@Table(name="change_management")
public class ChangeManagement {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="change_management_no")
	private String changeManagementNo;
	
	
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine  machine;
	
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;
	
	@Column(name="activity_type")
	private String activityType;
	
	@Column(name="category")
	private String category;
	
	@Column(name="start_date")
	private Date startDate;
	
	
	@Column(name="start_time")
	private Date startTime;
	
	
	@Column(name="end_date")
	private Date endDate;
	
	
	@Column(name="end_time")
	private Date endTime;
	
	
	@Column(name="problem_statement")
	private String problemStatement;
	
	@Column(name="activity_description")
	private String activityDescription;
	
	@ManyToOne
	@JoinColumn(name="done_by")
	private User doneBy;
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department picDepartment;
	
	
	@Column(name="pic")
	private String pic;
	
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
	
	@Column(name="status")
	private String status;
	
	@Column(name="delet_bit")
	private int deletBit;
	
	
	
	@Transient
	List<Spare> spares;
	
	
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getChangeManagementNo() {
		return changeManagementNo;
	}

	public void setChangeManagementNo(String changeManagementNo) {
		this.changeManagementNo = changeManagementNo;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getProblemStatement() {
		return problemStatement;
	}

	public void setProblemStatement(String problemStatement) {
		this.problemStatement = problemStatement;
	}

	public String getActivityDescription() {
		return activityDescription;
	}

	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;
	}

	public User getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(User doneBy) {
		this.doneBy = doneBy;
	}

	public Department getPicDepartment() {
		return picDepartment;
	}

	public void setPicDepartment(Department picDepartment) {
		this.picDepartment = picDepartment;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

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

	public int getDeletBit() {
		return deletBit;
	}

	public void setDeletBit(int deletBit) {
		this.deletBit = deletBit;
	}

	public List<Spare> getSpares() {
		return spares;
	}

	public void setSpares(List<Spare> spares) {
		this.spares = spares;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ChangeManagement [id=" + id + ", changeManagementNo=" + changeManagementNo + ", machine=" + machine
				+ ", location=" + location + ", activityType=" + activityType + ", category=" + category
				+ ", startDate=" + startDate + ", startTime=" + startTime + ", endDate=" + endDate + ", endTime="
				+ endTime + ", problemStatement=" + problemStatement + ", activityDescription=" + activityDescription
				+ ", doneBy=" + doneBy + ", picDepartment=" + picDepartment + ", pic=" + pic + ", addedDate="
				+ addedDate + ", updatedDate=" + updatedDate + ", addedBy=" + addedBy + ", updatedBy=" + updatedBy
				+ ", active=" + active + ", status=" + status + ", deletBit=" + deletBit + ", spares=" + spares + "]";
	}
	
	
	
	
	
}
