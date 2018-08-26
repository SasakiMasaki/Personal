<%@page import="beans.ItemDataBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>カート</title>
	<%
		ArrayList<ItemDataBeans> cart = new ArrayList<ItemDataBeans>();
		cart = (ArrayList<ItemDataBeans>)session.getAttribute("cart");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="Cart" method="post">
			<div class="main-container">
				<h1>カート</h1>
				<%if(cart == null){%>
				<h2>カートに入っている商品はありません。</h2>
				<h2><a href="Top">トップページへ</a></h2>
				<%}else {%>
				<table>
					<thead>
						<tr>
							<td>商品名</td>
							<td>個数</td>
							<td></td>
						</tr>
					</thead>
					<tr>
						<td>サンプル商品1</td>
						<td><input type="number" name="number" value="1" min="0" max="99"></td>
						<td><button>取り消し</button></td>
					</tr>
					<tfoot>
						<tr>
						</tr>
					</tfoot>
				</table>
				<h2><a href="Buy">購入画面へ</a></h2>
				<%}%>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>