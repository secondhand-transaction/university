package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.BuyerGoods;
import entity.Goods;

import dao.CuishuyangDAO;

/**
 * Servlet implementation class PublishGoodsSerlvlet
 */
@WebServlet("/PublishGoodsSerlvlet")
public class PublishGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		// 1.��ȡ�ܹ��롰url-pattern����ƥ���·��
		String method = request.getServletPath();

		// (��ʱ����������ǲ�ѯ query.do)
		// System.out.println("request.getServletPath()��ȡ��ֵΪ: " + method);// ���
		// /query.do

		// 2.ͨ���ַ�����ȡ���ѷ����� query ��ȡ����
		method = method.substring(1, method.length() - 6);

		// System.out.println("��ȡ���ֵΪ�� "+ method);

		Method m = null;
		try {
			// 3.��ȡ��ǰ��������Ϊ method �ķ���
			m = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);

			// 4.���� method ����
			m.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void HistoryGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.setContentType("text/html; charset=UTF-8");//��ֹ��������

		HttpSession session = request.getSession();

		int user_id = (int) session.getAttribute("user_id");

		List<BuyerGoods> ListGoods = new ArrayList<BuyerGoods>();

		ListGoods = CuishuyangDAO.BuyerGoods(user_id);

		int start = 0;

		int cnt = 5;

		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch (NumberFormatException e) {
		}

		int next = start + cnt;

		int pre = start - cnt;

		int count = CuishuyangDAO.goodscount(user_id);

		int last = 0;

		if (count != 0) {

			if (0 == count % cnt)

				last = count - cnt;

			else

				last = count - count % cnt;
		}
		pre = pre < 0 ? 0 : pre;

		next = next > last ? last : next;

		// System.out.println(user_id);

		// System.out.println(user_id);

		request.setAttribute("next", next);

		request.setAttribute("pre", pre);

		request.setAttribute("last", last);

		request.setAttribute("ListGoods", ListGoods);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("buy.jsp");

		requestDispatcher.forward(request, response);

	}

	private void HistorySeller(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		int user_id = (int) session.getAttribute("user_id");

		// System.out.println(user_id);

		int start = 0;

		int cnt = 5;

		try {
			start = Integer.parseInt(request.getParameter("start"));
		} catch (NumberFormatException e) {
		}

		int next = start + cnt;

		int pre = start - cnt;

		int count = CuishuyangDAO.sumgoods(user_id);

		int last = 0;

		if (count != 0) {

			if (0 == count % cnt)

				last = count - cnt;

			else

				last = count - count % cnt;
		}

		pre = pre < 0 ? 0 : pre;

		next = next > last ? last : next;

		// System.out.println(user_id);

		List<Goods> ListGoods = new ArrayList<Goods>();

		ListGoods = CuishuyangDAO.Goods(user_id);

		request.setAttribute("ListGoods", ListGoods);

		request.setAttribute("next", next);

		request.setAttribute("pre", pre);

		request.setAttribute("last", last);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("sell.jsp");

		requestDispatcher.forward(request, response);

	}

	private void showgoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// System.out.println("query����������");

		HttpSession session = request.getSession();

		int goods_id = Integer.parseInt(request.getParameter("goods_id"));

		Goods good = CuishuyangDAO.showgoods(goods_id);

		// int goods_id = Intger.request.getParameter("submit");
		session.setAttribute("goods_id", goods_id);

		request.setAttribute("good", good);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("publishchange.jsp");

		requestDispatcher.forward(request, response);
	}

	private void updatepublish(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.setContentType("text/html; charset=UTF-8");

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		int goods_id = (int) session.getAttribute("goods_id");

		String goods_name = request.getParameter("goods_name");

		double price = Double.parseDouble(request.getParameter("price"));

		String description = request.getParameter("description");

		CuishuyangDAO.updateGoods(goods_name, price, description, goods_id);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("HistorySeller.doCui");

		requestDispatcher.forward(request, response);

		// System.out.println(goods_id);
	}

	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		int goods_id = Integer.parseInt(request.getParameter("goods_id"));

		CuishuyangDAO.cancel(goods_id);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("HistorySeller.doCui");

		requestDispatcher.forward(request, response);
	}

//	private void publish(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		
//		HttpSession session = request.getSession();
//		
//		int user_id = (int)session.getAttribute("user_id");
//		
//		String goods_name = request.getParameter("goods_name");
//		
//		double price = Double.parseDouble(request.getParameter("price"));
//		
//		String description = request.getParameter("description");
//		
//		int status = CuishuyangDAO.getUserstatus(user_id);
//		
//		if(0 == status) {
//		
//			request.setAttribute("user_status", status);
//			
//			RequestDispatcher requestDispatcher=request.getRequestDispatcher("publish.jsp");
//			
//			requestDispatcher.forward(request,response);
//		}
//		else {
//			
//			
//			
//			request.setAttribute("user_status", status);
//			
//			RequestDispatcher requestDispatcher=request.getRequestDispatcher("publish.jsp");
//			
//			requestDispatcher.forward(request,response);
//		}
//	
//	}

//		
//		
//		List<BuyerGoods> ListGoods = new ArrayList<BuyerGoods>();
//		
//		ListGoods = CuishuyangDAO.BuyerGoods(123);
//		
//		Iterator<BuyerGoods> it = ListGoods.iterator();
//		
//		while(it.hasNext()) {
//			
//			BuyerGoods b = (BuyerGoods)it.next();
//			
//			System.out.println(b.getGoods().getGoods_name());
//		}
//		while(it.hasNext()) {
//			
//			Goods goods = (Goods)it.next();
//			System.out.println(goods.getGoods_name()+ "  ");
//	}
}
