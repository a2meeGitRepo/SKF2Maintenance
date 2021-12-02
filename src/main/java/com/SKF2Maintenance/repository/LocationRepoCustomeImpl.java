package com.SKF2Maintenance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.SKF2Maintenance.model.Location;

public class LocationRepoCustomeImpl implements LocationRepoCustome{

	@PersistenceContext
	EntityManager entityManager;
	
	public List<Location> getlistLocationByLimit(int page_no, int item_per_page) {
		long result =(Long) entityManager.createQuery("SELECT count(l) FROM Location l where l.deleteBit=0").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from Location l where l.deleteBit=0  ", Location.class);
		int firstR = total_count - (page_no * item_per_page);
		int maxR = total_count - ((page_no - 1) * item_per_page);
		if(firstR<0) {
			firstR=0;
		}
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<Location> list = q.getResultList();
		return list;
	}

	public List<Location> getlistLocationByLimitAndSearch(String searchText, int pageNo, int perPage) {
		long result =  (Long) entityManager.createQuery("SELECT count(l) FROM Location l where l.locationName LIKE :searchText OR  l.locationCode LIKE :searchText and l.deleteBit=0").setParameter("searchText", "%"+searchText+"%").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from Location l where l.locationName LIKE :searchText OR  l.locationCode LIKE :searchText  and l.deleteBit=0", Location.class);
		int firstR = total_count - (pageNo * perPage);
		int maxR = total_count - ((pageNo - 1) * perPage);

		if(firstR<0) {
			firstR=0;
		}
		q.setParameter("searchText", "%"+searchText+"%");
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<Location> list = q.getResultList();
		return list;
	}

}
