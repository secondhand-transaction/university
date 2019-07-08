/**
 * 
 */
package entity;

/**
 * @author feng
 *
 */
public class Cart {
	private String goods_id;
	private String name;
	private String price;
	/**
	 * @return the goods_id
	 */
	public String getGoods_id() {
		return goods_id;
	}
	/**
	 * @param goods_id the goods_id to set
	 */
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @param goods_id
	 * @param name
	 * @param price
	 */
	public Cart(String goods_id, String name, String price) {
		super();
		this.goods_id = goods_id;
		this.name = name;
		this.price = price;
	}
	/**
	 * 
	 */
	public Cart() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
