<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Zyloz | Tenant Feedbacks</title>
</head>
<body>
	<table class="table table-striped">
    <thead>
        <tr>
            <th>feedback ID</th>
            <th>Tenant Name</th>
            <th>Description</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="feedback" items="${feedbacks}">
            <tr>
                <td>${feedback.feedbackId}</td>
                <td>${feedback.tenant.tenantFname} ${feedback.tenant.tenantLname}</td>
                <td>${feedback.feedbackDescription}</td>
                <td>${feedback.feedbackStatus}</td>
                <td>
                    <form action="${pageContext.request.contextPath}/feedback/update-status/${feedback.feedbackId}" method="post">
                        <select name="status" class="form-select">
                            <option value="Pending" <c:if test="${feedback.feedbackStatus == 'Pending'}">selected</c:if>>Pending</option>
                            <option value="Resolved" <c:if test="${feedback.feedbackStatus == 'Resolved'}">selected</c:if>>Resolved</option>
                        </select>
                        <button type="submit" class="btn btn-primary">Update Status</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</body>
</html>