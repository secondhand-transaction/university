package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LiuzihaoDAO;

@WebServlet("/userInfor")
public class ChangeUserInforServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeUserInforServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int user_id=123;
		String result = new String();
		result = LiuzihaoDAO.getUserInfor(user_id);
		//System.out.println(result);
		PrintWriter out = response.getWriter(); // 向客户端发送字符数据
		response.setContentType("text/text"); // 设置请求以及响应的内容类型以及编码方式
		response.setCharacterEncoding("UTF-8");
		out.write(result); // 将str字符传输到前台
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private void ChangeUserInfor(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		int user_id  = (int)session.getAttribute("user_id");
		
		
	}
	
	private void query(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
		System.out.println("success");
	}

}


