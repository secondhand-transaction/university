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
public List<HistoryBorrow1> list(int start, int count, Long stunumber) {
		List<HistoryBorrow1> historyBorrows = new ArrayList<HistoryBorrow1>();
		HistoryBorrow historyBorrow = null;
		HistoryBorrow1 historyBorrow1 = null;
		String sql = "select * from historyborrow where stunumber = ? order by borrowtime desc limit ?,?";
		try {
			Connection c = DBHelper.getInstance().getConnection();
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setLong(1, stunumber);
			ps.setInt(2, start);
			ps.setInt(3, count);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				historyBorrow = new HistoryBorrow();
				historyBorrow1 = new HistoryBorrow1();
				String ISBN = rs.getString("ISBN");
				int ID = rs.getInt("ID");
				Timestamp borrowtime = rs.getTimestamp("borrowtime");
				Timestamp returntime = rs.getTimestamp("returntime");
				String bookname = rs.getString("bookname");
				Long Stunumber = rs.getLong("stunumber");
				Double historyfine = rs.getDouble("historyfine");

				historyBorrow.setISBN(ISBN);
				historyBorrow.setID(ID);
				historyBorrow.setBorrowtime(borrowtime);
				historyBorrow.setReturntime(returntime);
				historyBorrow.setBookname(bookname);
				historyBorrow.setStunumber(stunumber);
				historyBorrow.setHistoryfine(historyfine);
				historyBorrow1.History1_History(historyBorrow);
				historyBorrows.add(historyBorrow1);
			
			}
			DBHelper.closeConnection(c, ps, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return historyBorrows;
	}
		
}