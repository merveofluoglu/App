<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AYŞE KILIÇ
  Date: 22.04.2023
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>

<head>
    <title>Post Files</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>

<body>

<header>
    <nav class="navbar navbar-expand-md navbar-dark" style="background-color: tomato">
        <div>
            <a href="${pageContext.request.contextPath}/jsp/list-file.jsp" class="navbar-brand">Post Files</a>
        </div>

        <ul class="navbar-nav">
            <li><a href="${pageContext.request.contextPath}/jsp/post.jsp" class="navbar-brand">Posts</a></li>
        </ul>
    </nav>
</header>
<br>
<form action="${pageContext.request.contextPath}/postFiles/getAllPostFiles" method="get">

    List Post Files:<input type="submit" value="List" />

</form>
</body>
</html>







