package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.MachineSpindle;

public interface MachineSpindleRepo extends JpaRepository<MachineSpindle, Integer>{
	@Query("From MachineSpindle m where m.machine.machineId=?1")
	List<MachineSpindle> getMappedSpindleByMachine(int machineId);

}
