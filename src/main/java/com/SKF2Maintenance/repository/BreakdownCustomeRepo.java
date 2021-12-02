package com.SKF2Maintenance.repository;

import java.util.List;

import com.SKF2Maintenance.model.Breakdown;

public interface BreakdownCustomeRepo {
	List<Breakdown> getlistBreakdownByLimit(int page_no, int item_per_page);

	List<Breakdown> getlistBreakdowntByLimitAndSearch(String searchText, int pageNo, int perPage);
}
