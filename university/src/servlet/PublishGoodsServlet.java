package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.CuishuyangDAO;
import dao.HelongxiangDAO;
import dao.XutaigeDao;
import entity.BuyerGoods;
import entity.Goods;
import entity.Information;
import entity.QueryGood;
import me.xiaosheng.word2vec.Word2Vec;

/**
 * Servlet implementation class PublishGoodsSerlvlet
 */
@WebServlet("/PublishGoodsSerlvlet")
public class PublishGoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CuishuyangDAO cuishuyangDAO = CuishuyangDAO.getInstance();

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
			throws ServletException, IOException{
		
		String method = request.getServletPath();

		method = method.substring(1, method.length() - 6);

		Method m = null;
		try {
			
			m = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);

			
			m.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void HistoryGoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		int user_id = (int) session.getAttribute("user_id");

		//CuishuyangDAO cuishuyangDAO = new CuishuyangDAO();
		
		List<BuyerGoods> ListGoods = new ArrayList<BuyerGoods>();

		int start = 0;

		int cnt = 5;

		try {
			
			start = Integer.parseInt(request.getParameter("start"));
		
		} catch (NumberFormatException e) {
		
		}

		int next = start + cnt;

		int pre = start - cnt;

		int count = CuishuyangDAO.goodscount(user_id);
		
		//System.out.println(count);

		int last = 0;

		if (count != 0) {

			if (0 == count % cnt)

				last = count - cnt;

			else

				last = count - count % cnt;
		}
		pre = pre < 0 ? 0 : pre;

		next = next > last ? last : next;
		
		ListGoods = cuishuyangDAO.BuyerGoods(user_id);

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
		
		//System.out.println("count" + count);

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

		ListGoods = cuishuyangDAO.Goods(user_id, start, cnt);
	//	ListGoods = cuishuyangDAO.BuyerGoods(user_id);

		request.setAttribute("ListGoods", ListGoods);

		request.setAttribute("next", next);

		request.setAttribute("pre", pre);

		request.setAttribute("last", last);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("sell.jsp");
		 
		requestDispatcher.forward(request, response);

	}

	private void showgoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		int goods_id = Integer.parseInt(request.getParameter("goods_id"));

		Goods good = cuishuyangDAO.showgoods(goods_id);

		// int goods_id = Intger.request.getParameter("submit");
		session.setAttribute("goods_id", goods_id);
		request.getSession().setMaxInactiveInterval(100000000);
		request.setAttribute("good", good);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("publishchange.jsp");

		requestDispatcher.forward(request, response);
	}
	
	private void publishgoods(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		//System.out.println("调用");
		
		int user_id = (int) request.getSession().getAttribute("user_id");
		
		int categoryId = Integer.parseInt(request.getParameter("categoryId")); 
		
		System.out.println(categoryId);

		String goods_name = request.getParameter("goods_name");
		
		System.out.println(goods_name);

		double price = Double.parseDouble(request.getParameter("price"));
		
		//System.out.println(price);
		
		//System.out.println(price);

		String description = request.getParameter("description");
		
		//System.out.println(description);
		
		Goods goods = new Goods();
		
		goods.setCategory_id(categoryId);
		goods.setDescription(description);
		goods.setGoods_name(goods_name);
		goods.setGoods_status(0);
		goods.setPrice(price);
		goods.setUser_id(user_id);
				 
		cuishuyangDAO.publishgoods(user_id,goods_name, description, price, categoryId);
		
		
		
		int goods_id = cuishuyangDAO.goods_id(user_id, goods_name, price);
		
		response.setContentType("text/html"); // 设置请求以及响应的内容类型以及编码方式
		response.setCharacterEncoding("UTF-8");
		System.out.println("publishGoodsServlet information ");
		information(goods ,request, response);
		
//		System.out.println("goods_id" + goods_id);
		
		if(-1 != goods_id) {
		
		
		//System.out.println(goods_id);
		
		request.getSession().setAttribute("goods_id", goods_id);
		request.getSession().setMaxInactiveInterval(100000000);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("publishimages.jsp");

		requestDispatcher.forward(request, response);
		
		}
		
	}

	private void information(Goods goods, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Word2Vec w2c = new Word2Vec();
		
		String path = "C:\\Baidu\\wiki_chinese_word2vec(Google).model";
		
		XutaigeDao xtgdao = new XutaigeDao();
		
		List<QueryGood> queryGood = xtgdao.getQueryGoodNameByCageId(goods.getCategory_id());
		
		try {
			w2c.loadGoogleModel(path);
			System.out.println("model load successful");
		} catch (IOException e) {
			e.printStackTrace();
		}
//		List<Integer> user_ids=new ArrayList<Integer>();
		HashMap<Integer, String> k_y = new HashMap<Integer, String>();
		for(QueryGood q : queryGood) {
//			System.out.println(q.getQueryName());
//			System.out.println(goods.getGoods_name());
			float similarDegree = w2c.wordSimilarity(goods.getGoods_name(), q.getQueryName());
			System.out.println("相似度:" + similarDegree);
			if(similarDegree > 0.6) {
				 k_y.put(q.getUserId(), q.getQueryName());
			}
		}
//		request.setAttribute("goods_name", goods.getGoods_name());
//		request.setAttribute("user_ids", user_ids);
//		request.getRequestDispatcher("AddInformationSystem.doHe").forward(request, response)
		sendToUser(goods, k_y, request, response);
	}

	private void sendToUser(Goods goods, HashMap<Integer, String> k_y, HttpServletRequest request,
			HttpServletResponse response) {
		HelongxiangDAO hlxDao = new HelongxiangDAO();
		String content_for = "您关注的%s已经上架, 请搜索%s";
		
		
		for(Integer user_id : k_y.keySet()) {
			Information data = new Information();
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
			
			String content = String.format(content_for, k_y.get(user_id), goods.getGoods_name());
			
			System.out.println("商品内容" + content);
			
			data.setBuyer_id(user_id);
			data.setSeller_id(-1);
			data.setContent(content);
			data.setNextinfor_id(0);
			data.setSendtime(sqlDate);
			
			boolean flag = hlxDao.insertInformation(data);
			System.out.println("插入成功");
		}
		
	}

	private void updatepublish(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.setContentType("text/html; charset=UTF-8");

		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();

		int goods_id = (int) session.getAttribute("goods_id");

		String goods_name = request.getParameter("goods_name");

		double price = Double.parseDouble(request.getParameter("price"));
		
		//System.out.println("price =" + price);

		String description = request.getParameter("description");

		cuishuyangDAO.updateGoods(goods_name, price, description, goods_id);
		
		//System.out.println("price");

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("HistorySeller.doCui");

		requestDispatcher.forward(request, response);


		//System.out.println(goods_id);
	}

	private void cancel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		response.setContentType("text/html; charset=UTF-8");

		int goods_id = Integer.parseInt(request.getParameter("goods_id"));

		//int goods_id = (int) request.getSession().getAttribute("goods_id");
		
		cuishuyangDAO.cancel(goods_id);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("HistorySeller.doCui");

		requestDispatcher.forward(request, response);
	
	}
	
	private void upload(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html; charset=UTF-8");
		
		int goods_id = (int)request.getSession().getAttribute("goods_id");
		
//		System.out.println(goods_id);
		
		PrintWriter out = response.getWriter();
		
		DiskFileItemFactory sf= new DiskFileItemFactory();//实例化磁盘被文件列表工厂
		
		String path = "C:\\Users\\feng\\Desktop\\workspace\\university\\WebContent\\img";//得到上传文件的存放目录

		sf.setRepository(new File(path));//设置文件存放目录
		
		sf.setSizeThreshold(1024*1024);//设置文件上传小于1M放在内存中
		
		String rename = new String();//文件新生成的文件名
		
		String fileName = new String();//文件原名称
		
		String name = new String();//普通field字段
		
		ServletFileUpload sfu = new ServletFileUpload(sf);
		
		try {
			
			List<FileItem> lst = sfu.parseRequest(request);//得到request中所有的元素
			
			for (FileItem fileItem : lst) {
				
				if(fileItem.isFormField()){
					
					if("name".equals(fileItem.getFieldName())){
						
						name = fileItem.getString("UTF-8");
					}
				}
				else{
					
					fileName = fileItem.getName();
					
					fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
					
					String houzhui = fileName.substring(fileName.lastIndexOf("."));
					
					String U = UUID.randomUUID().toString();
					
					rename = U + houzhui;
					
					//System.out.println(U);
					
					fileItem.write(new File(path, rename));
				
				}
				
				cuishuyangDAO.uploadimage(goods_id, rename);
				
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("HistoryGoods.doCui");

				requestDispatcher.forward(request, response);
			}
			
			
			
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.sendRedirect("message.jsp");
		out.flush();
		out.close();
	}
		
//		 Boolean isMultipart = ServletFileUpload.isMultipartContent(request);
//		 if (!isMultipart) {
//	           
//			 return; //如果不是就不用上传了
//	        
//		 }
		
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

