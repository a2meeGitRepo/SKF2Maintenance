package com.SKF2Maintenance.repository;

import java.util.List;

import com.SKF2Maintenance.model.MachineCheckPoint;

public interface MachineCheckPointCustomeRepo {

	List<MachineCheckPoint> getlistMaintanceCheckPointListByLimitAndSearch(String searchText, int pageNo, int perPage);

	List<MachineCheckPoint> getlistMaintanceCheckPointByLimit(int page_no, int item_per_page);
}
