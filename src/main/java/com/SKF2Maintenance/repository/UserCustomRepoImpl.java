package com.SKF2Maintenance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.SKF2Maintenance.model.User;

public class UserCustomRepoImpl implements UserCustomRepo {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<User> getUserList(int page_no, int item_per_page) {
		// TODO Auto-generated method stub
		long result =  (Long) entityManager.createQuery("SELECT count(u) FROM User u where u.deletBit=0").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from User where  deletBit=0", User.class);
		int firstR = total_count - (page_no * item_per_page);
		int maxR = total_count - ((page_no - 1) * item_per_page);
		

		if(firstR<0) {
			firstR=0;
		}
	
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<User> list = q.getResultList();
		return list;
	}

	public List<User> getlistUserByLimitAndSearch(String searchText, int pageNo, int perPage) {
		// TODO Auto-generated method stub
long result =  (Long) entityManager.createQuery("SELECT count(e) FROM User e where e.userName LIKE :searchText OR  e.firstName LIKE :searchText OR e.lastName LIKE :searchText and e.deletBit=0").setParameter("searchText", "%"+searchText+"%").getSingleResult();

		
		int total_count=(int) result;
		Query q = entityManager.createQuery("from User e where e.userName LIKE :searchText OR  e.firstName LIKE :searchText OR e.lastName LIKE :searchText and e.deletBit=0", User.class);
		int firstR = total_count - (pageNo * perPage);
		int maxR = total_count - ((pageNo - 1) * perPage);
		

		if(firstR<0) {
			firstR=0;
		}
		q.setParameter("searchText", "%"+searchText+"%");
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<User> list = q.getResultList();
		return list;
	}
	
	

}
