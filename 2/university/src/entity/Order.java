/**
 * 
 */
package entity;

/**
 * @author feng
 *
 */
public class Order {
	private int order_id;
	private int user_id;
	/**
	 * @return the order_id
	 */
	public int getOrder_id() {
		return order_id;
	}
	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
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
	 * @param order_id
	 * @param user_id
	 */
	public Order(int order_id, int user_id) {
		super();
		this.order_id = order_id;
		this.user_id = user_id;
	}
	/**
	 * 
	 */
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
