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

<center><h1>File List</h1></center>
<br>
<table class="container">

    <tr bgcolor="00FF7F">
        <th><b>Post file</b></th>
    </tr>
    <tbody>
    <jsp:useBean id="fileList" scope="request" type="java.util.List"/>
    <c:forEach items="${fileList}" var="pf">
        <tr>
            <td>${pf.getFile()}</td>
            <td>
                <form method="GET" action="<c:url value="/PostFilesServlet/getAllPostFiles"/>">
                    <input type="hidden" name="shelveGuid" value="${pf.getFile_id()}"/>
                    <button type="submit" class="dash-button">Books</button><br/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>







