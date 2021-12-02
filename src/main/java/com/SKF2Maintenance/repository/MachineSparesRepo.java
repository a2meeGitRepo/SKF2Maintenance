package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.MachineSpares;

public interface MachineSparesRepo extends JpaRepository<MachineSpares, Integer>{
	@Query("from MachineSpares m where m.machine.machineId=?1")
	List<MachineSpares> getMappedSparesByMachine(int machineId);

}
