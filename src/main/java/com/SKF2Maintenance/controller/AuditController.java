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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SKF2Maintenance.dto.AuditItemsDTO;
import com.SKF2Maintenance.dto.AuditItemsSaveDto;
import com.SKF2Maintenance.dto.AuditStageDto;
import com.SKF2Maintenance.dto.ResponceObj;
import com.SKF2Maintenance.model.AMCheckPoints;
import com.SKF2Maintenance.model.Audit;
import com.SKF2Maintenance.model.AuditDto;
import com.SKF2Maintenance.model.AuditItemValues;
import com.SKF2Maintenance.model.AuditItems;
import com.SKF2Maintenance.model.AuditStages;
import com.SKF2Maintenance.service.AuditService;

@RestController
@CrossOrigin("*")
@RequestMapping("/audit")
public class AuditController {
	
	@Autowired
	AuditService auditService;
	
	
	@RequestMapping(value = "/addAudit", method = RequestMethod.POST)
	public ResponseEntity<ResponceObj> addAudit(@RequestBody AuditDto  auditDto) {
		ResponceObj responceDTO= new ResponceObj();
		try {		
			if(auditDto.getItemValues().size()!=0){
				auditDto.getAudit().setAduitDate(new Date());
				Audit audit= auditService.saveAudit(auditDto.getAudit());
				for(AuditItemValues itemValues:auditDto.getItemValues()){
					itemValues.setAddedDate(new Date());
					itemValues.setAudit(audit);
					auditService.saveAuditItemValue(itemValues);
				}
			}
			responceDTO.setCode(200);
			responceDTO.setMessage("Added Successfully");
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		} catch (Exception e) {
			responceDTO.setCode(500);
			responceDTO.setMessage(e.getMessage());
			e.printStackTrace();
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		}
		}	
	
	
	@RequestMapping(value = "/getAudits", method = RequestMethod.GET)
	public @ResponseBody List<Audit> getAudits() {
		List<Audit> list= new  ArrayList<Audit>();
		try {	
			list=auditService.getAudits();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	@RequestMapping(value = "/getAuditStagesByMachine", method = RequestMethod.GET)
	public @ResponseBody List<AuditStageDto> getAuditStagesByMachine(@RequestParam("machineId") int machineId) {
		List<AuditStageDto> list= new  ArrayList<AuditStageDto>();
		try {	
			List<AuditStages>auditstages=auditService.getAuditStagesByMachine(machineId);
			for(AuditStages auditStag:auditstages){
				AuditStageDto auditStageDto= new AuditStageDto();
				auditStageDto.setAuditStageId(auditStag.getAuditStageId());
				auditStageDto.setMaximumScore(auditStag.getMaximumScore());
				auditStageDto.setStageName(auditStag.getStageName());
				auditStageDto.setStageNo(auditStag.getStageNo());
				List<AuditItems> items	=auditService.getAuditStagesItemByStage(auditStag.getAuditStageId());
				List<AuditItemsDTO> auditItemsDTOs= new ArrayList<AuditItemsDTO>();
				for(AuditItems audititems:items){
					AuditItemsDTO auditItemsDTO= new AuditItemsDTO();
					auditItemsDTO.setAuditStagesItemId(audititems.getAuditStagesItemId());
					auditItemsDTO.setDescription(audititems.getDescription());
					auditItemsDTO.setFullScore(audititems.getFullScore());
					auditItemsDTO.setItemName(audititems.getItemName());
					auditItemsDTOs.add(auditItemsDTO);
					
				}
				auditStageDto.setItems(auditItemsDTOs);
				
				list.add(auditStageDto);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping(value = "/getAuditStage2sByMachine", method = RequestMethod.GET)
	public @ResponseBody List<AuditStages> getAuditStage2sByMachine(@RequestParam("machineId") int machineId) {
		List<AuditStages> list= new  ArrayList<AuditStages>();
		try {	
			list=auditService.getAuditStagesByMachine(machineId);
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping(value = "/getAuditStagesItemByStage", method = RequestMethod.GET)
	public @ResponseBody List<AuditItems> getAuditStagesItemByStage(@RequestParam("stageId") int stageId) {
		List<AuditItems> list= new  ArrayList<AuditItems>();
		try {	
			list=auditService.getAuditStagesItemByStage(stageId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	
	
	@RequestMapping(value = "/addAuditStage", method = RequestMethod.POST)
	public ResponseEntity<ResponceObj> addAuditStage(@RequestBody AuditStages auditStages) {
		ResponceObj responceDTO= new ResponceObj();
		try {		
			auditStages.setActive(1);
			auditStages.setAddedDate(new Date());
			auditService.saveAudiitStage(auditStages);
			responceDTO.setCode(200);
			responceDTO.setMessage("Audit Stage Added Successfully");
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		} catch (Exception e) {
			responceDTO.setCode(500);
			responceDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		}
		}	
	
	@RequestMapping(value = "/getAuditStages", method = RequestMethod.GET)
	public @ResponseBody List<AuditStages> getAuditStages() {
		List<AuditStages> list= new  ArrayList<AuditStages>();
		try {	
			list=auditService.getAuditStages();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

	@RequestMapping(value = "/addAuditItem", method = RequestMethod.POST)
	public ResponseEntity<ResponceObj> addAuditItem(@RequestBody AuditItemsSaveDto auditItemsDto) {
		ResponceObj responceDTO= new ResponceObj();
		try {		
			for(AuditItems auditItems:auditItemsDto.getItems()){
				auditItems.setAuditStage(auditItemsDto.getAuditStage());
				auditItems.setMachine(auditItemsDto.getMachine());
				auditItems.setActive(1);
				auditItems.setAddecDate(new Date());
				auditService.saveAuditItems(auditItems);

			}
			responceDTO.setCode(200);
			responceDTO.setMessage("Audit Item Added Successfully");
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		} catch (Exception e) {
			responceDTO.setCode(500);
			responceDTO.setMessage(e.getMessage());
			return new ResponseEntity<ResponceObj>(responceDTO,HttpStatus.ACCEPTED)	;
		}
		}	
	
	@RequestMapping(value = "/getAuditItems", method = RequestMethod.GET)
	public @ResponseBody List<AuditItems> getAuditItems() {
		List<AuditItems> list= new  ArrayList<AuditItems>();
		try {	
			list=auditService.getAuditItems();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	
	
}
