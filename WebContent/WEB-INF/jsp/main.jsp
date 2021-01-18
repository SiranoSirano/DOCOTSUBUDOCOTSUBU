<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="model.User,model.Mutter,java.util.List" %>
<%
 //セッションスコープに保存されたユーザー情報を取得
User loginUser = (User) session.getAttribute("loginUser");

//アプリケーションスコープに保存されたリストを取得
@SuppressWarnings("unchecked")
List<Mutter> mutterList = (List<Mutter>)request.getAttribute("mutterList");

//リクエストスコープに保存されたエラーメッセージを取得
String errorMsg = (String) request.getAttribute("errorMsg");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>社員情報管理システムメイン</title>
</head>
<body>
<h1>社員情報管理システムメイン</h1>
<p><%= loginUser.getName() %>さんはログイン中です
<a href ="/DOCOTSUBUDOCOTSUBU/Logout">ログアウト</a>
</p>

<p><a href="/DOCOTSUBUDOCOTSUBU/Main">更新</a></p>
<form action="/DOCOTSUBUDOCOTSUBU/Main" method ="post">
<input type="text" name ="text">
<input type ="submit" value="入力" >
</form>

<%--　エラーメッセージのerrorMsgがある場合はそこに遷移して表示する --%>
<% if(errorMsg != null) {%>
<p><%= errorMsg %>><p>
<% } %>

<%-- ArrayListに格納されたインスタンスを先頭から取得、名前と入力内容を紐づけて表示 --%>
<%-- 拡張for文を使って取り出していく --%>
<% for(Mutter mutter : mutterList){ %>
	<p><%= mutter.getUserName() %>:<%= mutter.getText() %></p>
<% } %>

</body>
</html>