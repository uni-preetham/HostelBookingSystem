<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Zyloz | ${sessionScope.user.tenantFname}'s Dashboard</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
	integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<style>
.dropdown-toggle::after {
	display: none;
}

.text-blue {
	color: #69A4DB !important;
}

.dropdown-menu {
	margin: 0 !important;
	padding: 0 !important;
}

.custom-container {
	max-width: 770px !important;
	margin: auto;
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

.locationCards {
	/* width */ ::-webkit-scrollbar { width:10px;
	
}

/* Track */
::-webkit-scrollbar-track {
	background: #f1f1f1;
	border-radius: 20px;
}

/* Handle */
::-webkit-scrollbar-thumb {
	background: #888;
	border-radius: 20px;
}

/* Handle on hover */
::-webkit-scrollbar-thumb:hover {
	background: #555;
}
}
</style>
</head>
<body class="bg-light" style="overflow-x: hidden;">
	<!-- --------------------NAVBAR-------------------- -->
	<header>
		<nav class="navbar navbar-expand-lg py-3">
			<div
				class="container d-flex justify-content-between align-items-center ">
				<a class="navbar-brand" href="#"> <svg width="107" height="39"
						viewBox="0 0 107 39" fill="none"
						xmlns="http://www.w3.org/2000/svg">
<path
							d="M40.3423 20V18.6577L48.5753 6.3679H40.2528V4.72727H50.7827V6.0696L42.5497 18.3594H50.8722V20H40.3423ZM54.8693 24.2955C54.571 24.2955 54.305 24.2706 54.0714 24.2209C53.8377 24.1761 53.6761 24.1314 53.5866 24.0866L54.0341 22.5355C54.4616 22.6449 54.8395 22.6847 55.1676 22.6548C55.4957 22.625 55.7866 22.4783 56.0401 22.2148C56.2987 21.9563 56.5348 21.5362 56.7486 20.9545L57.0767 20.0597L52.8409 8.54545H54.75L57.9119 17.6733H58.0312L61.1932 8.54545H63.1023L58.2401 21.6705C58.0213 22.2621 57.7504 22.7518 57.4272 23.1396C57.104 23.5323 56.7287 23.8232 56.3011 24.0121C55.8786 24.201 55.4013 24.2955 54.8693 24.2955ZM67.1852 4.72727V20H65.4252V4.72727H67.1852ZM75.0621 20.2386C74.028 20.2386 73.1207 19.9925 72.3401 19.5004C71.5645 19.0082 70.958 18.3196 70.5205 17.4347C70.088 16.5497 69.8717 15.5156 69.8717 14.3324C69.8717 13.1392 70.088 12.0977 70.5205 11.2077C70.958 10.3178 71.5645 9.62678 72.3401 9.13459C73.1207 8.6424 74.028 8.39631 75.0621 8.39631C76.0961 8.39631 77.001 8.6424 77.7765 9.13459C78.5571 9.62678 79.1636 10.3178 79.5961 11.2077C80.0336 12.0977 80.2524 13.1392 80.2524 14.3324C80.2524 15.5156 80.0336 16.5497 79.5961 17.4347C79.1636 18.3196 78.5571 19.0082 77.7765 19.5004C77.001 19.9925 76.0961 20.2386 75.0621 20.2386ZM75.0621 18.6577C75.8476 18.6577 76.4939 18.4563 77.001 18.0536C77.5081 17.6509 77.8834 17.1214 78.127 16.4652C78.3707 15.8089 78.4925 15.098 78.4925 14.3324C78.4925 13.5668 78.3707 12.8533 78.127 12.1921C77.8834 11.5309 77.5081 10.9964 77.001 10.5888C76.4939 10.1811 75.8476 9.97727 75.0621 9.97727C74.2765 9.97727 73.6302 10.1811 73.1231 10.5888C72.616 10.9964 72.2407 11.5309 71.9971 12.1921C71.7535 12.8533 71.6317 13.5668 71.6317 14.3324C71.6317 15.098 71.7535 15.8089 71.9971 16.4652C72.2407 17.1214 72.616 17.6509 73.1231 18.0536C73.6302 18.4563 74.2765 18.6577 75.0621 18.6577ZM82.6108 20V18.6577L89.1136 10.3054V10.1861H82.8196V8.54545H91.3807V9.94744L85.0568 18.2401V18.3594H91.5895V20H82.6108Z"
							fill="black" />
<path d="M5.8839 21.6938H4.6452V30.6978H5.8839V21.6938Z" fill="#222831" />
<path d="M9.18688 18.0522H7.94818V30.6983H9.18688V18.0522Z"
							fill="#222831" />
<path d="M14.9672 9.10977H13.7285V30.6983H14.9672V9.10977Z"
							fill="#222831" />
<path d="M18.2703 6.26242H17.0316V30.6983H18.2703V6.26242Z"
							fill="#222831" />
<path d="M24.0512 25.5965H22.8125V30.6983H24.0512V25.5965Z"
							fill="#222831" />
<path d="M27.3549 28.1471H26.1162V30.6983H27.3549V28.1471Z"
							fill="#222831" />
<path
							d="M30.2448 32.8118V25.3716L21.1614 19.6205V0L10.8386 6.53499V13.2937L1.75462 19.0454V32.8124H0V33.9626H1.75462H10.8386H12.0773H19.9227H21.1614H30.2448H32V32.8124L30.2448 32.8118ZM2.99332 32.8118V19.6533L10.8386 14.686V32.8124L2.99332 32.8118ZM12.0773 32.8118V12.5098V7.14288L19.9227 2.17622V18.8361V32.8118H12.0773ZM21.1614 32.8118V21.0123L29.0061 25.9795V32.8124L21.1614 32.8118Z"
							fill="#222831" />
<path d="M29.1089 35.401H2.88989V36.5512H29.1089V35.401Z" fill="#222831" />
<path
							d="M40.0568 37.0714V28.3442H45.2898V29.2817H41.1136V32.2305H44.8977V33.168H41.1136V37.0714H40.0568ZM47.0998 37.0714V28.3442H52.3668V29.2817H48.1566V32.2305H52.0941V33.168H48.1566V36.1339H52.435V37.0714H47.0998ZM54.2717 37.0714V28.3442H59.5387V29.2817H55.3285V32.2305H59.266V33.168H55.3285V36.1339H59.6069V37.0714H54.2717ZM61.4435 37.0714V28.3442H62.5004V36.1339H66.5572V37.0714H61.4435ZM71.5685 37.0714V28.3442H72.6254V32.2305H77.2788V28.3442H78.3356V37.0714H77.2788V33.168H72.6254V37.0714H71.5685ZM87.815 32.7078C87.815 33.6283 87.6488 34.4237 87.3164 35.0942C86.984 35.7646 86.5281 36.2817 85.9485 36.6453C85.369 37.0089 84.707 37.1908 83.9627 37.1908C83.2184 37.1908 82.5565 37.0089 81.9769 36.6453C81.3974 36.2817 80.9414 35.7646 80.609 35.0942C80.2766 34.4237 80.1104 33.6283 80.1104 32.7078C80.1104 31.7874 80.2766 30.9919 80.609 30.3214C80.9414 29.651 81.3974 29.1339 81.9769 28.7703C82.5565 28.4067 83.2184 28.2249 83.9627 28.2249C84.707 28.2249 85.369 28.4067 85.9485 28.7703C86.5281 29.1339 86.984 29.651 87.3164 30.3214C87.6488 30.9919 87.815 31.7874 87.815 32.7078ZM86.7923 32.7078C86.7923 31.9521 86.6658 31.3143 86.413 30.7945C86.163 30.2746 85.8235 29.8811 85.3945 29.6141C84.9684 29.347 84.4911 29.2135 83.9627 29.2135C83.4343 29.2135 82.9556 29.347 82.5266 29.6141C82.1005 29.8811 81.761 30.2746 81.5082 30.7945C81.2582 31.3143 81.1332 31.9521 81.1332 32.7078C81.1332 33.4635 81.2582 34.1013 81.5082 34.6212C81.761 35.141 82.1005 35.5345 82.5266 35.8016C82.9556 36.0686 83.4343 36.2021 83.9627 36.2021C84.4911 36.2021 84.9684 36.0686 85.3945 35.8016C85.8235 35.5345 86.163 35.141 86.413 34.6212C86.6658 34.1013 86.7923 33.4635 86.7923 32.7078ZM89.592 28.3442H90.8533L93.8192 35.5885H93.9215L96.8874 28.3442H98.1488V37.0714H97.1602V30.4408H97.0749L94.3477 37.0714H93.3931L90.6658 30.4408H90.5806V37.0714H89.592V28.3442ZM100.268 37.0714V28.3442H105.535V29.2817H101.325V32.2305H105.262V33.168H101.325V36.1339H105.603V37.0714H100.268Z"
							fill="black" />
</svg></a>

				<div class="" id="navbarNavAltMarkup">
					<div class="navbar-nav">
						<a class="nav-item nav-link text-grey"
							href="${pageContext.request.contextPath}/tenant/dashboard">Home</a>
						<a class="nav-item nav-link text-grey"
							href="${pageContext.request.contextPath}/booking/viewbooking/${sessionScope.user.tenantId}">View
							bookings</a>
						<a class="nav-item nav-link text-grey"
							href="${pageContext.request.contextPath}/feedback/showfeedbackform">Feedback</a>
					</div>
				</div>

				<%-- <div style="">
					<a href="${pageContext.request.contextPath}/tenant/logout"
						class="button text-dark" style="text-decoration: none;">Logout</a>
				</div> --%>

				<div class="dropdown">
					<a class="nav-link text-dark button" href="#" id="userDropdown"
						role="button" data-bs-toggle="dropdown" aria-expanded="false"
						style="text-decoration: none;"> <i class="fa-solid fa-user"></i>
					</a>
					<ul class="dropdown-menu dropdown-menu-end"
						aria-labelledby="userDropdown">
						<li><a class="dropdown-item" disabled>Hi
								${sessionScope.user.tenantFname}</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/tenant/edituserform/${sessionScope.user.tenantId}">Edit
								Profile</a></li>
						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item"
							href="${pageContext.request.contextPath}/tenant/logout">Logout</a></li>
					</ul>
				</div>
			</div>
		</nav>
	</header>


	<!-- --------------------BODY-------------------- -->
	
	
	<section>
	   <!-- Display success message if available -->
        <c:if test="${not empty message}">
           <div id="alert-message" class="bg-success text-white font-weight-bold py-2 text-center" role="alert">${message}</div>
        </c:if>
		<div class="custom-container my-5 hero rounded">
			<div class="row hero rounded py-2">
				<div
					class="col d-flex flex-column justify-content-center align-items-center">
					<div>
						<h1 class="text-blue mb-3">Hello
							${sessionScope.user.tenantFname}</h1>
						<div class="form-group form-floating w-100">
							<input type="text" class="form-control" id="locationSearch"
								placeholder="Find a zyloz near you" onkeyup="searchHostels()" /><label
								for="locationSearch" class="form-label text-grey">Find a zyloz
								near you</label>
							<ul id="locationResults" class="dropdown-menu"
								style="display: none;"></ul>
						</div>
					</div>
				</div>
				<div class="col d-flex justify-content-center align-items-center"
					style="min-height: 22em; background: url('https://visor.gumlet.io//public/assets/home/desktop/hero-img.png?format=webp&w=522&h=480&compress=true') no-repeat center center; background-size: cover;">
				</div>
			</div>
		</div>
	</section>



	<section class="mb-5 d-flex align-items-center" style="height: 16em;">
		<div class="custom-container">
			<h2 class="text-center fw-semibold text-grey my-5">
				Find your <span style="color: #69A4DB;"> Zyloz </span>, in your city.
			</h2>
			<div
				class="locationCards custom-container align-items-center d-flex justify-content-center text-grey fw-semibold px-5 pb-2 mb-5"
				style="gap: 20px;">
				<!-- overflow-x: scroll; -->
				<!-- Loop through the hostel locations passed from the controller -->
				<c:forEach var="location" items="${hostellist}">
					<div class="border border-1 border-dark rounded text-center py-1"
						style="min-width: 10em; padding: 10px; font-size: 12px; position: relative;">
						<p>${location.hostelName}</p>
						<i class="fa-regular fa-building fa-5x pb-2 text-dark"></i><br />${location.hostelLocation}
						<a
							href="${pageContext.request.contextPath}/hostel/showhostelbyid/${location.hostelId}"
							class="" id="${location.hostelId}"><span class="link-spanner"
							style="position: absolute; width: 100%; height: 100%; top: 0; left: 0; z-index: 1;"></span></a>
					</div>
				</c:forEach>
			</div>
		</div>
	</section>

	<section class="mb-5 d-flex align-items-center" style="height: 16em;">
		<div class="custom-container mb-5">
			<h2 class="text-center fw-semibold text-grey my-5">
				We are India's <span style="color: #69A4DB;"> most affordable
				</span>hostels
			</h2>

			<div class="row" style="gap: 40px;">
				<div class="col text-center">
					<h1 class="text-blue fw-semibold">10k+</h1>
					<p class="text-dark">Satisfied Customers</p>
				</div>
				<div class="col text-center">
					<h1 class="text-blue fw-semibold">20+</h1>
					<p class="text-dark">Hostels</p>
				</div>
				<div class="col text-center">
					<h1 class="text-blue fw-semibold">10+</h1>
					<p class="text-dark">Locations and counting</p>
				</div>
			</div>
		</div>
	</section>
	<!-- --------------------FOOTER-------------------- -->

	<footer style="font-size: 14px;">
		<div class="row bg-white pt-3">
			<div
				class="col-4 d-flex flex-column justify-content-center align-items-center border-end">
				<div class="w-50">
					<a class="navbar-brand my-2" href="#"> <svg width="140"
							height="43" viewBox="0 0 140 43" fill="none"
							xmlns="http://www.w3.org/2000/svg">
<path
								d="M39.3011 35V32.6989L53.4148 11.6307H39.1477V8.81818H57.1989V11.1193L43.0852 32.1875H57.3523V35H39.3011ZM64.2045 42.3636C63.6932 42.3636 63.2372 42.321 62.8366 42.2358C62.4361 42.1591 62.1591 42.0824 62.0057 42.0057L62.7727 39.3466C63.5057 39.5341 64.1534 39.6023 64.7159 39.5511C65.2784 39.5 65.777 39.2486 66.2116 38.7969C66.6548 38.3537 67.0597 37.6335 67.4261 36.6364L67.9886 35.1023L60.7273 15.3636H64L69.4205 31.0114H69.625L75.0455 15.3636H78.3182L69.983 37.8636C69.608 38.8778 69.1435 39.7173 68.5895 40.3821C68.0355 41.0554 67.392 41.554 66.6591 41.8778C65.9347 42.2017 65.1165 42.3636 64.2045 42.3636ZM85.3175 8.81818V35H82.3004V8.81818H85.3175ZM98.8207 35.4091C97.0479 35.4091 95.4925 34.9872 94.1545 34.1435C92.8249 33.2997 91.7852 32.1193 91.0352 30.6023C90.2937 29.0852 89.9229 27.3125 89.9229 25.2841C89.9229 23.2386 90.2937 21.4531 91.0352 19.9276C91.7852 18.402 92.8249 17.2173 94.1545 16.3736C95.4925 15.5298 97.0479 15.108 98.8207 15.108C100.593 15.108 102.145 15.5298 103.474 16.3736C104.812 17.2173 105.852 18.402 106.593 19.9276C107.343 21.4531 107.718 23.2386 107.718 25.2841C107.718 27.3125 107.343 29.0852 106.593 30.6023C105.852 32.1193 104.812 33.2997 103.474 34.1435C102.145 34.9872 100.593 35.4091 98.8207 35.4091ZM98.8207 32.6989C100.167 32.6989 101.275 32.3537 102.145 31.6634C103.014 30.973 103.657 30.0653 104.075 28.9403C104.493 27.8153 104.701 26.5966 104.701 25.2841C104.701 23.9716 104.493 22.7486 104.075 21.6151C103.657 20.4815 103.014 19.5653 102.145 18.8665C101.275 18.1676 100.167 17.8182 98.8207 17.8182C97.4741 17.8182 96.3661 18.1676 95.4968 18.8665C94.6275 19.5653 93.984 20.4815 93.5664 21.6151C93.1488 22.7486 92.94 23.9716 92.94 25.2841C92.94 26.5966 93.1488 27.8153 93.5664 28.9403C93.984 30.0653 94.6275 30.973 95.4968 31.6634C96.3661 32.3537 97.4741 32.6989 98.8207 32.6989ZM111.761 35V32.6989L122.909 18.3807V18.1761H112.119V15.3636H126.795V17.767L115.955 31.983V32.1875H127.153V35H111.761Z"
								fill="black" />
<path d="M5.8839 26.6938H4.6452V35.6978H5.8839V26.6938Z" fill="#222831" />
<path d="M9.18688 23.0522H7.94818V35.6983H9.18688V23.0522Z"
								fill="#222831" />
<path d="M14.9672 14.1098H13.7285V35.6983H14.9672V14.1098Z"
								fill="#222831" />
<path d="M18.2703 11.2624H17.0316V35.6983H18.2703V11.2624Z"
								fill="#222831" />
<path d="M24.0513 30.5965H22.8126V35.6983H24.0513V30.5965Z"
								fill="#222831" />
<path d="M27.3549 33.1471H26.1162V35.6983H27.3549V33.1471Z"
								fill="#222831" />
<path
								d="M30.2448 37.8118V30.3716L21.1614 24.6205V5L10.8386 11.535V18.2937L1.75462 24.0454V37.8124H0V38.9626H1.75462H10.8386H12.0773H19.9227H21.1614H30.2448H32V37.8124L30.2448 37.8118ZM2.99332 37.8118V24.6533L10.8386 19.686V37.8124L2.99332 37.8118ZM12.0773 37.8118V17.5098V12.1429L19.9227 7.17622V23.8361V37.8118H12.0773ZM21.1614 37.8118V26.0123L29.0061 30.9795V37.8124L21.1614 37.8118Z"
								fill="#222831" />
<path d="M29.1089 40.401H2.88989V41.5512H29.1089V40.401Z" fill="#222831" />
</svg>
					</a>
					<p class="text-grey my-3">Comfortable, affordable hostel with a
						welcoming community. Book your stay today!</p>
				</div>
			</div>
			<div
				class="col-8 d-flex flex-column justify-content-center align-items-center">
				<div class="d-flex align-items-center justify-content-around w-75">
					<ul class="list-unstyled">
						<li class="my-3"><a href="#" class="nav-link">Careers</a></li>
						<li class="my-3"><a href="#" class="nav-link">T&C</a></li>
						<li class="my-3"><a href="#" class="nav-link">FAQs</a></li>
						<li class="my-3"><a href="#" class="nav-link">Privacy
								Policy</a></li>
					</ul>
					<ul class="list-unstyled">
						<li class="my-3"><a href="http://www.instagram.com" class="nav-link"><i
								class="fa-brands fa-instagram mx-2"></i>Instagram</a></li>
						<li class="my-3"><a href="http://www.facebook.com" class="nav-link"><i
								class="fa-brands fa-facebook mx-2"></i></i>Facebook</a></li>
						<li class="my-3"><a href="http://www.x.com" class="nav-link"><i
								class="fa-brands fa-x-twitter mx-2"></i></i>Twitter</a></li>
						<li class="my-3"><a href="#" class="nav-link"><i
								class="fa-solid fa-phone mx-2"></i></i></i>Phone</a></li>
					</ul>
				</div>
			</div>
			<p class="text-center text-grey mt-3">&copy;2024 Zyloz</p>
		</div>
	</footer>

	<script>
    // Function to search hostels as user types
    function searchHostels() {
        let query = document.getElementById('locationSearch').value;
        
        // Search only when query length is more than 2 characters
        if (query.length > 2) {
            fetch(`${pageContext.request.contextPath}/hostel/search?query=` + query)
            .then(response => response.text())
            .then(data => {
                // Populate the dropdown with search results
                document.getElementById('locationResults').innerHTML = data;
                document.getElementById('locationResults').style.display = 'block';
            });
        } else {
            document.getElementById('locationResults').style.display = 'none';
        }
    }

    // Hide dropdown when clicking outside the input or dropdown
    document.addEventListener('click', function(event) {
        let input = document.getElementById('locationSearch');
        let dropdown = document.getElementById('locationResults');
        
        // If the click happens outside the input and the dropdown, hide the dropdown
        if (!input.contains(event.target) && !dropdown.contains(event.target)) {
            dropdown.style.display = 'none';
        }
    });

    // Ensure dropdown stays visible when clicking inside it
    document.getElementById('locationSearch').addEventListener('focus', function() {
        let dropdown = document.getElementById('locationResults');
        if (dropdown.innerHTML.trim() !== '') {
            dropdown.style.display = 'block';
        }
    });
    
    // Wait for the DOM to load before running the script
    document.addEventListener("DOMContentLoaded", function() {
        // Set a timeout to hide the alert message after 3 seconds (3000 milliseconds)
        setTimeout(function() {
            var alertElement = document.getElementById("alert-message");
            if (alertElement) {
                alertElement.style.display = 'none'; // Hide the alert message
            }
        }, 3000);
    });
</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>