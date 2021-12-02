/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//import com.net.dto.EmployeeDetailsEngineerDto;
//import com.net.dto.EmployeeDetailsManagerDto;
import com.SKF2Maintenance.dto.ResponceObject;
//import com.net.model.AssetAllocation;
//import com.net.model.Complaint;
import com.SKF2Maintenance.model.Employee;
import com.SKF2Maintenance.model.Role;
import com.SKF2Maintenance.model.User;
import com.SKF2Maintenance.repository.EmployeeRepo;
//import com.net.service.AssetTransactionServices;
//import com.net.service.ComplaintServices;
import com.SKF2Maintenance.service.EmailSmsService;
//import com.net.service.EmailSmsServiceImpl;
import com.SKF2Maintenance.service.EmployeeService;
import com.SKF2Maintenance.service.UserLoginServices;

/**
 * @author lenovo
 *
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/employee")
public class EmployeeController {
		
	@Autowired
	EmployeeService employeeService;
	@Autowired
	UserLoginServices userLoginServices;
	@Autowired
	EmailSmsService emailSmsService;
//	@Autowired
//	AssetTransactionServices assetTransactionServices;
//	@Autowired
//	ComplaintServices complaintServices;
	
	@Autowired
	EmployeeRepo employeeRepo;
	
	@RequestMapping(value = "/addNewEmployee", method = RequestMethod.POST)
	public @ResponseBody ResponceObject addEmployee(@RequestBody Employee employee) {
		ResponceObject responceObject = new ResponceObject();
		try {
			Employee emp=new Employee();
			
			if(employee.getUpdDatetime()!=null) {
				emp.setUpdDatetime(employee.getUpdDatetime());	
				
				User user=userLoginServices.getUserByEmployeeId(employee.getEmployeeId());
				user.setFirstName(employee.getfName());
				user.setLastName(employee.getlName());
				
				userLoginServices.saveUser(user);
			}else {
				emp.setUpdDatetime(employee.getUpdDatetime());	
			}
			
			employeeService.addEmployee(employee);
			
			
			Employee emlpoyee1=employeeService.getEmployeeById(employee.getEmployeeId());
			//User user=userLoginServices.getUserByEmployeeId(emlpoyee1.getEmployeeId());
			if(employee.getUpdDatetime()==null) {
				if(!employee.getEmailId().equalsIgnoreCase("")){
				String message="<h5> Hello"+emlpoyee1.getTitle()+" "+emlpoyee1.getfName()+" "+emlpoyee1.getlName()+",</h5> Your registration  have been successfull <br><br> "+"<br><br>"+"Regards<br>";
				//System.out.println("pass :"+ user.getPassword().toString());
				emailSmsService.sendMail(emlpoyee1.getEmailId(), "Registration has been Successfull", message);
				}
				responceObject.setCode(200);
				responceObject.setMsg("Employee Added Successfully");
			}else {
				
					if(!employee.getEmailId().equalsIgnoreCase("")){
					String message="<h5> Welcome "+emlpoyee1.getTitle()+" "+emlpoyee1.getfName()+" "+emlpoyee1.getlName()+",</h5> Your data  have been edited successfully <br><br> "+"<br><br>"+"Regards<br>";
					//System.out.println("pass :"+ user.getPassword().toString());
					emailSmsService.sendMail(emlpoyee1.getEmailId(), "Data has been edited Successfully", message);
					}
					responceObject.setCode(200);
					responceObject.setMsg("selected employee data has been updated Successfully");
			
		}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.toString());
			responceObject.setCode(500);
			responceObject.setMsg("Something Wrong");
		}
		return responceObject;
	}
	
	
	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST)
	public @ResponseBody ResponceObject changeStatus(@RequestBody Employee employee) {
		ResponceObject responceObject = new ResponceObject();
		try {
			employeeService.addEmployee(employee);
			User user=userLoginServices.getUserByEmployeeId(employee.getEmployeeId());
			
			if(employee.getUserCreated()==1) {
				
				if(employee.getActive()==1) {				
					user.setActive(1);
				}else{				
					user.setActive(0);
					}
			userLoginServices.saveUser(user);
			}		
			
			
			
			responceObject.setCode(200);
			responceObject.setMsg("Status Changed Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.toString());
			responceObject.setCode(500);
			responceObject.setMsg("Something Wrong , please try again");
		}
		return responceObject;
	}
	
	@RequestMapping(value = "/getAllEmployee", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployee() {
		List<Employee> list = null;
		try {
			list = employeeService.getAllEmployee();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	@RequestMapping(value = "/getAllAvtiveEmployee", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllAvtiveEmployee() {
		List<Employee> list = null;
		try {
			list = employeeService.getAllAvtiveEmployee();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/getAllEmployeeByManager", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEmployeeByManager(@RequestParam("employeeId") int employeeId) {
		List<Employee> list = null;
		try {
			list = employeeService.getAllEmployeeByManager(employeeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping(value = "/getAllManagers", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllManagers() {
		List<Employee> list = null;
		try {
			list = employeeService.getAllManagers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping(value = "/getAllEngineers", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getAllEngineers() {
		List<Employee> list = null;
		try {
			list = employeeService.getAllEngineers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/getlistEmployeeByLimit/{page_no}/{item_per_page}", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getlistEmployeeByLimit(@PathVariable("page_no") int page_no,@PathVariable("item_per_page") int item_per_page) {
		List<Employee> list= new  ArrayList<Employee>();
		try {	
			list=employeeService.getlistEmployeeByLimit(page_no,item_per_page);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@RequestMapping(value = "/getlistEmployeeByLimitAndSearch", method = RequestMethod.GET)
	public @ResponseBody List<Employee> getlistEmployeeByLimitAndSearch(@RequestParam("searchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("perPage") int perPage) {
		List<Employee> list= new  ArrayList<Employee>();
		try {	
			
			list=employeeService.getlistEmployeeByLimitAndSearch(searchText,pageNo,perPage);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	@RequestMapping(value = "/getEmployeesCount", method = RequestMethod.GET)
	public @ResponseBody int  getEmployeesCount() {
		int  supplierCount= 0;
		try {
			supplierCount= employeeService.getEmployeesCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplierCount;
	}
	@RequestMapping(value = "/getEmployeesCountAndSearch", method = RequestMethod.GET)
	public @ResponseBody int  getEmployeesCountAndSearch(@RequestParam("searchText") String searchText) {
		int  supplierCount= 0;
		try {
			supplierCount= employeeService.getEmployeesCountAndSearch(searchText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return supplierCount;
	}
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public @ResponseBody ResponceObject createUser(@RequestBody Employee employee) {
		ResponceObject responceObject = new ResponceObject();
		try {
			User  user = new User();
					user.setActive(1);
					user.setAddedDate(new Date());
					//user.setBranch(1);
					user.setFirstName(employee.getfName());
					user.setLastName(employee.getlName());
					user.setPassword("Reset123");
					user.setEmployeeId(employee.getEmployeeId());
					user.setAddedBy(employee.getAddedBy());
					Role role = new Role();
					System.out.println("Des :: "+employee.getDesignation().getDesignationId());
					
					if(employee.getDesignation().getDesignationId()==2){
						role.setRoleId(4);
					}else if (employee.getDesignation().getDesignationId()==3){
						role.setRoleId(3);
					}else{
						role.setRoleId(2);
					}
					
					
					user.setBranch(employee.getBranch());
					user.setRole(role);
					user.setUpdatedDatetime(new Date());
					user.setUserName(employee.getEmoloyeeCode());
					user.setAddedBy(employee.getAddedBy());
					userLoginServices.saveUser(user);
			
					employee.setUserCreated(1);
					employeeService.addEmployee(employee);
					if(!employee.getEmailId().equalsIgnoreCase("")){
						String message="<h5> Hello "+employee.getTitle()+" "+employee.getfName()+" "+employee.getlName()+",</h5> Your User Registration has done Successfully ,Please refer following detials <br><br>  UserName :"+user.getUserName()+"<br> Password : "+user.getPassword()+"<br>";
						emailSmsService.sendMail(employee.getEmailId(), "User Registration Sucessfull", message);
					}
					
			responceObject.setCode(200);
			responceObject.setMsg("User Created Successfully");
		} catch (Exception e) {
			System.err.println(e.toString());
			responceObject.setCode(500);
			responceObject.setMsg("Something Wrong");
		}
		return responceObject;
	}
	
	
	@RequestMapping(value = "/checkUhfCode", method = RequestMethod.GET)
	public @ResponseBody boolean  checkUhfCode(@RequestParam("uhfCode") String uhfCode) {
		boolean  uhfCodeISAvailable= false;
		try {
			uhfCodeISAvailable= employeeService.checkUhfCode(uhfCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return uhfCodeISAvailable;
	}
	@RequestMapping(value = "/checkEmployeeCode", method = RequestMethod.GET)
	public @ResponseBody boolean  checkEmployeeCode(@RequestParam("employeeCode") String employeeCode) {
		boolean employeeCodeISAvailable= false;
		try {
			employeeCodeISAvailable= employeeService.checkEmployeeCode(employeeCode);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employeeCodeISAvailable;
	}
	
	/********************get employee details by employee Id****************************/
	
	@RequestMapping(value = "/getEmployeedetailsByEmployeeId", method = RequestMethod.GET)
	public @ResponseBody Employee  getEmployeedetailsByEmployeeId(@RequestParam("employeeId") int employeeId) {
		Employee employee=null;
		try {
			 employee= employeeService.getEmployeeById(employeeId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
	@RequestMapping(value = "/getEmployeeDetailsUserApp", method = RequestMethod.GET)
	public @ResponseBody Employee  getEmployeeDetailsUserApp(@RequestParam("employeeCode") String employeeCode) {
		Employee employee= new Employee();
		try {
			Employee employee1= employeeService.getEmployeeByCode(employeeCode);
			employee.setfName(employee1.getfName());
			employee.setTitle(employee1.getTitle());
			employee.setlName(employee1.getlName());
			employee.setEmoloyeeCode(employee1.getEmoloyeeCode());
			employee.setDesignation(employee1.getDesignation());
			employee.setDepartment(employee1.getDepartment());
			employee.setDateOfBirth(employee1.getDateOfBirth());
			employee.setEmailId(employee1.getEmailId());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employee;
	}
//	@RequestMapping(value = "/getEmployeeDetailsMangerApp", method = RequestMethod.GET)
//	public @ResponseBody EmployeeDetailsManagerDto  getEmployeeDetailsMangerApp(@RequestParam("employeeCode") String employeeCode) {
//		EmployeeDetailsManagerDto employeeDetailsDto = new EmployeeDetailsManagerDto();
//		try {
//			Employee employee= new  Employee();
//			Employee employee1= employeeService.getEmployeeByCode(employeeCode);
//			employee.setfName(employee1.getfName());
//			employee.setTitle(employee1.getTitle());
//			employee.setlName(employee1.getlName());
//			employee.setEmoloyeeCode(employee1.getEmoloyeeCode());
//			employee.setDesignation(employee1.getDesignation());
//			employee.setDepartment(employee1.getDepartment());
//			employee.setDateOfBirth(employee1.getDateOfBirth());
//			employee.setEmailId(employee1.getEmailId());
//			employeeDetailsDto.setEmployee(employee);
//			List<AssetAllocation> assets= assetTransactionServices.getAllocatedAssetByEmployee(employee1.getEmployeeId());
//			employeeDetailsDto.setAssets(assets);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return employeeDetailsDto;
//	}
//	@RequestMapping(value = "/getEmployeeDetailsEngineerApp", method = RequestMethod.GET)
//	public @ResponseBody EmployeeDetailsEngineerDto  getEmployeeDetailsEngineerApp(@RequestParam("employeeCode") String employeeCode) {
//		EmployeeDetailsEngineerDto employeeDetailsEngineerDto = new EmployeeDetailsEngineerDto();
//		try {
//			Employee employee= new  Employee();
//			Employee employee1= employeeService.getEmployeeByCode(employeeCode);
//			employee.setfName(employee1.getfName());
//			employee.setTitle(employee1.getTitle());
//			employee.setlName(employee1.getlName());
//			employee.setEmoloyeeCode(employee1.getEmoloyeeCode());
//			employee.setDesignation(employee1.getDesignation());
//			employee.setDepartment(employee1.getDepartment());
//			employee.setDateOfBirth(employee1.getDateOfBirth());
//			employee.setEmailId(employee1.getEmailId());
//			employeeDetailsEngineerDto.setEmployee(employee);
//			List<Complaint> complaints= complaintServices.getAllComplaintByEmployee(employee1.getEmployeeId());
//			employeeDetailsEngineerDto.setComplaints(complaints);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return employeeDetailsEngineerDto;
//	}
//	
/*************************** Forget Password *******************************************/
	
	@RequestMapping(value = "/eMailList", method = RequestMethod.GET)
	public @ResponseBody User getEmployeeEmail(@RequestParam("emailId") String emailId) {
		User user = new User();
		try {
			Optional<Employee> userList = employeeRepo.getUsersEmailByEmailId(emailId);
			System.out.println("userlist : "+userList.isPresent());
			if(userList.isPresent()) {
				user=userLoginServices.getUserByEmployeeId(userList.get().getEmployeeId());
				
			}else {
				user=null;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
/******************** Update Password in database **********************/
	
	@RequestMapping(value = "/updateByForgetPass", method = RequestMethod.POST)
	public @ResponseBody ResponceObject updateByForgetPass(@RequestBody User user) {
		ResponceObject responceObject = new ResponceObject();
		try {
			System.out.println("user password :"+user.getPassword().toString());
			Employee employee=employeeService.getEmployeeById(user.getEmployeeId());
			
			User user2=userLoginServices.getUserByEmployeeId(user.getEmployeeId());
			
			
					if(!employee.getEmailId().equalsIgnoreCase("")){
						String message="<h5> Hello "+employee.getTitle()+" "+employee.getfName()+" "+employee.getlName()+",</h5> Your password has been  upadated , Please refer the following detials <br><br>  UserName :"+user2.getUserName()+"<br> Password : "+user.getPassword()+"<br><br>"+"Regards<br>";
						System.out.println("pass :"+ user.getPassword().toString());
						emailSmsService.sendMail(employee.getEmailId(), "Password has changed Successfully", message);
					}
					
					
			user2.setPassword(user.getPassword());
			userLoginServices.saveUser(user2);
					
			responceObject.setCode(200);
			responceObject.setMsg("Password Updated Successfully");
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println(e.toString());
			responceObject.setCode(500);
			responceObject.setMsg("Something Wrong");
		}
		return responceObject;
	}
}
