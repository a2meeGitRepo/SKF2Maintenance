package com.SKF2Maintenance.repository;



import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SKF2Maintenance.model.Machine;


public interface MachineRepo extends JpaRepository<Machine, Integer>,MachineRepoCustome{

	@Query("select count(m)from Machine m where m.deleteBit=0")
	int countUndelet();

	@Query("select count(*)from Machine m where m.machineName LIKE %:searchText% OR  m.assetNumber LIKE %:searchText% and  m.deleteBit=0")
	int getMachineCountAndSerach(@Param("searchText")String searchText);
	@Query("from Machine m where m.assetNumber=?1")
	Optional<Machine> getMachineByAssetNumber(String assetNumber);
	@Query("from Machine m where m.deleteBit=0")
	List<Machine> getAllMachines();
	@Query("from Machine m where m.deleteBit=0 and m.machineName=?1")
	Optional<Machine> getMachineByName(String machineName);
	@Query("from Machine m where m.chennelNo is not null")
	List<Machine> getAllMachinesChannelNotNull();
	
	@Query("from Machine m where m.chennelNo=?1")
	List<Machine> getAllMachineByChannerNo(String channelNo);
	@Query("from Machine m where m.machineName=?1")
	List<Machine> getMachinesByame(String machineName);

	
	
}
