package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Information;
import utils.DBHelper;


public class HelongxiangDAO {
	private  static final String url = "jdbc:mysql://localhost:3306/university?useUnicode=true&characterEncoding=UTF-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&useSSL=false&serverTimezone=UTC&autoReconnect=true";
	private  static final String user = "root";
	private  static final String password = "root";

	
//	static{
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
////			conn = DriverManager.getConnection(url, user, password);
////			System.out.println("succsessful");
////			smt = conn.createStatement();
//		} catch (ClassNotFoundException  e) {
//			System.err.println("Jdbc Driver not found");
//		}
//	}
	public static java.sql.Connection getCon() throws SQLException{
		
			//Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			System.out.println("database connect succsessful");
			return conn;
	} 
	
	/*
	 * ����һ���µ�����
	 * @return ����ɹ�������Ϣ Boolean
	 */
	public Boolean insertInformation(Information data) {
		Connection conn = null;
		try {
			conn = DBHelper.getInstance().getConnection();
			Statement stmt = conn.createStatement();
			int buyer_id = data.getBuyer_id();
			int seller_id = data.getSeller_id();
			String content = data.getContent();
			Date sendtime = data.getSendtime();
			int beforeinfor_id = data.getNextinfor_id();
			int information_status = 0;
			String sql="INSERT INTO information(`buyer_id`,`seller_id`,`content`,`sendtime`,`nextinfor_id`,`information_status`)VALUES("
					+buyer_id+", "+seller_id +" , '"+ content +"', '"+ sendtime +"' ,"+beforeinfor_id+","+information_status+")"; 
			int i=stmt.executeUpdate(sql);
			if(i!=1) {
				System.out.println("HelongxinagDao Insert");
				return false;
			}else {
				return true;
			}
	}catch (SQLException s) {
		System.out.println(s);
		return false;
	}
		finally {

		}
	}
	
	/*
	 * ͨ���������Ϣid��Information���в�ѯ������ص����ݡ�
	 * @param ��Ϣid��int
	 * @return Information����б�
	 */
	public ArrayList<Information> selectInformationByInformationid(int id) {
		Connection conn = null;
		ArrayList<Information> inflist = new ArrayList<Information>();
		try {
			
			conn = getCon();
			Statement stmt = conn.createStatement();
			String sql="select buyer_id,seller_id,content,sendtime,nextinfor_id,information_status from information where information_id = "+id; 
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {
				Information inf = new Information();
				inf.setInformation_id(id);
				inf.setBuyer_id(rs.getInt("buyer_id"));
				inf.setSeller_id(rs.getInt("seller_id"));
				inf.setContent(rs.getString("content"));
				inf.setSendtime(rs.getDate("sendtime"));
				inf.setNextinfor_id(rs.getInt("nextinfor_id"));
				inf.setInformation_status(rs.getInt("information_status"));
				inflist.add(inf);
			}
			return inflist;
	}catch (SQLException s) {
		System.out.println(s);	
		return null;
	}
		finally {
			if (conn != null) {
				try {					
					conn.close();					
				} catch (SQLException ignore) {					
				}
			}
		}
	}
	
	/*
	 * ͨ������Ľ�����id��Information���в�ѯ������ص����ݡ�
	 * @param ������id��int
	 * @return Information����б�
	 */
	public ArrayList<Information> selectInformationBybuyerid(int id) {
		Connection conn = null;
		ArrayList<Information> inflist = new ArrayList<Information>();
		try {
			
			conn = getCon();
			Statement stmt = conn.createStatement();
			String sql="select information_id,buyer_id,seller_id,content,sendtime,nextinfor_id,information_status from information where buyer_id = "+id; 
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {
				Information inf = new Information();
				inf.setInformation_id(rs.getInt("information_id"));
				inf.setBuyer_id(id);
				inf.setSeller_id(rs.getInt("seller_id"));
				inf.setContent(rs.getString("content"));
				inf.setSendtime(rs.getDate("sendtime"));
				inf.setNextinfor_id(rs.getInt("nextinfor_id"));
				inf.setInformation_status(rs.getInt("information_status"));
				inflist.add(inf);
			}
			return inflist;
	}catch (SQLException s) {
		System.out.println(s);	
		return null;
	}
		finally {
			if (conn != null) {
				try {					
					conn.close();					
				} catch (SQLException ignore) {					
				}
			}
		}
	}
	
	/*
	 * ͨ������˫����id��Information���в�ѯ������ص����ݡ�
	 * @param ������id��int ���ͷ���id
	 * @return Information����б�
	 */
	public ArrayList<Information> selectInformationByTwoid(int id1, int id2) {
		Connection conn = null;
		ArrayList<Information> inflist = new ArrayList<Information>();
		try {
			
			conn = getCon();
			Statement stmt = conn.createStatement();
			String sql="select information_id,buyer_id,seller_id,content,sendtime,nextinfor_id,information_status from information "
					+ "where (buyer_id = "+ id1 + " and seller_id = " + id2 +" ) or (buyer_id =" + id2 + " and seller_id = " + id1 +" )"; 
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {
				Information inf = new Information();
				inf.setInformation_id(rs.getInt("information_id"));
				inf.setBuyer_id(rs.getInt("buyer_id"));
				inf.setSeller_id(rs.getInt("seller_id"));
				inf.setContent(rs.getString("content"));
				inf.setSendtime(rs.getDate("sendtime"));
				inf.setNextinfor_id(rs.getInt("nextinfor_id"));
				inf.setInformation_status(rs.getInt("information_status"));
				inflist.add(inf);
			}
			return inflist;
	}catch (SQLException s) {
		System.out.println(s);	
		return null;
	}
		finally {
			if (conn != null) {
				try {					
					conn.close();					
				} catch (SQLException ignore) {					
				}
			}
		}
	}
	
	/*
	 * ͨ���û���id��User���в�ѯ�û���name��
	 * @param �û�id��int 
	 * @return �û�name,string
	 */
	public String selectUsernameByUserid(int id) {
		Connection conn = null;
		String name = null;
		try {
			conn = getCon();
			Statement stmt = conn.createStatement();
			String sql="select user_name from user where user_id = "+id;
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {
				 name = rs.getString("user_name");
			}
			return name;
		}catch (SQLException s) {
			System.out.println(s);	
			return null;
		}
			finally {
				if (conn != null) {
					try {					
						conn.close();					
					} catch (SQLException ignore) {					
					}
				}
			}	
	}
	/*
	 * ͨ�����ͷ�id����շ�id����Information���״̬
	 * @param ���շ�id int�����ͷ�id
	 * @return ����״̬�ɹ�������Ϣ Boolean
	 */
	public Boolean updateStatusOfInformation(int buyer_id, int sellers_id) {
		Connection conn = null;
		try {
			conn = getCon();
			Statement stmt = conn.createStatement();
			String sql="update information set information_status = 1 where buyer_id="+ buyer_id + " and seller_id = " + sellers_id;
			int i=stmt.executeUpdate(sql);
			if(i!=1) {
				System.out.println("����ʧ�ܣ�");
				return false;
			}else {
				return true;
			}
		}catch (SQLException s) {
			System.out.println(s);	
			return null;	
		}
			finally {
				if (conn != null) {
					try {					
						conn.close();					
					} catch (SQLException ignore) {					
					}
				}
			}
		
	}
}
