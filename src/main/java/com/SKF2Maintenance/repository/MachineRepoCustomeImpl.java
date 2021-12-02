package com.SKF2Maintenance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.SKF2Maintenance.model.Machine;


public class MachineRepoCustomeImpl implements MachineRepoCustome {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<Machine> getListMachineByLimitAndSerach(String searchText, int pageNo, int perPage) {
		
				long result =  (Long) entityManager.createQuery("SELECT count(m) FROM Machine m where m.machineName LIKE :searchText OR  m.assetNumber LIKE :searchText and m.deleteBit=0").setParameter("searchText", "%"+searchText+"%").getSingleResult();
				int total_count=(int) result;
				Query q = entityManager.createQuery("from Machine m where m.machineName LIKE :searchText OR  m.assetNumber LIKE :searchText  and m.deleteBit=0", Machine.class);
				int firstR = total_count - (pageNo * perPage);
				int maxR = total_count - ((pageNo - 1) * perPage);

				if(firstR<0) {
					firstR=0;
				}
				q.setParameter("searchText", "%"+searchText+"%");
				q.setFirstResult(firstR); // modify this to adjust paging
				q.setMaxResults(maxR);
				
				List<Machine> list = q.getResultList();
				return list;
	}

	public List<Machine> getlistMachineByLimit(int page_no, int item_per_page) {
		long result =  (Long) entityManager.createQuery("SELECT count(m) FROM Machine m where m.deleteBit=0").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from Machine m where m.deleteBit=0  ", Machine.class);
		int firstR = total_count - (page_no * item_per_page);
		int maxR = total_count - ((page_no - 1) * item_per_page);
		
		if(firstR<0) {
			firstR=0;
		}
	
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<Machine> list = q.getResultList();
		return list;
	}

}
 