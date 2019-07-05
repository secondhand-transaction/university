/**
 * 
 */
package entity;

import java.sql.Date;

/**
 * @author feng
 *
 */
public class Information {
	private int information_id;
	private int buyer_id;
	private int seller_id;
	private String content;
	private Date sendtime;
	private int nextinfor_id;
	private int information_status;
	/**
	 * @return the information_id
	 */
	public int getInformation_id() {
		return information_id;
	}
	/**
	 * @param information_id the information_id to set
	 */
	public void setInformation_id(int information_id) {
		this.information_id = information_id;
	}
	/**
	 * @return the buyer_id
	 */
	public int getBuyer_id() {
		return buyer_id;
	}
	/**
	 * @param buyer_id the buyer_id to set
	 */
	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the sendtime
	 */
	public Date getSendtime() {
		return sendtime;
	}
	/**
	 * @param sendtime the sendtime to set
	 */
	public void setSendtime(Date sendtime) {
		this.sendtime = sendtime;
	}
	/**
	 * @return the nextinfor_id
	 */
	public int getNextinfor_id() {
		return nextinfor_id;
	}
	/**
	 * @param nextinfor_id the nextinfor_id to set
	 */
	public void setNextinfor_id(int nextinfor_id) {
		this.nextinfor_id = nextinfor_id;
	}
	/**
	 * @return the information_status
	 */
	public int getInformation_status() {
		return information_status;
	}
	/**
	 * @param information_status the information_status to set
	 */
	public void setInformation_status(int information_status) {
		this.information_status = information_status;
	}
	/**
	 * @param information_id
	 * @param buyer_id
	 * @param seller_id
	 * @param content
	 * @param sendtime
	 * @param nextinfor_id
	 * @param information_status
	 */
	public Information(int information_id, int buyer_id, int seller_id, String content, Date sendtime, int nextinfor_id,
			int information_status) {
		super();
		this.information_id = information_id;
		this.buyer_id = buyer_id;
		this.seller_id = seller_id;
		this.content = content;
		this.sendtime = sendtime;
		this.nextinfor_id = nextinfor_id;
		this.information_status = information_status;
	}
	/**
	 * 
	 */
	public Information() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
