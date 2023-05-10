<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Favourite</title>
</head>
<body>
<form method="POST" action="<c:url value="/favourite/addFavourite"/>" enctype="multipart/form-data">

    <label for="userId">User Id:</label>
    <input id="userId" name="userId" type="number"/><br/>

    <label for="postId">Post Id</label>
    <input id="postId" name="postId" type="number"/><br/>

    <button type="submit">Submit</button><br/>
</form>

<c:choose>
    <c:when test="${message.error}">
        <p><c:out value="${message.message}"/></p>
    </c:when>
    <c:otherwise></c:otherwise>
</c:choose>

</body>
</html>