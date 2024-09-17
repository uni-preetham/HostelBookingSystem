<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room List</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container my-5">
        <h2 class="text-center">List of Rooms</h2>

        <table class="table table-bordered table-hover">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Room Number</th>
                    <th scope="col">Room Type</th>
                    <th scope="col">Price</th>
                    <th scope="col">Availability</th>
                    <th>Hostel Name</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="room" items="${rooms}">
                    <tr>
                        <td>${room.roomNumber}</td>
                        <td>${room.roomType}</td>
                        <td>${room.price}</td>
                        <td>
                            <c:choose>
                                <c:when test="${room.availability}">
                                    Available
                                </c:when>
                                <c:otherwise>
                                    Not Available
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${room.hostel.hostelName }</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
