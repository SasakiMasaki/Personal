<%@page import="beans.ItemDataBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>商品情報更新画面</title>
	<%
		ItemDataBeans item = (ItemDataBeans)request.getAttribute("item");
		String redirectMsg = (String)request.getAttribute("redirectMsg");
	%>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="ItemUpdate" enctype="multipart/form-data" method="post">
			<div class="main-container">
				<%if(redirectMsg != null){%>
				<h2><%=request.getAttribute("redirectMsg")%></h2>
				<%}%>
				<h2>「<%=item.getName()%>」の編集ページ</h2>
				<div class="flex">
					<div class="product">
						<img alt="<%=item.getFileName()%>" src="<%="img/"+item.getFileName()%>">
						<table>
							<tr>
								<th>画像</th>
								<td>
									<label for="file_upload">
										ファイルを選択
										<input type="file" name="file" id="file_upload">
									</label>
								</td>
							</tr>
							<tr>
								<th>商品名</th>
								<td><input required type="text" name="name" value="<%=item.getName()%>"></td>
							</tr>
							<tr>
								<th>単価</th>
								<td><input required type="number" name="price" value="<%=item.getPrice()%>" min="1" max="999999999">円</td>
							</tr>
						</table>
						<ul class="inline">
							<li><button type="submit" name="action" value="update">更新</button></li>
							<li><button type="submit" name="action" value="delete">削除</button></li>
							<li><input type="hidden" name="item_id" value=<%=item.getId()%>></li>
						</ul>
					</div>
					<div class="text">
						<textarea name="detail" rows="20" cols="40"><%=item.getDetail()%></textarea>
					</div>
				</div>
				<h2>
					<button type="submit" name="action" value="return">戻る</button>
				</h2>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>