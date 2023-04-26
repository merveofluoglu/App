<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Favourite</title>
</head>
<body>
<form method="POST" action="<c:url value="/favourite/addFavourite"/>" enctype="multipart/form-data">

    <label for="user_id">User Id:</label>
    <input id="user_id" name="user_id" type="number"/><br/>

    <label for="post_id">Post Id</label>
    <input id="post_id" name="post_id" type="number"/><br/>

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