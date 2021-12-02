package com.SKF2Maintenance.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SKF2Maintenance.model.AMCheckPoints;

public interface AmCheckPointsRepo extends JpaRepository<AMCheckPoints, Integer>,AMCheckPointsRepoCustome {

	@Query("select count(a)from AMCheckPoints a where a.deleteBit=0")
	long getAMCheckPointsCount();
	
	@Query("select count(*)from AMCheckPoints a where a.item LIKE %:searchText%    and  a.deleteBit=0")
	int getAMCheckPointsCountAndSearch(@Param("searchText") String searchText);
	
	@Query("from AMCheckPoints a where a.machine.assetNumber=?1 and a.frequency='Weekly'")
	List<AMCheckPoints> weeklyAMCheckPointByMachineCode(String code);

	@Query("from AMCheckPoints a where a.machine.assetNumber=?1 and a.frequency='Quaterly' ")
	List<AMCheckPoints> quatorlyAMCheckPointByMachineCode(String code);

	@Query("from AMCheckPoints a where a.machine.assetNumber=?1 and a.frequency='Yearly'")
	List<AMCheckPoints> yearlyAMCheckPointByMachineCode(String code);

	@Query("from AMCheckPoints a where a.machine.assetNumber=?1 and a.frequency='Half Yearly'")
	List<AMCheckPoints> halfYearlyAMCheckPointByMachineCode(String code);
	
	
	@Query("from AMCheckPoints a where a.machine.assetNumber=?1 and a.frequency='Daily'")
	List<AMCheckPoints> dailyAMCheckPointByMachineCode(String code);

	@Query("from AMCheckPoints a where a.deleteBit=0 and a.machine.machineId=?1")
	List<AMCheckPoints> getAMCheckPointByMachine(int machineId);

}
