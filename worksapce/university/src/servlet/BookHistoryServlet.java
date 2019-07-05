package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CuishuyangDAO;

/**
 * Servlet implementation class BookHistory
 */
@WebServlet("/BookHistory")
public class BookHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    //private CuishuyangDAO dao = CuishuyangDAO.getInstance();
   
    
    public static void main(String[] args) {
    	
//    	CuishuyangDAO dao = CuishuyangDAO.getInstance();
//    	int sum = CuishuyangDAO.sum();
//    	int[] orderId = new int[sum];
//    	orderId = CuishuyangDAO.orderID();
//    	for(int i = 0;i < sum; i++) {
//    		System.out.println(orderId[i]);
//    	int[] goodsId = CuishuyangDAO.goodsId();
//    	System.out.println(goodsId[0]);
    	int counts = CuishuyangDAO.goodscount(123);
    	System.out.println(counts);
    	int[] goodsId = CuishuyangDAO.goodsId(123);
    	for(int i = 0; i < counts; i++) {
    		System.out.println(goodsId[i]);
    	}
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
		doGet(request, response);
	}

}
