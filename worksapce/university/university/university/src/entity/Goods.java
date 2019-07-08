/**
 * 
 */
package entity;

/**
 * @author feng
 *
 */
public class Goods {
	private int goods_id;
	private int user_id;
	private String goods_name;
	private String description;
	private int goods_status;
	private int category_id;
	private double price;
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
	 * @return the goods_name
	 */
	public String getGoods_name() {
		return goods_name;
	}
	/**
	 * @param goods_name the goods_name to set
	 */
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the goods_status
	 */
	public int getGoods_status() {
		return goods_status;
	}
	/**
	 * @param goods_status the goods_status to set
	 */
	public void setGoods_status(int goods_status) {
		this.goods_status = goods_status;
	}
	/**
	 * @return the category_id
	 */
	public int getCategory_id() {
		return category_id;
	}
	/**
	 * @param category_id the category_id to set
	 */
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	/**
	 * @param goods_id
	 * @param user_id
	 * @param goods_name
	 * @param description
	 * @param goods_status
	 * @param category_id
	 * @param price
	 */
	public Goods(int goods_id, int user_id, String goods_name, String description, int goods_status, int category_id,
			double price) {
		super();
		this.goods_id = goods_id;
		this.user_id = user_id;
		this.goods_name = goods_name;
		this.description = description;
		this.goods_status = goods_status;
		this.category_id = category_id;
		this.price = price;
	}
	/**
	 * 
	 */
	public Goods() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
