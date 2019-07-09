package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import dao.XuejinlongDao;
import entity.Goods;
import entity.User;

public class ManagementServlet extends HttpServlet{
	XuejinlongDao xue = new XuejinlongDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");		
		request.setCharacterEncoding("UTF-8");
		String option = request.getParameter("op");
		System.out.println(option);
		if("listUsers".equals(option)) {
			listUsers(request, response);
		}else if("addUser".equals(option)) {
			addUser(request, response);
		}else if("delUser".equals(option)) {
			try {
				delUser(request, response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if("editUser".equals(option)) {
			editUser(request, response);
		}else if("searchUser".equals(option)) {
			try {
				searchUser(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if ("listBeiJuBao".equals(option)) {
			listBeiJuBao(request,response);
		}else if ("listAudited".equals(option)) {
			listAudited(request,response);
		}else if ("listXiaJia".equals(option)) {
			listXiaJia(request,response);
		}else if ("listNormal".equals(option)) {
			listNormal(request,response);
		}else if ("xiaJia".equals(option)) {
			try {
				xiaJia(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if ("recover".equals(option)) {
			try {
				recover(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}else if ("verify".equals(option)) {
			try {
				verify(request,response);
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
	}
	
	

	private void verify(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO 自动生成的方法存根
		int id = Integer.valueOf(request.getParameter("userId"));
		User user = xue.findUserById(id);
		xue.verify(user);
		request.getRequestDispatcher("/servlet/ManagementServlet?op=listUsers").forward(request, response);
		
	}

	private void recover(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO 自动生成的方法存根
		int id = Integer.valueOf(request.getParameter("goodsId"));
		Goods good = xue.findGoodsById(id);
		xue.recover(good);
		request.getRequestDispatcher("/servlet/ManagementServlet?op=listBeiJuBao").forward(request, response);
		
	}
	private void xiaJia(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO 自动生成的方法存根
		
		int id = Integer.valueOf(request.getParameter("goodsId"));
		Goods good = xue.findGoodsById(id);
		xue.xiaJia(good);
		request.getRequestDispatcher("/servlet/ManagementServlet?op=listBeiJuBao").forward(request, response);
		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

	private void searchUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		// TODO 自动生成的方法存根
		int userId = Integer.valueOf(request.getParameter("userId"));
		User user = xue.findUserById(userId);
		request.setAttribute("aa", user);
		request.getRequestDispatcher("/searchUser.jsp").forward(request, response);
		
	}

	private void editUser(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		int id = Integer.valueOf(request.getParameter("userId"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int phone = Integer.valueOf(request.getParameter("phone"));
		int userNumber = Integer.valueOf(request.getParameter("UserNumber"));
		String email = request.getParameter("email");
		int status = Integer.valueOf(request.getParameter("status"));
		
		User user = new User();
		user.setUser_id(id);
		user.setUser_name(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setUser_number(userNumber);
		user.setEmail(email);
		user.setUser_status(status);
		
		xue.update(user);
		request.getRequestDispatcher("/servlet/ManagementServlet?op=listUsers").forward(request, response);
		
	}

	private void delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException, SQLException {
		int id = Integer.valueOf(request.getParameter("userId"));
		User user = xue.findUserById(id);
		xue.deleteUser(user);
		request.getRequestDispatcher("/servlet/ManagementServlet?op=listUsers").forward(request, response);
	}

	private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		int id = Integer.valueOf(request.getParameter("id"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		int phone = Integer.valueOf(request.getParameter("phone"));
		int userNumber = Integer.valueOf(request.getParameter("UserNumber"));
		String email = request.getParameter("email");
		int status = Integer.valueOf(request.getParameter("status"));
		
		user.setUser_id(id);
		user.setUser_name(username);
		user.setPassword(password);
		user.setPhone(phone);
		user.setUser_number(userNumber);
		user.setEmail(email);
		user.setUser_status(status);
		
		xue.addUser(user);
		
		request.getRequestDispatcher("/servlet/ManagementServlet?op=listUsers").forward(request, response);
	}

	private void listUsers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<User> users = xue.findAllUser();
		request.setAttribute("a", users);
		request.getRequestDispatcher("/listUsers.jsp").forward(request, response);
		
	}
	
	private void listAudited(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		List<User> users = xue.findAllAuditedUser();
		request.setAttribute("audited", users);
		request.getRequestDispatcher("/listAudited.jsp").forward(request, response);
	}
	private void listBeiJuBao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		List<Goods> goodss = xue.findAllBeiJuBao();
		request.setAttribute("beiJuBao", goodss);
		request.getRequestDispatcher("/listBeiJuBao.jsp").forward(request, response);
	}
	private void listNormal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		List<Goods> goodss = xue.findAllNoraml();
		request.setAttribute("normal", goodss);
		request.getRequestDispatcher("/listNormal.jsp").forward(request, response);
	}
	private void listXiaJia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 自动生成的方法存根
		List<Goods> goodss = xue.findAllXiaJia();
		request.setAttribute("xiajia", goodss);
		request.getRequestDispatcher("/listXiaJia.jsp").forward(request, response);
	}
	
}
