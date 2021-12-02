/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.dto.LoginResponce;
import com.SKF2Maintenance.model.Role;
import com.SKF2Maintenance.model.User;
import com.SKF2Maintenance.repository.RoleRepo;
import com.SKF2Maintenance.repository.UserRepo;

/**
 * @author lenovo
 *
 */
@Transactional
@Service
public class UserLoginServicesImpl implements UserLoginServices {

	@Autowired
	UserRepo userRepo;
	@Autowired
	RoleRepo roleRepo;
	
	

	/* (non-Javadoc)
	 * @see com.net.service.UserLoginServices#loginUser(com.net.model.User)
	 */
	 
	public LoginResponce loginUser(User user) {
		// TODO Auto-generated method stub
		LoginResponce loginResponce= new LoginResponce();
		Optional<User>  users= userRepo.getUserByUserName(user.getUserName());
		if(users.isPresent()){
			Optional<User> validUser= userRepo.getUserByUserNameAndPassword(user.getUserName(),user.getPassword());
			System.out.println("ValidUser : "+validUser.isPresent());
			if(validUser.isPresent()){
				if(validUser.get().getActive()==0){
					loginResponce.setResponceCode(500);
					loginResponce.setResponceMsg("Inactive User ");
				}else{
					loginResponce.setResponceCode(200);
					loginResponce.setResponceMsg("Login successfully");
					validUser.get().setUserType(validUser.get().getRole().getRoleName());
					loginResponce.setData(validUser.get());
				}
				
			}else{

				loginResponce.setResponceCode(500);
				loginResponce.setResponceMsg("Invalid Password");
			}
		}else{
			loginResponce.setResponceCode(500);
			loginResponce.setResponceMsg("Invalid Username");
		}
		return loginResponce;
	}

	/* (non-Javadoc)
	 * @see com.net.service.UserLoginServices#saveUser(com.net.model.User)
	 */
	 
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		userRepo.save(user);
	}

	 
	public List<Role> getAllRole() {
		// TODO Auto-generated method stub
		return roleRepo.findAll();
	}

	 
	public User getUserById(int comById) {
		// TODO Auto-generated method stub
		Optional<User> optionals = userRepo.findById(comById);
		return optionals.isPresent()?optionals.get():null;
	}

	 
	public User getUserByEmployeeId(int employeeId) {
		// TODO Auto-generated method stub 
		Optional<User> optional=userRepo.getUserByEmployeeId(employeeId);
		return optional.isPresent()?optional.get():null;
	}

	 
	public List<User> getUserList(int page_no, int item_per_page) {
		// TODO Auto-generated method stub
		return userRepo.getUserList(page_no, item_per_page);
	}

	 
	public List<User> getlistUserByLimitAndSearch(String searchText, int pageNo, int perPage) {
		// TODO Auto-generated method stub
		return userRepo.getlistUserByLimitAndSearch(searchText,  pageNo,  perPage);
	}

	 
	public int getUserCount() {
		// TODO Auto-generated method stub
		return userRepo.getUserCount();
	}

	 
	public int getUserCountAndSearch(String searchText) {
		// TODO Auto-generated method stub
		return userRepo.getUserCountAndSearch(searchText);
	}

	 
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepo.getAllUsers();
	}

	 
	public void deletUser(User user) {
		// TODO Auto-generated method stub
		userRepo.delete(user);
	}

}
