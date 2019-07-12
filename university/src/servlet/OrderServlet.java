package servlet;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.*;
import entity.*;

/**
 * @author fengfengfenghong 添加至购物车
 */
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private ChenhongfengDAO cDAO = ChenhongfengDAO.getInstance();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		response.setHeader(" content-type", "text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		/* String ISBN =Integer.parseInt(request.getParameter("ISBN")); */
		HttpSession session = request.getSession(true);
		session.setMaxInactiveInterval(30 * 60);
		String kind = request.getParameter("submit");
		ArrayList<Cart> carts = (ArrayList<Cart>) session.getAttribute("carts");
		if (carts == null)
			carts = new ArrayList<Cart>();

		if (kind.equals("join")) {
			// String id = request.getParameter("goods_id");
			// String name = request.getParameter("goods_name");
			// String pricee = request.getParameter("price");
			
			String id = request.getParameter("goodsid");
			String name = cDAO.namebug(id);
			String price = request.getParameter("PRICE");
			String sid = cDAO.idbug(id);
			Cart cart = new Cart(id, name, price,sid);
			boolean f = false;
			for (int i = 0; i < carts.size(); i++) {
				if (carts.get(i).getGoods_id().equals(id))
					f = true;
			}
			if (!f)
				carts.add(cart);
			int user_id =(int) session.getAttribute("user_id");
			
			session.setAttribute("counts", carts.size());
			// System.out.println(carts.size());
			session.setAttribute("carts", carts);
			session.setAttribute("user_id", user_id);
			
			request.getSession().setMaxInactiveInterval(100000000);

			/*
			 * aBookDAO.add(ID,ph ); aBookDAO.update(ISBN,ID) ;
			 */

			request.getRequestDispatcher("cart.jsp").forward(request, response);
		} else if (kind.equals("deleteAll")) {
			session.removeAttribute("counts");
			session.removeAttribute("carts");
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		} else if (kind.equals("deleteOne")) {
			String ID = request.getParameter("GoodsID");
			for (int i = 0; i < carts.size(); i++) {
				if (carts.get(i).getGoods_id().equals(ID))
					carts.remove(i);
			}

			session.setAttribute("counts", carts.size());
			session.setAttribute("carts", carts);
			request.getSession().setMaxInactiveInterval(100000000);
			request.getRequestDispatcher("cart.jsp").forward(request, response);
		} else if (kind.equals("generate")) {
			String user_id = String.valueOf( session.getAttribute("user_id"));			
			cDAO.add(user_id, carts);
            request.setAttribute("carts", carts);           
			session.removeAttribute("counts");
			session.removeAttribute("carts");
			request.getRequestDispatcher("AddInformationSystem.doHe").forward(request, response);
		} else if (kind.equals("buyersure")) {
            String orderItem_id = request.getParameter("OrderItemID"); 
            String goods_id = request.getParameter("GoodsID"); 
            cDAO.buyersuer(orderItem_id, goods_id);
            response.sendRedirect("HistoryGoods.doCui");
  //          request.getRequestDispatcher("HistoryGoods.doCui").forward(request, response);
            
		} else if (kind.equals("buyercancel")) {
            String orderItem_id = request.getParameter("OrderItemID"); 
            String goods_id = request.getParameter("GoodsID"); 
			cDAO.buyercancel(orderItem_id, goods_id);			
            request.getRequestDispatcher("AddInformationSystem.doHe").forward(request, response);
			
		} else if (kind.equals("sellersure")) {
     
            String goods_id = request.getParameter("GoodsID"); 
			cDAO.sellersure( goods_id);
			response.sendRedirect("HistorySeller.doCui");
   //         request.getRequestDispatcher("HistorySeller.doCui").forward(request, response);
		} else if (kind.equals("sellercancel")) {
   
            String goods_id = request.getParameter("GoodsID"); 
			String buyer_id = cDAO.sellercancel( goods_id);
			request.setAttribute("BuyID", buyer_id);
            request.getRequestDispatcher("AddInformationSystem.doHe").forward(request, response);
		}else if(kind.equals("record")) {			
			List<Goods> goods = new ArrayList<Goods>();
			String id = request.getParameter("ID");
			goods=cDAO.list(id);
			 request.setAttribute("ListGoods", goods);
				request.getRequestDispatcher("seller_record.jsp").forward(request, response);		
		}

	}

}