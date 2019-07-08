package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
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
 * Servlet implementation class c
 */
@WebServlet("/c")
public class c extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ChenyuxiangDao cyxDao=ChenyuxiangDao.getInstance();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public c() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
	
	 public void query(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
		 
		 System.out.println("success");
	 }
	 
	 public void shop(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	
	    	System.out.println(1);
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

}
