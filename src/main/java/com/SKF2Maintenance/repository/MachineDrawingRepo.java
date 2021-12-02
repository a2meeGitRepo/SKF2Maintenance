package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.MachineDrawing;

public interface MachineDrawingRepo extends JpaRepository<MachineDrawing, Integer>{
	@Query("from MachineDrawing m where m.machine.machineId=?1 and drawing.fileType='Drawing'")
	List<MachineDrawing> getMachineDrawingByMachine(int machineId);
	@Query("from MachineDrawing m where m.machine.machineId=?1 and drawing.fileType='Setup Chart'")
	List<MachineDrawing> getMachineSetupChartByMachine(int machineId);

}
