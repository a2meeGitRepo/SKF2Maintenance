package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.MaintenanceDoneCheckPoint;

public interface MaintenanceDoneCheckPointRepo extends JpaRepository<MaintenanceDoneCheckPoint, Integer>{
	@Query("from MaintenanceDoneCheckPoint m where m.maintenance.id=?1")
	List<MaintenanceDoneCheckPoint> getMaintenanceDoneCheckPointByMaintenance(int maintenanceId);

}
