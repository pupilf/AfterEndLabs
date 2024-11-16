<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<strong>没有账号:<a href="Register.jsp">注册</a></strong>
    <form action="/lab2/login" method="post">
        username:<input type="text" name="username"> <br>
        password:<input type="password" name="password"> <br>
        <tr>
            <td>验证码</td>
            <td class="inputs">
                <input name="checkCode" type="text" id="checkCode">
                <img id="checkCodeImg" src="/lab2/checkCodeServlet">
                <a href="#" id="changeImg" >看不清？</a>
            </td>
        </tr>
        <br>
        Remember me<input type="checkbox" name="rememberMe" value="1">
        <button type="submit">logIn</button>
    </form>
    <strong>${status}</strong>

    <script>
        document.getElementById("changeImg").onclick = function () {
    //路径后面添加时间戳的目的是避免浏览器进行缓存静态资源
            document.getElementById("checkCodeImg").src = "/lab2/checkCodeServlet?"+new Date().getMilliseconds();
        }
    </script>
</body>
</html>
