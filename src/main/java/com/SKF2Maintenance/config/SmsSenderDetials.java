package com.SKF2Maintenance.config;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="sms_sender")
public class SmsSenderDetials {



@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name = "id")
private int id;
 @Column(name = "api_key")
 private String apiKey;
 
 @Column(name = "secret_key")
 private String secretKey;
 
 @Column(name = "use_type")
 private String useType;
 
 @Column(name = "sender_id")
 private String senderId;
 
 @Column(name = "url")
 private String url;
 
 @Column(name = "active")
 private int active;
 
 @Column(name = "added_date")
 private Date addedDate;
 
 @Column(name = "upd_datetime")
 private Date updDatetime;
 
 @Column(name = "upd_by")
 private String updBy;

 
 @Column(name = "added_by")
 private String addedBy;


public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getApiKey() {
	return apiKey;
}

public void setApiKey(String apiKey) {
	this.apiKey = apiKey;
}

public String getSecretKey() {
	return secretKey;
}

public void setSecretKey(String secretKey) {
	this.secretKey = secretKey;
}

public String getUseType() {
	return useType;
}

public void setUseType(String useType) {
	this.useType = useType;
}

public String getSenderId() {
	return senderId;
}

public void setSenderId(String senderId) {
	this.senderId = senderId;
}

public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
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

public Date getUpdDatetime() {
	return updDatetime;
}

public void setUpdDatetime(Date updDatetime) {
	this.updDatetime = updDatetime;
}

public String getUpdBy() {
	return updBy;
}

public void setUpdBy(String updBy) {
	this.updBy = updBy;
}

public String getAddedBy() {
	return addedBy;
}

public void setAddedBy(String addedBy) {
	this.addedBy = addedBy;
}

 
 
}
