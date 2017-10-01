<%@ page import="com.springbook.biz.board.impl.BoardDao" %>
<%@ page import="com.springbook.biz.board.impl.BoardDaoSimple" %>
<%@ page import="com.springbook.biz.board.BoardVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 1. 사용자 입력 정보 추출
	request.setCharacterEncoding("UTF-8");

	String title = request.getParameter("title");
	String writer = request.getParameter("writer");
	String content = request.getParameter("content");
	BoardVo board = new BoardVo();
	board.setTitle(title);
	board.setWriter(writer);
	board.setContent(content);
	
	// 2. DB 연동 처리
	BoardDao boardDao = new BoardDaoSimple();
	boardDao.insertBoard(board);
	
	// 3. 화면 네비게이션
	response.sendRedirect("getBoardList.jsp");
%>
