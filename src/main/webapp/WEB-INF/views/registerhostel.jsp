<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Add New Hostel</title>
    <!-- Add Bootstrap for styling -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
.was-validated .form-control:invalid {
	border-color: #dc3545;
}

.form-control:valid {
	border-color: #28a745;
}

.bg-cyan {
	background: #CAF0FF;
}

.custom-container {
	max-width: 768px !important;
}

.navbar {
	background: white;
}

.button {
	background: #CAF0FF;
	padding: 5px 12px;
	border-radius: 45px;
	text-align: center;
	border: 2px solid #CAF0FF;
}

.button:hover {
	background: white;
	border: 2px solid #CAF0FF;
	color: black;
	transition: 0.2s all;
}

.text-grey {
	color: #575454;
}

.hero {
	background: #CAF0FF;
}
</style>
</head>
<body>
    <div class="container mt-5">
        <h2 class="text-center">Add New Hostel</h2>
        
        <form:form modelAttribute="hostel" method="post" action="${pageContext.request.contextPath}/hostel/createhostel">
            <div class="form-group">
                <label for="hostelName">Hostel Name</label>
                <form:input path="hostelName" class="form-control" placeholder="Enter hostel name" required="true"/>
            </div>

            <div class="form-group">
                <label for="hostelLocation">Hostel Location</label>
                <form:input path="hostelLocation" class="form-control" placeholder="Enter hostel location" required="true"/>
            </div>

            <div class="form-group">
                <label for="hostelDescription">Hostel Description</label>
                <form:textarea path="hostelDescription" class="form-control" placeholder="Enter hostel description" rows="3" required="true"></form:textarea>
            </div>

            <div class="form-group">
                <label for="hostelAmenities">Hostel Amenities</label>
                <form:textarea path="hostelAmenities" class="form-control" placeholder="Enter hostel amenities (comma separated)" rows="3"></form:textarea>
            </div>

            <button type="submit" class="btn btn-primary">Add Hostel</button>
        </form:form>
    </div>

    <!-- Add Bootstrap JS for better functionality (optional) -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
