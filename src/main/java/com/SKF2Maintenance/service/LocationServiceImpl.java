package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.SKF2Maintenance.model.Location;
import com.SKF2Maintenance.repository.LocationRepo;

@Transactional
@Service
public class LocationServiceImpl implements LocationService {

	@Autowired
	private LocationRepo locationRepo;

	  
	public Optional<Location> getLocationById(int id) {
		return locationRepo.findById(id);
	}

	  
	public void addLocation(Location location) {
		locationRepo.save(location);
	}

	  
	public Location getLocationName(String locationName) {
		Optional<Location> optional = locationRepo.getBrandByName(locationName);
		return optional.isPresent()?optional.get():null;
	}

	  
	public List<Location> getlistLocationByLimit(int page_no, int item_per_page) {
		
		return locationRepo.getlistLocationByLimit(page_no,item_per_page);
	}

	  
	public List<Location> getlistLocationByLimitAndSearch(String searchText, int pageNo, int perPage) {
		
		return locationRepo.getlistLocationByLimitAndSearch(searchText,pageNo,perPage);
	}

	  
	public int getLocationCount() {
		
		return (int)locationRepo.getLocationCount();
	}

	  
	public int getLocationCountAndSearch(String searchText) {
		
		return locationRepo.getLocationCountAndSerach(searchText);
	}

	  
	public List<Location> getAllLocation() {
		
		return locationRepo.findAll();
	}


	public List<Location> getAllActiveLocation() {
		// TODO Auto-generated method stub
		return locationRepo.getAllActiveLocation();
	}
	
}
