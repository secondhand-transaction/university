/**
 * 
 */
package entity;

/**
 * @author feng
 *
 */
public class User {
	private int user_id;
	private String user_name;
	private String password;
	private int phone;
	private int user_number;
	private String email;
	private int user_status;
	/**
	 * @return the user_id
	 */
	public int getUser_id() {
		return user_id;
	}
	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
	 * @return the user_name
	 */
	public String getUser_name() {
		return user_name;
	}
	/**
	 * @param user_name the user_name to set
	 */
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the phone
	 */
	public int getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(int phone) {
		this.phone = phone;
	}
	/**
	 * @return the user_number
	 */
	public int getUser_number() {
		return user_number;
	}
	/**
	 * @param user_number the user_number to set
	 */
	public void setUser_number(int user_number) {
		this.user_number = user_number;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the user_status
	 */
	public int getUser_status() {
		return user_status;
	}
	/**
	 * @param user_status the user_status to set
	 */
	public void setUser_status(int user_status) {
		this.user_status = user_status;
	}
	/**
	 * @param user_id
	 * @param user_name
	 * @param password
	 * @param phone
	 * @param user_number
	 * @param email
	 * @param user_status
	 */
	public User(int user_id, String user_name, String password, int phone, int user_number, String email,
			int user_status) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.password = password;
		this.phone = phone;
		this.user_number = user_number;
		this.email = email;
		this.user_status = user_status;
	}
	/**
	 * 
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
