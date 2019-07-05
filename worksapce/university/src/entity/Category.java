/**
 * 
 */
package entity;

/**
 * @author feng
 *
 */
public class Category {
	private int category_id;
	private String category_name;
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
	 * @return the category_name
	 */
	public String getCategory_name() {
		return category_name;
	}
	/**
	 * @param category_name the category_name to set
	 */
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	/**
	 * @param category_id
	 * @param category_name
	 */
	public Category(int category_id, String category_name) {
		super();
		this.category_id = category_id;
		this.category_name = category_name;
	}
	/**
	 * 
	 */
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
