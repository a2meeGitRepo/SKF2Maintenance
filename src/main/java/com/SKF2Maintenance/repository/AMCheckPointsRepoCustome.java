package com.SKF2Maintenance.repository;

import java.util.List;

import com.SKF2Maintenance.model.AMCheckPoints;
import com.SKF2Maintenance.model.AMCheckPointsValue;

public interface AMCheckPointsRepoCustome {

	List<AMCheckPoints> getlistAmCheckPointByLimit(int page_no, int item_per_page);

	List<AMCheckPoints> getlistAMCheckPointsByLimitAndSearch(String searchText, int pageNo, int perPage);
	
	// AM Check Points Values 
	
	List<AMCheckPointsValue> getlistAMCheckPointValuesByLimit(int page_no, int item_per_page);

	List<AMCheckPointsValue> getlistAMCheckPointValuesByLimitAndSearch(String searchText, int pageNo, int perPage);

}
