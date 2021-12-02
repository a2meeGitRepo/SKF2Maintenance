package com.SKF2Maintenance.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.SKF2Maintenance.dto.ResponceObject;
import com.SKF2Maintenance.model.AMCheckPoints;
import com.SKF2Maintenance.model.AMCheckPointsValue;
import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.service.AMCheckPointsService;
import com.SKF2Maintenance.service.MachineService;

@RestController
@CrossOrigin("*")
@RequestMapping("/amCheckPoints")
public class AMCheckPointsController {
	
	@Autowired
	private AMCheckPointsService amCheckPointService;
	
	@Autowired
	private MachineService machineService;
	
	/*
	  *************************** AM Check Points ********************************
	*/
	
	//add Am Check Points
			@RequestMapping(value = "/addAmCheckPoints", method = RequestMethod.POST)
			public ResponseEntity<ResponceObj> addAmCheckPoints(@RequestBody AMCheckPoints  amCheckPoints) {
				ResponceObj responceDTO= new ResponceObj();
				try {		
					Optional<AMCheckPoints> optional=amCheckPointService.getAmCheckPointById(amCheckPoints.getAmCheckPointId());
					if(optional.isPresent()) {
						amCheckPoints.setUpdatedBy(amCheckPoints.getAddedBy());
						amCheckPoints.setUpdatedDate(new Date());
						responceDTO.setCode(200);
						responceDTO.setMessage("AM Check Points is Updated Successfully");
					}else {
						amCheckPoints.setAddedDate(new Date());
						responceDTO.setCode(200);
						responceDTO.setMessage("AM Check Points is Added Successfully");
					}
					amCheckPointService.addAmCheckPoints(amCheckPoints);
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				} catch (Exception e) {
					responceDTO.setCode(500);
					responceDTO.setMessage(e.getMessage());
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				}
				}	
			
			//delete Am Check Points
			@RequestMapping(value="/deleteAmCheckPoints",method=RequestMethod.POST)
			public @ResponseBody ResponceObject deleteById(@RequestBody AMCheckPoints amCheckPoints) {
				ResponceObject resObject=new ResponceObject();
				Optional<AMCheckPoints> optional=amCheckPointService.getAmCheckPointById(amCheckPoints.getAmCheckPointId());
				try {
					if(optional.isPresent()) {
						AMCheckPoints newAMCheckPoints=optional.get();
						newAMCheckPoints.setDeleteBit(1);
						amCheckPointService.addAmCheckPoints(newAMCheckPoints);
						resObject.setCode(200);
						resObject.setMsg("Am Check Points is Delete Successfully");
					}else {
						resObject.setMsg("Am Check Points Id not Found");
					}
				}catch(Exception e) {
					System.out.println(e.toString());
					resObject.setCode(500);
					resObject.setMsg("Something Wrong");
				}
				return resObject;
			}
			
			// Am Check Points change Status 
			@RequestMapping(value="/AmCheckPointsChangeStatus",method=RequestMethod.POST)
			public @ResponseBody ResponceObject changeStatus(@RequestBody AMCheckPoints amCheckPoints) {
				ResponceObject responceObject=new ResponceObject();
				try {
					if(amCheckPoints.getActive()==1){
						amCheckPoints.setActive(0);
					}else{
						amCheckPoints.setActive(1);
					}
						amCheckPointService.addAmCheckPoints(amCheckPoints);
						 responceObject.setCode(200);
						 responceObject.setMsg("Status Change Successfully");	
				}catch(Exception e) {
					 e.printStackTrace();
					 responceObject.setCode(500);
					 responceObject.setMsg("Something wrong");
				}
				return responceObject;
			}
			
