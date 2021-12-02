package com.SKF2Maintenance.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SKF2Maintenance.model.MachineDrawing;
import com.SKF2Maintenance.model.SetupChart;
import com.SKF2Maintenance.repository.SetupChartRepo;

@RestController
@CrossOrigin("*")
@RequestMapping("/setupchart")
public class SetupChartController {
	
	@Autowired
	SetupChartRepo setupChartRepo;
	@RequestMapping(value = "/getSetupChartTablesByMachine", method = RequestMethod.GET)
	public @ResponseBody Set<String> getSetupChartTablesByMachine(@RequestParam("machineId") int machineId) {
		Set<String> list = new HashSet<String>();
		try {
			List<SetupChart> list2 = setupChartRepo.getSetupChartByMachine(machineId);
			for(SetupChart setupChart:list2){
				list.add(setupChart.getHeadernName());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	@RequestMapping(value = "/getSetupChartByMachine", method = RequestMethod.GET)
	public @ResponseBody List<SetupChart> getSetupChartByMachine(@RequestParam("machineId") int machineId) {
		List<SetupChart> list = new ArrayList<SetupChart>();
		try {
			list = setupChartRepo.getSetupChartByMachine(machineId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@RequestMapping(value = "/getSetupChartByMachineAndTable", method = RequestMethod.GET)
	public @ResponseBody List<SetupChart> getSetupChartByMachineAndTable(@RequestParam("machineId") int machineId,@RequestParam("table") String table) {
		List<SetupChart> list = new ArrayList<SetupChart>();
		try {
			System.out.println("Table  "+table);
			list = setupChartRepo.getSetupChartByMachineAndTable(machineId,table);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
