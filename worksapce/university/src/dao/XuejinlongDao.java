package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.soap.SOAPBinding.Use;
import javax.xml.soap.SOAPException;

import org.apache.tomcat.jdbc.pool.interceptor.SlowQueryReport;

import utils.DBHelper;
import entity.Goods;
import entity.User;

public class XuejinlongDao {
	PreparedStatement ps;
	ResultSet rs;
	
	public void jubaoSucceed() {
		
	}
	
	public void update(User user) {
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "update user set user_name=?,user_password=?,phone=?,user_number=?,email=?,user_status=? where user_id="+user.getUser_id();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getPhone());
			ps.setInt(4, user.getUser_number());
			ps.setString(5, user.getEmail());
			ps.setInt(6, user.getUser_status());
			
			ps.execute();
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void verify(User user) {
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "update user set user_name=?,user_password=?,phone=?,user_number=?,email=?,user_status=? where user_id="+user.getUser_id();
			ps = conn.prepareStatement(sql);
			
			int status = 1;
			ps.setString(1, user.getUser_name());
			ps.setString(2, user.getPassword());
			ps.setInt(3, user.getPhone());
			ps.setInt(4, user.getUser_number());
			ps.setString(5, user.getEmail());
			ps.setInt(6, status);
			
			ps.execute();
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void xiaJia(Goods good) {
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "update goods set goods_id=?,user_id=?,goods_name=?,description=?,goods_status=?,category_id=?,price=? where goods_id="+good.getGoods_id();
			ps = conn.prepareStatement(sql);
			
			int status = 1;
			ps.setInt(1, good.getGoods_id());
			ps.setInt(2, good.getUser_id());
			ps.setString(3, good.getGoods_name());
			ps.setString(4, good.getDescription());
			ps.setInt(5, status);
			ps.setInt(6, good.getCategory_id());
			ps.setDouble(7, good.getPrice());
			
			ps.execute();
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void recover(Goods good) {
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "update goods set goods_id=?,user_id=?,goods_name=?,description=?,goods_status=?,category_id=?,price=? where goods_id="+good.getGoods_id();
			ps = conn.prepareStatement(sql);
			
			int status = 0;
			ps.setInt(1, good.getGoods_id());
			ps.setInt(2, good.getUser_id());
			ps.setString(3, good.getGoods_name());
			ps.setString(4, good.getDescription());
			ps.setInt(5, status);
			ps.setInt(6, good.getCategory_id());
			ps.setDouble(7, good.getPrice());
			
			ps.execute();
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	public void deleteUser(User user) {
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "delete from user where user_id="+user.getUser_id();
			ps = conn.prepareStatement(sql);
			ps.execute();
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addUser(User user) {
		Connection conn;
		try {
			conn = DBHelper.getInstance().getConnection();
			String sql = "insert into user (user_id,user_name,user_password,phone,user_number,email,user_status) values (?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			
			
			ps.setInt(1, user.getUser_id());
			ps.setString(2, user.getUser_name());
			ps.setString(3, user.getPassword());
			ps.setInt(4, user.getPhone());
			ps.setInt(5, user.getUser_number());
			ps.setString(6, user.getEmail());
			ps.setInt(7, user.getUser_status());
			
			ps.execute();
			DBHelper.closeConnection(conn,ps,rs);
			System.out.println("dao没问题");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public User findUserById(int id) throws SQLException {
		User user = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "select * from user where user_id="+id;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				user = new User();
				String name = rs.getString("user_name");
				String password = rs.getString("user_password");
				int phone = rs.getInt("phone");
				int user_number = rs.getInt("user_number");
				String email = rs.getString("email");
				int status = rs.getInt("user_status");
				
				user.setUser_id(id);
				user.setUser_name(name);
				user.setPassword(password);
				user.setPhone(phone);
				user.setUser_number(user_number);
				user.setEmail(email);
				user.setUser_status(status);
				
			}
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return user;
	}
	
	public Goods findGoodsById(int id) throws SQLException {
		Goods good = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "select * from goods where goods_id="+id;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if (rs.next()) {
				good = new Goods();
				
				int userId = rs.getInt("user_id");
				String goodName = rs.getString("goods_name");
				String description = rs.getString("description");
				int goodStatus = rs.getInt("goods_status");
				int cateId = rs.getInt("category_id");
				double price = rs.getDouble("price");
				
				
				good.setCategory_id(cateId);
				good.setDescription(description);
				good.setGoods_id(id);
				good.setGoods_name(goodName);
				good.setGoods_status(goodStatus);
				good.setPrice(price);
				good.setUser_id(userId);
				
			}
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return good;
	}
	
	public List<User> findAllUser() {
		List<User> users = new ArrayList<User>();
		User user = null;
		String sql = "select * from user";
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				int id = rs.getInt("user_id");
				String name = rs.getString("user_name");
				String password = rs.getString("user_password");
				int phone = rs.getInt("phone");
				int user_number = rs.getInt("user_number");
				String email = rs.getString("email");
				int status = rs.getInt("user_status");
				
				user.setUser_id(id);
				user.setUser_name(name);
				user.setPassword(password);
				user.setPhone(phone);
				user.setUser_number(user_number);
				user.setEmail(email);
				user.setUser_status(status);
				
				System.out.println("find all user");
				users.add(user);
			}
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			// TODO: handle 
			e.printStackTrace();
		}
		return users;
	}
	
	public List<User> findAllAuditedUser() {
		List<User> users = new ArrayList<User>();
		User user = null;
		String sql = "select * from user where user_status = 1";
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				int id = rs.getInt("user_id");
				String name = rs.getString("user_name");
				String password = rs.getString("user_password");
				int phone = rs.getInt("phone");
				int user_number = rs.getInt("user_number");
				String email = rs.getString("email");
				int status = rs.getInt("user_status");
				
				user.setUser_id(id);
				user.setUser_name(name);
				user.setPassword(password);
				user.setPhone(phone);
				user.setUser_number(user_number);
				user.setEmail(email);
				user.setUser_status(status);
				
				System.out.println("find all audited user");
				users.add(user);
			}
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			// TODO: handle 
			e.printStackTrace();
		}
		return users;
	}
	
	public List<Goods> findAllBeiJuBao() {
		List<Goods> goodss = new ArrayList<Goods>();
		Goods good = null;
		String sql = "select * from goods where goods_status= 2";
		try {
			Connection conn= DBHelper.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				good = new Goods();
				int goodId = rs.getInt("goods_id");
				int userId = rs.getInt("user_id");
				String goodName = rs.getString("goods_name");
				String description = rs.getString("description");
				int goodStatus = rs.getInt("goods_status");
				int categoryId = rs.getInt("category_id");
				double price = rs.getDouble("price");
				
				good.setGoods_id(goodId);
				good.setGoods_name(goodName);
				good.setUser_id(userId);
				good.setDescription(description);
				good.setGoods_status(goodStatus);
				good.setCategory_id(categoryId);
				good.setPrice(price);
				
				goodss.add(good);
 			}
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return goodss;
	}
	
	public List<Goods> findAllXiaJia() {
		List<Goods> goodss = new ArrayList<Goods>();
		Goods good = null;
		String sql = "select * from goods where goods_status= 1";
		try {
			Connection conn= DBHelper.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				good = new Goods();
				int goodId = rs.getInt("goods_id");
				int userId = rs.getInt("user_id");
				String goodName = rs.getString("goods_name");
				String description = rs.getString("description");
				int goodStatus = rs.getInt("goods_status");
				int categoryId = rs.getInt("category_id");
				double price = rs.getDouble("price");
				
				good.setGoods_id(goodId);
				good.setGoods_name(goodName);
				good.setUser_id(userId);
				good.setDescription(description);
				good.setGoods_status(goodStatus);
				good.setCategory_id(categoryId);
				good.setPrice(price);
				
				goodss.add(good);
 			}
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return goodss;
	}
	
	public List<Goods> findAllNoraml() {
		List<Goods> goodss = new ArrayList<Goods>();
		Goods good = null;
		String sql = "select * from goods where goods_status= 0";
		try {
			Connection conn= DBHelper.getInstance().getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				good = new Goods();
				int goodId = rs.getInt("goods_id");
				int userId = rs.getInt("user_id");
				String goodName = rs.getString("goods_name");
				String description = rs.getString("description");
				int goodStatus = rs.getInt("goods_status");
				int categoryId = rs.getInt("category_id");
				double price = rs.getDouble("price");
				
				good.setGoods_id(goodId);
				good.setGoods_name(goodName);
				good.setUser_id(userId);
				good.setDescription(description);
				good.setGoods_status(goodStatus);
				good.setCategory_id(categoryId);
				good.setPrice(price);
				
				goodss.add(good);
 			}
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return goodss;
	}
}
