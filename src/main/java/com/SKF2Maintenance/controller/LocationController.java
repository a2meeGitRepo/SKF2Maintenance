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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SKF2Maintenance.dto.ResponceObj;
import com.SKF2Maintenance.dto.ResponceObject;
import com.SKF2Maintenance.model.Location;
import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.service.LocationService;

@RestController
@CrossOrigin("*")
@RequestMapping("/location")
public class LocationController {

	@Autowired
	private LocationService locationService;
	
	//add Location
	@RequestMapping(value = "/addLocation", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceObj> addLocation(@RequestBody Location  location) {
		ResponceObj responceDTO= new ResponceObj();
		try {		
			Optional<Location> optional=locationService.getLocationById(location.getId());
			if(optional.isPresent()) {
				location.setUpdatedBy(location.getAddedBy());
				location.setUpdatedDate(new Date());
				responceDTO.setCode(200);
				responceDTO.setMessage("Location is Updated Successfully");
			}else {
				location.setAddedDate(new Date());
				responceDTO.setCode(200);
				responceDTO.setMessage("Location is Added Successfully");
			}
			locationService.addLocation(location);
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		} catch (Exception e) {
			responceDTO.setCode(500);
			responceDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		}
		}	
	
	//delete Location
	@RequestMapping(value="/deleteLocation",method=RequestMethod.POST)
	public @ResponseBody ResponceObject deleteById(@RequestBody Location location) {
		ResponceObject resObject=new ResponceObject();
		Optional<Location> optional=locationService.getLocationById(location.getId());
		try {
			if(optional.isPresent()) {
				Location newLocation=optional.get();
				newLocation.setDeleteBit(1);
				locationService.addLocation(newLocation);
				resObject.setCode(200);
				resObject.setMsg("Location is Delete Successfully");
			}else {
				resObject.setMsg("Location Id not Found");
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
	public @ResponseBody ResponceObject changeStatus(@RequestBody Location location) {
		ResponceObject responceObject=new ResponceObject();
		try {
				if(location.getActive()==1) {
					location.setActive(0);
				}else {
					location.setActive(1);
				}
				 locationService.addLocation(location);
				 responceObject.setCode(200);
				 responceObject.setMsg("Status Change Successfully");
		}catch(Exception e) {
			 e.printStackTrace();
			 responceObject.setCode(500);
			 responceObject.setMsg("Something wrong");
		}
		return responceObject;
	}
	//get All Locations
	@RequestMapping(value = "/getAllLocation", method = RequestMethod.GET)
	public @ResponseBody List<Location> getAllLocation() {
		List<Location> list= new  ArrayList<Location>();
		try {	
			list=locationService.getAllLocation();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/getAllActiveLocation", method = RequestMethod.GET)
	public @ResponseBody List<Location> getAllActiveLocation() {
		List<Location> list= new  ArrayList<Location>();
		try {	
			list=locationService.getAllActiveLocation();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
/********************************************** Search Operations ****************************************************/
	
	@RequestMapping(value = "/getlistLocationByLimit/{page_no}/{item_per_page}", method = RequestMethod.GET)
	public @ResponseBody List<Location> getlistLocationDetailsByLimit(@PathVariable("page_no") int page_no,@PathVariable("item_per_page") int item_per_page) {
		List<Location> list= new  ArrayList<Location>();
		try {	
			list=locationService.getlistLocationByLimit(page_no,item_per_page);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//get Spares By limit Search
	@RequestMapping(value = "/getlistLocationByLimitAndSearch", method = RequestMethod.GET)
	public @ResponseBody List<Location> getlistLocationByLimitAndSearch(@RequestParam("searchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("perPage") int perPage) {
		List<Location> list= new  ArrayList<Location>();
		try {	
			list=locationService.getlistLocationByLimitAndSearch(searchText,pageNo,perPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//Spares count 
	@RequestMapping(value = "/getLocationCount", method = RequestMethod.GET)
	public @ResponseBody int  getSparesCount() {
		int  count= 0;
		try {
			count= locationService.getLocationCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	} 
	//Spares count and search 
	@RequestMapping(value = "/getLocationCountAndSearch", method = RequestMethod.GET)
	public @ResponseBody int  getLocationCountAndSearch(@RequestParam ("searchText") String searchText) {
		int  sparesCont= 0;
		try {
			sparesCont= locationService.getLocationCountAndSearch(searchText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sparesCont;
	}
}
