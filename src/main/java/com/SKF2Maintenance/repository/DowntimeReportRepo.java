package com.SKF2Maintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.DowntimeReport;

public interface DowntimeReportRepo extends JpaRepository<DowntimeReport, Integer>{
	@Query("from DowntimeReport m where m.machine.machineId=?1 and m.year=?2")
	Optional<DowntimeReport> getDowntimeReportBYMachineAndYear(int machineId, String year);
	@Query("from DowntimeReport m where  m.year=?1")
	List<DowntimeReport> getDowntimeReportByYear(String year);

}
