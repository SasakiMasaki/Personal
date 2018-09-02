<%@page import="beans.ItemDataBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="beans.BuyDataBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>購入履歴詳細</title>
	<%
		List<ItemDataBeans> itemList = (ArrayList<ItemDataBeans>)request.getAttribute("itemList");
		BuyDataBeans bdb = (BuyDataBeans)request.getAttribute("bdb");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="BuyHistory">
			<div class="main-container">
				<h1>購入履歴詳細</h1>
				<div class="pad">
					<table>
						<thead>
							<tr>
								<td>購入日時</td>
								<td>配送方法</td>
								<td>購入金額</td>
							</tr>
						</thead>
						<tr>
							<td><%=bdb.getFormatDate()%></td>
							<td><%=bdb.getDeliveryMethodName()%></td>
							<td><%=bdb.getTotalPrice()%>円</td>
						</tr>
					</table>
				</div>
				<div class="pad">
					<table>
						<thead>
							<tr>
								<td>商品名</td>
								<td>単価</td>
								<td>個数</td>
							</tr>
						</thead>
						<%for(ItemDataBeans item : itemList){%>
						<tr>
							<td><%=item.getName()%></td>
							<td><%=item.getPrice()%>円</td>
							<td><%=item.getCount()%></td>
						</tr>
						<%}%>
						<tr>
							<td><%=bdb.getDeliveryMethodName()%></td>
							<td><%=bdb.getDeliveryMethodPrice()%>円</td>
							<td></td>
						</tr>
						<tfoot>
							<tr>
								<td></td>
							</tr>
						</tfoot>
					</table>
				</div>
				<h2><button type="submit" name="action" value="return">戻る</button></h2>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>