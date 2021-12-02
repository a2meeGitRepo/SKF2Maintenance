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
@Table(name="breakdown")
public class Breakdown {
	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="breakdown_no")
	private String breakdownno;
	
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine machine;
	
	@Column(name="breakdown_type")
	private String breakdownType;
	
	@Column(name="breakdown_date")
	private Date breakdownDate;
	
	@Column(name="breakdown_time")
	private Date breakdownTime;
	
	@Column(name="breakdown_section")
	private String breakdownSection;
	
	@Column(name="problem")
	private String problem;
	
	@Column(name="priority")
	private String priority;
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
	@Column(name="watch_point")
	private String watchPoint;
	
	@Column(name="fault")
	private String fault;
	
	@Column(name="cause")
	private String cause;
	
	
	@Column(name="carrective_action_taken")
	private String carrectiveActionTaken;
	
	@Column(name="comment_obervation")
	private String commentObervation;
	
	
	
	@Column(name="report_date_time")
	private Date reportDateTime;
	
	@Column(name="done_by")
	private String doneBy;
	
	@Column(name="close_date")
	private Date closeDate;
	
	@Column(name="status")
	private String status;
	
	@Column(name="added_by")
	private String addedBy;
	
	@Column(name="added_date")
	private String addedDate;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_date")
	private String updatedDate;
	
	
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

	

	public String getBreakdownno() {
		return breakdownno;
	}

	public void setBreakdownno(String breakdownno) {
		this.breakdownno = breakdownno;
	}

	public String getBreakdownType() {
		return breakdownType;
	}

	public void setBreakdownType(String breakdownType) {
		this.breakdownType = breakdownType;
	}

	public Date getBreakdownDate() {
		return breakdownDate;
	}

	public void setBreakdownDate(Date breakdownDate) {
		this.breakdownDate = breakdownDate;
	}

	public Date getBreakdownTime() {
		return breakdownTime;
	}

	public void setBreakdownTime(Date breakdownTime) {
		this.breakdownTime = breakdownTime;
	}

	public String getBreakdownSection() {
		return breakdownSection;
	}

	public void setBreakdownSection(String breakdownSection) {
		this.breakdownSection = breakdownSection;
	}

	public String getProblem() {
		return problem;
	}

	public void setProblem(String problem) {
		this.problem = problem;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public Date getReportDateTime() {
		return reportDateTime;
	}

	public void setReportDateTime(Date reportDateTime) {
		this.reportDateTime = reportDateTime;
	}

	

	public String getDoneBy() {
		return doneBy;
	}

	public void setDoneBy(String doneBy) {
		this.doneBy = doneBy;
	}

	public Date getCloseDate() {
		return closeDate;
	}

	public void setCloseDate(Date closeDate) {
		this.closeDate = closeDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public String getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(String addedDate) {
		this.addedDate = addedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
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

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getWatchPoint() {
		return watchPoint;
	}

	public void setWatchPoint(String watchPoint) {
		this.watchPoint = watchPoint;
	}

	public String getFault() {
		return fault;
	}

	public void setFault(String fault) {
		this.fault = fault;
	}

	public String getCause() {
		return cause;
	}

	public void setCause(String cause) {
		this.cause = cause;
	}

	public String getCarrectiveActionTaken() {
		return carrectiveActionTaken;
	}

	public void setCarrectiveActionTaken(String carrectiveActionTaken) {
		this.carrectiveActionTaken = carrectiveActionTaken;
	}

	public String getCommentObervation() {
		return commentObervation;
	}

	public void setCommentObervation(String commentObervation) {
		this.commentObervation = commentObervation;
	}

	@Override
	public String toString() {
		return "Breakdown [id=" + id + ", breakdownno=" + breakdownno + ", machine=" + machine + ", breakdownType="
				+ breakdownType + ", breakdownDate=" + breakdownDate + ", breakdownTime=" + breakdownTime
				+ ", breakdownSection=" + breakdownSection + ", problem=" + problem + ", priority=" + priority
				+ ", department=" + department + ", watchPoint=" + watchPoint + ", fault=" + fault + ", cause=" + cause
				+ ", carrectiveActionTaken=" + carrectiveActionTaken + ", commentObervation=" + commentObervation
				+ ", reportDateTime=" + reportDateTime + ", doneBy=" + doneBy + ", closeDate=" + closeDate + ", status="
				+ status + ", addedBy=" + addedBy + ", addedDate=" + addedDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", active=" + active + ", deleteBit=" + deleteBit + ", getId()="
				+ getId() + ", getBreakdownno()=" + getBreakdownno() + ", getBreakdownType()=" + getBreakdownType()
				+ ", getBreakdownDate()=" + getBreakdownDate() + ", getBreakdownTime()=" + getBreakdownTime()
				+ ", getBreakdownSection()=" + getBreakdownSection() + ", getProblem()=" + getProblem()
				+ ", getPriority()=" + getPriority() + ", getMachine()=" + getMachine() + ", getReportDateTime()="
				+ getReportDateTime() + ", getDoneBy()=" + getDoneBy() + ", getCloseDate()=" + getCloseDate()
				+ ", getStatus()=" + getStatus() + ", getAddedBy()=" + getAddedBy() + ", getAddedDate()="
				+ getAddedDate() + ", getUpdatedBy()=" + getUpdatedBy() + ", getUpdatedDate()=" + getUpdatedDate()
				+ ", getActive()=" + getActive() + ", getDeleteBit()=" + getDeleteBit() + ", getDepartment()="
				+ getDepartment() + ", getWatchPoint()=" + getWatchPoint() + ", getFault()=" + getFault()
				+ ", getCause()=" + getCause() + ", getCarrectiveActionTaken()=" + getCarrectiveActionTaken()
				+ ", getCommentObervation()=" + getCommentObervation() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

	
}
