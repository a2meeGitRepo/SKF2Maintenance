package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.AuditStages;

public interface AuditStagesRepo extends JpaRepository<AuditStages, Integer>{
	@Query("from AuditStages a where a.machine.machineId=?1")
	List<AuditStages> getAuditStagesByMachine(int machineId);

}
