package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.Drawing;

public interface DrawingRepo extends JpaRepository<Drawing, Integer> {
	@Query("From Drawing d where d.fileType='Drawing'")
	List<Drawing> findAllDrawing();
	@Query("From Drawing d where d.fileType='Setup Chart'")
	List<Drawing> findAllSetupChart();

}
