/**
 * 
 */
package entity;

/**
 * @author feng
 *
 */
public class OrderItem {
	private int orderItem;
	private int order_id;
	private int goods_id;
	private int orderItem_status;
	/**
	 * @return the orderItem
	 */
	public int getOrderItem() {
		return orderItem;
	}
	/**
	 * @param orderitem the orderItem to set
	 */
	public void setOrderItem(int orderItem) {
		this.orderItem = orderItem;
	}
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
	 * @return the goods_id
	 */
	public int getGoods_id() {
		return goods_id;
	}
	/**
	 * @param goods_id the goods_id to set
	 */
	public void setGoods_id(int goods_id) {
		this.goods_id = goods_id;
	}
	/**
	 * @return the orderitem_status
	 */
	public int getOrderItem_status() {
		return orderItem_status;
	}
	/**
	 * @param orderitem_status the orderitem_status to set
	 */
	public void setOrderItem_status(int orderitem_status) {
		this.orderItem_status = orderitem_status;
	}
	/**
	 * @param orderitem
	 * @param order_id
	 * @param goods_id
	 * @param orderitem_status
	 */
	public OrderItem(int orderItem, int order_id, int goods_id, int orderItem_status) {
		super();
		this.orderItem = orderItem;
		this.order_id = order_id;
		this.goods_id = goods_id;
		this.orderItem_status = orderItem_status;
	}
	/**
	 * 
	 */
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
