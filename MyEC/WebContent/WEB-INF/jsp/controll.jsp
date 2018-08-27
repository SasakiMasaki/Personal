<%@page import="java.util.ArrayList"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>商品管理画面</title>
	<%
		List<ItemDataBeans> itemList = new ArrayList<ItemDataBeans>();
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="Controll" method="post">
			<div class="main-container">
				<h2>商品管理画面</h2>
				<div class="form inline">
					商品一覧
					<input type="text" name="keyword"><button type="submit" name="action" value="search">検索</button>
				</div>
				<div class="pad">
					<table class="result">
						<thead>
							<tr>
								<td>商品名</td>
								<td>各種変更</td>
							</tr>
						</thead>
						<tbody>
							<%for(ItemDataBeans item: itemList){%>
							<tr>
								<td><%=item.getName()%></td>
								<td>
									<ul class="inline">
										<li><button type="submit" name="detail" value=<%=item.getId()%>>詳細</button></li>
										<li><button type="submit" name="update" value=<%=item.getId()%>>更新</button></li>
										<li><button type="submit" name="delete" value=<%=item.getId()%>>削除</button></li>
									</ul>
								</td>
							</tr>
							<%}%>
						</tbody>
					</table>
				</div>
				<h2><button type="submit" name="action" value="regist">商品新規登録</button></h2>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>