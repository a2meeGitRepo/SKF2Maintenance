package com.SKF2Maintenance.service;

import java.util.Optional;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SKF2Maintenance.config.EmailSenderDetials;
import com.SKF2Maintenance.config.EmailUtility;
//import com.net.config.SMSUtility;
import com.SKF2Maintenance.config.SmsSenderDetials;
import com.SKF2Maintenance.dto.ResponceObject;
import com.SKF2Maintenance.dto.SendEmailData;
import com.SKF2Maintenance.dto.SendSMSData;
import com.SKF2Maintenance.repository.EmailSenderDetialsRepo;
import com.SKF2Maintenance.repository.SmsSenderDetialsRepo;


@Service
public class EmailSmsServiceImpl implements EmailSmsService{
	@Autowired
	SmsSenderDetialsRepo smsSenderDetialsRepo;
	
	@Autowired
	EmailSenderDetialsRepo emailSenderDetialsRepo;

	public ResponceObject sendMail(String recipient, String subject, String message) {
		// TODO Auto-generated method stub
		ResponceObject object = new ResponceObject();
		Optional<EmailSenderDetials> senders=emailSenderDetialsRepo.getActiveSender();
		if(senders.isPresent()){
			EmailUtility emailUtility = new EmailUtility();
			SendEmailData sendEmailData= new SendEmailData();
			sendEmailData.setDetials(senders.get());
			sendEmailData.setMessage(message+senders.get().getSigniture());
			sendEmailData.setRecipient(recipient);
			sendEmailData.setSubject(subject);
			try {
				emailUtility.sendEmail(sendEmailData);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				object.setCode(500);
				object.setMsg("Email Not Send :"+recipient);
			}
		}else{
			object.setCode(500);
			object.setMsg("Email Not Send :"+recipient);
		}
		return null;
	}

	public ResponceObject sendSMS(String phoneNo, String message) {
		// TODO Auto-generated method stub
		ResponceObject object = new ResponceObject();
		Optional<SmsSenderDetials> senders=smsSenderDetialsRepo.setAcriveSender();
		
		return null;
	}

}
