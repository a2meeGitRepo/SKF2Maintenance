package com.SKF2Maintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.BreakdownReport;

public interface BreakdownReportRepo  extends JpaRepository<BreakdownReport, Integer>{
	@Query("from BreakdownReport m where m.machine.machineId=?1 and m.year=?2")
	Optional<BreakdownReport> getBreakdownReportBYMachineAndYear(int machineId, String year);
	@Query("from BreakdownReport m where  m.year=?1")
	List<BreakdownReport> getBreakdownReportByYear(String year);

}
