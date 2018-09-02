<%@page import="beans.DeliveryMethodDataBeans"%>
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
		int total = 0;
		DeliveryMethodDataBeans dm = (DeliveryMethodDataBeans)request.getAttribute("dm");
		List<ItemDataBeans> cart = (ArrayList<ItemDataBeans>)session.getAttribute("cart");
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
					<%for(ItemDataBeans item : cart){%>
					<tr>
						<td><%=item.getName()%></td>
						<td><%=item.getPrice()%>円</td>
						<td><%=item.getCount()%></td>
						<td><%=item.getPrice() * item.getCount()%>円</td>
					</tr>
					<%total += item.getPrice() * item.getCount();
					}%>
					<tr>
						<td><%=dm.getName()%></td>
						<td></td>
						<td></td>
						<td><%=dm.getPrice()%>円</td>
					</tr>
					<tfoot>
						<tr>
							<td>合計</td>
							<td></td>
							<td></td>
							<td><%=total + dm.getPrice()%>円</td>
						</tr>
					</tfoot>
				</table>
				<h2>上記でお間違いなければ購入を確定します。</h2>
				<input type="hidden" name="dmId" value=<%=dm.getId()%>>
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