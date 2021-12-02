package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.MachineLocation;

public interface MachineLocationRepo extends JpaRepository<MachineLocation, Integer> {
	@Query("from MachineLocation m where m.machine.machineId=?1")
	List<MachineLocation> getMappedLocationByMachine(int machineId);

}
