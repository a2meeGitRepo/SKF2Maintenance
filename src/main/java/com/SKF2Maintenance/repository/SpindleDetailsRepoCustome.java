package com.SKF2Maintenance.repository;

import java.util.List;

import com.SKF2Maintenance.model.SpindleDetails;

public interface SpindleDetailsRepoCustome {
	
	List<SpindleDetails> getListSpindleByLimit(int page_no, int item_per_page);
	
	List<SpindleDetails> getListSpindleByLimitAndSerach(String searchText, int pageNo, int perPage);
}
