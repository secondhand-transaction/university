package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Cart;
import entity.Information;
import entity.Message;
import entity.User;
import dao.HelongxiangDAO;

public class HelongxiangServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public HelongxiangServlet() {
		super();
        // TODO Auto-generated constructor stub
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");

		response.setContentType("text/html;charset = UTF-8");

		request.setCharacterEncoding("UTF-8");

		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String method = request.getServletPath();

		method = method.substring(1, method.length() - 5);


		Method m = null;
		try {
			m = this.getClass().getDeclaredMethod(method, HttpServletRequest.class, HttpServletResponse.class);
			m.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//跳转到messagelist.jsp界面前的预处理
	public void GoToMessagelist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
//		PrintWriter out=response.getWriter();

		
		int id;
//		String userid = request.getParameter("user_id");
//		id = Integer.parseInt(userid);
//		id = (int) request.getAttribute("user_id");
		id = (int) session.getAttribute("user_id");
		
		ArrayList<Information> infs = new ArrayList<Information>();
		ArrayList<Information> is = new ArrayList<Information>();
		HelongxiangDAO hDAO = new HelongxiangDAO();
		is = hDAO.selectInformationBybuyerid(id);
		
		for(Information i : is) {
			if(i.getSeller_id() != -1) {
				infs.add(i);
			}
		}
		
		ArrayList<Message> messageList = new ArrayList<Message>();
		for(Information i : infs) {
			boolean flag = false;
			for(Message m : messageList) {
				if(i.getSeller_id() == m.getSeller_id()) {
					int num = m.getInformation_num();
					m.setInformation_num(num++);
					m.setDate(i.getSendtime());
					if(m.getStatus() == 1) {
						m.setStatus(i.getInformation_status());
					}
					flag = true;
					break;
				}
			}
			if(!flag) {
				Message mes = new Message();
				mes.setDate(i.getSendtime());
				mes.setInformation_num(1);
				mes.setSeller_id(i.getSeller_id());
				mes.setStatus(i.getInformation_status());
				String name = hDAO.selectUsernameByUserid(i.getSeller_id());
				mes.setSeller_name(name);
				messageList.add(mes);
			}
		}
		for(Message m : messageList) {
			System.out.println(m.getSeller_id());
		}
		

		session.setAttribute("messageList",messageList);
		request.getSession().setMaxInactiveInterval(100000000);
		request.getRequestDispatcher("messagelist.jsp").forward(request,response);
		
	}
	
	//messagelist.jsp跳转到message.jsp的预处理
	public void GoToMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
//		PrintWriter out=response.getWriter();
		
		int id,uid;
		String userid = request.getParameter("id");
		System.out.println(userid);
		id = Integer.parseInt(userid);
		uid = (int) session.getAttribute("user_id");
		System.out.println("id="+id);
		System.out.println("uid="+uid);
		
		ArrayList<Information> infs = new ArrayList<Information>();
		HelongxiangDAO hDAO = new HelongxiangDAO();
		hDAO.updateStatusOfInformation(uid, id);
		String bname = hDAO.selectUsernameByUserid(id);
		String uname = hDAO.selectUsernameByUserid(uid);
		System.out.println(bname+"  "+uname);
		infs = hDAO.selectInformationByTwoid(id, uid);
		session.setAttribute("ListInformations",infs);
		session.setAttribute("buyerid",id);
		session.setAttribute("userid",uid);
		session.setAttribute("bname",bname);
		session.setAttribute("uname",uname);
		request.getSession().setMaxInactiveInterval(100000000);
		request.getRequestDispatcher("message.jsp").forward(request,response);
	}
	
	//提交在message.jsp回复的消息
	public void AddInformation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out=response.getWriter();
		
		int bid,uid;
		String content;
		content = request.getParameter("content");
		String buyerid = request.getParameter("id");
		bid = Integer.parseInt(buyerid);
		uid = (int) session.getAttribute("user_id");
		
		java.util.Date utilDate = new java.util.Date();
		java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
		
		HelongxiangDAO hDAO = new HelongxiangDAO();
		Information data = new Information();
		data.setBuyer_id(bid);
		data.setSeller_id(uid);
		data.setContent(content);
		data.setSendtime(sqlDate);
		data.setNextinfor_id(0);
		
		boolean flag = hDAO.insertInformation(data);
		if(flag) {
			out.print("<script>alert('消息回复成功,将返回消息列表');window.location='GoToMessagelist.doHe';</script>");
		}else {
			out.print("<script>alert('消息回复失败，将返回消息列表');window.location='GoToMessagelist.doHe';</script>");
		}
	}
	
	public void GoToMessagelistSystem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
//		PrintWriter out=response.getWriter();

		
		int id;
//		String userid = request.getParameter("user_id");
//		id = Integer.parseInt(userid);
//		id = (int) request.getAttribute("user_id");
		id = (int) session.getAttribute("user_id");
		
		ArrayList<Information> is = new ArrayList<Information>();
		ArrayList<Information> infs = new ArrayList<Information>();
		HelongxiangDAO hDAO = new HelongxiangDAO();
		is = hDAO.selectInformationBybuyerid(id);
		
		for(Information i : is) {
			if(i.getSeller_id() == -1) {
				infs.add(i);
			}
		}
		
		ArrayList<Message> messageList = new ArrayList<Message>();
		for(Information i : infs) {
			boolean flag = false;
			for(Message m : messageList) {
				if(i.getSeller_id() == m.getSeller_id()) {
					int num = m.getInformation_num();
					m.setInformation_num(num++);
					m.setDate(i.getSendtime());
					if(m.getStatus() == 1) {
						m.setStatus(i.getInformation_status());
					}
					flag = true;
					break;
				}
			}
			if(!flag) {
				Message mes = new Message();
				mes.setDate(i.getSendtime());
				mes.setInformation_num(1);
//				mes.setSeller_id(i.getSeller_id());
				mes.setStatus(i.getInformation_status());
//				String name = hDAO.selectUsernameByUserid(i.getSeller_id());
				mes.setSeller_name("系统通知");
				messageList.add(mes);
			}
		}
		for(Message m : messageList) {
			System.out.println(m.getSeller_id());
		}
		

		session.setAttribute("messageList",messageList);
		request.getSession().setMaxInactiveInterval(100000000);
		request.getRequestDispatcher("messagelistSystem.jsp").forward(request,response);
		
	}
	
	//messagelist.jsp跳转到message.jsp的预处理
	public void GoToMessageSystem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
