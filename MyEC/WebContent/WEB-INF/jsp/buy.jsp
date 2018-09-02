<%@page import="beans.ItemDataBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>購入確認画面</title>
	<%
		List<ItemDataBeans> cartTemp = (ArrayList<ItemDataBeans>)request.getAttribute("cartTemp");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="Buy" method="post">
			<div class="main-container">
				<h1>購入確認画面</h1>
				<table>
					<thead>
						<tr>
							<td>商品名</td>
							<td>単価</td>
							<td>数量</td>
							<td>小計</td>
						</tr>
					</thead>
					<%for(ItemDataBeans item : cartTemp){%>
					<tr>
						<td><%=item.getName()%></td>
						<td><%=item.getPrice()%>円</td>
						<td><%=item.getCount()%></td>
						<td><%=item.getPrice() * item.getCount()%>円</td>
					</tr>
					<%}%>
					<tr>
						<td>配送方法＜サンプル1＞</td>
						<td></td>
						<td></td>
						<td>123456789円</td>
					</tr>
					<tfoot>
						<tr>
							<td>合計</td>
							<td></td>
							<td></td>
							<td>123456789円</td>
						</tr>
					</tfoot>
				</table>
				<h2>上記でお間違いなければ購入を確定します。</h2>
				<ul class="inline">
					<li><button type="submit" name="action" value="return">戻る</button></li>
					<li><button type="submit" name="action" value="buy">確定する</button></li>
				</ul>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>