package com.SKF2Maintenance.service;

import java.util.List;

import com.SKF2Maintenance.model.Breakdown;

public interface BreakdownService {

	List<Breakdown> getlistBreakdownByLimit(int page_no, int item_per_page);

	List<Breakdown> getlistBreakdowntByLimitAndSearch(String searchText, int pageNo, int perPage);

	int getBreakdownCount();

	int getBreakdownCountBySearch(String searchText);

	List<Breakdown> getAllOpenBreakdown();

	List<Breakdown> getAllClosedBreakdown();


	List<Breakdown> getAllClosedBreakdownForUser(String userId);

	List<Breakdown> getAllBreakdownForMachine(int machineId);

	List<Breakdown> getAllOpenBreakdownForMachine(int machineId);

	List<Breakdown> getAllClosedBreakdownForMachine(int machineId);

	void saveBreakdown(Breakdown breakdown);

	List<Breakdown> getAllBreakdown();

	List<Breakdown> getOpenBreakDownByMachine(int machineId);

}
