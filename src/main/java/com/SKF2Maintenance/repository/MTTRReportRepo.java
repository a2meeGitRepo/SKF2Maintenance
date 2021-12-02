package com.SKF2Maintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.MTTRReport;

public interface MTTRReportRepo  extends JpaRepository<MTTRReport, Integer>{
	@Query("from MTTRReport m where m.machine.machineId=?1 and m.year=?2")
	Optional<MTTRReport> getMTTRReportBYMachineAndYear(int machineId, String year);
	@Query("from MTTRReport m where  m.year=?1")
	List<MTTRReport> getMTTRReportByYear(String year);

}
