package com.SKF2Maintenance.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.SKF2Maintenance.dto.ResponceObject;
import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.model.MachineCheckPoint;
import com.SKF2Maintenance.model.MachineOwner;
import com.SKF2Maintenance.service.MachineService;

@RestController
@CrossOrigin("*")
@RequestMapping("/machine")
public class MachineController {
	
	
	@Autowired
	MachineService machineService;
	
	@RequestMapping(value = "/addMachine", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceObj> addMachine(@RequestBody Machine  machine) {
		ResponceObj responceDTO= new ResponceObj();
		try {		
			Optional<Machine> optional=machineService.getMachineById(machine.getMachineId());
			if(optional.isPresent()) {
				machine.setUpdatedBy(machine.getAddedBy());
				machine.setUpdatedDate(new Date());
				machine.setPurchaseDate(machine.getPurchaseDate());
				responceDTO.setCode(200);
				responceDTO.setMessage("Machine is Updated Successfully");
			}else {
				machine.setStatus("Active");
				machine.setAddedDate(new Date());
				machine.setPurchaseDate(new Date());
				responceDTO.setCode(200);
				responceDTO.setMessage("Machine is Added Successfully");
			}
			machineService.addMachine(machine);
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		} catch (Exception e) {
			responceDTO.setCode(500);
			responceDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		}
		}	
	//delete Machine
	@RequestMapping(value="/deleteMachine",method=RequestMethod.POST)
	public @ResponseBody ResponceObject deleteById(@RequestBody Machine machine) {
		ResponceObject resObject=new ResponceObject();
		Optional<Machine> optional=machineService.getMachineById(machine.getMachineId());
		try {
			if(optional.isPresent()) {
				Machine newMachine=optional.get();
				newMachine.setDeleteBit(1);
				machineService.addMachine(newMachine);
				resObject.setCode(200);
				resObject.setMsg("Machine is Delete Successfully");
			}else {
				resObject.setMsg("Machine Id not Found");
			}
		}catch(Exception e) {
			System.out.println(e.toString());
			resObject.setCode(500);
			resObject.setMsg("Something Wrong");
		}
		return resObject;
	}
	// change Status 
			@RequestMapping(value="/changeStatus",method=RequestMethod.POST)
			public @ResponseBody ResponceObject changeStatus(@RequestBody Machine machine) {
				ResponceObject responceObject=new ResponceObject();
				try {
					if(machine.getStatus()=="Active"||machine.getStatus().equalsIgnoreCase("Active")) {
						machine.setStatus("InActive");
						
						
					}else {
						machine.setStatus("Active");
					}		
					 machineService.addMachine(machine);
					 responceObject.setCode(200);
					 responceObject.setMsg("Status Change Successfully");
				}catch(Exception e) {
					 e.printStackTrace();
					 responceObject.setCode(500);
					 responceObject.setMsg("Something wrong");
				}
				return responceObject;
			}
			@RequestMapping(value = "/getlistMachineByLimit/{page_no}/{item_per_page}", method = RequestMethod.GET)
			public @ResponseBody List<Machine> getlistMachineByLimit(@PathVariable("page_no") int page_no,@PathVariable("item_per_page") int item_per_page) {
				List<Machine> list= new  ArrayList<Machine>();
				try {	
					list=machineService.getlistMachineByLimit(page_no,item_per_page);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			//get Machine By limit Search
			@RequestMapping(value = "/getlistMachineByLimitAndSearch", method = RequestMethod.GET)
			public @ResponseBody List<Machine> getlistMachineByLimitAndSearch(@RequestParam("searchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("perPage") int perPage) {
				List<Machine> list= new  ArrayList<Machine>();
				try {	
					list=machineService.getlistMachineByLimitAndSearch(searchText,pageNo,perPage);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			
			//Machine count 
			@RequestMapping(value = "/getMachineCount", method = RequestMethod.GET)
			public @ResponseBody int  getMachineCount() {
				int  count= 0;
				try {
					count= machineService.getMachineCount();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return count;
			}
			//Machine count and search 
			@RequestMapping(value = "/getMachineCountAndSearch", method = RequestMethod.GET)
			public @ResponseBody int  getMachineCountAndSearch(@RequestParam ("searchText") String searchText) {
				int  machineCount= 0;
				try {
					machineCount= machineService.getMachineCountAndSearch(searchText);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return machineCount;
			}
			@RequestMapping(value = "/getAllMachines", method = RequestMethod.GET)
			public @ResponseBody List<Machine> getAllMachines() {
				List<Machine> list= new  ArrayList<Machine>();
				try {	
					list=machineService.getAllMachines();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			@RequestMapping(value = "/getAllChannels", method = RequestMethod.GET)
			public @ResponseBody Set<String> getAllChannels() {
				Set<String> channels= new  HashSet<String>();
				try {	
					List<Machine> list=machineService.getAllMachinesChannelNotNull();
					for(Machine machine:list){
						 if (machine.getChennelNo()!=""||machine.getChennelNo()!=null||machine.getChennelNo().equalsIgnoreCase("null")) {
							 channels.add(machine.getChennelNo());
						}
						
					}
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return channels;
			}
			@RequestMapping(value = "/getAllMachineByChannerNo", method = RequestMethod.GET)
			public @ResponseBody List<Machine> getAllMachineByChannerNo(@RequestParam("channelNo") String channelNo) {
				List<Machine> list= new  ArrayList<Machine>();
				try {	
				list=machineService.getAllMachineByChannerNo(channelNo);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			@RequestMapping(value = "/checkAssetNumber", method = RequestMethod.GET)
			public @ResponseBody boolean  checkAssetNumber(@RequestParam ("assetNumber") String assetNumber) {
				boolean isPresent = false;
				try {
					isPresent= machineService.checkAssetNumber(assetNumber);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return isPresent;
			}
			
			@RequestMapping(value = "/addMachineCheckPoint", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<ResponceObj> addMachineCheckPoint(@RequestBody MachineCheckPoint  machineCheckPoint) {
				ResponceObj responceDTO= new ResponceObj();
				try {		
					Optional<MachineCheckPoint> optional=machineService.getMachineCheckPointByMachineIdAndCheckPointId(machineCheckPoint.getMachine().getMachineId(),machineCheckPoint.getCheckPointName());
					if(optional.isPresent()) {
						machineCheckPoint.setUpdatedBy(machineCheckPoint.getAddedBy());
						machineCheckPoint.setUpdatedDate(new Date());
						responceDTO.setCode(200);
						responceDTO.setMessage("Machine Check Point  is Updated Successfully");
					}else {
						machineCheckPoint.setAddedDate(new Date());
						responceDTO.setCode(200);
						responceDTO.setMessage("Machine Check Point  is Added Successfully");
					}
					machineService.addMachineCheckPoint(machineCheckPoint);
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				} catch (Exception e) {
					responceDTO.setCode(500);
					responceDTO.setMessage(e.getMessage());
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				}
				}
			
			@RequestMapping(value = "/changeStatusMachineCheckPoint", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<ResponceObj> changeStatusMachineCheckPoint(@RequestBody MachineCheckPoint  machineCheckPoint) {
				ResponceObj responceDTO= new ResponceObj();
				try {		
					if(machineCheckPoint.getActive()==1){
						machineCheckPoint.setActive(0);
					}else{
						machineCheckPoint.setActive(1);
					}
					responceDTO.setCode(200);
					responceDTO.setMessage("Status Change Successfully");
					machineService.addMachineCheckPoint(machineCheckPoint);
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				} catch (Exception e) {
					responceDTO.setCode(500);
					responceDTO.setMessage(e.getMessage());
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				}
				}
			
			@RequestMapping(value = "/deleteMachineChecpPoint", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<ResponceObj> deleteMachineChecpPoint(@RequestBody MachineCheckPoint  machineCheckPoint) {
				ResponceObj responceDTO= new ResponceObj();
				try {		
					machineCheckPoint.setDeleteBit(1);
					responceDTO.setCode(200);
					responceDTO.setMessage("Machine Check Point Deleyed Successfully");
					machineService.addMachineCheckPoint(machineCheckPoint);
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				} catch (Exception e) {
					responceDTO.setCode(500);
					responceDTO.setMessage(e.getMessage());
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				}
				}
			/********************************************Pagination Search For Macchine Check Point ************************************************/
			
			@RequestMapping(value = "/getlistMaintanceCheckPointByLimit/{page_no}/{item_per_page}", method = RequestMethod.GET)
			public @ResponseBody List<MachineCheckPoint> getlistMaintanceCheckPointByLimit(@PathVariable("page_no") int page_no,@PathVariable("item_per_page") int item_per_page) {
				List<MachineCheckPoint> list= new  ArrayList<MachineCheckPoint>();
				try {	
					list=machineService.getlistMaintanceCheckPointByLimit(page_no,item_per_page);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			//get Maintance By limit Search
			@RequestMapping(value = "/getlistMaintanceCheckPointListByLimitAndSearch", method = RequestMethod.GET)
			public @ResponseBody List<MachineCheckPoint> getlistMaintanceCheckPointListByLimitAndSearch(@RequestParam("searchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("perPage") int perPage) {
				List<MachineCheckPoint> list= new  ArrayList<MachineCheckPoint>();
				try {	
					list=machineService.getlistMaintanceCheckPointListByLimitAndSearch(searchText,pageNo,perPage);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
			}
			//Maintance count 
			@RequestMapping(value = "/getMachienChekPointCount", method = RequestMethod.GET)
			public @ResponseBody int  getMachienChekPointCount() {
				int  count= 0;
				try {
					count= machineService.getMachienChekPointCount();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return count;
			}
			//Maintance count and search 
			@RequestMapping(value = "/getMachienChekPointCountBySearch", method = RequestMethod.GET)
			public @ResponseBody int  getMachienChekPointCountBySearch(@RequestParam ("searchText") String searchText) {
				int  maintanceCount= 0;
				try {
					maintanceCount= machineService.getMachienChekPointCountBySearch(searchText);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return maintanceCount;
			}
//********************************************* MACHINE OWNER **************************************************************************************************//
			@RequestMapping(value = "/addMachineOwner", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
			public ResponseEntity<ResponceObj> addMachineOwner(@RequestBody MachineOwner  machineOwner) {
				ResponceObj responceDTO= new ResponceObj();
				try {		
					Optional<MachineOwner> optional=machineService.getMachineOwnerByid(machineOwner.getId());
					if(optional.isPresent()) {
						machineOwner.setUpdatedBy(machineOwner.getAddedBy());
						machineOwner.setUpdatedDate(new Date());
						responceDTO.setCode(200);
						responceDTO.setMessage("Machine Owner is Updated Successfully");
					}else{
						machineOwner.setAddedDate(new Date());
						responceDTO.setCode(200);
						responceDTO.setMessage("Machine Owner is Added Successfully");
					}
					machineService.addMachineOwner(machineOwner);
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				} catch (Exception e) {
					responceDTO.setCode(500);
					responceDTO.setMessage(e.getMessage());
					return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
				}
				}	
			
			//delete Machine Owner
					@RequestMapping(value="/deleteMachineOwner",method=RequestMethod.POST)
					public @ResponseBody ResponceObject deleteById(@RequestBody MachineOwner machine) {
						ResponceObject resObject=new ResponceObject();
						Optional<MachineOwner> optional=machineService.getMachineOwnerByid(machine.getId());
						System.out.println(optional);
						try {
							if(optional.isPresent()) {
								MachineOwner newMachine=optional.get();
								newMachine.setDeleteBit(1);
								machineService.addMachineOwner(newMachine);
								resObject.setCode(200);
								resObject.setMsg("Machine Owner is Delete Successfully");
							}else {
								resObject.setMsg("Machine Owner Id not Found");
							}
						}catch(Exception e) {
							System.out.println(e.toString());
							resObject.setCode(500);
							resObject.setMsg("Something Wrong");
						}
						return resObject;
					}
					// change Status 
					@RequestMapping(value="/changeStatusMachineOwner",method=RequestMethod.POST)
					public @ResponseBody ResponceObject changeStatusMachineOwner(@RequestBody MachineOwner machine) {
						ResponceObject responceObject=new ResponceObject();
						try {
							if(machine.getActive()==1){
								machine.setActive(0);
							}else{
								machine.setActive(1);
							}
							machineService.addMachineOwner(machine);
							responceObject.setCode(200);
							responceObject.setMsg("Ststus Change  Successfully");
						}catch(Exception e) {
							 e.printStackTrace();
							 responceObject.setCode(500);
							 responceObject.setMsg("Something wrong");
						}
						return responceObject;
					}
					
					//get All MachineOwner
					@RequestMapping(value = "/getAllMachineOwner", method = RequestMethod.GET)
					public @ResponseBody List<MachineOwner> getAllMachineOwner() {
						List<MachineOwner> list= new  ArrayList<MachineOwner>();
						try {	
							list=machineService.getAllMachineOwner();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						return list;
					}
					
					//get list machine owner by limit
					@RequestMapping(value = "/getlistMachineOwnerByLimit/{page_no}/{item_per_page}", method = RequestMethod.GET)
					public @ResponseBody List<MachineOwner> getlistMachineOwnerByLimit(@PathVariable("page_no") int page_no,@PathVariable("item_per_page") int item_per_page) {
						List<MachineOwner> list= new  ArrayList<MachineOwner>();
						try {	
							list=machineService.getlistMachineOwnerByLimit(page_no,item_per_page);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						return list;
					}
					
					//get Machine By limit Search
					@RequestMapping(value = "/getlistMachineOwnerByLimitAndSearch", method = RequestMethod.GET)
					public @ResponseBody List<MachineOwner> getlistMachineOwnerByLimitAndSearch(@RequestParam("searchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("perPage") int perPage) {
						List<MachineOwner> list= new  ArrayList<MachineOwner>();
						try {	
							list=machineService.getlistMachineOwnerByLimitAndSearch(searchText,pageNo,perPage);
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						return list;
					}
					//Machine count 
					@RequestMapping(value = "/getMachineOwnerCount", method = RequestMethod.GET)
					public @ResponseBody int  getMachineOwnerCount() {
						int  count= 0;
						try {
							count= machineService.getMachineCount();
						} catch (Exception e) {
							e.printStackTrace();
						}
						return count;
					}
					
					//Machine count and search 
					@RequestMapping(value = "/getMachineOwnerCountAndSearch", method = RequestMethod.GET)
					public @ResponseBody int  getMachineOwnerCountAndSearch(@RequestParam ("searchText") String searchText) {
						int  machineCount= 0;
						try {
							machineCount= machineService.getMachineOwnerCountAndSearch(searchText);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return machineCount;
					}
					
					@RequestMapping(value = "/getAllMachineOwnersByUser", method = RequestMethod.GET)
					public @ResponseBody List<MachineOwner>  getAllMachineOwnersByUser(@RequestParam ("userId") int userId) {
						List<MachineOwner> machineOwner= new ArrayList<MachineOwner>();
						try {
							machineOwner= machineService.getAllMachineOwnersByUser(userId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return machineOwner;
					}
			
					@RequestMapping(value = "/getMachineOwnersByMachine", method = RequestMethod.GET)
					public @ResponseBody List<MachineOwner>  getMachineOwnersByMachine(@RequestParam ("machieId") int machieId) {
						List<MachineOwner> machineOwner= new ArrayList<MachineOwner>();
						try {
							machineOwner= machineService.getMachineOwnersByMachine(machieId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return machineOwner;
					}
			
}
