/**
 * 
 */
package entity;

/**
 * @author feng
 *
 */
public class QueryGood {
	private int query_id;
	private int user_id;
	private String query_name;
	private int category_id;
	/**
	 * @return the query_id
	 */
	public int getQuery_id() {
		return query_id;
	}
	/**
	 * @param query_id the query_id to set
	 */
	public void setQuery_id(int query_id) {
		this.query_id = query_id;
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
	 * @return the query_name
	 */
	public String getQuery_name() {
		return query_name;
	}
	/**
	 * @param query_name the query_name to set
	 */
	public void setQuery_name(String query_name) {
		this.query_name = query_name;
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
	 * @param query_id
	 * @param user_id
	 * @param query_name
	 * @param category_id
	 */
	public QueryGood(int query_id, int user_id, String query_name, int category_id) {
		super();
		this.query_id = query_id;
		this.user_id = user_id;
		this.query_name = query_name;
		this.category_id = category_id;
	}
	/**
	 * 
	 */
	public QueryGood() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
