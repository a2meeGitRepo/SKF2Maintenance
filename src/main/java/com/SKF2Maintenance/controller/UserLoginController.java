/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.SKF2Maintenance.dto.LoginResponce;
import com.SKF2Maintenance.dto.ResponceObject;
import com.SKF2Maintenance.dto.UserDto;
import com.SKF2Maintenance.model.Employee;
import com.SKF2Maintenance.model.Role;
import com.SKF2Maintenance.model.User;
import com.SKF2Maintenance.repository.UserRepo;
import com.SKF2Maintenance.service.EmailSmsService;
import com.SKF2Maintenance.service.EmployeeService;
import com.SKF2Maintenance.service.UserLoginServices;


 
/**
 * @author lenovo
 *
 */

@RestController
@CrossOrigin("*")
@RequestMapping("/userLogin")
public class UserLoginController {
	
	@Autowired
	UserLoginServices userLoginServices;
	@Autowired
	UserRepo userRepo;
	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmailSmsService emailSmsService;
	/**********************************User By limit only************************************/
	@RequestMapping(value = "/list/{page_no}/{item_per_page}", method = RequestMethod.GET)
	public @ResponseBody List<User> getUsers(@PathVariable("page_no") int page_no,@PathVariable("item_per_page") int item_per_page) {
		List<User> userList = null;
		try {
			userList = userLoginServices.getUserList(page_no,item_per_page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	/*******************getAllUsers*****************************************************/
	@RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
	public @ResponseBody List<User> getAllUsers() {
		List<User> userList = null;
		try {
			userList = userLoginServices.getAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	/**************************User BY limit and searchText********************************/
	@RequestMapping(value = "/getlistUserByLimitAndSearch", method = RequestMethod.GET)
	public @ResponseBody List<User> getlistUserByLimitAndSearch(@RequestParam("searchText") String searchText,@RequestParam("pageNo") int pageNo,@RequestParam("perPage") int perPage) {
		List<User> userList = null;
		try {
			userList = userLoginServices.getlistUserByLimitAndSearch(searchText,pageNo,perPage);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	/***********************************User count  ****************************************/
	@RequestMapping(value = "/getUserCount", method = RequestMethod.GET)
	public @ResponseBody int  getUserCount() {
		int userCount=0;
		try {
			userCount = userLoginServices.getUserCount();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userCount;
	}
	/**************************************************************************************/
	
	/***********************************User count by search text ****************************************/
	@RequestMapping(value = "/getUserCountAndSearch", method = RequestMethod.GET)
	public @ResponseBody int getUserCountAndSearch(@RequestParam("searchText") String searchText) {
		int userCount=0;
		try {
			userCount = userLoginServices.getUserCountAndSearch(searchText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userCount;
	}
	/**************************************************************************************/
	@RequestMapping(value = "/getAllRole", method = RequestMethod.GET)
	public @ResponseBody List<Role> getAllRole() {
		List<Role> roleList = null;
		try {
			roleList = userLoginServices.getAllRole();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleList;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody LoginResponce loginUser(@RequestBody User user) {
	System.out.println("User code:" + user.getUserName() + " Password:" + user.getPassword());
		LoginResponce responce = new LoginResponce();
		try {
			responce = userLoginServices.loginUser(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponceObject addUser(@RequestBody User user) {
	//System.out.println("User code:" + user.getUserName() + " Password:" + user.getPassword());
	ResponceObject responce = new ResponceObject();
		try {
			//Employee employee=employeeService.getEmployeeById(user.getEmployeeId());
		/*	System.out.println("user save");
			if(user.getUpdatedDatetime()!=null) {
				userLoginServices.saveUser(user);
				responce.setCode(200);
				responce.setMsg("User data have been saved Successfully");
			}
			else {
				
				String message="<h5> Hello "+employee.getTitle()+" "+employee.getfName()+" "+employee.getlName()+",</h5> Your User data has been updated ,Please refer following detials <br><br>  UserName :"+user.getUserName()+"<br> Password : "+user.getPassword()+"<br>"+"<br>Regards,<br>";
				emailSmsService.sendMail(employee.getEmailId(), "User has been Updated", message);
				
				responce.setCode(200);
				responce.setMsg("Selected user has been updated successfully");
			}*/
			userLoginServices.saveUser(user);
			responce.setCode(200);
			responce.setMsg("User data have been saved Successfully");
		
		} catch (Exception e) {
			e.printStackTrace();
			responce.setCode(500);
			responce.setMsg("Something Wrong");
		}
		return responce;
	}
	
	@RequestMapping(value = "/mobilelogin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody LoginResponce mobilelogin(@RequestBody User user) {
	System.out.println("User code:" + user.getUserName() + " Password:" + user.getPassword());
		LoginResponce responce = new LoginResponce();
		try {
			responce = userLoginServices.loginUser(user);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responce;
	}
	
/**************************************************user by id*****************************************************************/
	
	@RequestMapping(value = "/getUserById", method = RequestMethod.GET)
	public @ResponseBody User getUserById(@RequestParam ("userId") int userId) {
		User user=new User();
		try {
			user=userLoginServices.getUserById(userId);
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		
		return user;
	}

/*************************************************My profile Update By mobile********************************************************************/	
	

	@RequestMapping(value = "/deletUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponceObject deletUser(@RequestBody User user) {
	//System.out.println("User code:" + user.getUserName() + " Password:" + user.getPassword());
	ResponceObject responce = new ResponceObject();
		try {
			
				userLoginServices.deletUser(user);
				responce.setCode(200);
				responce.setMsg("User deleted Successfully");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			responce.setCode(500);
			responce.setMsg("Something Wrong");
		}
		return responce;
	}
	

	@RequestMapping(value = "/changeStatus", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponceObject changeStatus(@RequestBody User user) {
	//System.out.println("User code:" + user.getUserName() + " Password:" + user.getPassword());
	ResponceObject responce = new ResponceObject();
		try {
			
				if(user.getActive()==1){
					user.setActive(0);
				}else{
					user.setActive(1);
				}
				userLoginServices.saveUser(user);
				responce.setCode(200);
				responce.setMsg("Status Change  Successfully");
			
			
		} catch (Exception e) {
			e.printStackTrace();
			responce.setCode(500);
			responce.setMsg("Something Wrong");
		}
		return responce;
	}
	
}