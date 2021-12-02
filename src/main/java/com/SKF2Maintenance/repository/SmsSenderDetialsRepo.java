package com.SKF2Maintenance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.config.SmsSenderDetials;

public interface SmsSenderDetialsRepo extends JpaRepository<SmsSenderDetials, Integer>{
	@Query("From SmsSenderDetials s where s.active=1")
	Optional<SmsSenderDetials> setAcriveSender();

}
