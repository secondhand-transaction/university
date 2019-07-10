package utils;

import java.util.Properties;
import entity.Auth;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	private Properties props;// ϵͳ����
	private Session mailSession;// �ʼ��Ự����
	private MimeMessage mimeMsg; // MIME�ʼ�����


	public SendMail(String SMTPHost, String Port, String MailUsername, String MailPassword) {
		Auth au = new Auth();
		props = java.lang.System.getProperties(); // ���ϵͳ���Զ���?
		props.put("mail.smtp.host", SMTPHost); // ����SMTP����
		props.put("mail.smtp.port", Port); // ���÷���˿ں�?
		props.put("mail.smtp.auth", "true");// ͬʱͨ����֤
		props.put("username", "hongfeng342@163.com");
		props.put("mail.debug", "true");
		// ����ʼ��Ự����? 
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
			// �����ʼ����ݣ����ʼ�body����ת��ΪHTML��ʽ
			mimeMsg.setContent(MailBody, "text/html;charset=gb2312");
			//mimeMsg.setDataHandler(new javax.activation.DataHandler(
				//	new StringDataSource(MailBody, "text/html")));
			// �����ʼ�
			Transport.send(mimeMsg);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean sendmailChangepass(String userNumber, String mailto,String userid,String PIN){
		String MailTo=mailto;
		String MailSubject="校园二手交易平台";
		String MailBCopyTo="";
	    String MailCopyTo="";
	    String MailBody= "<br>" + 
	    		"<br>" + 
	    		"用户:"+userNumber+ 
	    		"</br>" + 
	    		"<br>" +
	    		"修改密码:"+
	    		"<a href=http://localhost:8080/university/changepass.jsp?id="+userid+"&key="+ PIN +">修改密码</a>"+
	    		"</br>" +
	    		"请勿回复</br>" ;
		String SMTPHost = "smtp.163.com";
		String Port="25";
		String MailUsername = "hongfeng342@163.com";
		String MailPassword = "123qwe";//密码
		String MailFrom = "hongfeng342@163.com";
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
