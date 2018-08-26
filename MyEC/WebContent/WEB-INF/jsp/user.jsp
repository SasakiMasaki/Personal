<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.UserDataBeans"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>マイページ</title>
	<%
		UserDataBeans user = (UserDataBeans)request.getAttribute("user");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="">
			<div class="main-container">
				<h1>マイページ</h1>
				<table class="form">
					<tr>
						<th>名前</th>
						<td><input readonly type="text" name="name" value="<%=user.getName()%>"></td>
					</tr>
					<tr>
						<th>住所</th>
						<td><input readonly type="text" name="address" value="<%=user.getAddress()%>"></td>
					</tr>
					<tr>
						<th>メールアドレス</th>
						<td><input readonly type="email" name="email" value="<%=user.getEmail()%>"></td>
					</tr>
				</table>
				<h2><a href="updateUser.html">登録情報変更</a></h2>
				<div class="pad">
					<h2>購入履歴</h2>
					<table>
						<thead>
							<tr>
								<td></td>
								<td>購入日時</td>
								<td>配送方法</td>
								<td>購入金額</td>
							</tr>
						</thead>
						<tr>
							<td><a href="history.html">詳細</a></td>
							<td>00月00日00時00分</td>
							<td>配送方法例</td>
							<td>123456789円</td>
						</tr>
						<tfoot>
							<tr>
								<td></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>