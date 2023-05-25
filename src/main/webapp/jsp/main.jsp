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
    <br/><a href="${pageContext.request.contextPath}/jsp/list-files-html.jsp" >List Files</a>
    <!--<br/><a href="${pageContext.request.contextPath}/jsp/uploadFile-result.jsp" >List File</a>-->
    <br/><a href="${pageContext.request.contextPath}/jsp/get_favourites.jsp" >Favourites</a>
    <br/><a href="${pageContext.request.contextPath}/jsp/profile.jsp" >Profile</a>

    <%
        String role = (String) session.getAttribute("role");
        if (Objects.equals(role, "admin")) { %>
            <br/><a href="${pageContext.request.contextPath}/jsp/admin-dashboard/index.html" >Admin Dashboard</a>
    <% }
    %>
</body>
</html>
