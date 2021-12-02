package com.SKF2Maintenance.service;

import com.SKF2Maintenance.dto.ResponceObject;

public interface EmailSmsService {
	ResponceObject sendMail(String recipient,String subject,String message);
	ResponceObject sendSMS(String phoneNo,String message);
}
