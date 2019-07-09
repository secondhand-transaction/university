package utils;

import java.util.Properties;
import entity.Auth;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	private Properties props;// ÏµÍ³ï¿½ï¿½ï¿½ï¿½
	private Session mailSession;// ï¿½Ê¼ï¿½ï¿½á»°ï¿½ï¿½ï¿½ï¿½
	private MimeMessage mimeMsg; // MIMEï¿½Ê¼ï¿½ï¿½ï¿½ï¿½ï¿½


	public SendMail(String SMTPHost, String Port, String MailUsername, String MailPassword) {
		Auth au = new Auth();
		props = java.lang.System.getProperties(); // ï¿½ï¿½ï¿½ÏµÍ³ï¿½ï¿½ï¿½Ô¶ï¿½ï¿½ï¿?
		props.put("mail.smtp.host", SMTPHost); // ï¿½ï¿½ï¿½ï¿½SMTPï¿½ï¿½ï¿½ï¿½
		props.put("mail.smtp.port", Port); // ï¿½ï¿½ï¿½Ã·ï¿½ï¿½ï¿½Ë¿Úºï¿?
		props.put("mail.smtp.auth", "true");// Í¬Ê±Í¨ï¿½ï¿½ï¿½ï¿½Ö¤
		props.put("username", "dpqwh@163.com");
		props.put("mail.debug", "true");
		// ï¿½ï¿½ï¿½ï¿½Ê¼ï¿½ï¿½á»°ï¿½ï¿½ï¿½ï¿? 
		mailSession = Session.getInstance(props, au);
	}
  
	public boolean sendingMimeMail(String MailFrom, String MailTo,
			String MailCopyTo, String MailBCopyTo, String MailSubject,
			String MailBody) {
		try { 
			mimeMsg = new MimeMessage(mailSession);
			mimeMsg.setFrom(new InternetAddress(MailFrom));
//				mimeMsg.addRecipients(MimeMessage.RecipientType.TO,
//						InternetAddress.parse(MailTo));
//				mimeMsg.addRecipients(Message.RecipientType.TO, InternetAddress
//						.parse(MailTo));
//				mimeMsg.addRecipients(MimeMessage.RecipientType.CC,
//						InternetAddress.parse(props.getProperty("username")));
				mimeMsg.addRecipients(Message.RecipientType.TO,MailTo);
				mimeMsg.addRecipients(MimeMessage.RecipientType.CC, InternetAddress.parse(props.getProperty("username")));
//				mimeMsg.setRecipients(javax.mail.Message.RecipientType.CC,
//						InternetAddress.parse(props.getProperty("username")));
//				mimeMsg.setRecipients(javax.mail.Message.RecipientType.BCC,
//						InternetAddress.parse(props.getProperty("username")));

			mimeMsg.setSubject(MailSubject, "gb2312");
			// ï¿½ï¿½ï¿½ï¿½ï¿½Ê¼ï¿½ï¿½ï¿½ï¿½Ý£ï¿½ï¿½ï¿½ï¿½Ê¼ï¿½bodyï¿½ï¿½ï¿½ï¿½×ªï¿½ï¿½ÎªHTMLï¿½ï¿½Ê½
			mimeMsg.setContent(MailBody, "text/html;charset=gb2312");
			//mimeMsg.setDataHandler(new javax.activation.DataHandler(
				//	new StringDataSource(MailBody, "text/html")));
			// ï¿½ï¿½ï¿½ï¿½ï¿½Ê¼ï¿½
			Transport.send(mimeMsg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean sendmailChangepass(String userNumber, String mailto,String userid,String PIN){
		String MailTo=mailto;
		String MailSubject="Ð£Ô°¶þÊÖ½»Ò×Æ½Ì¨";
		String MailBCopyTo="";
	    String MailCopyTo="";
	    String MailBody= "<br>" + 
	    		"<br>" + 
	    		"ÓÃ»§:"+userNumber+ 
	    		"</br>" + 
	    		"<br>" +
	    		"ÐÞ¸ÄÃÜÂë:"+
	    		"<a href=http://localhost:8080/university/changepass.jsp?id="+userid+"&key="+ PIN +">ÐÞ¸ÄÃÜÂë</a>"+
	    		"</br>" +
	    		"Çë²»Òª»Ø¸´</br>" ;
		String SMTPHost = "smtp.163.com";
		String Port="25";
		String MailUsername = "dpqwh@163.com";
		String MailPassword = "sr1998120";
		String MailFrom = "dpqwh@163.com";
		if(SMTPHost==null||SMTPHost==""||MailUsername==null||MailUsername==""||MailPassword==null||MailPassword==""||MailFrom==null||MailFrom=="")
		{
			System.out.println("Servlet parameter Wrongs");
		}
		SendMail send=new SendMail(SMTPHost,Port,MailUsername,MailPassword);
		if(send.sendingMimeMail(MailFrom, MailTo, MailCopyTo, MailBCopyTo, MailSubject, MailBody)){
			return true;
		}
		else
		{
			return false;
		}
	}

}
