package com.SKF2Maintenance.dto;

import java.util.List;

import com.SKF2Maintenance.model.AMCheckPoints;
import com.SKF2Maintenance.model.Breakdown;
import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.model.Maintenance;

public class QRResponceDetials {
	private Machine  machine; 
	private List<Maintenance> todayMaintenances;
	private List<Maintenance> thisWeekmaintenances;
	private List<Breakdown> breakdowns;
	private List<AMCheckPoints> checkPoints;
	public Machine getMachine() {
		return machine;
	}
	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public List<Breakdown> getBreakdowns() {
		return breakdowns;
	}
	public void setBreakdowns(List<Breakdown> breakdowns) {
		this.breakdowns = breakdowns;
	}
	public List<AMCheckPoints> getCheckPoints() {
		return checkPoints;
	}
	public void setCheckPoints(List<AMCheckPoints> checkPoints) {
		this.checkPoints = checkPoints;
	}
	public List<Maintenance> getTodayMaintenances() {
		return todayMaintenances;
	}
	public void setTodayMaintenances(List<Maintenance> todayMaintenances) {
		this.todayMaintenances = todayMaintenances;
	}
	public List<Maintenance> getThisWeekmaintenances() {
		return thisWeekmaintenances;
	}
	public void setThisWeekmaintenances(List<Maintenance> thisWeekmaintenances) {
		this.thisWeekmaintenances = thisWeekmaintenances;
	}
	
	
	

}
