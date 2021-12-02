package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.BreakdownReport;
import com.SKF2Maintenance.model.DowntimeReport;
import com.SKF2Maintenance.model.MTBFReport;
import com.SKF2Maintenance.model.MTTRReport;
import com.SKF2Maintenance.repository.BreakdownReportRepo;
import com.SKF2Maintenance.repository.DowntimeReportRepo;
import com.SKF2Maintenance.repository.MTBFReportRepo;
import com.SKF2Maintenance.repository.MTTRReportRepo;

@Service
public class ReportServicesImpl  implements ReportServices{
	@Autowired
	MTBFReportRepo mTBFReportRepo;
	
	@Autowired
	MTTRReportRepo mTTRReportRepo;
	
	@Autowired
	DowntimeReportRepo downtimeReportRepo;
	
	@Autowired
	BreakdownReportRepo breakdownReportRepo;

	@Override
	public Optional<MTBFReport> getMTBFReportBYMachineAndYear(int machineId, String year) {
		// TODO Auto-generated method stub
		return mTBFReportRepo.getMTBFReportBYMachineAndYear(machineId,year);
	}

	@Override
	public void saveMTBFReport(MTBFReport mtbfReport) {
		// TODO Auto-generated method stub
		mTBFReportRepo.save(mtbfReport);
	}

	@Override
	public Optional<MTTRReport> getMTTRReportBYMachineAndYear(int machineId, String year) {
		// TODO Auto-generated method stub
		return mTTRReportRepo.getMTTRReportBYMachineAndYear(machineId,year);
	}

	@Override
	public void saveMTTRReport(MTTRReport mtbfReport) {
		// TODO Auto-generated method stub
		mTTRReportRepo.save(mtbfReport);
	}

	@Override
	public Optional<DowntimeReport> getDowntimeReportBYMachineAndYear(int machineId, String year) {
		// TODO Auto-generated method stub
		return downtimeReportRepo.getDowntimeReportBYMachineAndYear(machineId,year);
	}

	@Override
	public void saveDowntimeReport(DowntimeReport mtbfReport) {
		// TODO Auto-generated method stub
		downtimeReportRepo.save(mtbfReport);
	}

	@Override
	public Optional<BreakdownReport> getBreakdownReportBYMachineAndYear(int machineId, String year) {
		// TODO Auto-generated method stub
		return breakdownReportRepo.getBreakdownReportBYMachineAndYear(machineId,year);
	}

	@Override
	public void saveBreakdownReport(BreakdownReport mtbfReport) {
		// TODO Auto-generated method stub
		breakdownReportRepo.save(mtbfReport);
	}

	@Override
	public List<MTBFReport> getMTBFReportByYear(String year) {
		// TODO Auto-generated method stub
		return mTBFReportRepo.getMTBFReportByYear(year);
	}

	@Override
	public List<MTTRReport> getMTTRReportByYear(String year) {
		// TODO Auto-generated method stub
		return mTTRReportRepo.getMTTRReportByYear(year);
	}

	@Override
	public List<DowntimeReport> getDowntimeReportByYear(String year) {
		// TODO Auto-generated method stub
		return downtimeReportRepo.getDowntimeReportByYear(year);
	}

	@Override
	public List<BreakdownReport> getBreakdownReportByYear(String year) {
		// TODO Auto-generated method stub
		return breakdownReportRepo.getBreakdownReportByYear(year);
	}

}
