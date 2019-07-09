package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import dao.WanghaoDao;
import utils.*;
import entity.User;

public class WanghaoServlet extends HttpServlet{
		private static final long serialVersionUID = 1L;
		WanghaoDao dao = new WanghaoDao();
		public WanghaoServlet(){
			super();
		}
		
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			String option = request.getParameter("op");
			if("login".equals(option)) {
				userLogin(request, response);
			}else if("forgetPassword".equals(option)){
				forgetPassword(request, response);
			}else if("signup".equals(option)){
				signUp(request, response);
			}else if("changepassword".equals(option)){
				changePassword(request, response);
			}else if("adminlogin".equals(option)){
				adminLogin(request, response);
			}else if("logout".equals(option)){
				logOut(request, response);
			}else if("logout_admin".equals(option)){
				logOut_admin(request, response);
			}else if("nameAvaliable".equals(option)){
				nameAvailable(request, response);
			}else if("canChange".equals(option)){
				canChange(request, response);
			}
        }
        

		/**
		 * 判断用户名是否重复
		 * @param request
		 * @param response
		 * @throws IOException
		 */
		private void nameAvailable(HttpServletRequest request, HttpServletResponse response) throws IOException {
			PrintWriter out = response.getWriter();//获取输出流对象
			String userNumber = request.getParameter("userNumber");
			boolean available = dao.userName_available(userNumber);
			 //AJAX方式
	        if(available){
	            //如果返回true，说明用户名没有被创建，可以注册,返回0
	            out.println(0);
	        }else{
	            //用户名已经被注册，返回1
	            out.println(1);
	        }
		}

		/**
		 * 用户登陆
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private void userLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String username = request.getParameter("userNumber");
			String password = request.getParameter("password");
			System.out.println(username);
			System.out.println(password);
			String passwordMD5 = MD5.getMD5(password);
			System.out.println(passwordMD5);
			User user = dao.userLogin(username, passwordMD5);

			if(user == null) {
				response.sendRedirect(request.getContextPath()+"/login.jsp?info=error");
			}else {
				request.getSession().setAttribute("user", user);
				request.getSession().setMaxInactiveInterval(100000000);
				request.getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
		/**
		 * 检验用户和邮箱
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private void forgetPassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String userNumber = request.getParameter("userNumber");
			String email = request.getParameter("email");
			String Id = dao.inspectEmail(userNumber, email);
			if(Id != null){
				//邮箱发送
				String PIN = dao.getPIN(Id);
				SendMail.sendmailChangepass(userNumber, email, Id, PIN);
				request.getRequestDispatcher("/forget.jsp?info=ok").forward(request, response);
			}else{
				request.getRequestDispatcher("/forget.jsp?info=error").forward(request, response);
			}
		}

		/**
		 * 注册
		 * @param request
		 * @param response
		 * @throws ServletException
		 * @throws IOException
		 */
		private void signUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			User user = new User();
			String userNumber = request.getParameter("userNumber");
			//System.out.println(userNumber);//测试
			String password = request.getParameter("password");
			//System.out.println(password);//测试
			String passwordMD5 = MD5.getMD5(password);
			//System.out.println(passwordMD5);//测试
			String email = request.getParameter("email");
			//System.out.println(email);//测试
			String phone = request.getParameter("phone");
			//System.out.println(phone);//测试
			String userName = request.getParameter("userName");
			//System.out.println(userName);//测试
			user.setUser_number(Integer.valueOf(userNumber).intValue());
			user.setEmail(email);
			user.setPhone(Integer.valueOf(phone).intValue());
			user.setPassword(passwordMD5);
			user.setUser_status(0);
			user.setUser_name(userName);
			dao.addUser(user);
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		/**
		 * 忘记密码/邮箱更改
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServletException
		 */
		private void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			String userId = request.getParameter("userid");
			System.out.println(userId);
			String password = request.getParameter("password");
			System.out.println(password);
			String passwordMD5  = MD5.getMD5(password);
			System.out.println(passwordMD5);
				dao.changePasswordbyId(userId, passwordMD5);
				request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
		
		/**
		 * 
		 */
		public void canChange(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			PrintWriter out = response.getWriter();//获取输出流对象
			String userId = request.getParameter("id");
			String key = request.getParameter("key");
			boolean pass = dao.inspectUser(userId, key);
			if(pass){
				//如果返回true，说明匹配
	            out.println(0);
			}else{
				//不匹配，返回1
	            out.println(1);
			}
		}
		/**
		 * admin登陆，设置adminName的Session
		 * @param request
		 * @param response
		 * @throws IOException
		 * @throws ServletException
		 */
		private void adminLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
			String adminName = request.getParameter("adminNumber");
			String password = request.getParameter("password");
			String passwordMD5 = MD5.getMD5(password);
			String admin = dao.adminLogin(adminName, passwordMD5);

			if(admin == null) {
				response.sendRedirect(request.getContextPath()+"/admin_login.jsp?info=error");
			}else {
				request.getSession().setAttribute("adminName", admin);
				request.getSession().setMaxInactiveInterval(100000000);
				request.getRequestDispatcher("/admin_index.jsp").forward(request, response);
			}
		}
		

		private void logOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.getSession().removeAttribute("user");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

		private void logOut_admin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO 自动生成的方法存根
			request.getSession().removeAttribute("adminName");
			request.getRequestDispatcher("/login_admin.jsp").forward(request, response);
		}
		
		
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(request, response);
		}
}		
