<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Objects" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main Page</title>
</head>
<body>

    <a href="${pageContext.request.contextPath}/jsp/post.jsp" >View / Edit / Delete / Add Posts</a>
    <br/><a href="${pageContext.request.contextPath}/jsp/upload-file.jsp" >Upload File</a>
    <br/><a href="${pageContext.request.contextPath}/jsp/list-file.jsp" >List Files</a>
    <%
        String role = (String) session.getAttribute("role");
        if (Objects.equals(role, "admin")) { %>
            <br/><a href="${pageContext.request.contextPath}/jsp/admin-dashboard.jsp" >Admin Dashboard</a>
        <% }
    %>
</body>

</html>
