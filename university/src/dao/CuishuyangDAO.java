package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DBHelper;
import entity.Goods;
import entity.OrderItem;
import entity.BuyerGoods;

public class CuishuyangDAO {

	private CuishuyangDAO() {
	}

	private static ResultSet rs1;

	private static ResultSet rs2;

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
	// public static int sum(int uesr_id) //��ʱ��ֱ��ע�͵�
	public static int sum(int user_id) {

		int total = 0;

		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "select count(*) from `order` where user_id = " + user_id;// id = " + id�Ժ��ڴ˽���ǰ�˴�������ֵ

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

	public static int sumgoods(int user_id) {

		int total = 0;

		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "select count(*) from goods where user_id = " + user_id;// id = " + id�Ժ��ڴ˽���ǰ�˴�������ֵ

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

	// �ڴ˲�ѯ�������Ķ����Ĳ�Ʒ�ð����Ѿ����ɵĶ�����û��ɵ�
	// public static int[] userID(int user_id){
	public static int[] orderId(int user_id) {

		int total = CuishuyangDAO.sum(user_id);
		
		int i = 0;
		
		int[] orderId = new int[total];
		
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "select * from `order` where user_id = " + user_id;// user_id = " + user_id;

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

		

		int count = 0;
		
		int counts = 0;
		
		int sum = CuishuyangDAO.sum(user_id);
		
		int[] orderId = new int[sum];
		// System.out.println("sum" + sum);
		
		orderId = CuishuyangDAO.orderId(user_id);// orderId = CuishuyangDAO.orderID(user_id);
		
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			for (int i = 0; i < sum; i++) {
				String sql = "select count(*) from orderItem where  order_id = " + orderId[i];//
				// rs = s.executeQuery(sql);
				
				rs1 = s.executeQuery(sql);
				
				while (rs1.next()) {
					// System.out.println("***********");
					// System.out.println("orderid" + orderId[i]);
					count = rs1.getInt(1);
					// System.out.println("count" + count);
				}
				counts = counts + count;
				count = 0;
			}

			DBHelper.closeConnection(c, s, rs1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return counts;
	}

	// public static int[] orderItemId(int user_id)
	public static int[] goodsId(int user_id) {

		int sum = CuishuyangDAO.sum(user_id);

		int count = CuishuyangDAO.goodscount(user_id);
		// System.out.println("counts" + count);
		// System.out.println("**************");
		
		int[] orderId = new int[sum];
		
		orderId = CuishuyangDAO.orderId(user_id);// orderId = CuishuyangDAO.orderID(user_id);
		
		int[] goodsId = new int[count];
		
		int i = 0;
		
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			for (int j = 0; j < sum; j++) {

				
				String sql = "select goods_id from orderItem where order_id = " + orderId[j];//
				
				ResultSet rs = s.executeQuery(sql);

				while (rs1.next()) {
					
					goodsId[i] = rs.getInt("goods_id");
//					System.out.println("���ǵڼ���" + i );
//					System.out.println("goodsId " + rs.getInt("goods_id"));
					i++;
				}

			}
			// System.out.println("**************");
			DBHelper.closeConnection(c, s, rs1);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return goodsId;
	}

	/**
	 * @param user_id
	 * @return
	 */
	public static List<Goods> ListGoods(int user_id) {

		user_id = 123;

		List<Goods> ListGoods = new ArrayList<Goods>();
		int i = 0;
		// count ָ�ж�����goods ����
		int count = CuishuyangDAO.goodscount(user_id);
		int[] goodsId = new int[count];
		String[] strSql = new String[count];
		goodsId = CuishuyangDAO.goodsId(user_id);

		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			for (i = 0; i < count; i++) {

				strSql[i] = "select * from goods where goods_id = " + goodsId[i];

				// System.out.println(strSql[i]);

				ResultSet rs = s.executeQuery(strSql[i]);

				while (rs.next()) {

					Goods goods = new Goods();
					
					goods.setGoods_id(rs.getInt("goods_id"));
					
					goods.setUser_id(rs.getInt("user_id"));
					
					goods.setGoods_name(rs.getString("goods_name"));
					
					goods.setDescription(rs.getString("description"));
					
					goods.setCategory_id(rs.getInt("category_id"));
					
					goods.setPrice(rs.getInt("price"));
					
					goods.setGoods_status(rs.getInt("goods_status"));
					
					ListGoods.add(goods);
				}

			}

			ResultSet rs = s.executeQuery(strSql[0]);

			DBHelper.closeConnection(c, s, rs);

		} catch (

		Exception e) {
			e.printStackTrace();
		}

		return ListGoods;
	}

	public static int getUserstatus(int user_id) {

		int status = 0;

		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "select user_staus from user where user_id = " + user_id;// id = " + id�Ժ��ڴ˽���ǰ�˴�������ֵ

			ResultSet rs = s.executeQuery(sql);

			status = rs.getInt("user_status");

			// System.out.println("total:" + total);

			DBHelper.closeConnection(c, s, rs);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	//
	public static void PublishGoos(int user_id, String goods_name, String description, double price) {

		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "insert into reserve values(?,?,?,?,?,?,?,?)";

			PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			// ps.setInt

			// ps.execute();

			DBHelper.closeConnection(c, ps, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static List<BuyerGoods> BuyerGoods(int user_id) {

		int sum = CuishuyangDAO.sum(user_id);

		int[] order = new int[sum];

		order = CuishuyangDAO.orderId(user_id);
		
		//System.out.println("order = " + order[0]);
		
		List<BuyerGoods> BuyerGoods = new ArrayList<BuyerGoods>();

		String sql1 = new String();

		String sql2 = new String();

		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s1 = c.createStatement();

			Statement s2 = c.createStatement();

			for (int i = 0; i < sum; i++) {

				sql1 = "select * from orderitem where order_id = " + order[i];
				
				//System.out.println("order = " + order[i]);

				rs1 = s1.executeQuery(sql1);

				// System.out.println(i);

				while (rs1.next()) {

					OrderItem orderItem = new OrderItem();

					Goods goods = new Goods();

					orderItem.setGoods_id(rs1.getInt("goods_id"));

					orderItem.setOrder_id(rs1.getInt("order_id"));

					orderItem.setOrderItem(rs1.getInt("orderItem_id"));

					orderItem.setOrderItem_status(rs1.getInt("orderItem_status"));

					sql2 = "select * from goods where goods_id = " + rs1.getInt("goods_id");

					rs2 = s2.executeQuery(sql2);

					while (rs2.next()) {

						goods.setCategory_id(rs2.getInt("category_id"));

						goods.setDescription(rs2.getString("description"));

						goods.setGoods_id(rs2.getInt("goods_id"));

						goods.setGoods_name(rs2.getString("goods_name"));

						goods.setGoods_status(rs2.getInt("goods_status"));

						goods.setPrice(rs2.getDouble("price"));

						goods.setUser_id(rs2.getInt("user_id"));
					}

					BuyerGoods buyergoods = new BuyerGoods(goods, orderItem);

					BuyerGoods.add(buyergoods);

				}

			}

			DBHelper.closeConnection(c, s1, rs1);

			DBHelper.closeConnection(c, s1, rs2);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return BuyerGoods;

	}

	public static List<Goods> Goods(int user_id) {

		List<Goods> ListGoods = new ArrayList<Goods>();

		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "select * from goods where user_id = " + user_id;

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {

				Goods goods = new Goods();
				goods.setGoods_id(rs.getInt("goods_id"));
				goods.setUser_id(rs.getInt("user_id"));
				goods.setGoods_name(rs.getString("goods_name"));
				goods.setDescription(rs.getString("description"));
				goods.setCategory_id(rs.getInt("category_id"));
				goods.setPrice(rs.getInt("price"));
				goods.setGoods_status(rs.getInt("goods_status"));
				ListGoods.add(goods);
			}

			DBHelper.closeConnection(c, s, rs);

		} catch (

		Exception e) {
			e.printStackTrace();
		}

		return ListGoods;
	}

	public static Goods showgoods(int goods_id) {

		Goods good = new Goods();

		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "select * from goods where goods_id = " + goods_id;

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
			
			good.setGoods_id(rs.getInt("goods_id"));
			
			good.setUser_id(rs.getInt("user_id"));
			
			good.setGoods_name(rs.getString("goods_name"));
			
			good.setDescription(rs.getString("description"));
			
			good.setCategory_id(rs.getInt("category_id"));
			
			good.setPrice(rs.getInt("price"));
			
			good.setGoods_status(rs.getInt("goods_status"));
			
			}

			DBHelper.closeConnection(c, s, rs);

		} catch (

		Exception e) {
			e.printStackTrace();
		}

		return good;

	}
	
	public static void updateGoods(String goods_name, double price, String description, int goods_id) {
		
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "update goods set goods_name = ?, price = ?, description = ? where goods_id = " + goods_id;

			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, goods_name);
			
			ps.setDouble(2, price);
			
			ps.setString(3, description);
			
			ps.execute();

			DBHelper.closeConnection(c, ps, null);

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void cancel(int goods_id) {
		
		try {

			Connection c = DBHelper.getInstance().getConnection();

			Statement s = c.createStatement();

			String sql = "update goods set goods_status = ? where goods_id = " + goods_id;

			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setInt(1, -1);
			
			ps.execute();

			DBHelper.closeConnection(c, ps, null);

		} catch (

		Exception e) {
			e.printStackTrace();
		}
	}
}
