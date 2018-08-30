<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="beans.ItemDataBeans"%>
<%@page import="beans.SearchIndexBeans"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<%
		SearchIndexBeans indexs = (SearchIndexBeans)request.getAttribute("indexs");
		List<ItemDataBeans> itemList = (ArrayList<ItemDataBeans>)request.getAttribute("itemList");
	%>
	<title>「<%=indexs.getKeyword()%>」の検索結果</title>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<div class="main-container">
			<form action="SearchResult" method="post">
				<h2>「<%=indexs.getKeyword()%>」の検索結果：<%=indexs.getResultNum()%>件の商品が見つかりました</h2>
				<div class="form inline">
					<input type="text" name="search"><button>検索</button>
				</div>
			</form>
			<form action="SearchResult" method="post">
				<input type="hidden" name="keyword">
				<ul class="grid">
					<%for(ItemDataBeans item : itemList){%>
					<li class="content">
									<label for="detail">
										<img alt="<%=item.getFileName()%>" src="<%="img/" + item.getFileName()%>"><br>
										<%=item.getName()%><br>
										<%=item.getPrice()%>円
										<input type="submit" id="detail"name="detail" value=<%=item.getId()%>>
									</label>
<%-- 									<button type="submit" name="detail" value=<%=item.getId()%>> --%>
<!-- 									</button> -->
					</li>
					<%}%>
				</ul>
				<h2>
					<%if(indexs.getIndex() == 1){%>
					<label for="<%=indexs.getIndex() - 1%>" class="content disabled">
						&laquo;
						<input type="submit" id="<%=indexs.getIndex() - 1%>" name="index" value=<%=indexs.getIndex() - 1%> disabled>
					</label>
					<%}else{%>
					<label for="<%=indexs.getIndex() - 1%>" class="content">
						&laquo;
						<input type="submit" id="<%=indexs.getIndex() - 1%>" name="index" value=<%=indexs.getIndex() - 1%>>
					</label>
					<%}
					for(int i = 1 ; i <= indexs.getPageNum() ;i++){
					if(i == indexs.getIndex()){%>
					<label for="<%=i%>" class="content disabled">
						<%=i%>
						<input type="submit" id="<%=i%>" name="index" value=<%=i%> disabled>
					</label>
					<%}else if(indexs.getIndex() - 5 < i && i < indexs.getIndex() + 5){%>
					<label for="<%=i%>" class="content">
						<%=i%>
						<input type="submit" id="<%=i%>" name="index" value=<%=i%>>
					</label>
					<%}%>
					<%}%>
					<%if(indexs.getIndex() == indexs.getPageNum()){%>
					<label for="<%=indexs.getIndex() + 1%>" class="content disabled">
						&raquo;
						<input type="submit" id="<%=indexs.getIndex() + 1%>" name="index" value=<%=indexs.getIndex() + 1%> disabled>
					</label>
					<%}else{%>
					<label for="<%=indexs.getIndex() + 1%>" class="content">
						&raquo;
						<input type="submit" id="<%=indexs.getIndex() + 1%>" name="index" value=<%=indexs.getIndex() + 1%>>
					</label>
					<%}%>
				</h2>
			</form>
		</div>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>