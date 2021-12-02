package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import com.SKF2Maintenance.model.AMCheckPoints;
import com.SKF2Maintenance.model.AMCheckPointsValue;

public interface AMCheckPointsService {

	Optional<AMCheckPoints> getAmCheckPointById(int amCheckPointId);

	void addAmCheckPoints(AMCheckPoints amCheckPoints);
	
    //Pagination
	
	List<AMCheckPoints> getlistAmCheckPointByLimit(int page_no, int item_per_page);

	List<AMCheckPoints> getlistAMCheckPointsByLimitAndSearch(String searchText, int pageNo, int perPage);

	int getAMCheckPointsCount();

	int getAMCheckPointsCountAndSearch(String searchText);

	List<AMCheckPoints> getAllAMCheckPoints();

	Optional<AMCheckPointsValue> getAmCheckPointValueById(int amCheckPointsValueId);

	void addAmCheckPointsValue(AMCheckPointsValue amCheckPointsValue);

	List<AMCheckPointsValue> getlistAMCheckPointValuesByLimit(int page_no, int item_per_page);

	List<AMCheckPointsValue> getlistAMCheckPointValuesByLimitAndSearch(String searchText, int pageNo, int perPage);

	int getAMCheckPointValuesCountAndSearch(String searchText);

	int getAMCheckPointValuesCount();

	List<AMCheckPoints> dailyAMCheckPointByMachineCode(String code);

	List<AMCheckPoints> quatorlyAMCheckPointByMachineCode(String code);

	List<AMCheckPoints> halfYearlyAMCheckPointByMachineCode(String code);

	List<AMCheckPoints> yearlyAMCheckPointByMachineCode(String code);

	List<AMCheckPoints> weeklyAMCheckPointByMachineCode(String code);

	List<AMCheckPoints> getPendingAmCheckPointByMachineId(int machineId);

}
