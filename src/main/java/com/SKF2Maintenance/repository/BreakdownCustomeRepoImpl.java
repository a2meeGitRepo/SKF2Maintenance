package com.SKF2Maintenance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.SKF2Maintenance.model.AMCheckPoints;
import com.SKF2Maintenance.model.AMCheckPointsValue;
import com.SKF2Maintenance.model.Breakdown;

public class BreakdownCustomeRepoImpl implements BreakdownCustomeRepo {
	@PersistenceContext
	EntityManager entityManager;
	public List<Breakdown> getlistBreakdownByLimit(int page_no, int item_per_page) {
		// TODO Auto-generated method stub
		long result =  (Long) entityManager.createQuery("SELECT count(b) FROM Breakdown b where b.deleteBit=0").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("FROM Breakdown b where b.deleteBit=0", Breakdown.class);
		int firstR = total_count - (page_no * item_per_page);
		int maxR = total_count - ((page_no - 1) * item_per_page);
		if(firstR<0) {
			firstR=0;
		}
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<Breakdown> list = q.getResultList();
		return list;
	}

	public List<Breakdown> getlistBreakdowntByLimitAndSearch(String searchText, int pageNo, int perPage) {
		// TODO Auto-generated method stub
		long result = (Long) entityManager.createQuery("SELECT count(b) FROM Breakdown b where (b.breakdownno LIKE :searchText OR  b.machine.machineName LIKE :searchText OR  b.machine.assetNumber LIKE :searchText) and b.deleteBit=0").setParameter("searchText", "%"+searchText+"%").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from Breakdown b where (b.breakdownno LIKE :searchText OR  b.machine.machineName LIKE :searchText OR  b.machine.assetNumber LIKE :searchText) and b.deleteBit=0", Breakdown.class);
		int firstR = total_count - (pageNo * perPage);
		int maxR = total_count - ((pageNo - 1) * perPage);

		if(firstR<0) {
			firstR=0;
		}
		q.setParameter("searchText", "%"+searchText+"%");
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<Breakdown> list = q.getResultList();
		return list;
	}

}
