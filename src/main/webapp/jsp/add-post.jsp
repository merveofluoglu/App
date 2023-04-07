<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 6.04.2023
  Time: 08:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Post</title>
</head>
<body>
    <form method="post"  action="<c:url value="/post/add"/>">
      <label for="name">Name:</label>
      <input id="name" name="name" type="text"/><br/>

      <label for="description">Description:</label>
      <input id="description" name="description" type="text"/><br/>

        <label for="user_id">User Id:</label>
        <input id="user_id" name="user_id" type="text"/><br/>

        <label for="customer_id">Customer Id:</label>
        <input id="customer_id" name="customer_id" type="text"/><br/>

        <label for="price">Price:</label>
        <input id="price" name="price" type="text"/><br/>

        <label for="status">Status:</label>
        <input id="status" name="status" type="text"/><br/>

        <label for="category_id">Category Id:</label>
        <input id="category_id" name="category_id" type="text"/><br/>

        <label for="subcategory_id">SubCategory Id:</label>
        <input id="subcategory_id" name="subcategory_id" type="text"/><br/>

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
