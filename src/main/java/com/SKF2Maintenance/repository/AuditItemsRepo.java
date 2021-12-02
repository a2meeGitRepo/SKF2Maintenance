package com.SKF2Maintenance.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.model.AuditItems;

public interface AuditItemsRepo extends JpaRepository<AuditItems, Integer> {
	@Query("from AuditItems a where a.auditStage.auditStageId=?1")
	List<AuditItems> getAuditStagesItemByStage(Object s);

}
