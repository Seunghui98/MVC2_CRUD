package com.ssafy.guestbook.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ssafy.guestbook.model.GuestBookDto;
import com.ssafy.guestbook.model.UserDto;
import com.ssafy.guestbook.model.dao.GuestBookDao;
import com.ssafy.guestbook.model.dao.UserDao;


@WebServlet("/main")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private GuestBookDao guestBookDao = new GuestBookDao();
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String act = request.getParameter("act");
		String path = "/index.jsp";
		if("login".equals(act)) {
			path = login(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("logout".equals(act)) {
			path = logout(request, response);
			response.sendRedirect(path);
		} else if("list".equals(act)) {
			path = listBook(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("mvregist".equals(act)) {
			response.sendRedirect("regist.jsp");
		} else if("regist".equals(act)) {
			path = registBook(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("detail".equals(act)) {
			path = getBook(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("mvupdate".equals(act)) {
			path = mvUpdate(request, response);
			request.getRequestDispatcher(path).forward(request, response);
			
		} else if("update".equals(act)) {
			path = updateBook(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		} else if("delete".equals(act)) {
			path = deleteBook(request, response);
			request.getRequestDispatcher(path).forward(request, response);
		}
	}
	private String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "index.jsp";
	}
	private String login(HttpServletRequest request, HttpServletResponse response) {
		UserDao userDao = new UserDao();
		try {
			String id = request.getParameter("userid");
			String pwd = request.getParameter("userpwd");
			UserDto user = userDao.login(id, pwd);
			if(user != null) {
				HttpSession session = request.getSession();
				session.setAttribute("userInfo", user);
			} else {
				request.setAttribute("msg", "로그인 실패 ~ ㅠㅠ");
			}
			return "index.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500.jsp";
		}
	}
	
	private String deleteBook(HttpServletRequest request, HttpServletResponse response) {
		GuestBookDao guestBookDao = new GuestBookDao();
		try {
			guestBookDao.deleteBook(request.getParameter("isbn"));
			return listBook(request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500.jsp";
		}
	}
	
	private String updateBook(HttpServletRequest request, HttpServletResponse response) {
		GuestBookDao guestBookDao = new GuestBookDao();
		String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int price = Integer.parseInt(request.getParameter("price"));
        String desc = request.getParameter("desc");
        System.out.println(title +" "+desc);
        
		try {
			GuestBookDto guestBookDto = new GuestBookDto(isbn, title, author, price, desc);
			guestBookDao.updateBook(guestBookDto);
			request.setAttribute("isbn", isbn);
		
			return "/main?act=detail";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500.jsp";
		}
	}
	
	private String mvUpdate(HttpServletRequest request, HttpServletResponse response) {
		GuestBookDao guestBookDao = new GuestBookDao();
		try {
			GuestBookDto book = guestBookDao.getBook(request.getParameter("isbn"));
			request.setAttribute("book", book);
			return "update.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500.jsp";
		}
	}
	
	private String getBook(HttpServletRequest request, HttpServletResponse response) {
		GuestBookDao guestBookDao = new GuestBookDao();
		try {
			GuestBookDto book = guestBookDao.getBook(request.getParameter("isbn"));
			request.setAttribute("book", book);
			return "/detail.jsp";
			
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500.jsp";
		}
	}
	
	private String registBook(HttpServletRequest request, HttpServletResponse response) {
		String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        int price = Integer.parseInt(request.getParameter("price"));
        String desc = request.getParameter("desc");
		
		try {
			GuestBookDto guestBookDto = new GuestBookDto(isbn, title, author, price, desc);
			guestBookDao.registBook(guestBookDto);
			return listBook(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			return "error/500.jsp";
			
		}

		
	}
	
	private String listBook(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<GuestBookDto> list = guestBookDao.listBook();
			request.setAttribute("books", list);
			return "/list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "error/500.jsp";
	}


}