//		PrintWriter out=response.getWriter();
		
		int id,uid;
//		String userid = request.getParameter("id");
//		System.out.println(userid);
//		id = Integer.parseInt(userid);
		id =-1;
		uid = (int) session.getAttribute("user_id");
		System.out.println("id="+id);
		System.out.println("uid="+uid);
		
		ArrayList<Information> infs = new ArrayList<Information>();
		HelongxiangDAO hDAO = new HelongxiangDAO();
		hDAO.updateStatusOfInformation(uid, id);
//		String bname = hDAO.selectUsernameByUserid(id);
		String bname = "System";
		String uname = hDAO.selectUsernameByUserid(uid);
		System.out.println(bname+"  "+uname);
		infs = hDAO.selectInformationByTwoid(id, uid);
		session.setAttribute("ListInformations",infs);
		session.setAttribute("buyerid",id);
		session.setAttribute("userid",uid);
		session.setAttribute("bname",bname);
		session.setAttribute("uname",uname);
		request.getSession().setMaxInactiveInterval(100000000);
		request.getRequestDispatcher("messageSystem.jsp").forward(request,response);
	}
	public void AddInformationSystem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out=response.getWriter();
		
		String goods_id;
		int buyer_id;
		String buyer_phone;
		String receiver_id;
		String buyer_name;
		String kind = request.getParameter("submit");
		String content = "产生错误！";		
		if(kind.equals("generate"))
		{
			ArrayList<Cart> carts = (ArrayList<Cart>) request.getAttribute("carts");
			User user = (User) request.getSession().getAttribute("user");
			for(int i=0;i<carts.size();i++) {
			goods_id = carts.get(i).getGoods_id();
			buyer_name = user.getUser_name();
			buyer_phone = String.valueOf(user.getPhone());
			receiver_id=carts.get(i).getSeller_id();
			content = "你的物品ID："+goods_id +"被"+buyer_name+"选中，请尽快联系他（电话号码：" + buyer_phone +  "），完成交易。";
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
			
			HelongxiangDAO hDAO = new HelongxiangDAO();
			Information data = new Information();
			data.setBuyer_id(Integer.parseInt(receiver_id));
			data.setSeller_id(-1);
			data.setContent(content);
			data.setSendtime(sqlDate);
			data.setNextinfor_id(0);
			
			boolean flag = hDAO.insertInformation(data);
			if(flag) {
				System.out.print("成功");
			}else {
				System.out.print("失败");
			}
			
			}
			
			request.getRequestDispatcher("HistoryGoods.doCui").forward(request, response);
			
			
		}else if(kind.equals("buyercancel"))	
		{
			receiver_id=request.getParameter("ReceiverID");
			User user = (User) request.getSession().getAttribute("user");
			buyer_name = user.getUser_name();
			buyer_phone =String.valueOf(user.getPhone());
			goods_id = request.getParameter("GoodsID");
			content = "在交易过程中，你发布的物品ID:"+ goods_id +"被买家"+ buyer_name +"取消，有问题请联系他（电话号码：" + buyer_phone +  "）。";
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
			
			HelongxiangDAO hDAO = new HelongxiangDAO();
			Information data = new Information();
			data.setBuyer_id(Integer.parseInt(receiver_id));
			data.setSeller_id(-1);
			data.setContent(content);
			data.setSendtime(sqlDate);
			data.setNextinfor_id(0);
			
			boolean flag = hDAO.insertInformation(data);
			if(flag) {
				System.out.print("成功");
			}else {
				System.out.print("失败");
			}
			request.getRequestDispatcher("HistoryGoods.doCui").forward(request, response);
			
			
			
			
		}else if(kind.equals("sellercancel"))	
		{
			receiver_id= (String) request.getAttribute("BuyID");
			System.out.println(receiver_id);
			User user = (User) request.getSession().getAttribute("user");
			buyer_name = user.getUser_name();
			buyer_phone =String.valueOf(user.getPhone());
			goods_id = request.getParameter("GoodsID");
			content = "在交易过程中，你购买的物品ID:"+ goods_id +"被卖家"+ buyer_name +"取消，有问题请联系他（电话号码：" + buyer_phone +  "）。";
			
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(utilDate.getTime());
			
			HelongxiangDAO hDAO = new HelongxiangDAO();
			Information data = new Information();
			data.setBuyer_id(Integer.parseInt(receiver_id));
			data.setSeller_id(-1);
			data.setContent(content);
			data.setSendtime(sqlDate);
			data.setNextinfor_id(0);
			
			boolean flag = hDAO.insertInformation(data);
			if(flag) {
				System.out.print("成功");
			}else {
				System.out.print("失败");
			}
			
			request.getRequestDispatcher("HistorySeller.doCui").forward(request, response);
			}
						
	}
}
