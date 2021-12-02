package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import com.SKF2Maintenance.model.MachineDrawing;
import com.SKF2Maintenance.model.MachineLocation;
import com.SKF2Maintenance.model.MachineSpares;
import com.SKF2Maintenance.model.MachineSpindle;

public interface MachineMappingService {

	List<MachineSpares> getMappedSparesByMachine(int machineId);

	Optional<MachineSpares> getMachineSparesById(int id);

	void addMmachineSpare(MachineSpares spares);

	Optional<MachineLocation> getMachineLocationById(int id);

	void addMachineLocation(MachineLocation location);

	List<MachineLocation> getMappedLocationByMachine(int machineId);

	Optional<MachineSpindle> getMachineSpindleById(int id);

	void addMachineSpindle(MachineSpindle spindle);

	List<MachineSpindle> getMappedSpindleByMachine(int machineId);

	List<MachineSpares> getMappedSpares();

	List<MachineLocation> getMappedLocations();

	List<MachineSpindle> getAllMappedSpindles();

	List<MachineDrawing> getMachineDrawingByMachine(int machineId);

	List<MachineDrawing> getMachineDrawings();

	void addMachineDrawing(MachineDrawing drawing);

	List<MachineDrawing> getMappedSetupChartByMachine(int machineId);


}
