/**
 * 
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author wben 
 *
 */
public class DBHelper {

	public String url = "jdbc:mysql://localhost:3306/university?useUnicode=true&characterEncoding=UTF-8";
	public String username = "root";
	public String password = "123456";
	public static DBHelper instance = null;

	// 闁俺绻冮棃娆愶拷浣峰敩閻礁娼″▔銊ュ斀閺佺増宓佹惔鎾烩攳閸旑煉绱濇穱婵婄槈濞夈劌鍞介崣顏呭⒔鐞涘奔绔村▎锟�
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private DBHelper() {
	}

	/**
	 * 閼惧嘲绶辩猾璇茬杽娓氾拷
	 * 
	 * @return
	 */
	public static DBHelper getInstance() {
		// 缂佹瑧琚崝鐘绘敚,闂冨弶顒涚痪璺ㄢ柤楠炶泛褰�
		synchronized (DBHelper.class) {
			if (instance == null) {
				instance = new DBHelper();
			}
		}
		return instance;
	}

	/**
	 * 閼惧嘲绶辨潻鐐村复
	 * 
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

	/**
	 * 閸忔娊妫存潻鐐村复
	 * 
	 * @param conn
	 * @param st
	 * @param rs
	 */
	public static void closeConnection(Connection conn, Statement st,
			ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
