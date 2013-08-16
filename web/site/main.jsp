<%--
  Created by IntelliJ IDEA.
  User: frankcaron
  Date: 8/13/13
  Time: 1:30 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page language="java" session="true" %>
<%@ page import="com.stormpath.sdk.account.*" %>

<%

%>

<html>
<head>
    <title>Stormpath - Sample Shiro Java App</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/style/style.css" />
</head>
<body>

<p>
    You're all logged in. Way to go, champ. Your account info follows:
</p>

<%
    Account currentUser = (Account)session.getAttribute("Account");
%>
<p>
    Account Username: <%= currentUser.getUsername() %><br />
    Account First Name: <%= currentUser.getGivenName() %><br />
    Account Last Name: <%= currentUser.getSurname() %><br />
    Account Email: <%= currentUser.getEmail() %><br />
</p>

<a href="${pageContext.request.contextPath}/site/logout.jsp">Log Out</a>

</body>
</html>