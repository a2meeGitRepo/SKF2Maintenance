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
@Table(name="am_check_points_value")
public class AMCheckPointsValue {
	
	@Id
	@GeneratedValue
	@Column(name="am_check_points_value_id")
	private int amCheckPointsValueId;
	
	@ManyToOne
	@JoinColumn(name="am_check_points_id")
	private AMCheckPoints amCheckPoints;
	
	@Column(name="check_point_value")
	private String chekPointsValue;
	
	@Column(name="status")
	private String status; 
	
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

	public int getAmCheckPointsValueId() {
		return amCheckPointsValueId;
	}

	public void setAmCheckPointsValueId(int amCheckPointsValueId) {
		this.amCheckPointsValueId = amCheckPointsValueId;
	}
	
	public AMCheckPoints getAmCheckPoints() {
		return amCheckPoints;
	}

	public void setAmCheckPoints(AMCheckPoints amCheckPoints) {
		this.amCheckPoints = amCheckPoints;
	}

	public String getChekPointsValue() {
		return chekPointsValue;
	}

	public void setChekPointsValue(String chekPointsValue) {
		this.chekPointsValue = chekPointsValue;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
	
	

}
