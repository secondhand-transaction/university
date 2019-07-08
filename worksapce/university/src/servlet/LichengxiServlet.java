package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import dao.LichengxiDAO;


/**
 * Servlet implementation class UpdateAdmin
 */
public class LichengxiServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LichengxiServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		
		HttpSession session=request.getSession();
		int user_id=(int)session.getAttribute("user_id");
		//String user_ids = request.getParameter("user_id");
		//int  user_id = Integer.parseInt(user_ids);
		
		LichengxiDAO a = new LichengxiDAO();
		boolean judge;
		try {
			judge = a.UpdateUserState(user_id);
			if(judge==false) {
				out.print("<script>alert('…Í«Î ß∞‹£°');window.location='  ';</script>");
			}
			else {
				out.print("<script>alert('…Í«Î≥…π¶£°');window.location='  ';</script>");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}