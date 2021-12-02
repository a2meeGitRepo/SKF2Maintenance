package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.SetupChart;

public interface SetupChartRepo extends JpaRepository<SetupChart, Integer>{
	@Query("from SetupChart s where s.machine.machineId=?1")
	List<SetupChart> getSetupChartByMachine(int machineId);
	@Query("from SetupChart s where s.machine.machineId=?1 and s.headernName=?2")
	List<SetupChart> getSetupChartByMachineAndTable(int machineId, String table);

}
