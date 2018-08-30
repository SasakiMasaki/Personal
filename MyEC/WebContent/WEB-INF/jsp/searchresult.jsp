<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="beans.ItemDataBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<%
		List<ItemDataBeans> itemList = (ArrayList<ItemDataBeans>)request.getAttribute("itemList");
	%>
	<title>「<%=request.getAttribute("keyword")%>」の検索結果</title>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>