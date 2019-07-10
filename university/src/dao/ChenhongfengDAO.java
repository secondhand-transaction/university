/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import utils.DBHelper;
import entity.*;
/**
 * @author feng
 *
 */
public class ChenhongfengDAO {
	public static ChenhongfengDAO getInstance() {
		return new ChenhongfengDAO();
	}

	private Connection c;
	private Statement s;
	PreparedStatement ps1;
	PreparedStatement ps2;
	PreparedStatement ps;
	private ResultSet rs;
	public void add(String user_id,ArrayList<Cart> carts)
		  {

		try {

			c = DBHelper.getInstance().getConnection();
            int uid = Integer.parseInt(user_id);
            int order_id = Count.getCount();
			String sql1 = "insert into `order` (user_id) values(?)";
			 ps1 = c.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			ps1.setInt(1, uid);			
			ps1.execute();
			sql1 = "select order_id from `order` where user_id = ?";
			 ps1 = c.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
			ps1.setInt(1, uid);
			rs=ps1.executeQuery();
			while(rs.next()) {
				order_id=rs.getInt("order_id");
			}
			
			for(int i=0;i<carts.size();i++) {
				String sql = "insert into orderItem (order_id,goods_id,orderItem_status) values(?,?,?)";
				ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				ps.setInt(1, order_id);
				ps.setInt(2,Integer.parseInt(carts.get(i).getGoods_id()));
				ps.setInt(3, 0);
				ps.execute();
				String sql2 = "update goods set goods_status = 2 where goods_id =?";
				ps2 = c.prepareStatement(sql2);
				ps2.setInt(1, Integer.parseInt(carts.get(i).getGoods_id()));
				ps2.execute();
				
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeConnection(c, ps, null);
			DBHelper.closeConnection(c, ps1, rs);
			DBHelper.closeConnection(c, ps2, null);
		}
	}
	
	
	public void buyercancel(String orderItem_id,String goods_id) {
		try {

			c = DBHelper.getInstance().getConnection();
			String sql = "update orderItem set orderItem_status = -2 where orderItem_id=?";
			ps = c.prepareStatement(sql);			
			ps.setInt(1,Integer.parseInt(orderItem_id));
			ps.execute();
			String sql1 = "update goods set goods_status = 0 where goods_id=?";
			ps1 = c.prepareStatement(sql1);			
			ps1.setInt(1,Integer.parseInt(goods_id));
			ps1.execute();
			/*
			 * String sql1 = "  CREATE EVENT e_test" + Count.getCount() +
			 * " ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 300 SECOND" +
			 * " DO CALL timerr(" + ID + "," + ISBN + ");"; PreparedStatement
			 * ps1 = c.prepareStatement(sql1); ps1.execute();
			 */

			/* DBHelper.closeConnection(c, ps1, null); */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeConnection(c, ps, null);
			DBHelper.closeConnection(c, ps1, null);
		}

	}
	public String sellercancel(String goods_id) {
		String id = "000";
		try {

			c = DBHelper.getInstance().getConnection();
			int orderItem_id=0;;
			String sql = "select orderItem_id from orderItem where goods_id=? and orderItem_status = 2 ";
			ps = c.prepareStatement(sql);			
			ps.setInt(1,Integer.parseInt(goods_id));
			rs = ps.executeQuery();
			while (rs.next()) {
				orderItem_id = rs.getInt("orderItem_id");
			}
			
			if(orderItem_id==0) {
			sql = "select orderItem_id from orderItem where goods_id=? and orderItem_status = 0 ";
			ps = c.prepareStatement(sql);			
			ps.setInt(1,Integer.parseInt(goods_id));
			rs = ps.executeQuery();
			while (rs.next()) {
				orderItem_id = rs.getInt("orderItem_id");
			}
			}
			 sql = "update orderItem set orderItem_status = -1 where orderItem_id=?";
			ps = c.prepareStatement(sql);			
			ps.setInt(1,orderItem_id);
			ps.execute();
			String sql1 = "update goods set goods_status = 0 where goods_id=?";
			ps1 = c.prepareStatement(sql1);			
			ps1.setInt(1,Integer.parseInt(goods_id));
			ps1.execute();
			int order_id = 0 ; 
			sql1 = "select order_id from orderItem where orderItem_id=?";
			ps1 = c.prepareStatement(sql1);			
			ps1.setInt(1,orderItem_id);
			rs=ps1.executeQuery();
			while(rs.next()) {
				order_id=rs.getInt("order_id");
				System.out.println(order_id);
			}			
			sql1 = "select user_id from `order` where order_id=?";
			ps1 = c.prepareStatement(sql1);			
			ps1.setInt(1,order_id);
			rs=ps1.executeQuery();
			while(rs.next()) {
				id= String.valueOf(rs.getInt("user_id"));
				System.out.println(id);
			}
			/*
			 * String sql1 = "  CREATE EVENT e_test" + Count.getCount() +
			 * " ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 300 SECOND" +
			 * " DO CALL timerr(" + ID + "," + ISBN + ");"; PreparedStatement
			 * ps1 = c.prepareStatement(sql1); ps1.execute();
			 */

			/* DBHelper.closeConnection(c, ps1, null); */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeConnection(c, ps, null);
			DBHelper.closeConnection(c, ps1, rs);
		}
		return id;

	}
	public void buyersuer(String orderItem_id,String goods_id) {
		try {

			c = DBHelper.getInstance().getConnection();
			String sql = "select orderItem_status from orderItem where orderItem_id=?";
			ps = c.prepareStatement(sql);			
			ps.setInt(1,Integer.parseInt(orderItem_id));
			rs = ps.executeQuery();
			int status = 0;
			while (rs.next()) {
				 status = rs.getInt("orderItem_status");
			}
			status+=2;
			String sql1 = "update orderItem set orderItem_status = ? where orderItem_id=?";
			ps1 = c.prepareStatement(sql1);			
			ps1.setInt(2,Integer.parseInt(orderItem_id));
			ps1.setInt(1,status);
			ps1.execute();
			sql1 = "update goods set goods_status = 22 where goods_id=?";
			ps1 = c.prepareStatement(sql1);			
			ps1.setInt(1,Integer.parseInt(goods_id));
			ps1.execute();
			if(status==3) {
				String sql2 = "update goods set goods_status = 3 where goods_id=?";
				ps2 = c.prepareStatement(sql2);			
				ps2.setInt(1,Integer.parseInt(goods_id));
				ps2.execute();
			}
			/*
			 * String sql1 = "  CREATE EVENT e_test" + Count.getCount() +
			 * " ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 300 SECOND" +
			 * " DO CALL timerr(" + ID + "," + ISBN + ");"; PreparedStatement
			 * ps1 = c.prepareStatement(sql1); ps1.execute();
			 */

			/* DBHelper.closeConnection(c, ps1, null); */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeConnection(c, ps, rs);
			DBHelper.closeConnection(c, ps1, null);
			DBHelper.closeConnection(c, ps2, null);
		}

	}
	public void sellersure(String goods_id) {
		try {
			
			c = DBHelper.getInstance().getConnection();
			int orderItem_id=0;;
			String sql = "select orderItem_id from orderItem where goods_id=? and orderItem_status = 2 ";
			ps = c.prepareStatement(sql);			
			ps.setInt(1,Integer.parseInt(goods_id));
			rs = ps.executeQuery();
			while (rs.next()) {
				orderItem_id = rs.getInt("orderItem_id");
			}
			
			if(orderItem_id==0) {
			sql = "select orderItem_id from orderItem where goods_id=? and orderItem_status = 0 ";
			ps = c.prepareStatement(sql);			
			ps.setInt(1,Integer.parseInt(goods_id));
			rs = ps.executeQuery();
			while (rs.next()) {
				orderItem_id = rs.getInt("orderItem_id");
			}
			}
		
			sql = "select orderItem_status from orderItem where orderItem_id=?";
			ps = c.prepareStatement(sql);			
			ps.setInt(1,orderItem_id);
			rs = ps.executeQuery();
			int status = 0;
			while (rs.next()) {
				 status = rs.getInt("orderItem_status");
			}
			
			status+=1;
			String sql1 = "update orderItem set orderItem_status = ? where orderItem_id=?";
			ps1 = c.prepareStatement(sql1);			
			ps1.setInt(2,orderItem_id);
			ps1.setInt(1,status);
			ps1.execute();
			sql1 = "update goods set goods_status = 21 where goods_id=?";
			ps1 = c.prepareStatement(sql1);			
			ps1.setInt(1,Integer.parseInt(goods_id));
			ps1.execute();
			if(status==3) {
				String sql2 = "update goods set goods_status = 3 where goods_id=?";
				ps2 = c.prepareStatement(sql2);			
				ps2.setInt(1,Integer.parseInt(goods_id));
				ps2.execute();
			}
			/*
			 * String sql1 = "  CREATE EVENT e_test" + Count.getCount() +
			 * " ON SCHEDULE AT CURRENT_TIMESTAMP + INTERVAL 300 SECOND" +
			 * " DO CALL timerr(" + ID + "," + ISBN + ");"; PreparedStatement
			 * ps1 = c.prepareStatement(sql1); ps1.execute();
			 */

			/* DBHelper.closeConnection(c, ps1, null); */
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBHelper.closeConnection(c, ps, rs);
			DBHelper.closeConnection(c, ps1, null);
			DBHelper.closeConnection(c, ps2, null);
		}

	}
	
	public List<Goods> list(String user_id) {
		List<Goods> goods = new ArrayList<Goods>();

		try {

			Connection c = DBHelper.getInstance().getConnection();

			String sql = "select distinct goods_id,goods_name,price,goods_image,evaluation,goods_status from goods where user_id = ? ";
            
			 ps = c.prepareStatement(sql);
			 ps.setInt(1,Integer.parseInt(user_id));
			 rs = ps.executeQuery();
		

			while (rs.next()) {
				Goods good = new Goods();
		    	int goods_id = rs.getInt("goods_id");
		    	//System.out.println("asdasd"+goods_id);
				String goods_name = rs.getString("goods_name");
				double price = rs.getDouble("price");
				String goods_img = rs.getString("goods_image");
				int status = rs.getInt("goods_status");
				String eva = rs.getString("evaluation");
				good.setEvaluation(eva);
				good.setGoods_status(status);
				good.setGoods_img(goods_img);
	        	
				good.setGoods_id(goods_id);
				good.setGoods_name(goods_name);
				good.setPrice(price);
				goods.add(good);
				
			}
			
			DBHelper.closeConnection(c, ps, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goods;
	}
	public String namebug(String goods_id) {
		String name = "bug";

		try {

			Connection c = DBHelper.getInstance().getConnection();

			String sql = "select goods_name from goods where goods_id = ? ";
            
			 ps = c.prepareStatement(sql);
			 ps.setInt(1,Integer.parseInt(goods_id));
			 rs = ps.executeQuery();
		

			while (rs.next()) {
				
				name = rs.getString("goods_name");
			}
			
			DBHelper.closeConnection(c, ps, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
	public String idbug(String goods_id) {
		String id = "bug";

		try {

			Connection c = DBHelper.getInstance().getConnection();

			String sql = "select user_id from goods where goods_id = ? ";
            
			 ps = c.prepareStatement(sql);
			 ps.setInt(1,Integer.parseInt(goods_id));
			 rs = ps.executeQuery();
		

			while (rs.next()) {
				
				id = rs.getString("user_id");
			}
			
			DBHelper.closeConnection(c, ps, rs);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}
}
