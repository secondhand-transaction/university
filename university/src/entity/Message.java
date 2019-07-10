package entity;

import java.sql.Date;

public class Message {
	private int information_num;
	private int seller_id;
	private String seller_name;
	private int status;
	private Date date;
	
	
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the information_num
	 */
	public int getInformation_num() {
		return information_num;
	}
	/**
	 * @param information_num the information_num to set
	 */
	public void setInformation_num(int information_num) {
		this.information_num = information_num;
	}
	/**
	 * @return the seller_id
	 */
	public int getSeller_id() {
		return seller_id;
	}
	/**
	 * @param seller_id the seller_id to set
	 */
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}
	/**
	 * @return the seller_name
	 */
	public String getSeller_name() {
		return seller_name;
	}
	/**
	 * @param seller_name the seller_name to set
	 */
	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
}
