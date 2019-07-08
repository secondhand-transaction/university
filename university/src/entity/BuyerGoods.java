/**
 * 
 */
package entity;

/**
 * @author dell
 *
 */
public class BuyerGoods {

	
	private Goods goods;
	
	private OrderItem orderItem;

	public Goods getGoods() {
		return goods;
	}

	public void setGoods(Goods goods) {
		this.goods = goods;
	}

	public OrderItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	public BuyerGoods(Goods goods, OrderItem orderItem) {
		super();
		this.goods = goods;
		this.orderItem = orderItem;
	}
		
}
