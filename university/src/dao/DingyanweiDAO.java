package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;



public class DingyanweiDAO {
	private  static final String url = "jdbc:mysql://localhost:3306/university?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false&serverTimezone=UTC&autoReconnect=true";
	private  static final String user = "root";
	private  static final String password = "qwe123";

	
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

	public boolean AddComment(int goods_id,String comment) throws SQLException{
		Connection conn = getCon();
		try {
			Statement stmt = conn.createStatement();
			String sql="update goods set evaluation ='"+comment+"' where goods_id="+goods_id;
			int judge = stmt.executeUpdate(sql);
			
			if ( judge == 1) return true;
			else return false;
	}catch (SQLException s) {
		System.out.println(s);	
		return false;
	}
	}
}
