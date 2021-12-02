package com.SKF2Maintenance.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SKF2Maintenance.model.MachineOwner;

public interface MachineOwnerRepo extends JpaRepository<MachineOwner,Integer>,MachineOwnerCustomeRepo{

	@Query("select count(m)from MachineOwner m where m.deleteBit=0")
	long getMachineOwnerCount();

	@Query("select count(*)from MachineOwner m where m.machine.machineName LIKE %:searchText% OR  m.machine.assetNumber LIKE %:searchText% and  m.deleteBit=0")
	int getMachineOwnerCountAndSearch(@Param("searchText")String searchText);

	@Query("from MachineOwner m where m.user.user_id=?1")
	List<MachineOwner> getAllMachineOwnersByUser(int userId);
	@Query("from MachineOwner m where m.deleteBit=0")
	List<MachineOwner> getAllMachineOwner();
	@Query("from MachineOwner m where m.machine.machineId=?1")
	List<MachineOwner> getMachineOwnersByMachine(int machieId);



}
