package com.example.demo.utilities;


import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
	
	
	//helpful: https://stackoverflow.com/questions/38608089/could-not-connect-to-smtp-host-smtp-gmail-com-port-587-nested-exception-is?rq=1
	
	public static boolean SendEmail(EmailMessage message) {
		
		Properties props = new Properties();
		
		String host = "smtp.gmail.com";
		
		String username = "teamcalitate@gmail.com";
		String password = "2FactorAuth";
		
		props.put("mail.smtp.host", host);
		
		props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.user", username);
        
        
		Session session = Session.getDefaultInstance(props);
		
		try 
		{
			MimeMessage mimeMessage = new MimeMessage(session);
			
			mimeMessage.setFrom(message.getFromAddr());
			mimeMessage.addRecipient(Message.RecipientType.TO,  new InternetAddress(message.getToAddr()));
			mimeMessage.setSubject(message.getSubject());
			mimeMessage.setText(message.getMsgBody());
			
			Transport t = session.getTransport("smtp");
			t.connect(username, password);
			t.sendMessage(mimeMessage, mimeMessage.getAllRecipients());
			t.close();
			
			return true;
		}
		catch(MessagingException e) {
			
			e.printStackTrace();
			
			return false;
		}
	}
}
