package com.SKF2Maintenance.dto;

import java.util.List;

import javax.persistence.Transient;


public class ImageUploadDto {
	
	
	private int changeManagementId;
	private String addedBy;
	private List<ImageCode> images;
	
	public List<ImageCode> getImages() {
		return images;
	}
	public void setImages(List<ImageCode> images) {
		this.images = images;
	}
	public int getChangeManagementId() {
		return changeManagementId;
	}
	public void setChangeManagementId(int changeManagementId) {
		this.changeManagementId = changeManagementId;
	}
	public String getAddedBy() {
		return addedBy;
	}
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}
	
	




    
  

    
    
}
