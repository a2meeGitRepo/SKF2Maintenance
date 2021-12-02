package com.SKF2Maintenance.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SKF2Maintenance.dto.MaintenancceScheduleDto;
import com.SKF2Maintenance.dto.ResponceObj;
import com.SKF2Maintenance.model.Breakdown;
import com.SKF2Maintenance.model.MachineCheckPoint;
import com.SKF2Maintenance.model.MachineOwner;
import com.SKF2Maintenance.model.Maintenance;
import com.SKF2Maintenance.model.MaintenanceDoneCheckPoint;
import com.SKF2Maintenance.service.MachineService;
import com.SKF2Maintenance.service.MaintenanceServcie;

@RestController
@CrossOrigin("*")
@RequestMapping("/maintenance")
public class MaintenanceController {
	
	@Autowired
	MaintenanceServcie maintenanceServcie;
	@Autowired
	MachineService machineService;
	
	@RequestMapping(value = "/scheduleMaintenance", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceObj> scheduleMaintenance(@RequestBody MaintenancceScheduleDto  maintenancceScheduleDto) {
		ResponceObj responceDTO= new ResponceObj();
		try {		
			for (Date date:maintenancceScheduleDto.getDates()){
				System.out.println("Date :: "+date);
				
				Maintenance   maintenance= new Maintenance();
				maintenance.setActive(1);
				
				maintenance.setCreatedBy(maintenancceScheduleDto.getCreatedBy());
				maintenance.setCreatedDate(new Date());
				maintenance.setDeleteBit(0);
				maintenance.setFrequency(maintenancceScheduleDto.getFrequency());
				maintenance.setMachine(maintenancceScheduleDto.getMachine());
				maintenance.setSheduleDate(date);
				maintenance.setStatus("Open");
				maintenanceServcie.addMaintenance(maintenance);
			}
			System.out.println("Frequency  :; "+maintenancceScheduleDto.getFrequency());
			System.out.println("Created   :; "+maintenancceScheduleDto.getCreatedBy());
			System.out.println("Machine   :; "+maintenancceScheduleDto.getMachine().getMachineId());
			responceDTO.setCode(200);
			responceDTO.setMessage("Maintenance is Schedule Successfully");
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		} catch (Exception e) {
			responceDTO.setCode(500);
			responceDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		}
		}
	
	@RequestMapping(value = "/updateMaintenance", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceObj> updateMaintenance(@RequestBody Maintenance  maintenance) {
		ResponceObj responceDTO= new ResponceObj();
		try {	
			System.out.println("Update Maintenance ");
			maintenance.setStatus("Closed");
			maintenanceServcie.addMaintenance(maintenance);
			/*if(maintenance.getMaintenanceCheckPoints().size()!=0){
		
			for(MaintenanceDoneCheckPoint  maintenanceCheckPoint:maintenance.getMaintenanceCheckPoints()) {
				maintenanceCheckPoint.setAddedDate(new Date());
				maintenanceCheckPoint.setMaintenance(maintenance);
				maintenanceServcie.addMaintenanceDoneCheckPoint(maintenanceCheckPoint);	
			}
			}*/
			responceDTO.setCode(200);
			responceDTO.setMessage("Maintenance is Updated Successfully");
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		} catch (Exception e) {
			e.printStackTrace();
			responceDTO.setCode(500);
			responceDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		}
		}

	@RequestMapping(value = "/getTodayMaintenanceByMachine", method = RequestMethod.GET)
	public @ResponseBody List<Maintenance> getTodayMaintenanceByMachine(@RequestParam("machineId") int machineId){
		List<Maintenance> maintenance= new ArrayList<Maintenance>();
		maintenance=maintenanceServcie.getTodayMaintenanceByMachine(machineId);
		return maintenance;
	}
	
	@RequestMapping(value = "/getThisWeekMaintenanceDownByMachine", method = RequestMethod.GET)
	public @ResponseBody List<Maintenance> getThisWeekMaintenanceDownByMachine(@RequestParam("machineId") int machineId){
		List<Maintenance> maintenance= new ArrayList<Maintenance>();
		maintenance=maintenanceServcie.getThisWeekMaintenanceDownByMachine(machineId);
		return maintenance;
	}
	
	@RequestMapping(value = "/getMaintenanceDoneCheckPointByMaintenance", method = RequestMethod.GET)
	public @ResponseBody List<MaintenanceDoneCheckPoint> getMaintenanceDoneCheckPointByMaintenance(@RequestParam("maintenanceId") int maintenanceId){
		List<MaintenanceDoneCheckPoint> maintenancecheckPoint= new ArrayList<MaintenanceDoneCheckPoint>();
		maintenancecheckPoint=maintenanceServcie.getMaintenanceDoneCheckPointByMaintenance(maintenanceId);
		return maintenancecheckPoint;
	}
	
	@RequestMapping(value = "/getAllPendingMaintenanceForUser", method = RequestMethod.GET)
	public @ResponseBody List<Maintenance>  getAllPendingMaintenanceForUser(@RequestParam ("userId") int userId) {
		List<Maintenance> maintenances= new ArrayList<Maintenance>();
		try {
			List<MachineOwner> machineOwners= machineService.getAllMachineOwnersByUser(userId);
			
		//	System.out.println("MACHINE OWNER "+machineOwners.get(0).getMachine().getMachineId());
			/*for(MachineOwner machineOwner:machineOwners){
				List<Maintenance> pendingMaintenance=maintenanceServcie.getAllPendingMaintenanceForMachine(machineOwner.getMachine().getMachineId());
				
				System.out.println("Maintanace :: "+pendingMaintenance.size());
				for (Maintenance maintenance:pendingMaintenance) {
					maintenances.add(maintenance);
				}
				
			}*/
			 maintenances=maintenanceServcie.getAllPendingMaintenance();


		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maintenances;
	}
	@RequestMapping(value = "/getAllClosedMaintenanceForUser", method = RequestMethod.GET)
	public @ResponseBody List<Maintenance>  getAllCloseMaintenanceForUser(@RequestParam ("userId") int userId) {
		List<Maintenance> maintenances= new ArrayList<Maintenance>();
		try {
			List<MachineOwner> machineOwners= machineService.getAllMachineOwnersByUser(userId);
			for(MachineOwner machineOwner:machineOwners){
				List<Maintenance> pendingMaintenance=maintenanceServcie.getAllCloseMaintenanceForUser(machineOwner.getMachine().getMachineId());
				for (Maintenance maintenance:pendingMaintenance) {
					maintenances.add(maintenance);
				}
				
			}

			maintenances=maintenanceServcie.getAllCloseMaintenance();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return maintenances;
	}
	@RequestMapping(value = "/getThisWeekMaintenanceByUser", method = RequestMethod.GET)
	public @ResponseBody List<Maintenance>  getThisWeekMaintenanceDownByUser(@RequestParam ("userId") int userId) {
		List<Maintenance> maintenances= new ArrayList<Maintenance>();
		try {
			List<MachineOwner> machineOwners= machineService.getAllMachineOwnersByUser(userId);
			/*for(MachineOwner machineOwner:machineOwners){
				List<Maintenance> pendingMaintenance=maintenanceServcie.getThisWeekMaintenanceDownByMachine(machineOwner.getMachine().getMachineId());
				for (Maintenance maintenance:pendingMaintenance) {
					maintenances.add(maintenance);
				}
				
			}*/
			maintenances=maintenanceServcie.getThisWeekMaintenance();

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maintenances;
	}
	
	@RequestMapping(value = "/getTodayMaintenanceByUser", method = RequestMethod.GET)
	public @ResponseBody List<Maintenance>  getTodayMaintenanceByUser(@RequestParam ("userId") int userId) {
		List<Maintenance> maintenances= new ArrayList<Maintenance>();
		try {
			List<MachineOwner> machineOwners= machineService.getAllMachineOwnersByUser(userId);
		/*	for(MachineOwner machineOwner:machineOwners){
				List<Maintenance> pendingMaintenance=maintenanceServcie.getTodayMaintenanceByMachine(machineOwner.getMachine().getMachineId());
				for (Maintenance maintenance:pendingMaintenance) {
					maintenances.add(maintenance);
				}
				
			}*/
			maintenances=maintenanceServcie.getTodayMaintenance();

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maintenances;
	}
	
	@RequestMapping(value = "/getMaintenanceByStatus", method = RequestMethod.GET)
	public @ResponseBody List<Maintenance> getMaintenanceByStatus(@RequestParam("status") String status){
		List<Maintenance> maintenances= new ArrayList<Maintenance>();
	
		
		if(status.equalsIgnoreCase("All")){
			maintenances=maintenanceServcie.getAllMaintenance();

		}
		if(status.equalsIgnoreCase("Pending")){
			maintenances=maintenanceServcie.getPendingMaintenance();

		}
		if(status.equalsIgnoreCase("Closed")){
			maintenances=maintenanceServcie.getClosedMaintenance();

		}
		
		return maintenances;
	}
	
	@RequestMapping(value = "/getMachineCheckPointByMachine", method = RequestMethod.GET)
	public @ResponseBody List<MachineCheckPoint> getMachineCheckPointByMachine(@RequestParam("machineId") int machineId){
		List<MachineCheckPoint> maintenancecheckPoint= new ArrayList<MachineCheckPoint>();
		maintenancecheckPoint=maintenanceServcie.getMachineCheckPointByMachine(machineId);
		return maintenancecheckPoint;
	}
}
