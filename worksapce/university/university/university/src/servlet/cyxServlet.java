package servlet;

import java.util.List;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ChenyuxiangDao;
import entity.Goods;
/**
 * Servlet implementation class cyxServlet
 */

public class cyxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ChenyuxiangDao cyxDao=ChenyuxiangDao.getInstance();
    
    
    
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String method = request.getServletPath();

		method = method.substring(1, method.length() - 3);

		Method m = null;
		
		try {
			
			m = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);

			m.invoke(this, request, response);

		} catch (Exception e) {
		
			e.printStackTrace();
		
		}
	
	}
    
    
    
    
    public void shop(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	
    	request.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader( " content-type", "text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession(true);
    
		//ËÑË÷
		
		List<Goods> goods=null;
		goods = cyxDao.list();
        String keyword = request.getParameter("keyword");
        if(keyword!=null){
            session.setAttribute("shuru", keyword);
            }
            String keyword1=(String) session.getAttribute("shuru");
		
                	
            if (keyword1 != null && keyword1.length() > 0) {
            	try {
					goods = cyxDao.select(keyword1);
            	
    				} catch (Exception e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}
            }
        request.setAttribute("goods", goods);
		request.getRequestDispatcher("shop.jsp").forward(request, response);
    }
            //ÏêÏ¸ÐÅÏ¢
    
    public void detail(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader( " content-type", "text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession(true);
           int id =Integer.parseInt(request.getParameter("goods_id"));
    		List<Goods> goods=null;
    		try {
    			goods = cyxDao.detail(id);
    			
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		
            //System.out.println(goods.get(1).getGoods_name());
            

    		
   		request.setAttribute("goods", goods);
   		request.getRequestDispatcher("detail.jsp").forward(request, response);
    		
    
    }
    
    
    public void jubao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");  
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader( " content-type", "text/html; charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id =Integer.parseInt(request.getParameter("goods_id"));
		Goods goods=null;
		try {
			goods = cyxDao.jubao(id);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//request.setAttribute("goods", goods);
   		request.getRequestDispatcher("index.jsp").forward(request, response);
    }
	
}

