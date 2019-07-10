package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Category;
import entity.Goods;
import entity.QueryGood;
import utils.DBHelper;

public class XutaigeDao {
	PreparedStatement ps;
	ResultSet rs;
	public List<QueryGood> getQueryGoods() {
		List<QueryGood> qGoods = new ArrayList<QueryGood>();
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "select query_id, user_id, query_name, category.category_id,category_name from querygood inner JOIN category on querygood.category_id = category.category_id";
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				QueryGood qg = new QueryGood();
				
				int queryId = rs.getInt("query_id");
				int userId = rs.getInt("user_id");
				String queryName = rs.getString("query_name");
				int categoryId = rs.getInt("category.category_id");
				String categoryName = rs.getString("category_name");
				
				qg.setQueryId(queryId);
				qg.setUserId(userId);
				qg.setQueryName(queryName);
				qg.setCategoryId(categoryId);
				qg.setCategoryName(categoryName);
				
				
				qGoods.add(qg);
				
			}
			
			DBHelper.closeConnection(conn, ps,rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return qGoods;
	}
	
	public List<Category> getCatagorys() {
		List<Category> catagorys = new ArrayList<Category>();
		
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			
			String sql = "select * from category";
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Category c = new Category();
				
				int catagoryId = rs.getInt("category_id");
				
				String catagoryName = rs.getString("category_name");
				
				c.setCategory_id(catagoryId);
				c.setCategory_name(catagoryName);
				
				catagorys.add(c);
				
			}
			DBHelper.closeConnection(conn, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return catagorys;
	}
	
	public int addQueryGood(QueryGood qg) {
		try {
			Connection conn = DBHelper.getInstance().getConnection();

			String sql = "insert into querygood(user_id, query_name, category_id) values (?,?,?)";
			
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, qg.getUserId());
			ps.setString(2, qg.getQueryName());
			ps.setInt(3, qg.getCategoryId());
			
			ps.execute();
			
			DBHelper.closeConnection(conn, ps, null);

			return 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public Category getCategory(int categoryId) {
		Category c = null;
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			String sql = "select * from category where category_id= " + categoryId;
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				String categoryName = rs.getString("category_name");
				
				c = new Category();
				
				c.setCategory_id(categoryId);
				c.setCategory_name(categoryName);
			}
			
			DBHelper.closeConnection(conn, ps, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return c;
	}


	public List<QueryGood> getQueryGoodNameByCageId(int category_id) {
		List<QueryGood> queryGood = new ArrayList<QueryGood>();
		
		try {
			Connection conn = DBHelper.getInstance().getConnection();
			
			String sql = "select * from querygood where category_id="+category_id;
			
			QueryGood qg = null;
			
			ps = conn.prepareStatement(sql);
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				qg = new QueryGood();
				
				qg.setCategoryId(rs.getInt("category_id"));
				qg.setUserId(rs.getInt("user_id"));
				qg.setQueryName(rs.getString("query_name"));
				
				queryGood.add(qg);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return queryGood;
	}
}
