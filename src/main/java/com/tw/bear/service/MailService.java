package com.tw.bear.service;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Value("${spring.mail.username}")
	private String from;
	
	@Autowired
	private JavaMailSender mailSender;
	public void sendSimpleMail(String to,String subject,String content) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		message.setFrom(from);
		mailSender.send(message);
	}
	
	public void sendHtmlMail(String to,String subject,String content) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content,true);
		helper.setFrom(from);
		mailSender.send(message);
	}
	
	public void sendAttachMail(String to,String subject,String content,String filePath) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content);
		helper.setFrom(from);
		FileSystemResource file = new FileSystemResource(new File(filePath));
		String filename = file.getFilename();
		//多个附件的上传
		helper.addAttachment(filename, file);
		helper.addAttachment(filename+".jsp", file);
		mailSender.send(message);
	}
	
	public void sendInlineResourceMail(String to,String subject,String content,String rscPath,String rscId) throws MessagingException {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content,true);
		helper.setFrom(from);
		FileSystemResource res = new FileSystemResource(new File(rscPath));
		helper.addInline(rscId, res);
		mailSender.send(message);
	}
	
}
	