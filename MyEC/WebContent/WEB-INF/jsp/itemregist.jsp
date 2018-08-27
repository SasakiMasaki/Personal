<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>商品新規登録</title>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="adminRegist2.html">
			<div class="main-container">
				<h2>商品新規登録</h2>
				<div class="flex">
					<div class="product">
						<img alt="サンプル商品の画像" src="img/f_f_business_38_s512_f_business_38_1bg.png">
						<table>
							<tr>
								<th>画像</th>
								<td>
									<label for="file_upload">
										ファイルを選択
										<input type="file" id="file_upload">
									</label>
								</td>
							</tr>
							<tr>
								<th>単価</th>
								<td><input type="number" name="number" min="1" max="999999999">円</td>
							</tr>
						</table>
					</div>
					<div class="text">
						<textarea rows="20" cols="40"></textarea>
					</div>
				</div>
				<div class="pad">
					<ul class="inline">
						<li><a href="adminTop.html">戻る</a></li>
						<li><button>登録</button></li>
					</ul>
				</div>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>