package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LiuzihaoDAO;

@WebServlet("/userInforChange")
public class GetUserInforServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GetUserInforServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		// 从前端获取值
		String username = request.getParameter("title");
		String email = request.getParameter("title2");
		String usernumber = request.getParameter("title3");
		String phone = request.getParameter("title4");
		
		String result = username + "," + phone + "," + email + "," + usernumber;
		System.out.println("修改结果："+result);

		boolean isSuccess = LiuzihaoDAO.ChangeUserInfor(123, result);
		response.setContentType("text/text"); // 设置请求以及响应的内容类型以及编码方式
		response.setCharacterEncoding("UTF-8");
		out.write(result); // 将str字符传输到前台

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	// public static void main(String[] args) {
	// String result = new String();
	// result = LiuzihaoDAO.getUserInfor(123);
	// System.out.println(result);
	// }

}
