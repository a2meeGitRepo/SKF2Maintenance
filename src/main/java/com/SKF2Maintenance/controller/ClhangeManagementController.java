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

import com.SKF2Maintenance.dto.ResponceObj;
import com.SKF2Maintenance.model.Breakdown;
import com.SKF2Maintenance.model.ChangeManagement;
import com.SKF2Maintenance.model.ChangeManagementImage;
import com.SKF2Maintenance.model.ChangeManagementSpare;
import com.SKF2Maintenance.model.Department;
import com.SKF2Maintenance.model.Spare;
import com.SKF2Maintenance.service.ChangeMangementService;

@RestController
@CrossOrigin("*")
@RequestMapping("/changeMangement")
public class ClhangeManagementController {
	@Autowired
	ChangeMangementService changeMangementService;
	
	@RequestMapping(value = "/addChangeMangement", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceObj> addChangeMangement(@RequestBody ChangeManagement  changeManagement) {
		ResponceObj responceDTO= new ResponceObj();
		try {
			changeManagement.setAddedDate(new Date());
			changeManagement.setStartDate(new Date());
			System.out.println("ChangeMangement"+changeManagement.toString());
			changeMangementService.addChangeMangement(changeManagement);
			/*if(changeManagement.getSpares().size()!=0){
				System.out.println("Add Spare");
				for(Spare spare:changeManagement.getSpares()){
					ChangeManagementSpare changeManagementSpare= new ChangeManagementSpare();
					changeManagementSpare.setActive(1);
					changeManagementSpare.setAddedBy(changeManagement.getUpdatedBy());
					changeManagementSpare.setAddedDate(new Date());
					changeManagementSpare.setChangeManagement(changeManagement);
					changeManagementSpare.setSpare(spare);
					changeMangementService.saveChangemangementSpare(changeManagementSpare);
					
				}
			}*/
			responceDTO.setMessage("ChangeManagement is added Successfully");
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		} catch (Exception e) {
			e.printStackTrace();
			responceDTO.setCode(500);
			responceDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		}
		}
	
	@RequestMapping(value = "/updateChangeMangement", method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponceObj> updateChangeMangement(@RequestBody ChangeManagement  changeManagement) {
		ResponceObj responceDTO= new ResponceObj();
		try {	
			changeManagement.setAddedDate(new Date());
			changeMangementService.addChangeMangement(changeManagement);
			if(changeManagement.getSpares().size()!=0){
				for(Spare spare:changeManagement.getSpares()){
					ChangeManagementSpare changeManagementSpare= new ChangeManagementSpare();
					changeManagementSpare.setActive(1);
					changeManagementSpare.setAddedBy(changeManagement.getUpdatedBy());
					changeManagementSpare.setAddedDate(new Date());
					changeManagementSpare.setChangeManagement(changeManagement);
					changeManagementSpare.setSpare(spare);
					changeMangementService.saveChangemangementSpare(changeManagementSpare);
					
				}
			}
			responceDTO.setMessage("ChangeManagement is added Successfully");
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		} catch (Exception e) {
			responceDTO.setCode(500);
			responceDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		}
		}
	
	@RequestMapping(value = "/getAllChangeManagement", method = RequestMethod.GET)
	public @ResponseBody List<ChangeManagement>  getAllChangeManagement() {
		List<ChangeManagement> departments= new ArrayList<ChangeManagement>();
		try {
			departments= changeMangementService.getAllChangeManagement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departments;
	}
	@RequestMapping(value = "/getOpenChangeManagement", method = RequestMethod.GET)
	public @ResponseBody List<ChangeManagement>  getOpenChangeManagement() {
		List<ChangeManagement> departments= new ArrayList<ChangeManagement>();
		try {
			departments= changeMangementService.getOpenChangeManagement();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departments;
	}
	@RequestMapping(value = "/getSpareByChangeManagement", method = RequestMethod.GET)
	public @ResponseBody List<ChangeManagementSpare>  getSpareByChangeManagement(@RequestParam("changemanagementId") int changemanagementId) {
		List<ChangeManagementSpare> spares= new ArrayList<ChangeManagementSpare>();
		try {
			spares= changeMangementService.getSpareByChangeManagement(changemanagementId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return spares;
	}
	@RequestMapping(value = "/getImageByChangeManagement", method = RequestMethod.GET)
	public @ResponseBody List<ChangeManagementImage>  getImageByChangeManagement(@RequestParam("changemanagementId") int changemanagementId) {
		List<ChangeManagementImage> spares= new ArrayList<ChangeManagementImage>();
		try {
			spares= changeMangementService.getImageByChangeManagement(changemanagementId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return spares;
	}
}
