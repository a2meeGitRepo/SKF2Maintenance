package com.SKF2Maintenance.dto;
import java.util.List;

import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.model.MachineOwner;
import com.SKF2Maintenance.model.Spare;
import com.SKF2Maintenance.model.SpindleDetails;

public class MachineInfo {

	private Machine  machine;
	
	private List<Spare> Spare;
	
	private List<SpindleDetails> spindle;



	public List<Spare> getSpare() {
		return Spare;
	}

	public void setSpare(List<Spare> Spare) {
		this.Spare = Spare;
	}

	public List<SpindleDetails> getSpindle() {
		return spindle;
	}

	public void setSpindle(List<SpindleDetails> spindle) {
		this.spindle = spindle;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}
	

}
