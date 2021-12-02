/**
 * @Dattatray Bodhale
 */
package com.SKF2Maintenance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.SKF2Maintenance.model.Employee;


/**
 * @author lenovo
 *
 */
public class EmployeeCustomeRepoImpl implements EmployeeCustomeRepo {

	
	@PersistenceContext
	EntityManager entityManager;
	
	public List<Employee> getlistEmployeeByLimit(int page_no, int item_per_page) {
		// TODO Auto-generated method stub

		long result =  (Long) entityManager.createQuery("SELECT count(e) FROM Employee e").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from Employee e ", Employee.class);
		int firstR = total_count - (page_no * item_per_page);
		int maxR = total_count - ((page_no - 1) * item_per_page);
		

		if(firstR<0) {
			firstR=0;
		}
	
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<Employee> list = q.getResultList();
		return list;
	}

	
	public int getEmployeesCount() {
		// TODO Auto-generated method stub
		long result =  (Long) entityManager.createQuery("SELECT count(e) FROM Employee e ").getSingleResult();
		return (int) result;
	}


	public List<Employee> getlistEmployeeByLimitAndSearch(String searchText, int pageNo, int perPage) {
		// TODO Auto-generated method stub
		long result =  (Long) entityManager.createQuery("SELECT count(e) FROM Employee e where e.emoloyeeCode LIKE :searchText OR  e.fName LIKE :searchText OR e.uhfCode LIKE :searchText ").setParameter("searchText", "%"+searchText+"%").getSingleResult();

		
		int total_count=(int) result;
		Query q = entityManager.createQuery("from Employee e where e.emoloyeeCode LIKE :searchText OR  e.fName LIKE :searchText OR e.uhfCode LIKE :searchText ", Employee.class);
		int firstR = total_count - (pageNo * perPage);
		int maxR = total_count - ((pageNo - 1) * perPage);
		

		if(firstR<0) {
			firstR=0;
		}
		q.setParameter("searchText", "%"+searchText+"%");
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<Employee> list = q.getResultList();
		return list;
	}

}
