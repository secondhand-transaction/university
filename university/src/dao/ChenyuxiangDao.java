package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.*;
import utils.DBHelper;

public class ChenyuxiangDao {
	//private PreparedStatement preparedStatement = null;

	private ChenyuxiangDao() {
	}

	public static ChenyuxiangDao getInstance() {
		return new ChenyuxiangDao();
	}
	
	public List<Goods> select(String keyword) throws Exception {
		 List<Goods> goods = new ArrayList<Goods>();
		 
		 try {
		 String sql = "select distinct goods_id,goods_name,price,goods_image from goods where goods_name like '%"+keyword+"%' and goods_status = 0 ";
		 String sql1 = "select distinct goods_id,goods_name,price,goods_image from goods where goods_name like '%"+keyword+"%' and goods_status = 4 ";
		    Connection c = DBHelper.getInstance().getConnection();
		    PreparedStatement ps = c.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();
		    
		    PreparedStatement ps1 = c.prepareStatement(sql1);
		    ResultSet rs1 = ps1.executeQuery();
		    
		    
		    
		    while (rs.next()) {
		    	Goods good = new Goods();
		    	int goods_id = rs.getInt("goods_id");
				String goods_name = rs.getString("goods_name");
				double price = rs.getDouble("price");
				String goods_img = rs.getString("goods_image");
				good.setGoods_img(goods_img);
				good.setGoods_id(goods_id);
				good.setGoods_name(goods_name);
				good.setPrice(price);
				
				goods.add(good);
		    }
		    
		    
		    while (rs1.next()) {
		    	Goods good = new Goods();
		    	int goods_id = rs1.getInt("goods_id");
				String goods_name = rs1.getString("goods_name");
				double price = rs1.getDouble("price");
				String goods_img = rs1.getString("goods_image");
				good.setGoods_img(goods_img);
	        	
				good.setGoods_id(goods_id);
				good.setGoods_name(goods_name);
				good.setPrice(price);
				goods.add(good);
		    }
		    
		    
		    DBHelper.closeConnection(c, ps, rs);
		    DBHelper.closeConnection(c, ps1, rs1);
			} catch (Exception e) {
				e.printStackTrace();
			}

		    return goods;
		  }
	
