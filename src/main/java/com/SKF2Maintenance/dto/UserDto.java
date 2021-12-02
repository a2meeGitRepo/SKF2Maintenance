package com.SKF2Maintenance.dto;

import com.SKF2Maintenance.model.Branch;


public class UserDto {
 private String fName;
 private String lName;
 private String role;
 private String branch;
 private String profilrUrl;
 private String monileNo;
 private String emailId; 
 private String employeCode;
 private int employeId;
 private String desgn;
 private String dept;
 
/*******************getter and setter******************************/
 
public String getfName() {
	return fName;
}
public void setfName(String fName) {
	this.fName = fName;
}
public String getlName() {
	return lName;
}
public void setlName(String lName) {
	this.lName = lName;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

public String getProfilrUrl() {
	return profilrUrl;
}
public void setProfilrUrl(String profilrUrl) {
	this.profilrUrl = profilrUrl;
}
public String getMonileNo() {
	return monileNo;
}
public void setMonileNo(String monileNo) {
	this.monileNo = monileNo;
}
public String getEmailId() {
	return emailId;
}
public void setEmailId(String emailId) {
	this.emailId = emailId;
}
public String getEmployeCode() {
	return employeCode;
}
public void setEmployeCode(String employeCode) {
	this.employeCode = employeCode;
}
public int getEmployeId() {
	return employeId;
}
public void setEmployeId(int employeId) {
	this.employeId = employeId;
}
public String getDesgn() {
	return desgn;
}
public void setDesgn(String desgn) {
	this.desgn = desgn;
}
public String getBranch() {
	return branch;
}
public void setBranch(String branch) {
	this.branch = branch;
}
public String getDept() {
	return dept;
}
public void setDept(String dept) {
	this.dept = dept;
}


}
