<%--
  Created by IntelliJ IDEA.
  User: frankcaron
  Date: 8/13/13
  Time: 1:30 PM
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

        <form id="loginForm" method="post" action="LoginProcessorServlet">
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
                    <td colspan="2">
                        <input type="submit" name="submit" value="Log In" />
                    </td>
                </tr>
            </table>
        </form>
  </body>
</html>