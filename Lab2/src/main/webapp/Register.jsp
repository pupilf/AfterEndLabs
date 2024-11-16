<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<strong>已有账号:<a href="login.jsp">登录</a></strong> <br>
    <form action="/lab2/register" method="post">
        username:<input type="text" name="username">
        password:<input type="password" name="password">
        <button type="submit">Register</button>
    </form>
    <strong>${status}</strong>
</body>
</html>
