package com.SKF2Maintenance.repository;

import java.util.List;

import com.SKF2Maintenance.model.MachineOwner;

public interface MachineOwnerCustomeRepo {

	List<MachineOwner> getListMachineOwnerByLimitAndSearch(String searchText, int pageNo, int perPage);
	
	List<MachineOwner> getlistMachineOwnerByLimit(int page_no, int item_per_page);
}
