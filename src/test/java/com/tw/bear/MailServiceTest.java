package com.tw.bear;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.tw.bear.service.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {
	
	@Resource
	private MailService mailService;
	
	@Test
	public void sendMail() {
		mailService.sendSimpleMail("bear813@126.com", "这是我的第一个邮箱信息", "mail hello!!!");
		System.out.println(123);
	}
	
	@Test
	public void sendHtmlMail() throws MessagingException {
		String content = "<html>\n"+"<body>\n"+"<h2> hello mail</h2>"+"</body>\n"+"</html>";
		mailService.sendHtmlMail("bear813@126.com", "这是我的第一个html邮箱信息", content);
	}
	
	@Test
	public void sendAttachMail() throws MessagingException {
		String filepath = "E:/WORKSPACE2/MailBoot.zip";
		mailService.sendAttachMail("bear813@126.com", "这是我的第一个带附件的邮箱信息", "带附件的邮件内容",filepath);
		
	}
	@Test
	public void sendInlineResourceMail() throws MessagingException {
		String imgPath ="C:\\Users\\whltw\\Desktop\\000.jpg";
		String rscId="mail001";
		String content="<html><body>这是带有图片的邮件<img src=\'cid:"+rscId+"\'/></body></html>";
		mailService.sendInlineResourceMail("bear813@126.com", "这是我的第一个带图片的邮箱信息", content, imgPath, rscId);
	}
}
