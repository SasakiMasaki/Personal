<%@page import="beans.SearchIndexBeans"%>
<%@page import="beans.ItemDataBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>商品詳細情報画面</title>
	<%
		int id = (Integer)session.getAttribute("id");
		String redirectMsg = (String)request.getAttribute("redirectMsg");
		ItemDataBeans item = (ItemDataBeans)request.getAttribute("item");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="ItemDetail" method="post">
			<div class="main-container">
				<h2>「<%=item.getName()%>」の詳細ページ</h2>
				<%if(redirectMsg != null){%>
				<h2><%=redirectMsg%></h2>
				<%}%>
				<div class="flex">
					<div class="product">
						<img alt="<%=item.getFileName()%>" src="<%="img/"+item.getFileName()%>">
						<table>
							<tr>
								<th>単価</th>
								<td><%=item.getPrice()%>円</td>
							</tr>
						<%if(id != 1){%>
							<tr>
								<th>個数</th>
								<td><input type="number" name="count" value="1" min="1" max="99"></td>
							</tr>
						<%}%>
						</table>
						<%if(id == 1){%>
						<ul class="inline">
							<li><button type="submit" name="action" value="update">更新</button></li>
							<li><button type="submit" name="action" value="delete">削除</button></li>
						</ul>
						<%}else{%>
						<button type="submit" name="action" value="cart">カートへ入れる</button>
						<%}%>
					</div>
					<div class="text">
						<%=item.getDetail()%>
					</div>
				</div>
				<h2>
					<button type="submit" name="action" value="return">戻る</button>
					<input type="hidden" name="item_id" value=<%=item.getId()%>>
				</h2>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>