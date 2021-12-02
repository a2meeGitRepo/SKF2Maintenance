package com.SKF2Maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.Breakdown;
import com.SKF2Maintenance.repository.BreakdownRepo;

@Service
public class BreakdownServiceImpl implements BreakdownService {
	@Autowired
	BreakdownRepo breakdownRepo;

	public List<Breakdown> getlistBreakdownByLimit(int page_no, int item_per_page) {
		// TODO Auto-generated method stub
		return breakdownRepo.getlistBreakdownByLimit(page_no,item_per_page);
	}

	public List<Breakdown> getlistBreakdowntByLimitAndSearch(String searchText, int pageNo, int perPage) {
		// TODO Auto-generated method stub
		return breakdownRepo.getlistBreakdowntByLimitAndSearch(searchText,pageNo,perPage);
	}

	public int getBreakdownCount() {
		// TODO Auto-generated method stub
		return breakdownRepo.getBreakdownCount();
	}

	public int getBreakdownCountBySearch(String searchText) {
		// TODO Auto-generated method stub
		return breakdownRepo.getBreakdownCountBySearch(searchText);
	}

	public List<Breakdown> getAllOpenBreakdown() {
		// TODO Auto-generated method stub
		return breakdownRepo.getAllOpenBreakdown();
	}

	public List<Breakdown> getAllClosedBreakdown() {
		// TODO Auto-generated method stub
		return breakdownRepo.getAllClosedBreakdown();
	}


	public List<Breakdown> getAllClosedBreakdownForUser(String userId) {
		// TODO Auto-generated method stub
		return breakdownRepo.getAllClosedBreakdownForUser(userId);
	}

	public List<Breakdown> getAllBreakdownForMachine(int machineId) {
		// TODO Auto-generated method stub
		return breakdownRepo.getAllBreakdownForMachine(machineId);
	}

	public List<Breakdown> getAllOpenBreakdownForMachine(int machineId) {
		// TODO Auto-generated method stub
		return breakdownRepo.getAllOpenBreakdownForMachine(machineId);
	}

	public List<Breakdown> getAllClosedBreakdownForMachine(int machineId) {
		// TODO Auto-generated method stub
		return breakdownRepo.getAllClosedBreakdownForMachine(machineId);
	}

	public void saveBreakdown(Breakdown breakdown) {
		// TODO Auto-generated method stub
		breakdownRepo.save(breakdown);
	}

	@Override
	public List<Breakdown> getAllBreakdown() {
		// TODO Auto-generated method stub
		return breakdownRepo.findAll();
	}

	@Override
	public List<Breakdown> getOpenBreakDownByMachine(int machineId) {
		// TODO Auto-generated method stub
		return breakdownRepo.getAllOpenBreakdownForMachine(machineId);
	}

}
