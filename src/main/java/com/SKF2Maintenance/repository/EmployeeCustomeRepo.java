/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.repository;

import java.util.List;

import com.SKF2Maintenance.model.Employee;

/**
 * @author lenovo
 *
 */
public interface EmployeeCustomeRepo {
	List<Employee> getlistEmployeeByLimit(int page_no, int item_per_page);
	int getEmployeesCount();
	List<Employee> getlistEmployeeByLimitAndSearch(String searchText, int pageNo, int perPage);

}