			//get All AmCheckPoints
			@RequestMapping(value = "/getAllAMCheckPoints", method = RequestMethod.GET)
			public @ResponseBody List<AMCheckPoints> getAllAMCheckPoints() {
				List<AMCheckPoints> list= new  ArrayList<AMCheckPoints>();
				try {	
					list=amCheckPointService.getAllAMCheckPoints();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			
			/*
			  *************************** AM Check Points Value ********************************
			*/
			
			//add Am Check Points value
			@RequestMapping(value = "/addAmCheckPointsValue", method = RequestMethod.POST)
			public ResponseEntity<ResponceObj> addAmCheckPointsValue(@RequestBody AMCheckPointsValue  amCheckPointsValue) {
				ResponceObj responceDTO= new ResponceObj();
				try {		
					Optional<AMCheckPointsValue> optional=amCheckPointService.getAmCheckPointValueById(amCheckPointsValue.getAmCheckPointsValueId());
					if(optional.isPresent()) {
						amCheckPointsValue.setUpdatedBy(amCheckPointsValue.getAddedBy());
						amCheckPointsValue.setUpdatedDate(new Date());
						responceDTO.setCode(200);
						responceDTO.setMessage("AM Check Points Value is Updated Successfully");
					}else {
						amCheckPointsValue.setAddedDate(new Date());
						responceDTO.setCode(200);
						responceDTO.setMessage("AM Check Points Value is Added Successfully");
					}
					amCheckPointService.addAmCheckPointsValue(amCheckPointsValue);
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				} catch (Exception e) {
					responceDTO.setCode(500);
					responceDTO.setMessage(e.getMessage());
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				}
				}	

			//delete Am Check Points Value
			@RequestMapping(value="/deleteAmCheckPointsValue",method=RequestMethod.POST)
			public @ResponseBody ResponceObject amCPvdeleteById(@RequestBody AMCheckPointsValue amCheckPointsValue) {
				ResponceObject resObject=new ResponceObject();
				Optional<AMCheckPointsValue> optional=amCheckPointService.getAmCheckPointValueById(amCheckPointsValue.getAmCheckPointsValueId());
				try {
					if(optional.isPresent()) {
						AMCheckPointsValue newAMCheckPointsValue=optional.get();
						newAMCheckPointsValue.setDeleteBit(1);
						amCheckPointService.addAmCheckPointsValue(newAMCheckPointsValue);
						resObject.setCode(200);
						resObject.setMsg("Am Check Points Value is Delete Successfully");
					}else {
						resObject.setMsg("Am Check Points value Id not Found");
					}
				}catch(Exception e) {
					System.out.println(e.toString());
					resObject.setCode(500);
					resObject.setMsg("Something Wrong");
				}
				return resObject;
			}
			
			// Am Check Points Value change Status 
			@RequestMapping(value="/AmCheckPointsValueChangeStatus",method=RequestMethod.POST)
			public @ResponseBody ResponceObject amCPVchangeStatus(@RequestBody AMCheckPointsValue amCheckPointsValue) {
				ResponceObject responceObject=new ResponceObject();
				Optional<AMCheckPointsValue> optional=amCheckPointService.getAmCheckPointValueById(amCheckPointsValue.getAmCheckPointsValueId());
				try {
					if(optional.isPresent()) {
						AMCheckPointsValue newAMCheckPointsValue=optional.get();
						newAMCheckPointsValue.setStatus(amCheckPointsValue.getStatus());
						amCheckPointService.addAmCheckPointsValue(newAMCheckPointsValue);
						 responceObject.setCode(200);
						 responceObject.setMsg("Status Change Successfully");
					}else {
						responceObject.setCode(500);
						 responceObject.setMsg("Am Check Points Value Id not found");
					}		
				}catch(Exception e) {
					 e.printStackTrace();
					 responceObject.setCode(500);
					 responceObject.setMsg("Something wrong");
				}
				return responceObject;
			}
/********************************************** Search Operations AM Check Points ****************************************************/
			
			@RequestMapping(value = "/getlistAMCheckPointByLimit/{page_no}/{item_per_page}", method = RequestMethod.GET)
			public @ResponseBody List<AMCheckPoints> getlistAMCheckPointByLimit(@PathVariable("page_no") int page_no,@PathVariable("item_per_page") int item_per_page) {
				List<AMCheckPoints> list= new  ArrayList<AMCheckPoints>();
				try {	
					list=amCheckPointService.getlistAmCheckPointByLimit(page_no,item_per_page);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			
			//get Spares By limit Search
			@RequestMapping(value = "/getlistAMCheckPointsByLimitAndSearch", method = RequestMethod.GET)
			public @ResponseBody List<AMCheckPoints> getlistAMCheckPointsByLimitAndSearch(@RequestParam("searchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("perPage") int perPage) {
				List<AMCheckPoints> list= new  ArrayList<AMCheckPoints>();
				try {	
					list=amCheckPointService.getlistAMCheckPointsByLimitAndSearch(searchText,pageNo,perPage);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			
			//Spares count 
			@RequestMapping(value = "/getAMCheckPointsCount", method = RequestMethod.GET)
			public @ResponseBody int  getAMCheckPointsCount() {
				int  count= 0;
				try {
					count= amCheckPointService.getAMCheckPointsCount();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return count;
			} 
			//Spares count and search 
			@RequestMapping(value = "/getAMCheckPointsCountAndSearch", method = RequestMethod.GET)
			public @ResponseBody int  getAMCheckPointsCountAndSearch(@RequestParam ("searchText") String searchText) {
				int  AMCont= 0;
				try {
					AMCont= amCheckPointService.getAMCheckPointsCountAndSearch(searchText);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return AMCont;
			}
/********************************************** Search Operations AM Check Points And Values ****************************************************/
			
			@RequestMapping(value = "/getlistAMCheckPointValuesByLimit/{page_no}/{item_per_page}", method = RequestMethod.GET)
			public @ResponseBody List<AMCheckPointsValue> getlistAMCheckPointValuesByLimit(@PathVariable("page_no") int page_no,@PathVariable("item_per_page") int item_per_page) {
				List<AMCheckPointsValue> list= new  ArrayList<AMCheckPointsValue>();
				try {	
					list=amCheckPointService.getlistAMCheckPointValuesByLimit(page_no,item_per_page);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			
			//get Spares By limit Search
			@RequestMapping(value = "/getlistAMCheckPointValuesByLimitAndSearch", method = RequestMethod.GET)
			public @ResponseBody List<AMCheckPointsValue> getlistAMCheckPointValuesByLimitAndSearch(@RequestParam("searchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("perPage") int perPage) {
				List<AMCheckPointsValue> list= new  ArrayList<AMCheckPointsValue>();
				try {	
					list=amCheckPointService.getlistAMCheckPointValuesByLimitAndSearch(searchText,pageNo,perPage);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			
			//Spares count 
			@RequestMapping(value = "/getAMCheckPointValuesCount", method = RequestMethod.GET)
			public @ResponseBody int  getAMCheckPointValuesCount() {
				int  count= 0;
				try {
					count= amCheckPointService.getAMCheckPointValuesCount();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return count;
			} 
			//Spares count and search 
			@RequestMapping(value = "/getAMCheckPointValuessCountAndSearch", method = RequestMethod.GET)
			public @ResponseBody int  getAMCheckPointValuesCountAndSearch(@RequestParam ("searchText") String searchText) {
				int  AMValueCont= 0;
				try {
					AMValueCont= amCheckPointService.getAMCheckPointValuesCountAndSearch(searchText);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return AMValueCont;
			}
			
			

			@RequestMapping(value = "/dailyAMCheckPointByMachineCode", method = RequestMethod.GET)
			public @ResponseBody List<AMCheckPoints> dailyAMCheckPointByMachineCode(@RequestParam("code") String code) {
				List<AMCheckPoints> amCheckPoint = new ArrayList<AMCheckPoints>();
				amCheckPoint=amCheckPointService.dailyAMCheckPointByMachineCode(code);
				return amCheckPoint;
			}	
			
			@RequestMapping(value = "/weeklyAMCheckPointByMachineCode", method = RequestMethod.GET)
			public @ResponseBody List<AMCheckPoints> weeklyAMCheckPointByMachineCode(@RequestParam("code") String code) {
				List<AMCheckPoints> amCheckPoint = new ArrayList<AMCheckPoints>();
				amCheckPoint=amCheckPointService.weeklyAMCheckPointByMachineCode(code);
				
				return amCheckPoint;
			}
			
			@RequestMapping(value = "/quatorlyAMCheckPointByMachineCode", method = RequestMethod.GET)
			public @ResponseBody List<AMCheckPoints> quatorlyAMCheckPointByMachineCode(@RequestParam("code") String code) {
				List<AMCheckPoints> amCheckPoint = new ArrayList<AMCheckPoints>();
				amCheckPoint=amCheckPointService.quatorlyAMCheckPointByMachineCode(code);
				return amCheckPoint;
			}	
			
			@RequestMapping(value = "/yearlyAMCheckPointByMachineCode", method = RequestMethod.GET)
			public @ResponseBody List<AMCheckPoints> yearlyAMCheckPointByMachineCode(@RequestParam("code") String code) {
				List<AMCheckPoints> amCheckPoint = new ArrayList<AMCheckPoints>();
				amCheckPoint=amCheckPointService.yearlyAMCheckPointByMachineCode(code);
				return amCheckPoint;
			}	
			
			@RequestMapping(value = "/halfYearlyAMCheckPointByMachineCode", method = RequestMethod.GET)
			public @ResponseBody List<AMCheckPoints> halfYearlyAMCheckPointByMachineCode(@RequestParam("code") String code) {
				List<AMCheckPoints> amCheckPoint = new ArrayList<AMCheckPoints>();
				amCheckPoint=amCheckPointService.halfYearlyAMCheckPointByMachineCode(code);
				return amCheckPoint;
			}	
			
			
			@RequestMapping(value = "/allPendingAMCheckPointByMachineCode", method = RequestMethod.GET)
			public @ResponseBody List<AMCheckPoints> allPendingAMCheckPointByMachineCode(@RequestParam("code") String code) {
				List<AMCheckPoints> amCheckPoint = new ArrayList<AMCheckPoints>();
				Machine  machine= machineService.getMachineAssetNumber(code);
				if(machine!=null){
					amCheckPoint=amCheckPointService.getPendingAmCheckPointByMachineId(machine.getMachineId());

				}
				return amCheckPoint;
			}	
					
}
