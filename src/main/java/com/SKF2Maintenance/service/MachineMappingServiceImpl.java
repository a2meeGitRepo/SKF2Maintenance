package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.MachineDrawing;
import com.SKF2Maintenance.model.MachineLocation;
import com.SKF2Maintenance.model.MachineSpares;
import com.SKF2Maintenance.model.MachineSpindle;
import com.SKF2Maintenance.repository.MachineDrawingRepo;
import com.SKF2Maintenance.repository.MachineLocationRepo;
import com.SKF2Maintenance.repository.MachineSparesRepo;
import com.SKF2Maintenance.repository.MachineSpindleRepo;

@Service
public class MachineMappingServiceImpl implements MachineMappingService{
	
	@Autowired
	MachineSparesRepo machineSparesRepo;
	
	@Autowired
	MachineLocationRepo machineLocationRepo;
	@Autowired
	MachineSpindleRepo machineSpindleRepo;
	@Autowired
	MachineDrawingRepo machineDrawingRepo;

	public List<MachineSpares> getMappedSparesByMachine(int machineId) {
		// TODO Auto-generated method stub
		return machineSparesRepo.getMappedSparesByMachine(machineId);
	}

	public Optional<MachineSpares> getMachineSparesById(int id) {
		// TODO Auto-generated method stub
		return machineSparesRepo.findById(id);
	}

	public void addMmachineSpare(MachineSpares spares) {
		// TODO Auto-generated method stub
		machineSparesRepo.save(spares);
	}

	@Override
	public Optional<MachineLocation> getMachineLocationById(int id) {
		// TODO Auto-generated method stub
		return machineLocationRepo.findById(id);
	}

	@Override
	public void addMachineLocation(MachineLocation location) {
		// TODO Auto-generated method stub
		machineLocationRepo.save(location);
	}

	@Override
	public List<MachineLocation> getMappedLocationByMachine(int machineId) {
		// TODO Auto-generated method stub
		return machineLocationRepo.getMappedLocationByMachine(machineId);
	}

	@Override
	public Optional<MachineSpindle> getMachineSpindleById(int id) {
		// TODO Auto-generated method stub
		return machineSpindleRepo.findById(id);
	}

	@Override
	public void addMachineSpindle(MachineSpindle spindle) {
		// TODO Auto-generated method stub
		machineSpindleRepo.save(spindle);
	}

	@Override
	public List<MachineSpindle> getMappedSpindleByMachine(int machineId) {
		// TODO Auto-generated method stub
		return machineSpindleRepo.getMappedSpindleByMachine(machineId);
	}

	@Override
	public List<MachineSpares> getMappedSpares() {
		// TODO Auto-generated method stub
		return machineSparesRepo.findAll();
	}

	@Override
	public List<MachineLocation> getMappedLocations() {
		// TODO Auto-generated method stub
		return machineLocationRepo.findAll();
	}

	@Override
	public List<MachineSpindle> getAllMappedSpindles() {
		// TODO Auto-generated method stub
		return machineSpindleRepo.findAll();
	}

	@Override
	public List<MachineDrawing> getMachineDrawingByMachine(int machineId) {
		// TODO Auto-generated method stub
		return machineDrawingRepo.getMachineDrawingByMachine(machineId);
	}

	@Override
	public List<MachineDrawing> getMachineDrawings() {
		// TODO Auto-generated method stub
		return machineDrawingRepo.findAll();
	}

	@Override
	public void addMachineDrawing(MachineDrawing drawing) {
		// TODO Auto-generated method stub
		machineDrawingRepo.save(drawing);
	}

	@Override
	public List<MachineDrawing> getMappedSetupChartByMachine(int machineId) {
		// TODO Auto-generated method stub
		return machineDrawingRepo.getMachineSetupChartByMachine(machineId);
	}

	

}
