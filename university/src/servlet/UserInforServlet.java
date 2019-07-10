package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CuishuyangDAO;
import dao.LiuzihaoDAO;
import entity.User;

/**
 * Servlet implementation class UserInforServlet
 
 
*/
public class UserInforServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CuishuyangDAO cuishuyangDAO = CuishuyangDAO.getInstance();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserInforServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;charset = UTF-8");

		request.setCharacterEncoding("UTF-8");

		doPost(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getServletPath();

		method = method.substring(1, method.length() - 6);

		Method m = null;
		try {

			m = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);

			m.invoke(this, request, response);
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	private void ChangeUserInfor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		int user_id = (int) session.getAttribute("user_id");

		
		PrintWriter out = response.getWriter();
		// 从前端获取值
		String username = request.getParameter("title");
		
		String email = request.getParameter("title2");
		
		String phone = request.getParameter("title3");
		
		String result = username + "#" + email + "#" + phone;
		//System.out.println("修改结果："+result);

		boolean isSuccess = LiuzihaoDAO.ChangeUserInfor(user_id, result);
		
		response.setContentType("text/text"); // 设置请求以及响应的内容类型以及编码方式
		
		response.setCharacterEncoding("UTF-8");
		
		out.write(result); // 将str字符传输到前台

		// System.out.println(user_id);
//
//		PrintWriter out = response.getWriter();
//		// 从前端获取值
//
//		String username = request.getParameter("title");
//
//		String email = request.getParameter("title2");
//
//		String phone = request.getParameter("title3");
//
//		String result = username + "#" + email + "#" + phone + "#";
//		// System.out.println("修改结果："+result);
//
//		LiuzihaoDAO.ChangeUserInfor(user_id, result);
//		
//		out.write(result);

	}

	private void GetUserInfor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
  
		request.setCharacterEncoding("UTF-8");

		int user_id = (int) request.getSession().getAttribute("user_id");
	    
		//System.out.println("test: userid" + user_id);

//		String result = new String();
//		
//		result = LiuzihaoDAO.getUserInfor(user_id);
//		
//		PrintWriter out = response.getWriter(); // 向客户端发送字符数据
//		
//		response.setContentType("text/text"); // 设置请求以及响应的内容类型以及编码方式
//		
//		response.setCharacterEncoding("UTF-8");
//		
//		out.write(result); // 将str字符传输到前台
		
		User user = cuishuyangDAO.UserInf(user_id);
		
		request.setAttribute("user", user);
		
		User user1 = (User) request.getSession().getAttribute("user");
		
		user1.setUser_name(user.getUser_name());
		user1.setPhone(user.getPhone());
		user1.setEmail(user.getEmail());
		
		request.getSession().setAttribute("user", user1);
		request.getSession().setMaxInactiveInterval(100000000);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("UserInfor.jsp");

		requestDispatcher.forward(request, response);

	}

	private void PublishInfor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//System.out.println("user_id = ");
		
		request.setCharacterEncoding("UTF-8");

		int user_id = (int) request.getSession().getAttribute("user_id");
		
		//System.out.println("user_id = " + user_id);
		
		String user_name = request.getParameter("user_name");

		String email = request.getParameter("email");
		
		int phone = Integer.parseInt(request.getParameter("phone"));
		
		//System.out.println("phone" + phone);
		
		cuishuyangDAO.updatesUser(user_name, email, phone, user_id);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("GetUserInfor.doHao");

		requestDispatcher.forward(request, response);

//		PrintWriter out = response.getWriter();
//		// 从前端获取值
//
//		String goodsname = request.getParameter("title");
//
//		String price = request.getParameter("title2");
//
//		String des = request.getParameter("title3");
//
//		String result = goodsname + "#" + price + "#" + des;
//		// System.out.println("修改结果："+result);
//
//		LiuzihaoDAO.pushGoodsInfor(user_id, result);
//			
//		//RequestDispatcher requestDispatcher = request.getRequestDispatcher("sell.jsp");
//
//		//requestDispatcher.forward(request, response);
	}

}
