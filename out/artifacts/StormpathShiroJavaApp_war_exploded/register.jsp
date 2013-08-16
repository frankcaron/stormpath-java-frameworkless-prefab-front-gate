<%--
  Created by IntelliJ IDEA.
  User: frankcaron
  Date: 2013-08-15
  Time: 9:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <title>Stormpath - Sample Shiro Java App</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/style.css" />
</head>
<body>
<% if (request.getParameter("session") != null) { if (request.getParameter("session").equals("false")) { %>
<div id="errorBox">Your log in request failed. Check your credentials, and try again.</div>
<% } else if (request.getParameter("session").equals("expired")) { %>
<div id="errorBox">Your session has expired. Check your credentials, and try again.</div>
<% } } %>

<form id="regForm" method="post" action="RegProcessorServlet">
    <table>
        <tr>
            <td>Username: </td>
            <td><input type="text" name="username" /></td>
        </tr>
        <tr>
            <td>Password: </td>
            <td><input type="password" name="credential" /></td>
        </tr>
        <tr>
            <td>First Name: </td>
            <td><input type="text" name="firstName" /></td>
        </tr>
        <tr>
            <td>Last Name: </td>
            <td><input type="text" name="lastName" /></td>
        </tr>
        <tr>
            <td>Email: </td>
            <td><input type="text" name="email" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" name="submit" value="Register" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>