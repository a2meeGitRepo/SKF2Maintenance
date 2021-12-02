/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.Employee;
import com.SKF2Maintenance.repository.EmployeeRepo;

/**
 * @author lenovo
 *
 */
@Transactional
@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	@Autowired
	EmployeeRepo employeeRepo;
	
	
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepo.findAll();
	}


	 
	public void addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepo.save(employee);
	}

	/* (non-Javadoc)
	 * @see com.net.service.EmployeeService#getAllEmployeeByManager(int)
	 */
	 
	public List<Employee> getAllEmployeeByManager(int employeeId) {
		// TODO Auto-generated method stub
		return employeeRepo.getAllEmployeeByManager(employeeId);
	}


	/* (non-Javadoc)
	 * @see com.net.service.EmployeeService#getAllManagers()
	 */
	 
	public List<Employee> getAllManagers() {
		// TODO Auto-generated method stub
		return employeeRepo.getAllManagers();
	}


	/* (non-Javadoc)
	 * @see com.net.service.EmployeeService#getlistEmployeeByLimit(int, int)
	 */
	 
	public List<Employee> getlistEmployeeByLimit(int page_no, int item_per_page) {
		// TODO Auto-generated method stub
		return employeeRepo.getlistEmployeeByLimit(page_no,item_per_page);
	}


	
	 
	public int getEmployeesCount() {
		// TODO Auto-generated method stub
		return employeeRepo.getEmployeesCount();
	}


	 
	public Employee getEmployeeById(int requested_by) {
		// TODO Auto-generated method stub
		Optional<Employee> emps= employeeRepo.findById(requested_by);
		System.out.println("employee is null" +emps.isPresent());
		return emps.isPresent()?emps.get():null;
	}


	 
	public List<Employee> getAllEngineers() {
		// TODO Auto-generated method stub
		return employeeRepo.getAllEngineers();
	}


	 
	public Employee getEmployeeByUHFCode(String uhfCardNo) {
		// TODO Auto-generated method stub
		Optional<Employee> optionals = employeeRepo.getAllEmployeeByUhfNo(uhfCardNo);
		return optionals.isPresent()?optionals.get():null;
	}


	 
	public List<Employee> getAllAvtiveEmployee() {
		// TODO Auto-generated method stub
		return employeeRepo.getAllAvtiveEmployee();
	}


	 
	public boolean checkUhfCode(String uhfCode) {
		// TODO Auto-generated method stub
		Optional<Employee> optional= employeeRepo.getEmployeeByuhfCode(uhfCode);
		System.out.println("UHF CODE :: PRESENT :: "+optional.isPresent());
		if(optional.isPresent()){
			return true;
		}else{
			return false;
		}
	}


	 
	public boolean checkEmployeeCode(String employeeCode) {
		// TODO Auto-generated method stub
		Optional<Employee> optional= employeeRepo.getEmployeeByEmployeeCode(employeeCode);
		System.out.println("EMP CODE :: PRESENT :: "+optional.isPresent());
		if(optional.isPresent()){
			return true;
		}else{
			return false;
		}
	}


	 
	public Employee getEmployeeByCode(String employeeCode) {
		// TODO Auto-generated method stub
		Optional<Employee> optional= employeeRepo.getEmployeeByEmployeeCode(employeeCode);
		return optional.isPresent()?optional.get():null;
	}


	


	 
	public Employee getEmployeeIdByName(String getfName, String getlName) {
		// TODO Auto-generated method stub
		Optional<Employee> optional=employeeRepo.getEmployeeIdByName(getfName,getlName);
		return null;
	}


	 
	public int getAllEmployeeCount() {
		// TODO Auto-generated method stub
		return employeeRepo.getAllEmployeeCount() ;
	}


	 
	public List<Employee> getlistEmployeeByLimitAndSearch(String searchText, int pageNo, int perPage) {
		// TODO Auto-generated method stub
		return employeeRepo.getlistEmployeeByLimitAndSearch(searchText,pageNo,perPage);
	}


	 
	public int getEmployeesCountAndSearch(String searchText) {
		// TODO Auto-generated method stub
		return employeeRepo.getEmployeesCountAndSearch(searchText);
	}


	


	

}
