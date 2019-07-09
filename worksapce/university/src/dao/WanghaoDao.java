package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;
import utils.DBHelper;
/**
 * ���ڵ�½ע��͸�������
 * @author Wanghao
 *
 */
public class WanghaoDao {

	PreparedStatement ps;
	ResultSet rs;
	
	/**
	 * ��½���飬����user����
	 * @param userNumber �û��˺�
	 * @param password ����
	 * @return �û���������û��������붼����
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
	 * @param adminName ����Ա�û���
	 * @param password ����
	 * @return ����Ա�û���
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
	 * ��������
	 * @param userNumber �û���
	 * @param password ����
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
	  * ��������
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
	 * �����û����Ƿ��ظ�,�ظ�false
	 * @param userNumber �û���
	 * @return �Ƿ��ظ�
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
	 * �û�ע��
	 * @param user �û�
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
	 * ��������û�������ƥ��
	 * @param userNumber �û���
	 * @param email ����
	 * @return �û����ݿ��е�id
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
	 * ����id�������Ƿ�ƥ��
	 * @param userId �û����ݿ�id
	 * @param password ����
	 * @return boolean������ʾ�Ƿ�ƥ��
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
	 * ��ȡ���ݿ���ܹ���������Ϊ���������ƾ֤
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
