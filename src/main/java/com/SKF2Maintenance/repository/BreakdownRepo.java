package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SKF2Maintenance.model.Breakdown;
import com.SKF2Maintenance.service.BreakdownService;

public interface BreakdownRepo extends JpaRepository<Breakdown, Integer> ,BreakdownCustomeRepo{
	@Query("select count(*) from Breakdown b where b.deleteBit=0")
	int getBreakdownCount();
	
	@Query("select count(*) from Breakdown b where  (b.breakdownno LIKE :searchText OR  b.machine.machineName LIKE :searchText OR  b.machine.assetNumber LIKE :searchText) and b.deleteBit=0")
	int getBreakdownCountBySearch(@Param("searchText")String searchText);
	
	
	@Query("from Breakdown b where b.status='Open' and b.deleteBit=0")
	List<Breakdown> getAllOpenBreakdown();
	
	@Query("from Breakdown b where b.status='Closed' and b.deleteBit=0")
	List<Breakdown> getAllClosedBreakdown();
	
	@Query("from Breakdown b where b.machine.machineId=?1 and b.status='Closed' and b.deleteBit=0")
	List<Breakdown> getAllClosedBreakdownForUser(String userId);

	@Query("from Breakdown b where b.machine.machineId=?1 and b.deleteBit=0")
	List<Breakdown> getAllBreakdownForMachine(int machineId);
	
	@Query("from Breakdown b where b.machine.machineId=?1 and b.status='Open' and b.deleteBit=0")
	List<Breakdown> getAllOpenBreakdownForMachine(int machineId);
	
	@Query("from Breakdown b where b.machine.machineId=?1  and b.status='Closed' and b.deleteBit=0")
	List<Breakdown> getAllClosedBreakdownForMachine(int machineId);

	

}
