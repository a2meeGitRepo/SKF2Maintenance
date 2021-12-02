package com.SKF2Maintenance;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.SKF2Maintenance.config.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
public class Skf2MaintenanceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Skf2MaintenanceApplication.class, args);
	}

}
