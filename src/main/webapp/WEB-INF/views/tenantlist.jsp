<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tenant List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1 class="mt-4">Tenant List</h1>

        <table class="table table-bordered mt-4">
            <thead>
                <tr>
                    <th>Tenant ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Admin</th>
                </tr>
            </thead>
            <tbody>
                <!-- Loop through the list of tenants using JSTL -->
                <c:forEach var="tenant" items="${tenants}">
                    <tr>
                        <td>${tenant.tenantId}</td>
                        <td>${tenant.tenantFname}</td>
                        <td>${tenant.tenantLname}</td>
                        <td>${tenant.tenantEmail}</td>
                        <td>${tenant.tenantPhone}</td>
                        <td>${tenant.isAdmin ? 'Yes' : 'No'}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <a href="${pageContext.request.contextPath}/tenant/registertenantform" class="btn btn-primary mt-3">Add New Tenant</a>
    </div>
</body>
</html>