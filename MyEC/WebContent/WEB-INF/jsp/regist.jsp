<%@page import="beans.UserDataBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>新規登録</title>
	<%
		String errMsg = (String)request.getAttribute("errMsg");
		UserDataBeans udb = (UserDataBeans)request.getAttribute("udb");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="Regist" method="post">
			<div class="main-container">
				<h1>新規登録</h1>
				<%if(errMsg != null){%>
				<h2 class="warning"><%=errMsg%></h2>
				<%} %>
				<table class="form">
					<tr>
						<th>名前</th>
						<td><input type="text" name="name" value="<%= udb.getName() %>" required></td>
					</tr>
					<tr>
						<th>住所</th>
						<td><input type="text" name="address" value="<%= udb.getAddress() %>" required></td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td><input type="email" name="email" value="<%= udb.getEmail() %>" required></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><input type="password" name="password" required></td>
					</tr>
					<tr>
						<th>パスワード(確認)</th>
						<td><input type="password" name="confirm_password" required></td>
					</tr>
				</table>
				<h2><button>登録</button></h2>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>