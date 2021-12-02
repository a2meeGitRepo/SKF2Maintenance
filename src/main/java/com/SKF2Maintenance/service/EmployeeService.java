/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.service;

import java.util.List;

import com.SKF2Maintenance.model.Employee;


/**
 * @author lenovo
 *
 */
public interface EmployeeService {


	List<Employee> getAllEmployee();

	void addEmployee(Employee employee);

	List<Employee> getAllEmployeeByManager(int employeeId);

	List<Employee> getAllManagers();
	List<Employee> getlistEmployeeByLimit(int page_no, int item_per_page);

	int getEmployeesCount();

	Employee getEmployeeById(int requested_by);

	List<Employee> getAllEngineers();

	Employee getEmployeeByUHFCode(String uhfCardNo);

	List<Employee> getAllAvtiveEmployee();

	boolean checkUhfCode(String uhfCode);

	boolean checkEmployeeCode(String employeeCode);

	Employee getEmployeeByCode(String employeeCode);

	

	Employee getEmployeeIdByName(String getfName, String getlName);

	int getAllEmployeeCount();

	List<Employee> getlistEmployeeByLimitAndSearch(String searchText, int pageNo, int perPage);

	int getEmployeesCountAndSearch(String searchText);

	

	

	

	

}
