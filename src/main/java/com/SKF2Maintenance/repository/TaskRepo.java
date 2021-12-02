package com.SKF2Maintenance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.Task;

public interface TaskRepo extends JpaRepository<Task, Integer> {
	@Query("from Task t  where t.taskName=?1 and t.taskHeadId=?2")
	Optional<Task> getTaskByNameAndTaskHead(String task, int taskHeadId);
	@Query("from Task t  where t.taskName=?1 and t.machineId=?2 and t.taskType='TBM'")
	Optional<Task> getTBMTaskByMachineAndTaskName(String task, int machineId);

}
