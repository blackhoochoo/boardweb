package com.springbook.view.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.springbook.biz.board.BoardVo;
import com.springbook.biz.board.impl.BoardDao;
import com.springbook.biz.user.User;
import com.springbook.biz.user.impl.UserDao;

public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DispatcherServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 클라이언트의 요청 path 정보를 추출한다.
		String uri = request.getRequestURI();
		String path = uri.substring(uri.lastIndexOf("/"));
		
		// 2. 클라이언트의 요청 path에 따라 적절히 분기처리 한다.
		if("/login.do".equals(path)) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			
			User user = new User();
			user.setId(id);
			user.setPassword(password);
			
			UserDao userDao = new UserDao();
			User signedUser = userDao.getUser(user);
			
			// 3. 화면 네비게이션
			if(signedUser != null) {
				request.getSession().setAttribute("id", signedUser.getId());
				response.sendRedirect("getBoardList.do");
			} else {
				response.sendRedirect("login.jsp");
			}
		} else if("/logout.do".equals(path)) {
			System.out.println("로그아웃 처리");

			// 1. 브라우저와 연결된 세션 객체를 강제로 종료한다.
			HttpSession httpSession = request.getSession();
			httpSession.invalidate();
                   
			response.sendRedirect("login.do");
		} else if("/insertBoard.do".equals(path)) {
			System.out.println("�� ��� ó��");
			
			// 1. ����� �Է� ���� ����

			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			BoardVo board = new BoardVo();
			board.setTitle(title);
			board.setWriter(writer);
			board.setContent(content);
			
			// 2. DB ���� ó��
			BoardDao boardDao = new BoardDao();
			boardDao.insertBoard(board);
			
			// 3. ȭ�� �׺���̼�
			response.sendRedirect("getBoardList.do");
		} else if("/updateBoard.do".equals(path)) {
			System.out.println("�� ���� ó��");
			
			String title = request.getParameter("title");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			String seq = request.getParameter("seq");
			BoardVo board = new BoardVo();
			board.setSeq(Integer.parseInt(seq));
			board.setTitle(title);
			board.setWriter(writer);
			board.setContent(content);
			
			BoardDao boardDao = new BoardDao();
			boardDao.updateBoard(board);
			
			response.sendRedirect("getBoardList.do");
		} else if("/deleteBoard.do".equals(path)) {
			String seq = request.getParameter("seq");
			BoardVo board = new BoardVo();
			board.setSeq(Integer.parseInt(seq));
			
			BoardDao boardDao = new BoardDao();
			boardDao.deleteBoard(board);
			
			response.sendRedirect("getBoardList.do");
		} else if("/getBoard.do".equals(path)) {
			System.out.println("�� �� ��ȸ ó��");
			
			//1. �˻��� �Խñ� ��ȣ ����
			String seq = request.getParameter("seq");
			// 2. DB ���� ó��
			BoardDao boardDao = new BoardDao();
			BoardVo board = new BoardVo();
			board.setSeq(Integer.parseInt(seq));
			board = boardDao.getBoard(board);
			
			// 3. 검색 결과를 세션에 저장하고 상세 화면으로 이동한다.
			HttpSession session = request.getSession();
			session.setAttribute("board", board);
			response.sendRedirect("getBoard.jsp");
		} else if("/getBoardList.do".equals(path)) {
			System.out.println("�� ��� �˻� ó��");
			
			BoardVo board = new BoardVo();
			BoardDao boardDao = new BoardDao();
			List<BoardVo> boardList = boardDao.getBoardList(board);
			
			// 3. 검색 결과를 세션에 저장하고 목록 화면으로 이동한다.
			HttpSession session = request.getSession();
			session.setAttribute("boardList", boardList);
			response.sendRedirect("getBoardList.jsp");
		}
	}
}
