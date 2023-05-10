<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Get Customer Favourites</title>
</head>
<body>
<form method="get" action="${pageContext.request.contextPath}/favourite/getAll">

    List Favourites:<input type="submit" value="List" />
</form>
<c:if test='${not empty favourites}'>
<table>
    <tr>
        <th><b>Favourite Id</b></th>
        <th><b>Post Id</b></th>
        <th><b>User Id</b></th>
    </tr>

<tbody>
<c:forEach var="data" items="${favourites}">
    <tr>
        <td>${data.favouriteId}</td>
        <td>${data.userId}</td>
        <td>${data.postId}</td>
    </tr>
</c:forEach>
</tbody>
</table>
</c:if>
</body>
</html>