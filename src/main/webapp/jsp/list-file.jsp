<%--
  Created by IntelliJ IDEA.
  User: AYŞE KILIÇ
  Date: 22.04.2023
  Time: 23:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>

    <form action="${pageContext.request.contextPath}/postFiles/getAllPostFiles" method="get">

        Fetch Post Files:<input type="submit" value="Search" />

    </form>

</body>
</html>







