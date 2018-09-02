<%@page import="java.util.List"%>
<%@page import="beans.DeliveryMethodDataBeans"%>
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
		List<DeliveryMethodDataBeans> dmList = (ArrayList<DeliveryMethodDataBeans>)request.getAttribute("dmList");
		ArrayList<ItemDataBeans> cart = new ArrayList<ItemDataBeans>();
		cart = (ArrayList<ItemDataBeans>)session.getAttribute("cart");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<div class="main-container">
			<h1>カート</h1>
			<%if(cart == null){%>
				<h2>カートに入っている商品はありません。</h2>
				<h2><a href="Top">トップページへ</a></h2>
			<%}else {%>
				<form action="Cart" method="post">
					<table class="cart">
						<thead>
							<tr>
								<th>商品名</th>
								<td>個数</td>
								<td></td>
							</tr>
						</thead>
						<%for(ItemDataBeans item : cart){%>
							<tr>
								<th><%=item.getName()%></th>
								<td><input type="number" name="<%=cart.indexOf(item)%>" value=<%=item.getCount()%> min="0" max="99"></td>
								<td><button type="submit" name="cancell" value="<%=cart.indexOf(item)%>" >取り消し</button></td>
							</tr>
						<%}%>
							<tr>
								<th>配送方法</th>
								<td>
									<select name="dmId">
									<%for(DeliveryMethodDataBeans dm : dmList){%>
										<option value=<%=dm.getId()%>><%=dm.getName()%></option>
									<%}%>
									</select>
								</td>
								<td></td>
							</tr>
						<tfoot>
							<tr>
							</tr>
						</tfoot>
					</table>
					<h2><button type="submit" name="buy">購入画面へ</button></h2>
				</form>
			<%}%>
		</div>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>