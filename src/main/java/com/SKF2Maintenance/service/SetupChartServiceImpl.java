package com.SKF2Maintenance.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.SetupChart;
import com.SKF2Maintenance.repository.SetupChartRepo;

@Service
public class SetupChartServiceImpl implements SetupChartService {
	@Autowired
	SetupChartRepo setupChartRepo;

	@Override
	public void saveSetupchart(SetupChart chart) {
		// TODO Auto-generated method stub
		setupChartRepo.save(chart);
	}

}
