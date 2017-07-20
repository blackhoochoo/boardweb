<%@ page import="com.springbook.biz.user.impl.UserDao" %>
<%@ page import="com.springbook.biz.user.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	// 1. 사용자 입력 정보 추출
	String id = request.getParameter("id");
	String password = request.getParameter("password");
	
	// 2. DB 연동 처리
	User user = new User();
	user.setId(id);
	user.setPassword(password);
	
	UserDao userDao = new UserDao();
	User signedUser = userDao.getUser(user);
	
	// 3. 화면 네비게이션
	if(signedUser != null) {
		session.setAttribute("id", signedUser.getId());
		response.sendRedirect("getBoardList.jsp");
	} else {
		response.sendRedirect("login.jsp");
	}
%>