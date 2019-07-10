package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import utils.DBHelper;
/**
 * 用于登陆注册和更改密码
 * @author Wanghao
 *
 */
public class WanghaoDao {

	PreparedStatement ps;
	ResultSet rs;
	
	/**
	 * 登陆检验，返回user对象
	 * @param userNumber 用户账号
	 * @param password 密码
	 * @return 用户对象除了用户名和密码都包含
	 */
	public User userLogin(String userNumber, String password){
		User user = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "select * from user where user_number=? and user_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userNumber);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				user = new User();
				String user_id = rs.getString("user_id");
				String user_name = rs.getString("user_name");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String user_status = rs.getString("user_status");
				user.setUser_id(Integer.parseInt(user_id));
				user.setUser_name(user_name);
				user.setPhone(Integer.parseInt(phone));
				user.setEmail(email);
				user.setUser_status(Integer.parseInt(user_status));
			}
			
			DBHelper.closeConnection(conn, ps,rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	/**
	 * 
	 * @param adminName 管理员用户名
	 * @param password 密码
	 * @return 管理员用户名
	 */
	public String adminLogin(String adminName, String password){
		String admin = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "select * from administrator where admin_number=? and admin_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, adminName);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				admin = adminName;
			}
			
			DBHelper.closeConnection(conn, ps,rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}
	
	/**
	 * 更改密码
	 * @param userNumber 用户名
	 * @param password 密码
	 */
	public void changePasswordbyuserNumber(String userNumber, String password){
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "update user set user_password=? where user_number=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, userNumber);
			ps.execute();
			
			DBHelper.closeConnection(conn, ps,rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	 /**
	  * 更改密码
	  * @param userId
	  * @param password
	  */
	public void changePasswordbyId(String userId, String password){
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "update user set user_password=? where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, password);
			ps.setString(2, userId);
			ps.execute();
			
			DBHelper.closeConnection(conn, ps,rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 检验用户名是否重复,重复false
	 * @param userNumber 用户名
	 * @return 是否重复
	 */
	public boolean userName_available(String userNumber){
		boolean available = true;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "select * from user where user_number=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userNumber);
			rs = ps.executeQuery();

			if(rs.next()) {
				available = false;
			}
			
			DBHelper.closeConnection(conn, ps,rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return available;
	}
	/**
	 * 用户注册
	 * @param user 用户
	 */
	
	public void addUser(User user){
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "insert into user(user_name,user_password,phone,user_number,email,user_status) values (?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
				
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getPassword());
			ps.setString(3, String.valueOf(user.getPhone()));
			ps.setDouble(4, user.getUser_number());
            ps.setString(5, user.getEmail());
			ps.setString(6, String.valueOf(user.getUser_status()));
			ps.execute();
			
			DBHelper.closeConnection(conn, ps, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 对邮箱和用户名进行匹配
	 * @param userNumber 用户名
	 * @param email 邮箱
	 * @return 用户数据库中的id
	 */
	public String inspectEmail(String userNumber,String email){
		
		String userId = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "select * from user where user_number=? and email=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userNumber);
			ps.setString(2, email);
			rs = ps.executeQuery();

			if(rs.next()) {
				userId = rs.getString("user_id");
			}
			
			DBHelper.closeConnection(conn, ps,rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userId;
	}
	
	/**
	 * 检验id与密码是否匹配
	 * @param userId 用户数据库id
	 * @param password 密码
	 * @return boolean类型显示是否匹配
	 */
	public boolean inspectUser(String userId, String password){
		boolean isAvaliable = false;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "select * from user where user_id=? and user_password=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				isAvaliable = true;
			}
			
			DBHelper.closeConnection(conn, ps,rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isAvaliable;
	}
	/**
	 * 获取数据库加密过的密码作为更改密码的凭证
	 * @param userid
	 * @return
	 */
	public String getPIN(String userid){
		String PIN = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "select * from user where user_id=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();

			if(rs.next()) {
				PIN = rs.getString("user_password");
			}
			
			DBHelper.closeConnection(conn, ps,rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return PIN;
	}
}
