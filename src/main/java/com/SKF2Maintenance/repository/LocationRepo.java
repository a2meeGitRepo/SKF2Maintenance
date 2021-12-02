package com.SKF2Maintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SKF2Maintenance.model.Location;

public interface LocationRepo extends JpaRepository<Location, Integer>,LocationRepoCustome {

	@Query("from Location l where l.locationName=?1")
	Optional<Location> getBrandByName(String locationName);

	@Query("select count(l)from Location l where l.deleteBit=0")
	long getLocationCount();

	@Query("select count(*)from Location l where l.locationName LIKE %:searchText% OR  l.locationCode LIKE %:searchText% and  l.deleteBit=0")
	int getLocationCountAndSerach(@Param("searchText")String searchText);

	@Query("from Location l where l.deleteBit=0 and l.active=1")
	List<Location> getAllActiveLocation();

}
