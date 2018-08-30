<%@page import="beans.ItemDataBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>商品情報削除画面</title>
	<%
		ItemDataBeans item = (ItemDataBeans)request.getAttribute("item");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="ItemDelete" method="post">
			<div class="main-container">
				<h2>「<%=item.getName()%>」の削除ページ</h2>
				<div class="flex">
					<div class="product">
						<img alt="<%=item.getFileName()%>" src="<%="img/"+item.getFileName()%>">
						<table>
							<tr>
								<th>単価</th>
								<td><%=item.getPrice()%>円</td>
							</tr>
						</table>
						<ul class="inline">
							<li><button type="submit" name="action" value="detail">詳細</button></li>
							<li><button type="submit" name="action" value="update">更新</button></li>
							<li><input type="hidden" name="item_id" value=<%=item.getId()%>></li>
						</ul>

					</div>
					<div class="text">
						<%=item.getDetail()%>
					</div>
				</div>
				<h2>本当にこの商品情報を削除してよろしいですか</h2>
				<h2 class="inline">
					<button type="submit" name="action" value="return">戻る</button>
					<button type="submit" name="action" value="delete">削除する</button>
				</h2>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>