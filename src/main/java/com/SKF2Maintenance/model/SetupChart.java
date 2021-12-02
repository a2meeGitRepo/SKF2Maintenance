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
@Table(name="setup_chart")
public class SetupChart {
	
	
	@Id
	@GeneratedValue
	@Column(name="setup_chart_id")
	private int setupChartId;
	
	@ManyToOne
	@JoinColumn(name="machine_id")
	private Machine machine;
	
	@Column(name="name")
	private String name;
	
	@Column(name="channel")
	private String channel;
	
	@Column(name="type")
	private String type;
	
	@Column(name="header_name")
	private String headernName;
	
	@Column(name="sr_value")
	private String srValue;
	
	@Column(name="value")
	private String value;
	
	
	@Column(name="setup_description")
	private String setupDescription;
	
	
	@Column(name="unit")
	private String unit;
	
	
	@Column(name="tol")
	private String tol;
	
	@Column(name="added_date")
	private Date addedDate;
	
	@Column(name="active")
	private int active;
	
	@Column(name="upd_date")
	private Date updDate;

	public int getSetupChartId() {
		return setupChartId;
	}

	public void setSetupChartId(int setupChartId) {
		this.setupChartId = setupChartId;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHeadernName() {
		return headernName;
	}

	public void setHeadernName(String headernName) {
		this.headernName = headernName;
	}

	public String getSrValue() {
		return srValue;
	}

	public void setSrValue(String srValue) {
		this.srValue = srValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getSetupDescription() {
		return setupDescription;
	}

	public void setSetupDescription(String setupDescription) {
		this.setupDescription = setupDescription;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getTol() {
		return tol;
	}

	public void setTol(String tol) {
		this.tol = tol;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Date getUpdDate() {
		return updDate;
	}

	public void setUpdDate(Date updDate) {
		this.updDate = updDate;
	}
	
	
	
	
	

}
