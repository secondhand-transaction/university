package servlet;

import java.io.IOException;

import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.XutaigeDao;
import entity.QueryGood;

/**
 * Servlet implementation class SeekPurchase
 */
public class SeekPurchaseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public SeekPurchaseServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset = UTF-8");		
		request.setCharacterEncoding("UTF-8");
		seekPurchase(request, response);
	}
	
	

	private void seekPurchase(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		XutaigeDao xutaigeDao = new XutaigeDao();
		request.setCharacterEncoding("UTF-8");
		String categoryId = request.getParameter("categoryId");
		String queryName = request.getParameter("queryName");
		int user_id = (int)request.getSession().getAttribute("user_id");
		
		int category_id = Integer.parseInt(categoryId);
		
		QueryGood qg = new QueryGood();
		
		qg.setCategoryId(category_id);
		qg.setQueryName(queryName);
		qg.setUserId(user_id);
		
		xutaigeDao.addQueryGood(qg);
		
		request.getRequestDispatcher("/shop.do").forward(request, response);;
	
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
