package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import com.SKF2Maintenance.model.BreakdownReport;
import com.SKF2Maintenance.model.DowntimeReport;
import com.SKF2Maintenance.model.MTBFReport;
import com.SKF2Maintenance.model.MTTRReport;

public interface ReportServices {

	Optional<MTBFReport> getMTBFReportBYMachineAndYear(int machineId, String year);

	void saveMTBFReport(MTBFReport mtbfReport);

	Optional<MTTRReport> getMTTRReportBYMachineAndYear(int machineId, String year);

	void saveMTTRReport(MTTRReport mtbfReport);

	Optional<DowntimeReport> getDowntimeReportBYMachineAndYear(int machineId, String year);

	void saveDowntimeReport(DowntimeReport mtbfReport);

	Optional<BreakdownReport> getBreakdownReportBYMachineAndYear(int machineId, String year);

	void saveBreakdownReport(BreakdownReport mtbfReport);

	List<MTBFReport> getMTBFReportByYear(String year);

	List<MTTRReport> getMTTRReportByYear(String year);

	List<DowntimeReport> getDowntimeReportByYear(String year);

	List<BreakdownReport> getBreakdownReportByYear(String year);

}
