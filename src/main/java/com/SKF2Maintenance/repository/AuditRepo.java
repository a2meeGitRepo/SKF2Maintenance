package com.SKF2Maintenance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.SKF2Maintenance.model.Audit;

public interface AuditRepo extends JpaRepository<Audit, Integer> {

}
