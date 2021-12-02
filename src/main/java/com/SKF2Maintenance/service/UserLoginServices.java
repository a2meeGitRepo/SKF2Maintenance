/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.service;

import java.util.List;

import com.SKF2Maintenance.dto.LoginResponce;
import com.SKF2Maintenance.model.Role;
import com.SKF2Maintenance.model.User;

/**
 * @author lenovo
 *
 */
public interface UserLoginServices {

	
	List<User> getUserList(int page_no, int item_per_page);

	
	LoginResponce loginUser(User user);

	void saveUser(User user);


	List<Role> getAllRole();


	User getUserById(int comById);


	User getUserByEmployeeId(int employeeId);


	List<User> getlistUserByLimitAndSearch(String searchText, int pageNo, int perPage);


	int getUserCount();


	int getUserCountAndSearch(String searchText);


	List<User> getAllUsers();


	void deletUser(User user);


	

}
