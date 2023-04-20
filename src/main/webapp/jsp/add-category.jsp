<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Category</title>
</head>
<body>
<form method="POST" action="<c:url value="/category/add"/>" enctype="multipart/form-data">
    <label for="name">Name:</label>
    <input id="name" name="name" type="text"/><br/>

    <label for="category_id">Category Id:</label>
    <input id="category_id" name="category_id" type="text"/><br/>

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