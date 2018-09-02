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
		<div class="main-container">
			<h1>カート</h1>
			<%if(cart == null){%>
				<h2>カートに入っている商品はありません。</h2>
				<h2><a href="Top">トップページへ</a></h2>
			<%}else {%>
				<form action="Cart" method="post">
					<table>
						<thead>
							<tr>
								<td>商品名</td>
								<td>個数</td>
								<td></td>
							</tr>
						</thead>
						<%for(ItemDataBeans item : cart){%>
							<tr>
								<td><%=item.getName()%></td>
								<td><input type="number" name="<%=cart.indexOf(item)%>" value=<%=item.getCount()%> min="0" max="99"></td>
								<td><button type="submit" name="cancell" value="<%=cart.indexOf(item)%>" >取り消し</button></td>
							</tr>
						<%}%>
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