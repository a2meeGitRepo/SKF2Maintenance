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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SKF2Maintenance.dto.ResponceObj;
import com.SKF2Maintenance.model.Breakdown;
import com.SKF2Maintenance.model.MachineCheckPoint;
import com.SKF2Maintenance.model.MachineOwner;
import com.SKF2Maintenance.model.Maintenance;
import com.SKF2Maintenance.model.MaintenanceDoneCheckPoint;
import com.SKF2Maintenance.service.BreakdownService;
import com.SKF2Maintenance.service.MachineService;

@RestController
@CrossOrigin("*")
@RequestMapping("/breakdown")
public class BreakdownController {
	
	@Autowired
	MachineService machineService;
	@Autowired
	BreakdownService breakdwonService;
	
	

	@RequestMapping(value = "/updateBreakdown", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceObj> updateBreakdown(@RequestBody Breakdown  breakdown) {
		ResponceObj responceDTO= new ResponceObj();
		try {	
			System.out.println(" Update Breakdown : "+breakdown.toString());
			breakdown.setStatus("Closed");
			breakdwonService.saveBreakdown(breakdown);
			responceDTO.setCode(200);
			responceDTO.setMessage("Breakdown is Updated Successfully");
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		} catch (Exception e) {
			responceDTO.setCode(500);
			responceDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		}
		}
	
	
	@RequestMapping(value = "/getlistBreakdownByLimit/{page_no}/{item_per_page}", method = RequestMethod.GET)
	public @ResponseBody List<Breakdown> getlistBreakdownByLimit(@PathVariable("page_no") int page_no,@PathVariable("item_per_page") int item_per_page) {
		List<Breakdown> list= new  ArrayList<Breakdown>();
		try {	
			list=breakdwonService.getlistBreakdownByLimit(page_no,item_per_page);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//get Maintance By limit Search
	@RequestMapping(value = "/getlistBreakdowntByLimitAndSearch", method = RequestMethod.GET)
	public @ResponseBody List<Breakdown> getlistBreakdowntByLimitAndSearch(@RequestParam("searchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("perPage") int perPage) {
		List<Breakdown> list= new  ArrayList<Breakdown>();
		try {	
			list=breakdwonService.getlistBreakdowntByLimitAndSearch(searchText,pageNo,perPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//Maintance count 
	@RequestMapping(value = "/getBreakdownCount", method = RequestMethod.GET)
	public @ResponseBody int  getBreakdownCount() {
		int  count= 0;
		try {
			count= breakdwonService.getBreakdownCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	//Maintance count and search 
	@RequestMapping(value = "/getBreakdownCountBySearch", method = RequestMethod.GET)
	public @ResponseBody int  getBreakdownCountBySearch(@RequestParam ("searchText") String searchText) {
		int  maintanceCount= 0;
		try {
			maintanceCount= breakdwonService.getBreakdownCountBySearch(searchText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return maintanceCount;
	}
	@RequestMapping(value = "/getAllOpenBreakdown", method = RequestMethod.GET)
	public @ResponseBody List<Breakdown>  getAllOpenBreakdown() {
		List<Breakdown> breakdowns= new ArrayList<Breakdown>();
		try {
			breakdowns= breakdwonService.getAllOpenBreakdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return breakdowns;
	}
	@RequestMapping(value = "/getAllClosedBreakdown", method = RequestMethod.GET)
	public @ResponseBody List<Breakdown>  getAllClosedBreakdown() {
		List<Breakdown> breakdowns= new ArrayList<Breakdown>();
		try {
			breakdowns= breakdwonService.getAllClosedBreakdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return breakdowns;
	}
	
	
	@RequestMapping(value = "/getBreakDownByStatus", method = RequestMethod.GET)
	public @ResponseBody List<Breakdown>  getBreakDownByStatus(@RequestParam("status") String status) {
		List<Breakdown> breakdowns= new ArrayList<Breakdown>();
		try {
			if(status.equalsIgnoreCase("All")){
				breakdowns= breakdwonService.getAllBreakdown();
			}
			if(status.equalsIgnoreCase("Pending")){
				breakdowns= breakdwonService.getAllOpenBreakdown();
			}
			if(status.equalsIgnoreCase("Closed")){
				breakdowns= breakdwonService.getAllClosedBreakdown();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return breakdowns;
	}
	
	
	@RequestMapping(value = "/getAllOpenBreakdownForUser", method = RequestMethod.GET)
	public @ResponseBody List<Breakdown>  getAllOpenBreakdown(@RequestParam ("userId") int userId) {
		List<Breakdown> breakdowns= new ArrayList<Breakdown>();
		try {
			List<MachineOwner> machineOwners= machineService.getAllMachineOwnersByUser(userId);
		
			System.out.println("Machine Qon "+machineOwners.size());

			/*for(MachineOwner machineOwner:machineOwners){
				List<Breakdown> openBreakDowns=breakdwonService.getAllOpenBreakdownForMachine(machineOwner.getMachine().getMachineId());
				for (Breakdown breakdown:openBreakDowns) {
					breakdowns.add(breakdown);
				}
			
			}*/
			breakdowns=breakdwonService.getAllOpenBreakdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return breakdowns;
	}
	@RequestMapping(value = "/getAllClosedBreakdownForUser", method = RequestMethod.GET)
	public @ResponseBody List<Breakdown>  getAllClosedBreakdownForUser(@RequestParam ("userId") int userId) {
		List<Breakdown> breakdowns= new ArrayList<Breakdown>();
		try {
			List<MachineOwner> machineOwners= machineService.getAllMachineOwnersByUser(userId);
			System.out.println("Machine Qon "+machineOwners.size());
			/* for(MachineOwner machineOwner:machineOwners){
					List<Breakdown> openBreakDowns=breakdwonService.getAllClosedBreakdownForMachine(machineOwner.getMachine().getMachineId());		
					for (Breakdown breakdown:openBreakDowns) {
						breakdowns.add(breakdown);
					}
					
				}
		*/
			
			breakdowns=breakdwonService.getAllClosedBreakdown();		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return breakdowns;
	}
	@RequestMapping(value = "/getAllBreakdownForMachine", method = RequestMethod.GET)
	public @ResponseBody List<Breakdown>  getAllBreakdownForMachine(@RequestParam ("machineId") int machineId) {
		List<Breakdown> breakdowns= new ArrayList<Breakdown>();
		try {
			 breakdowns=breakdwonService.getAllBreakdownForMachine(machineId);

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return breakdowns;
	}
	@RequestMapping(value = "/getAllOpenBreakdownForMachine", method = RequestMethod.GET)
	public @ResponseBody List<Breakdown>  getAllOpenBreakdownForMachine(@RequestParam ("machineId") int machineId) {
		List<Breakdown> breakdowns= new ArrayList<Breakdown>();
		try {
			 breakdowns=breakdwonService.getAllOpenBreakdownForMachine(machineId);

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return breakdowns;
	}
	@RequestMapping(value = "/getAllClosedBreakdownForMachine", method = RequestMethod.GET)
	public @ResponseBody List<Breakdown>  getAllClosedBreakdownForMachine(@RequestParam ("machineId") int machineId) {
		List<Breakdown> breakdowns= new ArrayList<Breakdown>();
		try {
			 breakdowns=breakdwonService.getAllClosedBreakdownForMachine(machineId);

		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return breakdowns;
	}
}
