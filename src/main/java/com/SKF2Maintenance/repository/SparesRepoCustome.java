package com.SKF2Maintenance.repository;

import java.util.List;

import com.SKF2Maintenance.model.Spare;

public interface SparesRepoCustome {

	List<Spare> getListSpareByLimit(int page_no, int item_per_page);
	
	List<Spare> getListSpareByLimitAndSearch(String searchText, int pageNo, int perPage);
	
}
