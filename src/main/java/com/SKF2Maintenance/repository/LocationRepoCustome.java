package com.SKF2Maintenance.repository;

import java.util.List;

import com.SKF2Maintenance.model.Location;

public interface LocationRepoCustome {
	
	List<Location> getlistLocationByLimit(int page_no, int item_per_page);
	
	List<Location> getlistLocationByLimitAndSearch(String searchText, int pageNo, int perPage);

}
