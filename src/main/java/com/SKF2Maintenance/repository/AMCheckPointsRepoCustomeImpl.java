package com.SKF2Maintenance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.SKF2Maintenance.model.AMCheckPoints;
import com.SKF2Maintenance.model.AMCheckPointsValue;

public class AMCheckPointsRepoCustomeImpl implements AMCheckPointsRepoCustome{

	@PersistenceContext
	EntityManager entityManager;
	
	public List<AMCheckPoints> getlistAmCheckPointByLimit(int page_no, int item_per_page) {
		long result =  (Long) entityManager.createQuery("SELECT count(a) FROM AMCheckPoints a where a.deleteBit=0").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from AMCheckPoints a where a.deleteBit=0  ", AMCheckPoints.class);
		int firstR = total_count - (page_no * item_per_page);
		int maxR = total_count - ((page_no - 1) * item_per_page);
		if(firstR<0) {
			firstR=0;
		}
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<AMCheckPoints> list = q.getResultList();
		return list;
	}

	public List<AMCheckPoints> getlistAMCheckPointsByLimitAndSearch(String searchText, int pageNo, int perPage) {
		
		long result =  (Long) entityManager.createQuery("SELECT count(a) FROM AMCheckPoints a where a.item LIKE :searchText OR  a.checkPointCode LIKE :searchText and a.deleteBit=0").setParameter("searchText", "%"+searchText+"%").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from AMCheckPoints a where a.item LIKE :searchText 	 and a.deleteBit=0", AMCheckPoints.class);
		int firstR = total_count - (pageNo * perPage);
		int maxR = total_count - ((pageNo - 1) * perPage);

		if(firstR<0) {
			firstR=0;
		}
		q.setParameter("searchText", "%"+searchText+"%");
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<AMCheckPoints> list = q.getResultList();
		return list;
	}

	public List<AMCheckPointsValue> getlistAMCheckPointValuesByLimit(int page_no, int item_per_page) {
		long result = (Long) entityManager.createQuery("SELECT count(a) FROM AMCheckPointsValue a where a.deleteBit=0").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from AMCheckPointsValue a where a.deleteBit=0  ", AMCheckPointsValue.class);
		int firstR = total_count - (page_no * item_per_page);
		int maxR = total_count - ((page_no - 1) * item_per_page);
		if(firstR<0) {
			firstR=0;
		}
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<AMCheckPointsValue> list = q.getResultList();
		return list;
	}

	public List<AMCheckPointsValue> getlistAMCheckPointValuesByLimitAndSearch(String searchText, int pageNo,int perPage) {
		long result = (Long) entityManager.createQuery("SELECT count(a) FROM AMCheckPointsValue a where a.chekPointsValue LIKE :searchText OR  a.status LIKE :searchText and a.deleteBit=0").setParameter("searchText", "%"+searchText+"%").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from AMCheckPointsValue a where a.chekPointsValue LIKE :searchText OR  a.status LIKE :searchText  and a.deleteBit=0", AMCheckPointsValue.class);
		int firstR = total_count - (pageNo * perPage);
		int maxR = total_count - ((pageNo - 1) * perPage);

		if(firstR<0) {
			firstR=0;
		}
		q.setParameter("searchText", "%"+searchText+"%");
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<AMCheckPointsValue> list = q.getResultList();
		return list;
	}

}
