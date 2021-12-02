package com.SKF2Maintenance.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
	public String getMachineImages() {
		return machineImages;
	}

	public void setMachineImages(String machineImages) {
		this.machineImages = machineImages;
	}

	private String uploadDir;
	private String cceEmail;
	private String uploadInvoice;
	private String machineImages;
    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

	public String getCceEmail() {
		return cceEmail;
	}

	public void setCceEmail(String cceEmail) {
		this.cceEmail = cceEmail;
	}

	public String getUploadInvoice() {
		return uploadInvoice;
	}

	public void setUploadInvoice(String uploadInvoice) {
		this.uploadInvoice = uploadInvoice;
	}
    
    
}
