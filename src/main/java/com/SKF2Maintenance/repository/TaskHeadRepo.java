package com.SKF2Maintenance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.TaskHead;

public interface TaskHeadRepo  extends JpaRepository<TaskHead, Integer>{
	@Query("from TaskHead t where t.taskHeadName=?1 and t.machine.machineId=?2")
	Optional<TaskHead> getTaskHeadByNameAndMachine(String taskHead, int machineId);

}
