package com.SKF2Maintenance.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.Maintenance;

public interface MaintenanceRepo extends JpaRepository<Maintenance, Integer>{
	@Query("from Maintenance m where m.machine.machineId=?1 and Date(m.sheduleDate)>=?2 and Date(m.sheduleDate)<=?3 and m.status='Open'")
	List<Maintenance> getThisWeekMaintenanceDownByMachine(int machineId, Date time, Date time2);
	@Query("from Maintenance m where m.machine.machineId=?1 and Date(m.sheduleDate)=?2 and m.status='Open'")
	List<Maintenance> getTodayMaintenanceByMachine(int machineId, Date date);
	
	@Query("from Maintenance m where m.machine.machineId=?1 and m.status='Open'")
	List<Maintenance> getAllPendingMaintenanceForMachine(int machineId);

	@Query("from Maintenance m where m.machine.machineId=?1 and m.status='Closed'")
	List<Maintenance> getAllCloseMaintenanceForUser(int machineId);
	@Query("from Maintenance m where m.status='Open'")
	List<Maintenance> getPendingMaintenance();
	@Query("from Maintenance m where m.status='Closed'")
	List<Maintenance> getClosedMaintenance();
	
	
	@Query("from Maintenance m where m.machine.machineId=?1 and m.status='Closed' and m.maintenanceDoneBy=?2")
	List<Maintenance> getDoneMaintenanceDownByMachine(int machineId, String userId);
	@Query("from Maintenance m where  m.status='Open'")
	List<Maintenance> getAllPendingMaintenance();
	@Query("from Maintenance m where  m.status='Closed'")
	List<Maintenance> getAllCloseMaintenance();
	@Query("from Maintenance m where  Date(m.sheduleDate)>=?2 and Date(m.sheduleDate)<=?3 and m.status='Open'")
	List<Maintenance> getThisWeekMaintenance(Date time, Date time2);
	@Query("from Maintenance m where  Date(m.sheduleDate)=?2 and m.status='Open'")
	List<Maintenance> getTodayMaintenance(Date date);

}
