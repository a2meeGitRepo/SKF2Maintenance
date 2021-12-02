package com.SKF2Maintenance.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.model.MachineSpindle;
import com.SKF2Maintenance.model.SpindleDetails;
import com.SKF2Maintenance.repository.MachineSpindleRepo;
import com.SKF2Maintenance.repository.SpindleDetailsRepo;

@Service
public class SpindleDetailsServiceImpl implements SpindleDetailsService {

	@Autowired
	private SpindleDetailsRepo spindleDetailsRepo;
	@Autowired
	MachineSpindleRepo machineSpindleRepo;
	public Optional<SpindleDetails> getSpindleById(int id) {
		return spindleDetailsRepo.findById(id);
	}

	public SpindleDetails addSpindle(SpindleDetails spindleDetails) {
		return spindleDetailsRepo.save(spindleDetails);
	}

	 
	public List<SpindleDetails> getlistSpindleByLimit(int page_no, int item_per_page) {
		
		return spindleDetailsRepo.getListSpindleByLimit(page_no,item_per_page);
	}

	 
	public List<SpindleDetails> getlistSpindleByLimitAndSearch(String searchText, int pageNo, int perPage) {
		
		return spindleDetailsRepo.getListSpindleByLimitAndSerach(searchText,pageNo,perPage);
	}

	 
	public int getSpindleCount() {
		
		return (int)spindleDetailsRepo.getSpindleCount();
	}

	 
	public int getSpindleCountAndSearch(String searchText) {
		
		return spindleDetailsRepo.getSpindleCoundAndSerach(searchText);
	}

	 
	public List<SpindleDetails> getAllSpindle() {

		return spindleDetailsRepo.findAll();
	}

	public List<SpindleDetails> getAllActiveSpindle() {
		// TODO Auto-generated method stub
		return spindleDetailsRepo.getAllActiveSpindle();
	}

	@Override
	public List<SpindleDetails> getSpindleByMmachine(int machineId) {
		// TODO Auto-generated method stub
		List<SpindleDetails>  details= new ArrayList<SpindleDetails>();
		List<MachineSpindle> machineSpindles= machineSpindleRepo.getMappedSpindleByMachine(machineId);
		for(MachineSpindle machineSpindle:machineSpindles){
			details.add(machineSpindle.getSpindle());
		}
		return details;
	}

	
}
