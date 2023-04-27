<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: AYŞE KILIÇ
  Date: 27.04.2023
  Time: 01:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>File Upload</title>
</head>
<body>
<h1>File Upload</h1>
<br/>
<jsp:useBean id="postFiles" scope="request" class="resource.PostFiles"/>
<c:if test="${not empty postFiles}">
    <ul>
        <li>file id: <c:out value="${postFiles.file_id}"/></li>
        <li>post id: <c:out value="${postFiles.post_id}"/></li>

        <c:choose>
            <c:when test="${postFiles.hasFile()}">
                <li>file:
                    <ul>
                        <li>File type: <c:out value="${postFiles.file_media_type}"/> </li>
                            <li>size: <c:out value="${postFiles.fileSize}"/> </li>
                            <li>image: <br/>
                                <img src="<c:url value="postFiles/loadPostFile">
                                <c:param name="file_id" value="${postFiles.file_id}"/></c:url>"/>
                        </li>
                    </ul>
                </li>
            </c:when>

            <c:otherwise><li>file: not available</li></c:otherwise>
        </c:choose>
    </ul>
</c:if>
</body>
</html>
