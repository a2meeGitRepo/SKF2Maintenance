package com.SKF2Maintenance.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SKF2Maintenance.model.AMCheckPointsValue;

public interface AMCheckPointsValueRepo extends JpaRepository<AMCheckPointsValue, Integer>,AMCheckPointsRepoCustome{

	@Query("select count(a)from AMCheckPointsValue a where a.deleteBit=0")
	int getAMCheckPointValuesCount();
	
	@Query("select count(*)from AMCheckPointsValue a where a.chekPointsValue LIKE %:searchText% OR  a.status LIKE %:searchText% and  a.deleteBit=0")
	int getAMCheckPointValuesCountAndSearch(@Param("searchText")String searchText);
	
	
	@Query("from AMCheckPointsValue a where a.deleteBit=0 and a.amCheckPoints.amCheckPointId=?1 and Date(a.addedDate)>=?2 and Date(a.addedDate)<=?3")
	Optional<AMCheckPointsValue> getThisweekAMCheckPointValueByAMCheckPoint(int amCheckPointId, Date time, Date time2);
	@Query("from AMCheckPointsValue a where a.deleteBit=0 and a.amCheckPoints.amCheckPointId=?1 and Date(a.addedDate)>=?2 and Date(a.addedDate)<=?3")
	Optional<AMCheckPointsValue> getMonthyAMCheckPointValueByAMCheckPoint(int amCheckPointId, Date time, Date time2);
	@Query("from AMCheckPointsValue a where a.deleteBit=0 and a.amCheckPoints.amCheckPointId=?1 and Date(a.addedDate)=?2")
	Optional<AMCheckPointsValue> getTodayAMCheckPointValueByAMCheckPoint(int amCheckPointId, Date date);

}
