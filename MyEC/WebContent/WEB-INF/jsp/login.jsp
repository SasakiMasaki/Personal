<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<jsp:include page="/baselayout/head.jsp" />
	<title>ログイン</title>
</head>
<body>
	<jsp:include page="/baselayout/header.jsp" />
	<div class="container">
		<form action="Login" method="post">
			<div class="main-container">
				<h1>ログイン</h1>
				<table class="form">
					<tr>
						<th>メールアドレス</th>
						<td><input type="email" name="email"></td>
					</tr>
					<tr>
						<th>パスワード</th>
						<td><input type="password" name="pass"></td>
					</tr>
				</table>
				<h2><button>ログイン</button></h2>
				<h2>アカウントをお持ちでない方は<a href="Regist">こちらへ</a>どうぞ</h2>
			</div>
		</form>
	</div>
	<jsp:include page="/baselayout/footer.jsp"/>
</body>
</html>