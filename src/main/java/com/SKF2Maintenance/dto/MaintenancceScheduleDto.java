package com.SKF2Maintenance.dto;

import java.util.Date;
import java.util.List;

import com.SKF2Maintenance.model.Machine;

public class MaintenancceScheduleDto {
	List<Date> dates;
	private String frequency;
	private String createdBy;
	private Machine machine;
	public List<Date> getDates() {
		return dates;
	}
	public void setDates(List<Date> dates) {
		this.dates = dates;
	}
	public String getFrequency() {
		return frequency;
	}
	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	
	
	
	
	
	

}
