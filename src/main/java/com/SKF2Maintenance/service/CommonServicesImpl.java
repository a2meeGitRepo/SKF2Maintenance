package com.SKF2Maintenance.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.Branch;
import com.SKF2Maintenance.model.Department;
import com.SKF2Maintenance.repository.BranchRepo;
import com.SKF2Maintenance.repository.DepartmentRepo;

@Service
public class CommonServicesImpl implements CommonServices {
	@Autowired
	DepartmentRepo departmentRepo;
	@Autowired
	BranchRepo branchRepo;
	

	@Override
	public List<Department> getAllDepartment() {
		// TODO Auto-generated method stub
		return departmentRepo.findAll();
	}


	@Override
	public List<Branch> getAllBranch() {
		// TODO Auto-generated method stub
		return branchRepo.findAll();
	}

}
