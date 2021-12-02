package com.SKF2Maintenance.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.SKF2Maintenance.config.EmailSenderDetials;

public interface EmailSenderDetialsRepo extends JpaRepository<EmailSenderDetials, Integer> {
	@Query("From EmailSenderDetials e where e.active=1")
	Optional<EmailSenderDetials> getActiveSender();

}
