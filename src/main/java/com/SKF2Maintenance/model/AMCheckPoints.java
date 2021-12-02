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
@Table(name="am_check_points")
public class AMCheckPoints {
	
	@Id
	@GeneratedValue
	@Column(name="am_check_point_id")
	private int amCheckPointId;
	
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine machine;
	
	@Column(name="item")
	private String item;
	
	@Column(name="standard")
	private String satandard;
	
	
	@Column(name="tool")
	private String tool;
	
	@Column(name="frequency")
	private String frequency;
	
	@Column(name="min_time")
	private String minTime;
	
	@Column(name="responsibility")
	private String responsibility;
	
	@Column(name="clean")
	private boolean clean;
	@Column(name="lubricate")
	private boolean lubricate;
	@Column(name="inspect")
	private boolean inspect;
	@Column(name="tight")
	private boolean tight;
	
	
	@Column(name="added_date")
	private Date addedDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="added_by")
	private int addedBy;
	
	@Column(name="updated_by")
	private int updatedBy;
	
	@Column(name="active")
	private int active;
	
	@Column(name="delete_bit")
	private int deleteBit;

	public int getAmCheckPointId() {
		return amCheckPointId;
	}

	public void setAmCheckPointId(int amCheckPointId) {
		this.amCheckPointId = amCheckPointId;
	}
	
	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getSatandard() {
		return satandard;
	}

	public void setSatandard(String satandard) {
		this.satandard = satandard;
	}



	public String getTool() {
		return tool;
	}

	public void setTool(String tool) {
		this.tool = tool;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
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
	
	public int getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(int addedBy) {
		this.addedBy = addedBy;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
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

	public String getMinTime() {
		return minTime;
	}

	public void setMinTime(String minTime) {
		this.minTime = minTime;
	}

	public String getResponsibility() {
		return responsibility;
	}

	public void setResponsibility(String responsibility) {
		this.responsibility = responsibility;
	}

	public boolean isClean() {
		return clean;
	}

	public void setClean(boolean clean) {
		this.clean = clean;
	}

	public boolean isLubricate() {
		return lubricate;
	}

	public void setLubricate(boolean lubricate) {
		this.lubricate = lubricate;
	}

	public boolean isInspect() {
		return inspect;
	}

	public void setInspect(boolean inspect) {
		this.inspect = inspect;
	}

	public boolean isTight() {
		return tight;
	}

	public void setTight(boolean tight) {
		this.tight = tight;
	}
	
	
	
}
