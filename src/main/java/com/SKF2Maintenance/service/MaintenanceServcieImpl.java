package com.SKF2Maintenance.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.model.MachineCheckPoint;
import com.SKF2Maintenance.model.Maintenance;
import com.SKF2Maintenance.model.MaintenanceDoneCheckPoint;
import com.SKF2Maintenance.model.Task;
import com.SKF2Maintenance.model.TaskHead;
import com.SKF2Maintenance.repository.MachineCheckPointRepo;
import com.SKF2Maintenance.repository.MaintenanceDoneCheckPointRepo;
import com.SKF2Maintenance.repository.MaintenanceRepo;
import com.SKF2Maintenance.repository.TaskHeadRepo;
import com.SKF2Maintenance.repository.TaskRepo;

@Service
public class MaintenanceServcieImpl implements MaintenanceServcie{
	@Autowired
	MaintenanceRepo maintenanceRepo;
	@Autowired
	MaintenanceDoneCheckPointRepo maintenanceDoneCheckPointRepo;
	@Autowired
	MachineCheckPointRepo machineCheckPointRepo;
	@Autowired
	TaskHeadRepo taskHeadRepo;
	@Autowired
	TaskRepo taskRepo;

	public void addMaintenance(Maintenance maintenance) {
		// TODO Auto-generated method stub
		maintenanceRepo.save(maintenance);
	}

	public void addMaintenanceDoneCheckPoint(MaintenanceDoneCheckPoint maintenanceCheckPoint) {
		// TODO Auto-generated method stub
		maintenanceDoneCheckPointRepo.save(maintenanceCheckPoint);
	}

	

	public List<Maintenance> getTodayMaintenanceByMachine(int machineId) {
		// TODO Auto-generated method stub
		return maintenanceRepo.getTodayMaintenanceByMachine(machineId,new Date());
	}

	public List<Maintenance> getThisWeekMaintenanceDownByMachine(int machineId) {
		// TODO Auto-generated method stub
		Calendar calender=Calendar.getInstance();
		calender.setTime(new Date());
		//int dayOfWeek=calender.get(Calendar.DAY_OF_WEEK);
		calender.set(Calendar.DAY_OF_WEEK, calender.getFirstDayOfWeek());
		
		Calendar calender2= Calendar.getInstance();
		calender2.setTime(calender.getTime());
		calender2.add(Calendar.DATE, 6);
		
		
		return maintenanceRepo.getThisWeekMaintenanceDownByMachine(machineId,calender.getTime(),calender2.getTime());
	}

	public List<MaintenanceDoneCheckPoint> getMaintenanceDoneCheckPointByMaintenance(int maintenanceId) {
		// TODO Auto-generated method stub
		return maintenanceDoneCheckPointRepo.getMaintenanceDoneCheckPointByMaintenance(maintenanceId);
	}

	public List<Maintenance> getAllPendingMaintenanceForMachine(int machineId) {
		// TODO Auto-generated method stub
		return maintenanceRepo.getAllPendingMaintenanceForMachine(machineId);
	}

	public List<Maintenance> getAllCloseMaintenanceForUser(int machineId) {
		// TODO Auto-generated method stub
		return maintenanceRepo.getAllCloseMaintenanceForUser(machineId);
	}

	@Override
	public List<Maintenance> getAllMaintenance() {
		// TODO Auto-generated method stub
		return maintenanceRepo.findAll();
	}

	@Override
	public List<Maintenance> getPendingMaintenance() {
		// TODO Auto-generated method stub
		return maintenanceRepo.getPendingMaintenance();
	}

	@Override
	public List<Maintenance> getClosedMaintenance() {
		// TODO Auto-generated method stub
		return maintenanceRepo.getClosedMaintenance();
	}

	@Override
	public List<MachineCheckPoint> getMachineCheckPointByMachine(int machineId) {
		// TODO Auto-generated method stub
		return machineCheckPointRepo.getMachineCheckPointByMachine(machineId);
	}

	@Override
	public List<Maintenance> getDoneMaintenanceDownByMachine(int machineId, String userId) {
		// TODO Auto-generated method stub
		return maintenanceRepo.getDoneMaintenanceDownByMachine(machineId,userId);
	}

	@Override
	public Optional<TaskHead> getTaskHeadByNameAndMachine(String taskHead, int machineId) {
		// TODO Auto-generated method stub
		return taskHeadRepo.getTaskHeadByNameAndMachine(taskHead,machineId);
	}

	@Override
	public TaskHead saveTaskHead(TaskHead head2) {
		// TODO Auto-generated method stub
		return taskHeadRepo.save(head2);
	}

	@Override
	public Optional<Task> getTaskByNameAndTaskHead(String task, int taskHeadId) {
		// TODO Auto-generated method stub
		return taskRepo.getTaskByNameAndTaskHead(task,taskHeadId);
	}

	@Override
	public Task saveTask(Task task3) {
		// TODO Auto-generated method stub
		return taskRepo.save(task3);
	}

	@Override
	public Optional<Task> getTBMTaskByMachineAndTaskName(String task, int machineId) {
		// TODO Auto-generated method stub
		return taskRepo.getTBMTaskByMachineAndTaskName(task,machineId);
	}

	@Override
	public List<Maintenance> getAllPendingMaintenance() {
		// TODO Auto-generated method stub
		return maintenanceRepo.getAllPendingMaintenance();
	}

	@Override
	public List<Maintenance> getAllCloseMaintenance() {
		// TODO Auto-generated method stub
		return maintenanceRepo.getAllCloseMaintenance();
	}

	@Override
	public List<Maintenance> getThisWeekMaintenance() {
		// TODO Auto-generated method stub
		Calendar calender=Calendar.getInstance();
		calender.setTime(new Date());
		//int dayOfWeek=calender.get(Calendar.DAY_OF_WEEK);
		calender.set(Calendar.DAY_OF_WEEK, calender.getFirstDayOfWeek());
		
		Calendar calender2= Calendar.getInstance();
		calender2.setTime(calender.getTime());
		calender2.add(Calendar.DATE, 6);
		
		
		return maintenanceRepo.getThisWeekMaintenance(calender.getTime(),calender2.getTime());
	}

	@Override
	public List<Maintenance> getTodayMaintenance() {
		// TODO Auto-generated method stub
		return maintenanceRepo.getTodayMaintenance(new Date());
	}

}
