package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import com.SKF2Maintenance.model.Machine;
import com.SKF2Maintenance.model.MachineCheckPoint;
import com.SKF2Maintenance.model.MachineOwner;

public interface MachineService {

	Optional<Machine> getMachineById(int machineId);

	void addMachine(Machine machine);

	List<Machine> getlistMachineByLimit(int page_no, int item_per_page);

	List<Machine> getlistMachineByLimitAndSearch(String searchText, int pageNo, int perPage);

	int getMachineCount();

	int getMachineCountAndSearch(String searchText);

	List<Machine> getAllMachines();

	boolean checkAssetNumber(String assetNumber);

	void addMachineCheckPoint(MachineCheckPoint machineCheckPoint);

	List<MachineCheckPoint> getlistMaintanceCheckPointByLimit(int page_no, int item_per_page);

	List<MachineCheckPoint> getlistMaintanceCheckPointListByLimitAndSearch(String searchText, int pageNo, int perPage);

	int getMachienChekPointCount();

	int getMachienChekPointCountBySearch(String searchText);

	Optional<MachineCheckPoint> getMachineCheckPointByMachineIdAndCheckPointId(int machineId, String checkPointName);

	Optional<MachineOwner> getMachineOwnerByid(int id);

	void addMachineOwner(MachineOwner machineOwner);

	List<MachineOwner> getAllMachineOwner();

	List<MachineOwner> getlistMachineOwnerByLimit(int page_no, int item_per_page);

	List<MachineOwner> getlistMachineOwnerByLimitAndSearch(String searchText, int pageNo, int perPage);

	int getMachineOwnerCountAndSearch(String searchText);

	List<MachineOwner> getAllMachineOwnersByUser(int userId);

	Machine getMachineAssetNumber(String assetNumber);

	Optional<Machine> getMachineByAssetNumber(String assetNumber);

	Optional<Machine> getMachineByName(String machineName);

	Machine saveMachine(Machine machine2);

	List<MachineOwner> getMachineOwnersByMachine(int machieId);

	List<Machine> getAllMachinesChannelNotNull();

	List<Machine> getAllMachineByChannerNo(String channelNo);

	List<Machine> getMachinesByame(String machineName);


}
