package entity;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Auth extends Authenticator {

	private String username = "";
	private String password = "";

	public Auth() {
		this.username = username;
		this.password = password;
	}
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication("hongfeng342@163.com", "123qwe");
	} 
}
