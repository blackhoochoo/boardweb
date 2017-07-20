<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글 목록</title>
</head>
<body>
<center>
<h1>글 목록</h1>
<h3>${userName }<spring:message code="message.board.list.welcomeMsg"/>...<a href="logout.do">Log-out</a></h3>

<!-- 검색 시작 -->
<form action="getBoardList.do" method="post">
<table border="1" cellpadding="0" cellspacing="0" width="700">
<tr>
	<td align="right">
		<select name="searchCondition">
			<c:forEach items="${conditionMap}" var="option">
				<option value="${option.value }">${option.key }</option>
			</c:forEach>
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

<%-- <% for(Board doc : boardList) { %> --%>
<c:forEach items="${boardList }" var="doc">
<tr>
	<td>${doc.seq }</td>
	<td><a href="getBoard.do?seq=${doc.seq }">${doc.title }</a></td>
	<td>${doc.writer }</td>
	<td><fmt:formatDate value="${doc.regdate }" pattern="yyyy-MM-dd"/></td>
	<td>${doc.cnt }</td>
</tr>
</c:forEach>
<%-- <%} %> --%>
</table>
<br>
<a href="insertBoard.jsp">새글 등록</a>
</center>
</body>
</html>