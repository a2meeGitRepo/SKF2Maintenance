/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author lenovo
 *
 */
@Entity
@Table(name="employee_mst")
public class Employee {
	@Id
	@GeneratedValue
	@Column(name="employee_id")
	private int employeeId;
	
	@Column(name="emoloyee_code")
	private String emoloyeeCode;
	
	@Column(name="fName")
	private String fName;
	
	@Column(name="lName")
	private String lName;
	
	
	@Column(name="email_id")
	private String emailId;
	
	
	@Column(name="contactNo")
	private String contactNo;
	
	
	@Column(name="gender")
	private String gender;
	
	
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private Branch branch;
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@ManyToOne
	@JoinColumn(name="designation_id")
	private Designation designation;
	
	/*@Column(name="date_of_birth")
	private Date dateOfBirth;*/
	
	@Column(name="date_of_birth")
	private Date dateOfBirth;
	
	@Column(name="date_of_joining")
	private Date dateOfJoining;
		
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name="title")
	private String title;
	
	
	@Column(name="work_location")
	private String workLocation;
	

	@Column(name="manager_id")
	private int managerId;
	

	@Column(name="cost_center")
	private String costCenter;
	

	@Column(name="business_unit")
	private String businessUnit;
	

	@Column(name="subsidiary")
	private String subsidiary;
	

	@Column(name="desk_no")
	private String deskNo;
	
	

	@Column(name="user_created")
	private int userCreated;
	
	@Column(name="active")
	private int active;
	
	@Column(name="added_date")
	private Date addedDate;
	
	@Column(name="upd_datetime")
	private Date updDatetime;
	
	@Column(name="added_by")
	private String addedBy;

	
	@Column(name="uhf_code")
	private String uhfCode;
	
	@Column(name="profile_img_url")
	private String profileImgUrl;
	
	@Column(name="profile_img_name")
	private String profileImgName;
	/**
	 * @return the employeeId
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the emoloyeeCode
	 */
	public String getEmoloyeeCode() {
		return emoloyeeCode;
	}

	/**
	 * @param emoloyeeCode the emoloyeeCode to set
	 */
	public void setEmoloyeeCode(String emoloyeeCode) {
		this.emoloyeeCode = emoloyeeCode;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * @return the emailId
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId the emailId to set
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the contactNo
	 */
	public String getContactNo() {
		return contactNo;
	}

	/**
	 * @param contactNo the contactNo to set
	 */
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * @return the department
	 */
	public Department getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(Department department) {
		this.department = department;
	}

	/**
	 * @return the designation
	 */
	public Designation getDesignation() {
		return designation;
	}

	/**
	 * @param designation the designation to set
	 */
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}



	/**
	 * @return the dateOfJoining
	 */
	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	/**
	 * @param dateOfJoining the dateOfJoining to set
	 */
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the workLocation
	 */
	public String getWorkLocation() {
		return workLocation;
	}

	/**
	 * @param workLocation the workLocation to set
	 */
	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	/**
	 * @return the managerId
	 */
	public int getManagerId() {
		return managerId;
	}

	/**
	 * @param managerId the managerId to set
	 */
	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	/**
	 * @return the costCenter
	 */
	public String getCostCenter() {
		return costCenter;
	}

	/**
	 * @param costCenter the costCenter to set
	 */
	public void setCostCenter(String costCenter) {
		this.costCenter = costCenter;
	}

	/**
	 * @return the businessUnit
	 */
	public String getBusinessUnit() {
		return businessUnit;
	}

	/**
	 * @param businessUnit the businessUnit to set
	 */
	public void setBusinessUnit(String businessUnit) {
		this.businessUnit = businessUnit;
	}

	/**
	 * @return the subsidiary
	 */
	public String getSubsidiary() {
		return subsidiary;
	}

	/**
	 * @param subsidiary the subsidiary to set
	 */
	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}

	/**
	 * @return the deskNo
	 */
	public String getDeskNo() {
		return deskNo;
	}

	/**
	 * @param deskNo the deskNo to set
	 */
	public void setDeskNo(String deskNo) {
		this.deskNo = deskNo;
	}

	/**
	 * @return the userCreated
	 */
	public int getUserCreated() {
		return userCreated;
	}

	/**
	 * @param userCreated the userCreated to set
	 */
	public void setUserCreated(int userCreated) {
		this.userCreated = userCreated;
	}

	/**
	 * @return the active
	 */
	public int getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(int active) {
		this.active = active;
	}


	/**
	 * @return the addedDate
	 */
	public Date getAddedDate() {
		return addedDate;
	}

	/**
	 * @param addedDate the addedDate to set
	 */
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	/**
	 * @return the addedBy
	 */
	public String getAddedBy() {
		return addedBy;
	}

	/**
	 * @param addedBy the addedBy to set
	 */
	public void setAddedBy(String addedBy) {
		this.addedBy = addedBy;
	}

	/**
	 * @return the uhfCode
	 */
	public String getUhfCode() {
		return uhfCode;
	}

	/**
	 * @param uhfCode the uhfCode to set
	 */
	public void setUhfCode(String uhfCode) {
		this.uhfCode = uhfCode;
	}

	public String getProfileImgUrl() {
		return profileImgUrl;
	}

	public void setProfileImgUrl(String profileImgUrl) {
		this.profileImgUrl = profileImgUrl;
	}

	public String getProfileImgName() {
		return profileImgName;
	}

	public void setProfileImgName(String profileImgName) {
		this.profileImgName = profileImgName;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", managerId=" + managerId + "]";
	}

	/**
	 * @return the updDatetime
	 */
	public Date getUpdDatetime() {
		return updDatetime;
	}

	/**
	 * @param updDatetime the updDatetime to set
	 */
	public void setUpdDatetime(Date updDatetime) {
		this.updDatetime = updDatetime;
	}
	
	
	
	
	
	
}
