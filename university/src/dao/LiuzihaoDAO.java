package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import utils.DBHelper;

public class LiuzihaoDAO {

	private LiuzihaoDAO() {
	}

	public static LiuzihaoDAO getInstance() {
		return new LiuzihaoDAO();
	}

	public static String getUserInfor(int user_id) {
		String userInfor = new String();// name

		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = " select * from user where user_id = " + user_id;// 获取用户个人信息

			ResultSet rs = s.executeQuery(sql);
			String username = "", email = "";
			int phone = 0;
			while (rs.next()) {

				username = rs.getString("user_name");
				email = rs.getString("email");
				phone = rs.getInt("phone");
				
				

			}
			userInfor=username+"#"+email+"#"+phone;

			DBHelper.closeConnection(c, s, rs);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userInfor;
	}

	public static boolean ChangeUserInfor(int user_id, String infor) {

		boolean rs = false;
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			int phone, usernumber;
			String email = "", username = "";
			username = infor.split("#")[0];
			phone = Integer.parseInt(infor.split("#")[2]);
			email = infor.split("#")[1];
			// UPDATE Websites
			// SET alexa='5000', country='USA'
			// WHERE name='菜鸟教程';

			String sql = "update user set user_name=" + "'" + username + "'"
					+ ",phone=" + "'" + phone + "'," + "email=" + "'" + email
					+ "'" + "where user_id=" + "'" + user_id + "'";
			rs = s.execute(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}

	public static boolean pushGoodsInfor(int user_id, String infor) {

		boolean rs = false;

		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			double price;
			String goodsName = "", des = "";

			goodsName = infor.split("#")[0];
			price = Double.valueOf(infor.split("#")[1]);
			des = infor.split("#")[2];
			// INSERT INTO Persons VALUES ('Gates', 'Bill', 'Xuanwumen 10',
			// 'Beijing')

			// String sql =
			// "insert into goods(user_id,goods_name,price,description,goods_status,category_id) values("+
			// "'"+user_id+"'"+","+"'"+goodsName+"'"+","+"'"+price+"'"+","+"'"+des+"'"+","+"'"+status+"'"+","+"'"+category_id+"'"+")";
			String sql = "insert into goods(user_id,goods_name,price,description,goods_status,category_id) values(?,?,?,?,?,?)";

			PreparedStatement ps = c.prepareStatement(sql);

			ps.setInt(1, user_id);
			ps.setString(2, goodsName);
			ps.setDouble(3, price);
			ps.setString(4, des);
			ps.setInt(5, 0);
			ps.setInt(6, 1);

			ps.execute();

			DBHelper.closeConnection(c, ps, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;

	}

}
