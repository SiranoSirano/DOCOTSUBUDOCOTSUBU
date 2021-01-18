<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--ログイン結果を出力するビュー --%>
<%@ page import = "model.User" %>
<%
//セッションスコープからユーザーのログイン情報を取得
User loginUser = (User) session.getAttribute("loginUser");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理システム</title>
</head>
<body>
<h1>社員情報管理システムログイン</h1>
<%--ログイン情報がnullじゃないなら、ログインが通ったら --%>
<% if(loginUser != null) { %>
	<p>ログインに成功しました</p>
	<p>ようこそ<%= loginUser.getName() %>さん</p>
	<a href ="/DOCOTSUBUDOCOTSUBU/Main">社員情報管理画面へ</a>
	<a href="/DOCOTSUBUDOCOTSUBU/index.jsp">TOPへ戻る</a>
<%  }else { %>
	<p>ログインに失敗しました</p>
	<a href="/DOCOTSUBUDOCOTSUBU/index.jsp">TOPへ戻る</a>
<% } %>
</body>
</html>