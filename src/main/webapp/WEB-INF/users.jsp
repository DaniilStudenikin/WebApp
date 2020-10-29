<%@ page import="java.util.List" %>
<%@ page import="ru.itis.javalab.models.User" %><%--
  Created by IntelliJ IDEA.
  User: whoami
  Date: 21.10.2020
  Time: 19:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<div>
    <h1 style="color: ${cookie.get("color").value}">Users</h1>
    <form action="/users" method="post">
        <select name="color">
            <option value="red">RED</option>
            <option value="green">GREEN</option>
            <option value="blue">BLUE</option>
            <option value="pink">PINK</option>
        </select>
        <input type="submit" value="OK">
    </form>
</div>
<div>
    <table>
        <tr>
            <th>Username</th>
            <th>Password</th>
        </tr>
        <%
            List<User> users = (List<User>) request.getAttribute("usersForJsp");
            for (User user : users) {
        %>
        <tr>
            <td><%=user.getUsername()%>
            </td>
            <td><%=user.getPassword()%>
            </td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>
