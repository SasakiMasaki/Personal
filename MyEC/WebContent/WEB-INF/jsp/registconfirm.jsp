<%@page import="beans.UserDataBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>登録情報確認画面</title>
	<%
		UserDataBeans udb = (UserDataBeans)session.getAttribute("udb");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="RegistConfirm" method="post">
			<div class="main-container">
				<h1>新規登録</h1>
				<table>
					<tr>
						<th>名前</th>
						<td><input type="text" name="name" value="<%= udb.getName() %>" readonly></td>
					</tr>
					<tr>
						<th>住所</th>
						<td><input type="text" name="address" value="<%= udb.getAddress() %>" readonly></td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td><input type="email" name="email" value="<%= udb.getEmail() %>" readonly></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><input type="password" name="password" value="<%=udb.getPassword() %>" readonly></td>
					</tr>
			</table>
				<h2>上記内容で情報を登録します。</h2>
				<ul class="inline">
					<li><button type="submit" name="confirm" value="cancel">戻る</button></li>
					<li><button type="submit" name="confirm" value="regist">確定する</button></li>
				</ul>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>