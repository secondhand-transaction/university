package dao;

import entity.HistoryBorrow;
import entity.HistoryBorrow1;
import utils.DBHelper;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class HistoryBorrowDAO_1 {

	public HistoryBorrowDAO_1 () {}
	
	public void addCurrentBorrow(HistoryBorrow historyBorrow) {
		try {
			Connection c = DBHelper.getInstance().getConnection();
			String sql = "insert into historyborrow (ISBN, ID,Borrowtime,Returntime,Bookname,Stunumber,Historyfine) values (?,?,?,?,?,?,?)";
			PreparedStatement ps = c.prepareStatement(sql);
			
			ps.setString(1, historyBorrow.getISBN());
			ps.setInt(2, historyBorrow.getID());
			ps.setTimestamp(3, historyBorrow.getBorrowtime());
			ps.setTimestamp(4, historyBorrow.getReturntime());
			ps.setString(5, historyBorrow.getBookname());
			ps.setLong(6, historyBorrow.getStunumber());
			ps.setDouble(7, historyBorrow.getHistoryfine());
			
			ResultSet rs = ps.executeQuery(sql);
			
			DBHelper.closeConnection(c, ps, rs);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}