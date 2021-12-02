package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import com.SKF2Maintenance.model.Location;

public interface LocationService {

	Optional<Location> getLocationById(int id);

	void addLocation(Location location);

	Location getLocationName(String locationName);

	List<Location> getlistLocationByLimit(int page_no, int item_per_page);

	List<Location> getlistLocationByLimitAndSearch(String searchText, int pageNo, int perPage);

	int getLocationCount();

	int getLocationCountAndSearch(String searchText);

	List<Location> getAllLocation();

	List<Location> getAllActiveLocation();

}
