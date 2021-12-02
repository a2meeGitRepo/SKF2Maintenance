package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.model.MachineCheckPoint;
import com.SKF2Maintenance.model.MachineOwner;
import com.SKF2Maintenance.repository.MachineCheckPointRepo;
import com.SKF2Maintenance.repository.MachineOwnerRepo;
import com.SKF2Maintenance.repository.MachineRepo;

@Service
public class MachineServiceImpl implements MachineService{
	
	@Autowired 
	MachineRepo machineRepo;
	@Autowired
	MachineCheckPointRepo machineCheckPointRepo;
	@Autowired
	MachineOwnerRepo machineOwnerRepo;

	public Optional<Machine> getMachineById(int machineId) {
		// TODO Auto-generated method stub
		return machineRepo.findById(machineId);
	}

	public void addMachine(Machine machine) {
		// TODO Auto-generated method stub
		machineRepo.save(machine);
	}

	public List<Machine> getlistMachineByLimit(int page_no, int item_per_page) {
		// TODO Auto-generated method stub
		return machineRepo.getlistMachineByLimit(page_no,item_per_page);
	}

	public List<Machine> getlistMachineByLimitAndSearch(String searchText, int pageNo, int perPage) {
		// TODO Auto-generated method stub
		return machineRepo.getListMachineByLimitAndSerach(searchText, pageNo, perPage);
	}

	public int getMachineCount() {
		// TODO Auto-generated method stub
		return machineRepo.countUndelet();
	}

	public int getMachineCountAndSearch(String searchText) {
		// TODO Auto-generated method stub
		return machineRepo.getMachineCountAndSerach(searchText);
	}

	public List<Machine> getAllMachines() {
		// TODO Auto-generated method stub
		return machineRepo.getAllMachines();
	}

	public boolean checkAssetNumber(String assetNumber) {
		// TODO Auto-generated method stub
		Optional<Machine> optional= machineRepo.getMachineByAssetNumber(assetNumber);
		return optional.isPresent()?true:false;
	}

	public void addMachineCheckPoint(MachineCheckPoint machineCheckPoint) {
		// TODO Auto-generated method stub
		machineCheckPointRepo.save(machineCheckPoint);
	}

	public List<MachineCheckPoint> getlistMaintanceCheckPointByLimit(int page_no, int item_per_page) {
		// TODO Auto-generated method stub
		return machineCheckPointRepo.getlistMaintanceCheckPointByLimit(page_no,item_per_page);
	}

	public List<MachineCheckPoint> getlistMaintanceCheckPointListByLimitAndSearch(String searchText, int pageNo,
			int perPage) {
		// TODO Auto-generated method stub
		return machineCheckPointRepo.getlistMaintanceCheckPointListByLimitAndSearch(searchText,pageNo,perPage);
	}

	public int getMachienChekPointCount() {
		// TODO Auto-generated method stub
		return machineCheckPointRepo.getMachienChekPointCount();
	}

	public int getMachienChekPointCountBySearch(String searchText) {
		// TODO Auto-generated method stub
		return machineCheckPointRepo.getMachienChekPointCountBySearch(searchText);
	}

	public Optional<MachineCheckPoint> getMachineCheckPointByMachineIdAndCheckPointId(int machineId, String checkPointName) {
		// TODO Auto-generated method stub
		return machineCheckPointRepo.getMachineCheckPointByMachineIdAndCheckPointId(machineId,checkPointName);
	}

	public Optional<MachineOwner> getMachineOwnerByid(int id) {
		// TODO Auto-generated method stub
		return machineOwnerRepo.findById(id);
	}

	public void addMachineOwner(MachineOwner machineOwner) {
		// TODO Auto-generated method stub
		machineOwnerRepo.save(machineOwner);
	}

	public List<MachineOwner> getAllMachineOwner() {
		// TODO Auto-generated method stub
		return machineOwnerRepo.getAllMachineOwner();
	}

	public List<MachineOwner> getlistMachineOwnerByLimit(int page_no, int item_per_page) {
		// TODO Auto-generated method stub
		return machineOwnerRepo.getlistMachineOwnerByLimit(page_no, item_per_page);
	}

	public List<MachineOwner> getlistMachineOwnerByLimitAndSearch(String searchText, int pageNo, int perPage) {
		// TODO Auto-generated method stub
		return machineOwnerRepo.getListMachineOwnerByLimitAndSearch(searchText, pageNo, perPage);
	}

	public int getMachineOwnerCountAndSearch(String searchText) {
		// TODO Auto-generated method stub
		return machineOwnerRepo.getMachineOwnerCountAndSearch(searchText);
	}

	public List<MachineOwner> getAllMachineOwnersByUser(int userId) {
		// TODO Auto-generated method stub
		return machineOwnerRepo.getAllMachineOwnersByUser(userId);
	}

	@Override
	public Machine getMachineAssetNumber(String assetNumber) {
		// TODO Auto-generated method stub
		Optional<Machine> optional=machineRepo.getMachineByAssetNumber(assetNumber);
		System.out.println("OPTIONAL :: "+optional.isPresent());
		return optional.isPresent()?optional.get():null;
	}

	@Override
	public Optional<Machine> getMachineByAssetNumber(String assetNumber) {
		// TODO Auto-generated method stub
		return machineRepo.getMachineByAssetNumber(assetNumber);
	}

	@Override
	public Optional<Machine> getMachineByName(String machineName) {
		// TODO Auto-generated method stub
		return machineRepo.getMachineByName(machineName);
	}

	@Override
	public Machine saveMachine(Machine machine2) {
		// TODO Auto-generated method stub
		return machineRepo.save(machine2);
	}

	@Override
	public List<MachineOwner> getMachineOwnersByMachine(int machieId) {
		// TODO Auto-generated method stub
		return machineOwnerRepo.getMachineOwnersByMachine(machieId);
	}

	@Override
	public List<Machine> getAllMachinesChannelNotNull() {
		// TODO Auto-generated method stub
		return machineRepo.getAllMachinesChannelNotNull();
	}

	@Override
	public List<Machine> getAllMachineByChannerNo(String channelNo) {
		// TODO Auto-generated method stub
		return machineRepo.getAllMachineByChannerNo(channelNo);
	}

	@Override
	public List<Machine> getMachinesByame(String machineName) {
		// TODO Auto-generated method stub
		return machineRepo.getMachinesByame(machineName);
	}

	
}