	public List<Goods> list() {
		List<Goods> goods = new ArrayList<Goods>();

		try {

			Connection c = DBHelper.getInstance().getConnection();

			String sql = "select distinct goods_id,goods_name,price,goods_image from goods where goods_status = 0 order by goods_id desc";
            String sql1 ="select distinct goods_id,goods_name,price,goods_image from goods where goods_status = 4 order by goods_id desc";
			PreparedStatement ps = c.prepareStatement(sql);
			PreparedStatement ps1 = c.prepareStatement(sql1);
			
			ResultSet rs = ps.executeQuery();
			ResultSet rs1 = ps1.executeQuery();

			while (rs.next()) {
				Goods good = new Goods();
		    	int goods_id = rs.getInt("goods_id");
		    	//System.out.println("asdasd"+goods_id);
				String goods_name = rs.getString("goods_name");
				double price = rs.getDouble("price");
				String goods_img = rs.getString("goods_image");
				good.setGoods_img(goods_img);
	        	
				good.setGoods_id(goods_id);
				good.setGoods_name(goods_name);
				good.setPrice(price);
				goods.add(good);
				
			}
			
			while (rs1.next()) {
				Goods good = new Goods();
		    	int goods_id = rs1.getInt("goods_id");
		    	//System.out.println("asdasd"+goods_id);
				String goods_name = rs1.getString("goods_name");
				double price = rs1.getDouble("price");
				String goods_img = rs1.getString("goods_image");
				good.setGoods_img(goods_img);
	        	
				good.setGoods_id(goods_id);
				good.setGoods_name(goods_name);
				good.setPrice(price);
				goods.add(good);
				
			}
			
			DBHelper.closeConnection(c, ps, rs);
			DBHelper.closeConnection(c, ps1, rs1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return goods;
	}
	
	
	public List<Goods> detail(int goods_id) throws Exception {
		 List<Goods> goods = new ArrayList<Goods>();
		 
		 try {
			 
			 Connection c = DBHelper.getInstance().getConnection();
			 //String sql1 = "select * from goods where goods_id = " + goods_id;
		    String sql ="select distinct goods_name,price,user_id,goods_image,description,goods_status from goods where goods_id = " + goods_id;
		    
		    PreparedStatement ps = c.prepareStatement(sql);
			
		    ResultSet rs = ps.executeQuery();
		    
		    while (rs.next()) {
		    	Goods good = new Goods();
				String goods_name = rs.getString("goods_name");
				double price = rs.getDouble("price");
				int user_id =rs.getInt("user_id"); 
				String goods_img = rs.getString("goods_image");
				String de = rs.getString("description");
				int status = rs.getInt("goods_status");
				good.setGoods_status(status);
				good.setDescription(de);		
				good.setGoods_img(goods_img);
	        	
				good.setGoods_id(goods_id);
				good.setGoods_name(goods_name);
				good.setPrice(price);
				good.setUser_id(user_id);
				goods.add(good);
		    }
		    
		    DBHelper.closeConnection(c, ps, rs);
			} catch (Exception e) {
				e.printStackTrace();
			}

		    return goods;
		  }
	
	
	public Goods jubao(int goods_id) throws Exception {
		 Goods goods = new Goods();
		 
		 try {
			 
			 Connection c = DBHelper.getInstance().getConnection();
			 //String sql1 = "select * from goods where goods_id = " + goods_id;
		    String sql ="update goods set goods_status = 4 where goods_id = " + goods_id;
		    
		    PreparedStatement ps = c.prepareStatement(sql);
			
		    ps.execute();
		    
//		    while (rs.next()) {
//		    	Goods good = new Goods();
//				String goods_name = rs.getString("goods_name");
//				double price = rs.getDouble("price");
//				int user_id =rs.getInt("user_id"); 
//				
//	        	
//				good.setGoods_id(goods_id);
//				good.setGoods_name(goods_name);
//				good.setPrice(price);
//				good.setUser_id(user_id);
//				goods.add(good);
//		    }
		    
		    DBHelper.closeConnection(c, ps,null);
			} catch (Exception e) {
				e.printStackTrace();
			}

		    return goods;
		  }
	
	
	public List<Goods> category(int ct_id) throws Exception {
		 List<Goods> goods = new ArrayList<Goods>();
		 try {
		 String sql = "select distinct goods_id,goods_name,price,goods_image from goods where category_id = " + ct_id + " and goods_status = 0 ";
		 String sql1 = "select distinct goods_id,goods_name,price,goods_image from goods where category_id = " + ct_id + " and goods_status = 4 ";
		    Connection c = DBHelper.getInstance().getConnection();
		    PreparedStatement ps = c.prepareStatement(sql);
		    ResultSet rs = ps.executeQuery();
		    PreparedStatement ps1 = c.prepareStatement(sql1);
		    ResultSet rs1 = ps1.executeQuery();
		    while (rs.next()) {
		    	Goods good = new Goods();
		    	int goods_id = rs.getInt("goods_id");
				String goods_name = rs.getString("goods_name");
				double price = rs.getDouble("price");
				String goods_img = rs.getString("goods_image");
				good.setGoods_img(goods_img);
				good.setGoods_id(goods_id);
				good.setGoods_name(goods_name);
				good.setPrice(price);
				goods.add(good);
		    }
		    while (rs1.next()) {
		    	Goods good = new Goods();
		    	int goods_id = rs1.getInt("goods_id");
				String goods_name = rs1.getString("goods_name");
				double price = rs1.getDouble("price");
				String goods_img = rs1.getString("goods_image");
				good.setGoods_img(goods_img);
				good.setGoods_id(goods_id);
				good.setGoods_name(goods_name);
				good.setPrice(price);
				goods.add(good);
		    }
		    DBHelper.closeConnection(c, ps, rs);
		    DBHelper.closeConnection(c, ps1, rs1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    return goods;
		  }
	
//	public List<Goods> digital() throws Exception {
//		 List<Goods> goods = new ArrayList<Goods>();
//		 try {
//		 String sql = "select distinct goods_id,goods_name,price from goods where category_id = 2 and goods_status = 0 ";
//		 String sql1 = "select distinct goods_id,goods_name,price from goods where category_id = 2 and goods_status = 2 ";
//		    Connection c = DBHelper.getInstance().getConnection();
//		    PreparedStatement ps = c.prepareStatement(sql);
//		    ResultSet rs = ps.executeQuery();
//		    PreparedStatement ps1 = c.prepareStatement(sql1);
//		    ResultSet rs1 = ps1.executeQuery();
//		    while (rs.next()) {
//		    	Goods good = new Goods();
//		    	int goods_id = rs.getInt("goods_id");
//				String goods_name = rs.getString("goods_name");
//				double price = rs.getDouble("price");
//				good.setGoods_id(goods_id);
//				good.setGoods_name(goods_name);
//				good.setPrice(price);
//				goods.add(good);
//		    }
//		    while (rs1.next()) {
//		    	Goods good = new Goods();
//		    	int goods_id = rs1.getInt("goods_id");
//				String goods_name = rs1.getString("goods_name");
//				double price = rs1.getDouble("price");
//				good.setGoods_id(goods_id);
//				good.setGoods_name(goods_name);
//				good.setPrice(price);
//				goods.add(good);
//		    }
//		    DBHelper.closeConnection(c, ps, rs);
//		    DBHelper.closeConnection(c, ps1, rs1);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		    return goods;
//		  }
	
	
	
	
}
