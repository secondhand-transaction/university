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

import entity.Information;
import entity.Message;
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
		System.out.print(1);
		System.out.print(method);

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
		System.out.print(1);
		
		int id;
//		String userid = request.getParameter("user_id");
//		id = Integer.parseInt(userid);
//		id = (int) request.getAttribute("user_id");
		id = (int) session.getAttribute("user_id");
		
		ArrayList<Information> infs = new ArrayList<Information>();
		HelongxiangDAO hDAO = new HelongxiangDAO();
		infs = hDAO.selectInformationBybuyerid(id);
		
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

		session.setAttribute("messageList",messageList);
		request.getRequestDispatcher( "messagelist.jsp").forward(request,response);
		
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
		id = Integer.parseInt(userid);
		uid = (int) session.getAttribute("user_id");
		
		ArrayList<Information> infs = new ArrayList<Information>();
		HelongxiangDAO hDAO = new HelongxiangDAO();
		hDAO.updateStatusOfInformation(uid, id);
		String bname = hDAO.selectUsernameByUserid(id);
		String uname = hDAO.selectUsernameByUserid(uid);
		
		infs = hDAO.selectInformationByTwoid(id, uid);
		session.setAttribute("ListInformations",infs);
		session.setAttribute("buyerid",id);
		session.setAttribute("userid",uid);
		session.setAttribute("bname",bname);
		session.setAttribute("uname",uname);
		request.getRequestDispatcher( "messgae.jsp").forward(request,response);
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
}
