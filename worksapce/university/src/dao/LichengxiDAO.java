package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class LichengxiDAO {
	private  static final String url = "jdbc:mysql://localhost:3306/university?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false&serverTimezone=UTC&autoReconnect=true";
	private  static final String user = "root";
	private  static final String password = "li3660857";

	
	static{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException  e) {
			System.err.println("Jdbc Driver not found");
		}
	}
	public static java.sql.Connection getCon() throws SQLException{
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("succsessful");
			return conn;
	} 
	
	public boolean UpdateUserStatus(int user_id) throws SQLException{
		Connection conn = getCon();
		try {
			Statement stmt = conn.createStatement();
			String sql="update user set user_status = 1 where user_id="+user_id;
			int judge = stmt.executeUpdate(sql);
			if ( judge == 1) return true;
			else return false;
	}catch (SQLException s) {
		System.out.println(s);	
		return false;
	}
	}
	
}
