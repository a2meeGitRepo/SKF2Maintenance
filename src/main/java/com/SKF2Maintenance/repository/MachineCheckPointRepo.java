package com.SKF2Maintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SKF2Maintenance.model.MachineCheckPoint;

public interface MachineCheckPointRepo extends JpaRepository<MachineCheckPoint, Integer>,MachineCheckPointCustomeRepo {
	@Query("Select count(*) from MachineCheckPoint m where m.deleteBit=0")
	int getMachienChekPointCount();
	
	@Query("from MachineCheckPoint m where m.deleteBit=0 and m.machine.machineId=?1 and m.checkPointName=?2")
	Optional<MachineCheckPoint> getMachineCheckPointByMachineIdAndCheckPointId(int machineId, String checkPointName);
	@Query("select count(*)from MachineCheckPoint m where (m.checkPointName LIKE :searchText OR m.machine.machineName LIKE :searchText OR  m.machine.assetNumber LIKE :searchText) and m.deleteBit=0")
	int getMachienChekPointCountBySearch(@Param("searchText")String searchText);
	@Query("from MachineCheckPoint m where m.deleteBit=0 and m.machine.machineId=?1")
	List<MachineCheckPoint> getMachineCheckPointByMachine(int machineId);

	

}
