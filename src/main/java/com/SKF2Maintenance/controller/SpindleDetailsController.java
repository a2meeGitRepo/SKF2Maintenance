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
import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.model.Spare;
import com.SKF2Maintenance.model.SpindleDetails;
import com.SKF2Maintenance.service.SpindleDetailsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/spindleDetails")
public class SpindleDetailsController {

	@Autowired
	private SpindleDetailsService spindleDetailsSer;
	
	//add Spindle
		@RequestMapping(value = "/addSpindle", method = RequestMethod.POST)
		public ResponseEntity<ResponceObj> addSpindle(@RequestBody SpindleDetails  spindleDetails) {
			ResponceObj responceDTO= new ResponceObj();
			try {		
				Optional<SpindleDetails> optional=spindleDetailsSer.getSpindleById(spindleDetails.getId());
				if(optional.isPresent()) {
					spindleDetails.setUpdatedBy(spindleDetails.getAddedBy());
					spindleDetails.setUpdatedDate(new Date());
					spindleDetails.setActive(1);
					responceDTO.setCode(200);
					responceDTO.setMessage("Spindle is Updated Successfully");
				}else {
					spindleDetails.setActive(1);

					spindleDetails.setAddedDate(new Date());
					responceDTO.setCode(200);
					responceDTO.setMessage("Spindle is Added Successfully");
				}
				spindleDetailsSer.addSpindle(spindleDetails);
				return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
			} catch (Exception e) {
				responceDTO.setCode(500);
				responceDTO.setMessage(e.getMessage());
				return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
			}
			}	
		
		//delete Spindle
		@RequestMapping(value="/deleteSpindle",method=RequestMethod.POST)
		public @ResponseBody ResponceObject deleteById(@RequestBody SpindleDetails spindleDetails) {
			ResponceObject resObject=new ResponceObject();
			Optional<SpindleDetails> optional=spindleDetailsSer.getSpindleById(spindleDetails.getId());
			try {
				if(optional.isPresent()) {
					SpindleDetails newSpindle=optional.get();
					newSpindle.setDeleteBit(1);
					spindleDetailsSer.addSpindle(newSpindle);
					resObject.setCode(200);
					resObject.setMsg("Spindle is Delete Successfully");
				}else {
					resObject.setMsg("Spindle Id not Found");
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
				public @ResponseBody ResponceObject changeStatus(@RequestBody SpindleDetails spindleDetails) {
					ResponceObject responceObject=new ResponceObject();
					
					try {
						if(spindleDetails.getActive()==1) {
							spindleDetails.setActive(0);	
						}else {
							spindleDetails.setActive(1);
						}
						spindleDetailsSer.addSpindle(spindleDetails);
						responceObject.setCode(200);
						responceObject.setMsg("Status Change Successfully");	
					}catch(Exception e) {
						 e.printStackTrace();
						 responceObject.setCode(500);
						 responceObject.setMsg("Something wrong");
					}
					return responceObject;
				}
				
				//get All Spindle
				@RequestMapping(value = "/getAllSpindle", method = RequestMethod.GET)
				public @ResponseBody List<SpindleDetails> getAllSpindle() {
					List<SpindleDetails> list= new  ArrayList<SpindleDetails>();
					try {	
						list=spindleDetailsSer.getAllSpindle();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return list;
				}
				@RequestMapping(value = "/getAllActiveSpindle", method = RequestMethod.GET)
				public @ResponseBody List<SpindleDetails> getAllActiveSpindle() {
					List<SpindleDetails> list= new  ArrayList<SpindleDetails>();
					try {	
						list=spindleDetailsSer.getAllActiveSpindle();
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return list;
				}
/********************************************** Search Operations ****************************************************/
				
				@RequestMapping(value = "/getlistSpindleByLimit/{page_no}/{item_per_page}", method = RequestMethod.GET)
				public @ResponseBody List<SpindleDetails> getlistSpindleDetailsByLimit(@PathVariable("page_no") int page_no,@PathVariable("item_per_page") int item_per_page) {
					List<SpindleDetails> list= new  ArrayList<SpindleDetails>();
					try {	
						list=spindleDetailsSer.getlistSpindleByLimit(page_no,item_per_page);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return list;
				}
				
				//get SpindleDetails By limit Search
				@RequestMapping(value = "/getlistSpindleByLimitAndSearch", method = RequestMethod.GET)
				public @ResponseBody List<SpindleDetails> getlistSpindleByLimitAndSearch(@RequestParam("searchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("perPage") int perPage) {
					List<SpindleDetails> list= new  ArrayList<SpindleDetails>();
					try {	
						list=spindleDetailsSer.getlistSpindleByLimitAndSearch(searchText,pageNo,perPage);
						
					} catch (Exception e) {
						e.printStackTrace();
					}
					return list;
				}
				
				//Spindle count 
				@RequestMapping(value = "/getSpindleCount", method = RequestMethod.GET)
				public @ResponseBody int  getSpindleCount() {
					int  count= 0;
					try {
						count= spindleDetailsSer.getSpindleCount();
					} catch (Exception e) {
						e.printStackTrace();
					}
					return count;
				}
				//Spindle count and search 
				@RequestMapping(value = "/getSpindleCountAndSearch", method = RequestMethod.GET)
				public @ResponseBody int  getSpindleCountAndSearch(@RequestParam ("searchText") String searchText) {
					int  spindleCount= 0;
					try {
						spindleCount= spindleDetailsSer.getSpindleCountAndSearch(searchText);
					} catch (Exception e) {
						e.printStackTrace();
					}
					return spindleCount;
				}
}
