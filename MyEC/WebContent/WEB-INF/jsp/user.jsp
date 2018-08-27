<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="beans.UserDataBeans"%>
<%@ page import="beans.BuyDataBeans" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>マイページ</title>
	<%
		UserDataBeans user = (UserDataBeans)request.getAttribute("user");
		List<BuyDataBeans> buyList = (ArrayList<BuyDataBeans>)request.getAttribute("buyList");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="User" method="post">
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
				<h2><button type="submit" name="action" value="update">登録情報変更</button></h2>
				<div class="pad">
					<h2>購入履歴</h2>
					<%if(buyList == null){%>
					<h2>購入履歴はまだありません</h2>
					<%}else{%>
					<table>
						<thead>
							<tr>
								<td></td>
								<td>購入日時</td>
								<td>配送方法</td>
								<td>購入金額</td>
							</tr>
						</thead>
						<%for(BuyDataBeans buy : buyList){%>
						<tr>
							<td><button type="submit" name="buyId" value=<%=buy.getId()%>>詳細</button></td>
							<td><%=buy.getBuyDate()%></td>
							<td><%=buy.getDeliveryMethod()%></td>
							<td><%=buy.getTotalPrice() %>円</td>
						</tr>
						<%}%>
						<tfoot>
							<tr>
								<td></td>
							</tr>
						</tfoot>
					</table>
					<%}%>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>