package com.SKF2Maintenance.service;

import java.util.List;

import com.SKF2Maintenance.model.Branch;
import com.SKF2Maintenance.model.Department;

public interface CommonServices {

	List<Department> getAllDepartment();

	List<Branch> getAllBranch();

}
