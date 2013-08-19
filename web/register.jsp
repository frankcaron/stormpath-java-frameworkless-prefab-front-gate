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
    <title>Stormpath - Sample Java App</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/style.css" />
</head>
<body>
<div class="pageContainer">
        <% if (request.getParameter("registration") != null) { if (request.getParameter("registration").equals("false")) { %>
    <div class="errorBox">Your registration failed. Please try again.</div>
        <% } else { %>
    <div class="successBox">Your account was created. Please <a href="login.jsp">click here</a> to log in.</div>
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
                <td colspan="2" class="loginForm-controls">
                    <input type="submit" name="submit" value="Register" />
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>