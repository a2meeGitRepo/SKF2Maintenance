package com.SKF2Maintenance.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SKF2Maintenance.model.Branch;
import com.SKF2Maintenance.model.Breakdown;
import com.SKF2Maintenance.model.Department;
import com.SKF2Maintenance.service.CommonServices;

@RestController
@CrossOrigin("*")
@RequestMapping("/common")
public class CommonController {
	
	
	@Autowired
	CommonServices commonServices;
	@RequestMapping(value = "/getAllDepartment", method = RequestMethod.GET)
	public @ResponseBody List<Department>  getAllDepartment() {
		List<Department> departments= new ArrayList<Department>();
		try {
			departments= commonServices.getAllDepartment();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return departments;
	}
	
	
	@RequestMapping(value = "/getAllBranch", method = RequestMethod.GET)
	public @ResponseBody List<Branch>  getAllBranch() {
		List<Branch> branchs= new ArrayList<Branch>();
		try {
			branchs= commonServices.getAllBranch();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return branchs;
	}
	
}
