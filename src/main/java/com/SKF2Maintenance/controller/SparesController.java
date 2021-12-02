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
import com.SKF2Maintenance.service.SparesService;

@RestController
@CrossOrigin("*")
@RequestMapping("/spares")
public class SparesController {

	@Autowired
	private SparesService spareService;
	
	//add Spares
		@RequestMapping(value = "/addSpares", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
		public ResponseEntity<ResponceObj> addSpares(@RequestBody Spare  spares) {
			ResponceObj responceDTO= new ResponceObj();
			try {		
				Optional<Spare> optional=spareService.getSpareById(spares.getId());
				if(optional.isPresent()) {
					spares.setUpdatedBy(spares.getAddedBy());
					spares.setUpdatedDate(new Date());
					spares.setActive(1);
					responceDTO.setCode(200);
					responceDTO.setMessage("Spares is Updated Successfully");
				}else {
					spares.setActive(1);
					spares.setAddedDate(new Date());
					responceDTO.setCode(200);
					responceDTO.setMessage("Spares is Added Successfully");
				}
				spareService.addSpare(spares);
				return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
			} catch (Exception e) {
				responceDTO.setCode(500);
				responceDTO.setMessage(e.getMessage());
				return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
			}
			}	
		
		//delete Spares
		@RequestMapping(value="/deleteSpares",method=RequestMethod.POST)
		public @ResponseBody ResponceObject deleteById(@RequestBody Spare spares) {
			ResponceObject resObject=new ResponceObject();
			Optional<Spare> optional=spareService.getSpareById(spares.getId());
			try {
				if(optional.isPresent()) {
					Spare newSpares=optional.get();
					newSpares.setDeleteBit(1);
					spareService.addSpare(newSpares);
					resObject.setCode(200);
					resObject.setMsg("Spares is Delete Successfully");
				}else {
					resObject.setMsg("Spares Id not Found");
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
		public @ResponseBody ResponceObject changeStatus(@RequestBody Spare spares) {
			ResponceObject responceObject=new ResponceObject();
			try {
					if(spares.getActive()==1) {
						spares.setActive(0);	
					}else {
						spares.setActive(1);
					}
					System.out.println("Status : "+spares.getActive());
					spareService.addSpare(spares);
					responceObject.setCode(200);
					responceObject.setMsg("Status Change Successfully");	
			}catch(Exception e) {
				 e.printStackTrace();
				 responceObject.setCode(500);
				 responceObject.setMsg("Something wrong");
			}
			return responceObject;
		}
		
		//get All Spares
		@RequestMapping(value = "/getAllSpares", method = RequestMethod.GET)
		public @ResponseBody List<Spare> getAllSpares() {
			List<Spare> list= new  ArrayList<Spare>();
			try {	
				list=spareService.getAllSpare();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}

		@RequestMapping(value = "/getAllActiveSpares", method = RequestMethod.GET)
		public @ResponseBody List<Spare> getAllActiveSpares() {
			List<Spare> list= new  ArrayList<Spare>();
			try {	
				list=spareService.getAllActiveSpares();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
/********************************************** Search Operations ****************************************************/
		
		@RequestMapping(value = "/getlistSparesByLimit/{page_no}/{item_per_page}", method = RequestMethod.GET)
		public @ResponseBody List<Spare> getlistSparesDetailsByLimit(@PathVariable("page_no") int page_no,@PathVariable("item_per_page") int item_per_page) {
			List<Spare> list= new  ArrayList<Spare>();
			try {	
				list=spareService.getlistSpareByLimit(page_no,item_per_page);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		//get Spares By limit Search
		@RequestMapping(value = "/getlistSparesByLimitAndSearch", method = RequestMethod.GET)
		public @ResponseBody List<Spare> getlistSparesByLimitAndSearch(@RequestParam("searchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("perPage") int perPage) {
			List<Spare> list= new  ArrayList<Spare>();
			try {	
				list=spareService.getlistSpareByLimitAndSearch(searchText,pageNo,perPage);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
		}
		
		//Spares count 
		@RequestMapping(value = "/getSparesCount", method = RequestMethod.GET)
		public @ResponseBody int  getSparesCount() {
			int  count= 0;
			try {
				count= spareService.getSpareCount();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return count;
		} 
		//Spares count and search 
		@RequestMapping(value = "/getSparesCountAndSearch", method = RequestMethod.GET)
		public @ResponseBody int  getSparesCountAndSearch(@RequestParam ("searchText") String searchText) {
			int  sparesCont= 0;
			try {
				sparesCont= spareService.getSpareCountAndSearch(searchText);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sparesCont;
		}
}
