<%@ page import="java.util.List" %>
<%@ page import="com.springbook.biz.board.impl.BoardDao" %>
<%@ page import="com.springbook.biz.board.impl.BoardDaoSimple" %>
<%@ page import="com.springbook.biz.board.BoardVo" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	// 1. 사용자 입력 정보 추출(검색 기능은 나중에 구현)
	// 2. DB 연동 처리
	BoardVo board = new BoardVo();
	BoardDao boardDao = new BoardDaoSimple();
	List<BoardVo> boardList = boardDao.getBoardList(board);
	
	// 3. 응답 화면 구성
%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 목록</title>
</head>
<body>
<center>
<h1>글 목록</h1>
<h3>테스트님 환영합니다...<a href="logout.do">Log-out</a></h3>

<!-- 검색 시작 -->
<form action="getBoardList.jsp" method="post">
<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<td align="right">
		<select name="searchCondition">
			<option value="TITLE">제목</option>
			<option value="CONTENT">내용</option>
		</select>
		<input name="searchKeyword" type="text" />
		<input type="submit" value="검색"/>
	</td>
</tr>
</table>
</form>
<!-- 검색 종료 -->
<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<th bgcolor="orange" width="100">번호</th>
	<th bgcolor="orange" width="100">제목</th>
	<th bgcolor="orange" width="100">작성자</th>
	<th bgcolor="orange" width="100">등록일</th>
	<th bgcolor="orange" width="100">조회수</th>
</tr>

<% for(BoardVo doc : boardList) { %>
<tr>
	<td><%=doc.getSeq() %></td>
	<td><a href="getBoard.jsp?seq=<%=doc.getSeq() %>"><%=doc.getContent() %></a></td>
	<td><%=doc.getWriter() %></td>
	<td><%=doc.getRegdate() %></td>
	<td><%=doc.getCnt() %></td>
</tr>
<%} %>
</table>
<br>
<a href="insertBoard.jsp">새글 등록</a>
</center>
</body>
</html>