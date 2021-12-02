package com.SKF2Maintenance.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.SKF2Maintenance.dto.ResponceObj;
import com.SKF2Maintenance.model.Drawing;
import com.SKF2Maintenance.model.Employee;
import com.SKF2Maintenance.model.Location;
import com.SKF2Maintenance.model.MachineDrawing;
import com.SKF2Maintenance.model.MachineLocation;
import com.SKF2Maintenance.model.MachineSpares;
import com.SKF2Maintenance.model.MachineSpindle;
import com.SKF2Maintenance.model.Spare;
import com.SKF2Maintenance.model.SpindleDetails;
import com.SKF2Maintenance.repository.DrawingRepo;
import com.SKF2Maintenance.service.LocationService;
import com.SKF2Maintenance.service.MachineMappingService;
import com.SKF2Maintenance.service.SparesService;
import com.SKF2Maintenance.service.SpindleDetailsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/machineMapping")
public class MachineMappingController {
	
					@Autowired
					MachineMappingService machineMappingService;
					@Autowired
					SparesService sparesService;
					@Autowired
					LocationService locationService;
					@Autowired
					SpindleDetailsService spindleDetailsService;
					@Autowired
					DrawingRepo drawingRepo;
					
					//add Location
						@RequestMapping(value = "/addMmachineSpare", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
						public ResponseEntity<ResponceObj> addMmachineSpare(@RequestBody List<MachineSpares>  machineSpares) {
							ResponceObj responceDTO= new ResponceObj();
							try {	
								for(MachineSpares spares:machineSpares){
									Optional<MachineSpares> optional=machineMappingService.getMachineSparesById(spares.getId());
									if(optional.isPresent()) {
								
										spares.setUpdDatetime(new Date());
										
									}else {
										spares.setAddedDate(new Date());
										spares.setActive(1);
										
									}
									machineMappingService.addMmachineSpare(spares);
								}
								responceDTO.setCode(200);
								responceDTO.setMessage("Machine Mappped  Successfully");
								return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
							} catch (Exception e) {
								responceDTO.setCode(500);
								responceDTO.setMessage(e.getMessage());
								return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
							}
							}	
						
						@RequestMapping(value = "/getMappedSpares", method = RequestMethod.GET)
						public @ResponseBody List<MachineSpares> getMappedSpares() {
							List<MachineSpares> list = null;
							try {
								list = machineMappingService.getMappedSpares();
							} catch (Exception e) {
								e.printStackTrace();
							}
							return list;
						}
				
					@RequestMapping(value = "/getMappedSparesByMachine", method = RequestMethod.GET)
					public @ResponseBody List<MachineSpares> getMappedSparesByMachine(@RequestParam("machineId") int machineId) {
						List<MachineSpares> list = null;
						try {
							list = machineMappingService.getMappedSparesByMachine(machineId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return list;
					}
					@RequestMapping(value = "/getAvailabelSparesMappingMachine", method = RequestMethod.GET)
					public @ResponseBody List<Spare> getAvailabelSparesMappingMachine(@RequestParam("machineId") int machineId) {
						List<Spare> list = new  ArrayList<Spare>();
						try {
							List<MachineSpares> mappedSpares = machineMappingService.getMappedSparesByMachine(machineId);
							List<Spare> allSpare=sparesService.getAllActiveSpares();
							for(Spare spare : allSpare){
								if(mappedSpares.stream().anyMatch(mappedSpare -> mappedSpare.getSpare().getId()==spare.getId())) {
									
								}else{
									list.add(spare);
								}	
							}
							
							
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						return list;
					}
				
					
					//************************************** LOCATION ****************************************************
					
					
					@RequestMapping(value = "/addMachineLocation", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
					public ResponseEntity<ResponceObj> addMachineLocation(@RequestBody List<MachineLocation>  machineLocations) {
						ResponceObj responceDTO= new ResponceObj();
						try {	
							for(MachineLocation location:machineLocations){
								Optional<MachineLocation> optional=machineMappingService.getMachineLocationById(location.getId());
								if(optional.isPresent()) {
							
									location.setUpdDatetime(new Date());
									
								}else {
									location.setAddedDate(new Date());
									location.setActive(1);
									
								}
								machineMappingService.addMachineLocation(location);
							}
							responceDTO.setCode(200);
							responceDTO.setMessage("Machine  Location Mappped  Successfully");
							return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
						} catch (Exception e) {
							responceDTO.setCode(500);
							responceDTO.setMessage(e.getMessage());
							return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
						}
						}	
					
				
				
				@RequestMapping(value = "/getMappedLocations", method = RequestMethod.GET)
				public @ResponseBody List<MachineLocation> getMappedLocations() {
					List<MachineLocation> list = new ArrayList<MachineLocation>();
					try {
						list = machineMappingService.getMappedLocations();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return list;
				}
				@RequestMapping(value = "/getMappedLocationByMachine", method = RequestMethod.GET)
				public @ResponseBody List<MachineLocation> getMappedLocationByMachine(@RequestParam("machineId") int machineId) {
					List<MachineLocation> list = new ArrayList<MachineLocation>();
					try {
						list = machineMappingService.getMappedLocationByMachine(machineId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return list;
				}
				@RequestMapping(value = "/getAvailabelLocationMappingMachine", method = RequestMethod.GET)
				public @ResponseBody List<Location> getAvailabelLocationMappingMachine(@RequestParam("machineId") int machineId) {
					List<Location> list = new  ArrayList<Location>();
					try {
						List<MachineLocation> mappedlocations = machineMappingService.getMappedLocationByMachine(machineId);
						List<Location> allloc=locationService.getAllActiveLocation();
						for(Location location : allloc){
							if(mappedlocations.stream().anyMatch(mappedlocation -> mappedlocation.getLocation().getId()==location.getId())) {
								
							}else{
								list.add(location);
							}	
						}
						
						
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return list;
				}
				
				
				
				//************************************** Spindle  ****************************************************
				
				
				@RequestMapping(value = "/addMachineSpindle", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<ResponceObj> addMachineSpindle(@RequestBody List<MachineSpindle>  machineSpindle) {
					ResponceObj responceDTO= new ResponceObj();
					try {	
						for(MachineSpindle spindle:machineSpindle){
							Optional<MachineSpindle> optional=machineMappingService.getMachineSpindleById(spindle.getId());
							if(optional.isPresent()) {
						
								spindle.setUpdDatetime(new Date());
								
							}else {
								spindle.setAddedDate(new Date());
								spindle.setActive(1);
								
							}
							machineMappingService.addMachineSpindle(spindle);
						}
						responceDTO.setCode(200);
						responceDTO.setMessage("Machine  Spindle Mappped  Successfully");
						return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
					} catch (Exception e) {
						responceDTO.setCode(500);
						responceDTO.setMessage(e.getMessage());
						return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
					}
					}	
				
				@RequestMapping(value = "/getAllMappedSpindles", method = RequestMethod.GET)
				public @ResponseBody List<MachineSpindle> getAllMappedSpindles() {
				List<MachineSpindle> list = new ArrayList<MachineSpindle>();
				try {
					list = machineMappingService.getAllMappedSpindles();
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
				}
				
				@RequestMapping(value = "/getMappedSpindleByMachine", method = RequestMethod.GET)
				public @ResponseBody List<MachineSpindle> getMappedSpindleByMachine(@RequestParam("machineId") int machineId) {
				List<MachineSpindle> list = new ArrayList<MachineSpindle>();
				try {
					list = machineMappingService.getMappedSpindleByMachine(machineId);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
				}
				@RequestMapping(value = "/getAvailabelSpindleByMachine", method = RequestMethod.GET)
				public @ResponseBody List<SpindleDetails> getAvailabelSpindleByMachine(@RequestParam("machineId") int machineId) {
				List<SpindleDetails> list = new  ArrayList<SpindleDetails>();
				try {
					List<MachineSpindle> mappedSpindles = machineMappingService.getMappedSpindleByMachine(machineId);
					List<SpindleDetails> spindles=spindleDetailsService.getAllActiveSpindle();
					for(SpindleDetails spindleDetails : spindles){
						if(mappedSpindles.stream().anyMatch(mappedSpindle -> mappedSpindle.getSpindle().getId()==spindleDetails.getId())) {
							
						}else{
							list.add(spindleDetails);
						}	
					}
					
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
				return list;
				}
				
				
				
				//***************************************************************** Drawing ************************************************
				
				
				@RequestMapping(value = "/addMachineDrawing", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
				public ResponseEntity<ResponceObj> addMachineDrawing(@RequestBody List<MachineDrawing>  machineDrawings) {
					ResponceObj responceDTO= new ResponceObj();
					try {	
						for(MachineDrawing drawing:machineDrawings){
							/*Optional<MachineSpares> optional=machineMappingService.getMachineSparesById(spares.getId());
							if(optional.isPresent()) {
						
								spares.setUpdDatetime(new Date());
								
							}else {
								spares.setAddedDate(new Date());
								spares.setActive(1);
								
							}*/
							drawing.setAddedBy("admin");
							drawing.setAddedDate(new Date());
							drawing.setActive(1);
							machineMappingService.addMachineDrawing(drawing);
						}
						responceDTO.setCode(200);
						responceDTO.setMessage("Machine Mappped  Successfully");
						return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
					} catch (Exception e) {
						responceDTO.setCode(500);
						responceDTO.setMessage(e.getMessage());
						return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
					}
					}	
				
				@RequestMapping(value = "/getMachineDrawings", method = RequestMethod.GET)
				public @ResponseBody List<MachineDrawing> getMachineDrawings() {
					List<MachineDrawing> list = null;
					try {
						list = machineMappingService.getMachineDrawings();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return list;
				}
				
					@RequestMapping(value = "/getMachineDrawingByMachine", method = RequestMethod.GET)
					public @ResponseBody List<MachineDrawing> getMachineDrawingByMachine(@RequestParam("machineId") int machineId) {
					List<MachineDrawing> list = new ArrayList<MachineDrawing>();
					try {
						list = machineMappingService.getMachineDrawingByMachine(machineId);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return list;
					}
					
					
					@RequestMapping(value = "/getMappedDrawingByMachine", method = RequestMethod.GET)
					public @ResponseBody List<MachineDrawing> getMappedDrawingByMachine(@RequestParam("machineId") int machineId) {
						List<MachineDrawing> list = new ArrayList<MachineDrawing>();
						try {
							list = machineMappingService.getMachineDrawingByMachine(machineId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return list;
					}
					@RequestMapping(value = "/getAvailabelDrawingMappingMachine", method = RequestMethod.GET)
					public @ResponseBody List<Drawing> getAvailabelDrawingMappingMachine(@RequestParam("machineId") int machineId) {
						List<Drawing> list = new  ArrayList<Drawing>();
						try {
							List<MachineDrawing> mappedDrawings= machineMappingService.getMachineDrawingByMachine(machineId);
							List<Drawing> drawings=drawingRepo.findAllDrawing();
							System.out.println("Drawing :: "+drawings.size());
							
							for(Drawing drawing : drawings){
								if(mappedDrawings.stream().anyMatch(mappedDrawing -> mappedDrawing.getDrawing().getId()==drawing.getId())) {
									
								}else{
									list.add(drawing);
								}	
							}
							
							
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						return list;
					}
					
					@RequestMapping(value = "/getMappedSetupChartByMachine", method = RequestMethod.GET)
					public @ResponseBody List<MachineDrawing> getMappedSetupChartByMachine(@RequestParam("machineId") int machineId) {
						List<MachineDrawing> list = new ArrayList<MachineDrawing>();
						try {
							list = machineMappingService.getMappedSetupChartByMachine(machineId);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return list;
					}
					@RequestMapping(value = "/getAvailabelSetupChartMappingMachine", method = RequestMethod.GET)
					public @ResponseBody List<Drawing> getAvailabelSetupChartMappingMachine(@RequestParam("machineId") int machineId) {
						List<Drawing> list = new  ArrayList<Drawing>();
						try {
							List<MachineDrawing> mappedDrawings= machineMappingService.getMappedSetupChartByMachine(machineId);
							List<Drawing> drawings=drawingRepo.findAllDrawing();
							System.out.println("Drawing :: "+drawings.size());
							
							for(Drawing drawing : drawings){
								if(mappedDrawings.stream().anyMatch(mappedDrawing -> mappedDrawing.getDrawing().getId()==drawing.getId())) {
									
								}else{
									list.add(drawing);
								}	
							}
							
							
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						return list;
					}
				
}
