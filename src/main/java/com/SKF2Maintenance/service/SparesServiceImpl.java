package com.SKF2Maintenance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.MachineSpares;
import com.SKF2Maintenance.model.Spare;
import com.SKF2Maintenance.repository.MachineSparesRepo;
import com.SKF2Maintenance.repository.SparesRepo;

@Transactional
@Service
public class SparesServiceImpl implements SparesService{

	@Autowired
	private SparesRepo spareRepo;
	@Autowired 
	MachineSparesRepo machineSparesRepo;

	 
	public Optional<Spare> getSpareById(int id) {
		return spareRepo.findById(id);
	}

	 
	public Spare addSpare(Spare Spare) {
		return spareRepo.save(Spare);
	}

	 
	public List<Spare> getlistSpareByLimit(int page_no, int item_per_page) {
		
		return spareRepo.getListSpareByLimit(page_no,item_per_page);
	}

	 
	public List<Spare> getlistSpareByLimitAndSearch(String searchText, int pageNo, int perPage) {
		
		return spareRepo.getListSpareByLimitAndSearch(searchText,pageNo,perPage);
	}

	 
	public int getSpareCount() {
		
		return (int)spareRepo.getSparesCount();
	}

	 
	public int getSpareCountAndSearch(String searchText) {
		
		return (int) spareRepo.getSpareCountAndSerach(searchText);
	}

	 
	public List<Spare> getAllSpare() {
		return spareRepo.findAll();
	}


	public List<Spare> getAllActiveSpares() {
		// TODO Auto-generated method stub
		return spareRepo.getAllActiveSpares() ;
	}


	@Override
	public List<Spare> getSpareByMachine(int machineId) {
		// TODO Auto-generated method stub
		List<Spare> spares= new ArrayList<Spare>();
		List<MachineSpares> list = machineSparesRepo.getMappedSparesByMachine(machineId);
		for(MachineSpares machineSpares:list){
			spares.add(machineSpares.getSpare());
		}
		return spares;
	}


	@Override
	public Optional<Spare> getSpareBySpareName(String spareName) {
		// TODO Auto-generated method stub
		return spareRepo.getSpareBySpareName(spareName);
	}
}
