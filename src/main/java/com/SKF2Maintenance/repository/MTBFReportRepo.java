package com.SKF2Maintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.MTBFReport;

public interface MTBFReportRepo extends JpaRepository<MTBFReport, Integer>{
	@Query("from MTBFReport m where m.machine.machineId=?1 and m.year=?2")
	Optional<MTBFReport> getMTBFReportBYMachineAndYear(int machineId, String year);
	@Query("from MTBFReport m where  m.year=?1")
	List<MTBFReport> getMTBFReportByYear(String year);

}
