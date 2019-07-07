package dao;

import java.sql.Connection;
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
			while (rs.next()) {
				String username = "", email = "";
				int usernumber, phone;
				username = rs.getString("user_name");
				userInfor = userInfor + username + ",";
				phone = rs.getInt("phone");
				userInfor = userInfor + phone + ",";
				email = rs.getString("email");
				userInfor = userInfor + email + ",";
				usernumber = rs.getInt("user_number");
				userInfor = userInfor + usernumber;

			}

			DBHelper.closeConnection(c, s, rs);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return userInfor;
	}

	public static boolean ChangeUserInfor(int user_id, String infor) {
	

		boolean rs=false;
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			int phone ,usernumber;
			String  email = "", username = "";
			username = infor.split(",")[0];
			phone = Integer.parseInt(infor.split(",")[1]);
			email = infor.split(",")[2];
			usernumber =Integer.parseInt( infor.split(",")[3]);
//			UPDATE Websites 
//			SET alexa='5000', country='USA' 
//			WHERE name='菜鸟教程';

			String sql = "update user set user_name="+"'"+username+"'"+
			",phone="+"'"+phone+"',"+"email="+"'"+email+"',"+"user_number="+"'"+usernumber+"'"+"where user_id="+"'"+user_id+"'";
			rs=s.execute(sql);

		
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
	}
	public static boolean pushGoodsInfor(int user_id,String infor) {
		boolean rs=false;
		
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

             int status=0,category_id=1;//init status
			double price;
			String  goodsName="",des="";
			
			goodsName = infor.split(",")[0];
			des = infor.split(",")[1];
			price = Double.valueOf(infor.split(",")[2]);
			// INSERT INTO Persons VALUES ('Gates', 'Bill', 'Xuanwumen 10', 'Beijing')

			String sql = "insert into goods(user_id,goods_name,price,description,goods_status,category_id) values("+
			user_id+","+goodsName+","+price+","+des+","+status+","+category_id+")";
			rs=s.execute(sql);	

		} catch (Exception e) {
			e.printStackTrace();
		}

		return rs;
		
		
	}
	

}
