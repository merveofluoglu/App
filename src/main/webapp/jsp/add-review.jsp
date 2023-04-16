<%--
  Created by IntelliJ IDEA.
  User: gorkemyilmaz
  Date: 16/04/23
  Time: 16:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <title>Add Review</title>
</head>
<body>
<form method="POST" action="<c:url value="/review/add"/>" enctype="multipart/form-data">

  <label for="description">Description:</label>
  <input id="description" name="description" type="text"/><br/>

  <label for="point_scale">Point Scale</label>
  <input id="point_scale" name="point_scale" type="text"/><br/>

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

