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
@Table(name="change_management_image")
public class ChangeManagementImage {
	

	@Id
	@GeneratedValue
	@Column(name="id")
	private int id;
	
	
	@Column(name="image_type")
	private String imageType;
	
	
	@ManyToOne
	@JoinColumn(name="change_management_id")
	private ChangeManagement changeManagement;
	
	
	@Column(name="image_name")
	private String imageName;
	
	@Column(name="location_url")
	private String locationUrl;
	
	@Column(name="base64_code")
	private String base64Code;
	
	
	

	@Column(name="added_by")
	private String addedBy;
	
	@Column(name="added_date")
	private Date addedDate;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	
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

	public String getImageType() {
		return imageType;
	}

	public void setImageType(String imageType) {
		this.imageType = imageType;
	}

	public ChangeManagement getChangeManagement() {
		return changeManagement;
	}

	public void setChangeManagement(ChangeManagement changeManagement) {
		this.changeManagement = changeManagement;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public String getLocationUrl() {
		return locationUrl;
	}

	public void setLocationUrl(String locationUrl) {
		this.locationUrl = locationUrl;
	}

	public String getAddedBy() {
		return addedBy;
	}

	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
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

	public String getBase64Code() {
		return base64Code;
	}

	public void setBase64Code(String base64Code) {
		this.base64Code = base64Code;
	}
	
	
	
	
	
	
	
}
