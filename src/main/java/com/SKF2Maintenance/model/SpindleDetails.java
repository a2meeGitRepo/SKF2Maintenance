package com.SKF2Maintenance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="spindle_details")
public class SpindleDetails {

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	@Column(name="spindle_name")
	private String spindleName;
	
	@Column(name="spindle_type")
	private String spindleType;
	
	@Column(name="spindle_details")
	private String spindleDetails;
	
	@Column(name="spares_info")
	private String sparesInfo;
	

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public String getSpindleName() {
		return spindleName;
	}

	public void setSpindleName(String spindleName) {
		this.spindleName = spindleName;
	}

	public String getSpindleType() {
		return spindleType;
	}

	public void setSpindleType(String spindleType) {
		this.spindleType = spindleType;
	}

	public String getSpindleDetails() {
		return spindleDetails;
	}

	public void setSpindleDetails(String spindleDetails) {
		this.spindleDetails = spindleDetails;
	}

	public String getSparesInfo() {
		return sparesInfo;
	}

	public void setSparesInfo(String sparesInfo) {
		this.sparesInfo = sparesInfo;
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

	public void setActive(int active) {
		this.active = active;
	}

	public int getDeleteBit() {
		return deleteBit;
	}

	public void setDeleteBit(int deleteBit) {
		this.deleteBit = deleteBit;
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
	
	
}
