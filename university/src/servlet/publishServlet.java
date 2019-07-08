package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LiuzihaoDAO;

@WebServlet("/publish")
public class publishServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public publishServlet() {
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
		
		int user_id=123;
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		// 从前端获取值
		String goodsname = request.getParameter("title");
		String price = request.getParameter("title2");
		String des = request.getParameter("title3");
		String result = goodsname + "#" + price + "#" + des;
		//System.out.println("修改结果："+result);

		boolean isSuccess = LiuzihaoDAO.pushGoodsInfor(user_id, result);
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



}
