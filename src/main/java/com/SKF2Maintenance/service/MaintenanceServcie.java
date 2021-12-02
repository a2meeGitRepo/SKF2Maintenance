package com.SKF2Maintenance.service;

import java.util.List;
import java.util.Optional;

import com.SKF2Maintenance.model.MachineCheckPoint;
import com.SKF2Maintenance.model.Maintenance;
import com.SKF2Maintenance.model.MaintenanceDoneCheckPoint;
import com.SKF2Maintenance.model.Task;
import com.SKF2Maintenance.model.TaskHead;

public interface MaintenanceServcie {

	void addMaintenance(Maintenance maintenance);

	void addMaintenanceDoneCheckPoint(MaintenanceDoneCheckPoint maintenanceCheckPoint);


	List<Maintenance> getTodayMaintenanceByMachine(int machineId);

	List<Maintenance> getThisWeekMaintenanceDownByMachine(int machineId);

	List<MaintenanceDoneCheckPoint> getMaintenanceDoneCheckPointByMaintenance(int maintenanceId);

	List<Maintenance> getAllPendingMaintenanceForMachine(int machineId);

	List<Maintenance> getAllCloseMaintenanceForUser(int machineId);

	List<Maintenance> getAllMaintenance();

	List<Maintenance> getPendingMaintenance();

	List<Maintenance> getClosedMaintenance();

	List<MachineCheckPoint> getMachineCheckPointByMachine(int machineId);

	List<Maintenance> getDoneMaintenanceDownByMachine(int machineId, String userId);

	Optional<TaskHead> getTaskHeadByNameAndMachine(String taskHead, int machineId);

	TaskHead saveTaskHead(TaskHead head2);

	Optional<Task> getTaskByNameAndTaskHead(String task, int taskHeadId);

	Task saveTask(Task task3);

	Optional<Task> getTBMTaskByMachineAndTaskName(String task, int machineId);

	List<Maintenance> getAllPendingMaintenance();

	List<Maintenance> getAllCloseMaintenance();

	List<Maintenance> getThisWeekMaintenance();

	List<Maintenance> getTodayMaintenance();

}
