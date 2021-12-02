package com.SKF2Maintenance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.MachineOwner;

@Service
public class MachineOwnerCustomeRepoImpl implements MachineOwnerCustomeRepo {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<MachineOwner> getListMachineOwnerByLimitAndSearch(String searchText, int pageNo, int perPage) {
		
		long result = (Long) entityManager.createQuery("SELECT count(m) FROM MachineOwner m where m.machine.name LIKE :searchText OR  m.machine.assetNumber LIKE :searchText and m.deleteBit=0").setParameter("searchText", "%"+searchText+"%").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from MachineOwner m where m.machine.name LIKE :searchText OR  m.machine.assetNumber LIKE :searchText  and m..deleteBit=0", MachineOwner.class);
		int firstR = total_count - (pageNo * perPage);
		int maxR = total_count - ((pageNo - 1) * perPage);

		if(firstR<0) {
			firstR=0;
		}
		q.setParameter("searchText", "%"+searchText+"%");
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<MachineOwner> list = q.getResultList();
		return list;
	}

	public List<MachineOwner> getlistMachineOwnerByLimit(int page_no, int item_per_page) {
		
		long result =  (Long) entityManager.createQuery("SELECT count(m) FROM MachineOwner m where m.deleteBit=0").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from MachineOwner m where m.deleteBit=0  ", MachineOwner.class);
		int firstR = total_count - (page_no * item_per_page);
		int maxR = total_count - ((page_no - 1) * item_per_page);
		
		if(firstR<0) {
			firstR=0;
		}
	
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<MachineOwner> list = q.getResultList();
		return list;
	}

}
