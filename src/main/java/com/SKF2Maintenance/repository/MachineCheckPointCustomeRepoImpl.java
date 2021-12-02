package com.SKF2Maintenance.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.model.MachineCheckPoint;

public class MachineCheckPointCustomeRepoImpl implements MachineCheckPointCustomeRepo {

	@PersistenceContext
	EntityManager entityManager;


	public List<MachineCheckPoint> getlistMaintanceCheckPointListByLimitAndSearch(String searchText, int pageNo,
			int perPage) {
		// TODO Auto-generated method stub
		long result =  (Long) entityManager.createQuery("SELECT count(m) FROM MachineCheckPoint m where (m.checkPointName LIKE :searchText OR m.machine.machineName LIKE :searchText OR  m.machine.assetNumber LIKE :searchText) and m.deleteBit=0").setParameter("searchText", "%"+searchText+"%").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from MachineCheckPoint m where (m.checkPointName LIKE :searchText OR m.machine.machineName LIKE :searchText OR  m.machine.assetNumber LIKE :searchText) and m.deleteBit=0", MachineCheckPoint.class);
		int firstR = total_count - (pageNo * perPage);
		int maxR = total_count - ((pageNo - 1) * perPage);

		if(firstR<0) {
			firstR=0;
		}
		q.setParameter("searchText", "%"+searchText+"%");
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<MachineCheckPoint> list = q.getResultList();
		return list;
	}

	public List<MachineCheckPoint> getlistMaintanceCheckPointByLimit(int page_no, int item_per_page) {
		// TODO Auto-generated method stub
		long result =  (Long) entityManager.createQuery("SELECT count(m) FROM MachineCheckPoint m where m.deleteBit=0").getSingleResult();
		int total_count=(int) result;
		Query q = entityManager.createQuery("from MachineCheckPoint m where m.deleteBit=0  ", MachineCheckPoint.class);
		int firstR = total_count - (page_no * item_per_page);
		int maxR = total_count - ((page_no - 1) * item_per_page);
		
		if(firstR<0) {
			firstR=0;
		}
	
		q.setFirstResult(firstR); // modify this to adjust paging
		q.setMaxResults(maxR);
		
		List<MachineCheckPoint> list = q.getResultList();
		return list;
	}

}
