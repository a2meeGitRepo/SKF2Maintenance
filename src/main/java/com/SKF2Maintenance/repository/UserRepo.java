/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.SKF2Maintenance.model.User;

/**
 * @author lenovo
 *
 */
public interface UserRepo extends JpaRepository<User, Integer> ,UserCustomRepo{

	@Query("from User u where u.userName=?1")
	Optional<User> getUserByUserName(String userName);
	@Query("from User u where u.userName=?1 and u.password=?2 ")
	Optional<User> getUserByUserNameAndPassword(String userName, String password);
	@Query("from User u where u.employeeId=?1")
	Optional<User> getUserByEmployeeId(int employeeId);
	@Query("select count(*) from User e where e.deletBit=0")
	int getUserCount();
	@Query("select count(*) from User e where  e.userName LIKE %:searchText% OR  e.firstName LIKE %:searchText% OR e.lastName LIKE %:searchText% and e.deletBit=0")
	int getUserCountAndSearch(@Param("searchText") String searchText);
	@Query("from User u")
	List<User> getAllUsers();
	
	

}
