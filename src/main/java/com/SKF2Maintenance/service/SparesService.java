package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import com.SKF2Maintenance.model.Spare;

public interface SparesService {

	Optional<Spare> getSpareById(int id);

	Spare addSpare(Spare Spare);

	List<Spare> getlistSpareByLimit(int page_no, int item_per_page);

	List<Spare> getlistSpareByLimitAndSearch(String searchText, int pageNo, int perPage);

	int getSpareCount();

	int getSpareCountAndSearch(String searchText);

	List<Spare> getAllSpare();

	List<Spare> getAllActiveSpares();

	List<Spare> getSpareByMachine(int machineId);

	Optional<Spare> getSpareBySpareName(String spareName);

}
