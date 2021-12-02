package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.model.SpindleDetails;

public interface SpindleDetailsService {

	Optional<SpindleDetails> getSpindleById(int id);

	SpindleDetails addSpindle(SpindleDetails spindleDetails);

	List<SpindleDetails> getlistSpindleByLimit(int page_no, int item_per_page);

	List<SpindleDetails> getlistSpindleByLimitAndSearch(String searchText, int pageNo, int perPage);

	int getSpindleCount();

	int getSpindleCountAndSearch(String searchText);

	List<SpindleDetails> getAllSpindle();

	List<SpindleDetails> getAllActiveSpindle();

	List<SpindleDetails> getSpindleByMmachine(int machineId);
}
