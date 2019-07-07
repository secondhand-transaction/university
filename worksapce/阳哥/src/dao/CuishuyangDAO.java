package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBHelper;
import entity.Goods;

public class CuishuyangDAO {

	private CuishuyangDAO() {
	}

	public static CuishuyangDAO getInstance() {
		return new CuishuyangDAO();
	}

	/*
	 * try {
	 * 
	 * Connection c = DBHelper.getInstance().getConnection();
	 * 
	 * Statement s = c.createStatement();
	 * 
	 * String sql = ;
	 * 
	 * ResultSet rs = s.executeQuery(sql); while (rs.next()) { }
	 * 
	 * DBHelper.closeConnection(c, s, rs);
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 */
	// public static int sum(int uesr_id) //到时候直接注释掉
	public static int sum(int user_id) {

		user_id = 123;

		int total = 0;

		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "select count(*) from `order` where user_id = " + user_id;// id = " + id以后在此接受前端传过来的值

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				total = rs.getInt(1);
			}

			// System.out.println("total:" + total);

			DBHelper.closeConnection(c, s, rs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	// 在此查询买家买过的东西的产品好包括已经生成的订单还没完成的
	// public static int[] userID(int user_id){
	public static int[] orderId(int user_id) {

		user_id = 123;

		int total = CuishuyangDAO.sum(user_id);
		int i = 0;
		int[] orderId = new int[total];
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "select * from `order` where user_id = 123";// user_id = " + user_id;

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				orderId[i] = rs.getInt("order_id");
				// System.out.println("orderId" + orderId[i]);
				i++;
			}

			DBHelper.closeConnection(c, s, rs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderId;
	}

	public static int goodscount(int user_id) {

		user_id = 123;

		int count = 0;
		int counts = 0;
		int sum = CuishuyangDAO.sum(user_id);
		int[] orderId = new int[sum];
		// System.out.println("sum" + sum);
		orderId = CuishuyangDAO.orderId(user_id);// orderId = CuishuyangDAO.orderID(user_id);
		String sql = new String();
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			for (int i = 0; i < sum; i++) {
				sql = "select count(*) from orderItem where orderItem_status = 2 and order_id = " + orderId[i];//
				// rs = s.executeQuery(sql);
				if (i + 1 == sum)
					break;
				ResultSet rs = s.executeQuery(sql);
				while (rs.next()) {
					// System.out.println("***********");
					// System.out.println("orderid" + orderId[i]);
					count = rs.getInt(1);
					// System.out.println("count" + count);
				}
				counts = counts + count;
				count = 0;
			}

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				count = rs.getInt(1);
			}
			counts = counts + count;

			DBHelper.closeConnection(c, s, rs);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return counts;
	}

	// public static int[] orderItemId(int user_id)
	public static int[] goodsId(int user_id) {

		user_id = 123;

		int sum = CuishuyangDAO.sum(user_id);
		int count = CuishuyangDAO.goodscount(user_id);
		// System.out.println("counts" + count);
		// System.out.println("**************");
		int[] orderId = new int[sum];
		orderId = CuishuyangDAO.orderId(user_id);// orderId = CuishuyangDAO.orderID(user_id);
		int[] goodsId = new int[count];
		int i = 0;
		String sql = new String();
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			for (int j = 0; j < sum; j++) {

				sql = "select goods_id from orderItem where orderItem_status = 2 and order_id = " + orderId[j];//
				ResultSet rs = s.executeQuery(sql);
				if (j + 1 == sum)
					break;
				while (rs.next()) {
					goodsId[i] = rs.getInt("goods_id");
//					System.out.println("这是第几个" + i );
//					System.out.println("goodsId " + rs.getInt("goods_id"));
					i++;
				}

			}
			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				goodsId[i] = rs.getInt("goods_id");
//				System.out.println("这是第几个" + i );
//				System.out.println("goodsId " + rs.getInt("goods_id"));
				i++;
			}
			// System.out.println("**************");
			DBHelper.closeConnection(c, s, rs);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return goodsId;
	}
	
	public static List<Goods> listGoods(int user_id){
		
		List<Goods> list = new ArrayList<Goods>();
		
		
		  try {
		  
		  Connection c = DBHelper.getInstance().getConnection();
		  
		  Statement s = c.createStatement();
		  
		  String sql = ;
		  
		  ResultSet rs = s.executeQuery(sql); while (rs.next()) { }
		  
		  DBHelper.closeConnection(c, s, rs);
		  
		  } catch (Exception e) { e.printStackTrace(); }
		 
		
		
		
		return list;
	}
	
	
}
