<%@page import="beans.UserDataBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>登録情報変更</title>
	<%
		String errMsg = (String)request.getAttribute("errMsg");
		UserDataBeans user = (UserDataBeans)request.getAttribute("udb");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="UpdateUser" method="post">
			<div class="main-container">
				<h1>登録情報変更</h1>
				<%if(errMsg != null){%>
				<h2 class="warning"><%=errMsg%></h2>
				<%} %>
				<table class="form">
					<tr>
						<th>名前</th>
						<td><input type="text" name="name" value="<%=user.getName()%>" required></td>
					</tr>
					<tr>
						<th>住所</th>
						<td><input type="text" name="address" value="<%=user.getAddress()%>" required></td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td><input type="email" name="email" value="<%=user.getEmail()%>" required></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><input type="password" name="password"></td>
					</tr>
					<tr>
						<th>パスワード(確認)</th>
						<td><input type="password" name="confirm_password"></td>
					</tr>
				</table>
				<h2><button type="submit" name="action" value="update">更新</button></h2>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>