package com.SKF2Maintenance.repository;

import java.util.List;

import com.SKF2Maintenance.model.Machine;

public interface MachineRepoCustome {

	List<Machine> getListMachineByLimitAndSerach(String searchText, int pageNo, int perPage);
	
	List<Machine> getlistMachineByLimit(int page_no, int item_per_page);
}
