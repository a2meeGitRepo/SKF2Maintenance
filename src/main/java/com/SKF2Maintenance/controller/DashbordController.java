package com.SKF2Maintenance.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SKF2Maintenance.dto.DashbordCount;
import com.SKF2Maintenance.dto.MachineInfo;
import com.SKF2Maintenance.dto.QRResponceDetials;
import com.SKF2Maintenance.dto.ResponceObj;
import com.SKF2Maintenance.model.AMCheckPoints;
import com.SKF2Maintenance.model.Breakdown;
import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.model.MachineOwner;
import com.SKF2Maintenance.model.Maintenance;
import com.SKF2Maintenance.model.Spare;
import com.SKF2Maintenance.model.SpindleDetails;
import com.SKF2Maintenance.service.AMCheckPointsService;
import com.SKF2Maintenance.service.BreakdownService;
import com.SKF2Maintenance.service.MachineService;
import com.SKF2Maintenance.service.MaintenanceServcie;
import com.SKF2Maintenance.service.SparesService;
import com.SKF2Maintenance.service.SpindleDetailsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/dashbord")
public class DashbordController {
	
	
	
	@Autowired
	private MaintenanceServcie maintenanceService;
	
	@Autowired
	private MachineService machineService;
	
	@Autowired
	private SparesService SpareService;
	
	@Autowired
	private SpindleDetailsService spindleDetailsService;
	@Autowired
	private BreakdownService breakdownService;
	@Autowired
	private AMCheckPointsService aMCheckPointsService; 
	@Autowired
	BreakdownService breakdwonService;
	


	@GetMapping(value = "/countDashboard", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DashbordCount dashbordCount(@RequestParam("userId") int userId) {
		DashbordCount cDashboard = new DashbordCount();
		try {
			int todayCount=0;
			int thisWeekCount=0;
			int doneCount=0;
			
			List<MachineOwner> machineOwner= machineService.getAllMachineOwnersByUser(userId);
			//System.out.println(machineOwner);
			for(MachineOwner newowner:machineOwner){
				List<Maintenance> todayMaintenance= maintenanceService.getTodayMaintenanceByMachine(newowner.getMachine().getMachineId());

				List<Maintenance> thisWeekMaintenance= maintenanceService.getThisWeekMaintenanceDownByMachine(newowner.getMachine().getMachineId());
				
				List<Maintenance> doneMaintenance= maintenanceService.getDoneMaintenanceDownByMachine(newowner.getMachine().getMachineId(),String.valueOf(userId));
				
				todayCount+=todayMaintenance.size();
				thisWeekCount+=thisWeekMaintenance.size();
				doneCount+=doneMaintenance.size();
			}
			cDashboard.setDoneCount(doneCount);
			cDashboard.setThisWeekcount(thisWeekCount);
			cDashboard.setTodayCount(todayCount);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDashboard;
	}
	@GetMapping(value = "/countBreakCount", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody DashbordCount countBreakCount(@RequestParam("userId") int userId) {
		DashbordCount cDashboard = new DashbordCount();
		try {
			
			List<MachineOwner> machineOwners= machineService.getAllMachineOwnersByUser(userId);
			List<Breakdown> openbreakdowns= new ArrayList<Breakdown>();
			List<Breakdown> closedreakdowns= new ArrayList<Breakdown>();

			for(MachineOwner machineOwner:machineOwners){
				List<Breakdown> openBreakDowns=breakdwonService.getAllOpenBreakdownForMachine(machineOwner.getMachine().getMachineId());
				System.out.println("OPEN "+openBreakDowns.size());

				for (Breakdown breakdown:openBreakDowns) {
					openbreakdowns.add(breakdown);
				}
				
			}
			cDashboard.setOpenBreakdon(openbreakdowns.size());
			System.out.println("machineOwners "+machineOwners.size());

			 for(MachineOwner machineOwner:machineOwners){
					List<Breakdown> openBreakDowns=breakdwonService.getAllClosedBreakdownForMachine(machineOwner.getMachine().getMachineId());				
					System.out.println("Closed "+openBreakDowns.size());

					for (Breakdown breakdown:openBreakDowns) {
						closedreakdowns.add(breakdown);
					}
					
				}

			 cDashboard.setClosedBreakdon(closedreakdowns.size());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cDashboard;
	}
	@GetMapping(value = "/getMachineInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody MachineInfo getMachineInfo(@RequestParam("assetNumber") String assetNumber) {
		MachineInfo machineInfo=new MachineInfo();
		try {
			
			Machine machine= machineService.getMachineAssetNumber(assetNumber);
			machineInfo.setMachine(machine);
			List<Spare> Spare=SpareService.getSpareByMachine(machine.getMachineId());
			List<SpindleDetails> spindleDetails=spindleDetailsService.getSpindleByMmachine(machine.getMachineId());
			machineInfo.setSpare(Spare);
			machineInfo.setSpindle(spindleDetails);
			
		} catch (Exception e) {
			e.printStackTrace();
				
		}
		return machineInfo;
	}
	@GetMapping(value = "/getQRCodeDetialsResponce", produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponceObj getQRCodeDetialsResponce(@RequestParam("assetNumber") String assetNumber) {
		ResponceObj responceObj=new ResponceObj();
		try {
			QRResponceDetials detials = new QRResponceDetials();
			Machine  machine= machineService.getMachineAssetNumber(assetNumber);
			if(machine!=null){
				responceObj.setCode(200);
				responceObj.setMessage("Valid QrCode");
				detials.setMachine(machine);
				List<Breakdown> breakdowns=breakdownService.getOpenBreakDownByMachine(machine.getMachineId());
				List<Maintenance> thisWeek=maintenanceService.getThisWeekMaintenanceDownByMachine(machine.getMachineId());
				List<Maintenance> today=maintenanceService.getTodayMaintenanceByMachine(machine.getMachineId());
				List<AMCheckPoints> amCheckPoints=aMCheckPointsService.getPendingAmCheckPointByMachineId(machine.getMachineId());
				detials.setBreakdowns(breakdowns);
				detials.setThisWeekmaintenances(thisWeek);
				detials.setTodayMaintenances(today);
				detials.setCheckPoints(amCheckPoints);
				responceObj.setData(detials);
			}else{
				responceObj.setCode(500);
				responceObj.setMessage("Invalid QrCode");
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
				
		}
		return responceObj;
	}
	
}
