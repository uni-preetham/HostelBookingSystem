<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="list-group">
    <!-- Check if hostellist is empty -->
    <c:choose>
        <c:when test="${empty hostellist}">
            <li class="list-group-item">No results found</li>
        </c:when>
        <c:otherwise>
            <!-- Iterate through the hostel list and display results -->
            <c:forEach var="location" items="${hostellist}">
                    <a href="${pageContext.request.contextPath}/hostel/showhostelbyid/${location.hostelId}" class="list-group-item list-group-item-action">
                        ${location.hostelName} - ${location.hostelLocation}
                    </a>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</ul>

</body>
</html>