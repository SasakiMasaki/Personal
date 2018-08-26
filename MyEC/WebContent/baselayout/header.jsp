<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<header>
		<nav>
			<a class="logo" href="Top">MyEC</a>
			<ul class="f-right inline">
				<%if((Integer)session.getAttribute("id") == null){%>
					<li><a href="Regist">新規登録</a></li>
					<li><a href="Cart">カート</a></li>
					<li><a href="Login">ログイン</a></li>
				<%}else{%>
					<li><a href="User">マイページ</a></li>
					<li><a href="Cart">カート</a></li>
					<li><a href="Logout">ログアウト</a></li>
				<%}%>
			</ul>
		</nav>
	</header>