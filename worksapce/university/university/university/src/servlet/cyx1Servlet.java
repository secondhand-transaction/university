package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChenyuxiangDao;
import entity.Goods;

/**
 * Servlet implementation class cyx1Servlet
 */
@WebServlet("/cyx1Servlet")
public class cyx1Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChenyuxiangDao cDao = ChenyuxiangDao.getInstance();

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader( " content-type", "text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		int id =Integer.parseInt(request.getParameter("goods_id"));


		List<Goods> goods=null;
		try {
			goods = cDao.detail(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		request.setAttribute("goods", goods);
		request.getRequestDispatcher("detail.jsp").forward(request, response);
}
}
