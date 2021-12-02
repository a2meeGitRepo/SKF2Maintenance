/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SKF2Maintenance.model.Employee;


/**
 * @author lenovo
 *
 */
public interface EmployeeRepo extends JpaRepository<Employee, Integer>,EmployeeCustomeRepo{

	@Query("from Employee e where e.managerId=?1")
	List<Employee> getAllEmployeeByManager(int employeeId);

	@Query("from Employee e where e.designation.designationId=2")
	List<Employee> getAllManagers();
	@Query("from Employee e where e.designation.designationId=3")
	List<Employee> getAllEngineers();
	@Query("from Employee e where e.uhfCode=?1")
	Optional<Employee> getAllEmployeeByUhfNo(String uhfCardNo);
	@Query("from Employee e where e.active=1")
	List<Employee> getAllAvtiveEmployee();
	@Query("from Employee e where e.uhfCode=?1")
	Optional<Employee> getEmployeeByuhfCode(String uhfCode);
	@Query("from Employee e where e.emoloyeeCode=?1")
	Optional<Employee> getEmployeeByEmployeeCode(String employeeCode);

	@Query("from Employee e where e.emailId=?1")
	Optional<Employee> getUsersEmailByEmailId(String emailId);

	@Query("from Employee e where e.fName=?1 and e.lName=?2")
	Optional<Employee> getEmployeeIdByName(String getfName, String getlName);
	
	@Query("select count(*) from Employee e where e.active=1")
	int getAllEmployeeCount();
	@Query("select count(*) from Employee e where e.fName  LIKE %:searchText% OR e.emoloyeeCode  LIKE %:searchText% OR e.uhfCode LIKE %:searchText%")
	int getEmployeesCountAndSearch(@Param("searchText") String searchText);


	


	

}
