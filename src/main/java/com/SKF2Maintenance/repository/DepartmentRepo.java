package com.SKF2Maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SKF2Maintenance.model.Department;

public interface DepartmentRepo  extends JpaRepository<Department, Integer>{

}
