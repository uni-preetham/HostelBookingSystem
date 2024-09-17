<%@ page import="com.crimsonlogic.hostelmanagementsystem.entity.Booking"%>
<%@ page
	import="com.crimsonlogic.hostelmanagementsystem.service.BookingService"%>
<%@ page
	import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@ page import="javax.servlet.http.HttpServletRequest"%>
<%@ page import="javax.servlet.http.HttpServletResponse"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%-- <%
    String bookingId = request.getParameter("bookingId");
    BookingService bookingService = (BookingService) WebApplicationContextUtils
        .getRequiredWebApplicationContext(application).getBean(BookingService.class);
    Booking booking = bookingService.showBookingById(bookingId);
%> --%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Payment</title>
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
</style>
<script>
	function setMinDate() {
		const today = new Date().toISOString().split('T')[0];
		document.getElementById('checkInDate').setAttribute('min', today);
	}

	function updateCheckOutMinDate() {
		const checkInDate = document.getElementById('checkInDate').value;
		if (checkInDate) {
			const checkInDateObj = new Date(checkInDate);
			checkInDateObj.setDate(checkInDateObj.getDate() + 1); // Add 1 day
			const minCheckOutDate = checkInDateObj.toISOString().split('T')[0];
			document.getElementById('checkOutDate').setAttribute('min',
					minCheckOutDate);
			calculateTotalPrice();
		}
	}

	function calculateTotalPrice() {
		const checkInDate = new Date(
				document.getElementById('checkInDate').value);
		const checkOutDate = new Date(
				document.getElementById('checkOutDate').value);
		const roomPrice = parseFloat(document.getElementById('roomPrice').value);

		if (checkInDate && checkOutDate && roomPrice) {
			const timeDifference = checkOutDate - checkInDate;
			const dayDifference = timeDifference / (1000 * 3600 * 24);
			const totalPrice = dayDifference * roomPrice;

			if (dayDifference > 0) {
				document.getElementById('totalPrice').value = totalPrice
						.toFixed(2);
			} else {
				document.getElementById('totalPrice').value = 'Invalid dates';
			}
		}
	}

	document.addEventListener('DOMContentLoaded', setMinDate);
</script>


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
		<div class="row" style="height: 100vh;">
			<div class="col-4 hero p-5">
				<h1 class="text-center text-dark mb-5 text-capitalize text-grey">Booking
					Summary</h1>
				<ul class="card rounded p-4"
					style="list-style-type: none; font-size: 16px; background: rgba(255, 255, 255, 0.8) !important;">
					<li class="my-2 text-dark d-flex justify-content-between">Booking ID
						<span class=" fw-semibold">${booking.bookingId}</span></li>
					<li
						class="my-2 text-dark py-3 border-top border-bottom d-flex justify-content-between">Total
						<span class=" fw-semibold">&#8377;${booking.totalPrice}</span></li>
				</ul>
			</div>


			<div class="col-8 p-5 d-flex flex-column">
				<div class="row justify-content-center" style="gap: 40px;">
					<h2 class="text-center mb-3">Payment Method</h2>
					<div class="row justify-content-center">
						<div class="col-md-6">
							<!-- Include payment form or payment gateway integration here -->

							<form
								action="${pageContext.request.contextPath}/payment/confirmpayment"
								method="post" id="paymentForm">
								<input type="hidden" name="bookingId"
									value="${booking.bookingId}"> <input type="hidden"
									name="paymentAmount" value="${booking.totalPrice}">

								<!-- Payment Method Options -->
								<input type="radio" class="btn-check" name="paymentMethod"
									id="cash" autocomplete="off" value="Cash"
									onclick="togglePaymentForms()" checked> <label
									class="btn mb-2 w-100" for="cash">Cash</label> <input
									type="radio" class="btn-check" name="paymentMethod" id="card"
									autocomplete="off" value="Card" onclick="togglePaymentForms()">
								<label class="btn mb-2 w-100" for="card">Card</label> <input
									type="radio" class="btn-check" name="paymentMethod" id="upi"
									autocomplete="off" value="UPI" onclick="togglePaymentForms()">
								<label class="btn mb-3 w-100" for="upi">UPI</label>

								<!-- Card Payment Form -->
								<div id="cardForm" style="display: none;">
									<div class="form-group form-floating mb-2">
										<input type="text" class="form-control" id="cardnumber"
											name="cardnumber" placeholder=""
											pattern="\d{4} \d{4} \d{4} \d{4}" maxlength="19"> <label
											for="cardnumber" class="form-label">Debit or Credit
											Card</label> <small id="cardnumberError" class="text-danger"
											style="display: none;">Card number is required.</small>
									</div>

									<div class="d-flex" style="gap: 20px;">
										<div class="form-group form-floating mb-2">
											<input type="password" class="form-control" id="cvv"
												name="cvv" placeholder="" pattern="\d{3}" maxlength="3">
											<label for="cvv" class="form-label">CVV</label> <small
												id="cvvError" class="text-danger" style="display: none;">CVV
												is required.</small>
										</div>

										<div class="form-group form-floating mb-2">
											<input type="text" class="form-control" id="expiry"
												name="expiry" placeholder="" pattern="\d{2}/\d{2}"
												maxlength="5" oninput="validateExpiry()"> <label
												for="expiry" class="form-label">Expiry (MM/YY)</label> <small
												id="expiryError" class="text-danger" style="display: none;">Expiry
												date is required</small>
										</div>

									</div>
								</div>

								<!-- UPI Payment Section -->
								<div id="upiForm" style="display: none;" class="text-center">
									<img style="width: 200px; height: 200px; aspect-ratio: 1/1;"
										src="https://raw.githubusercontent.com/uni-preetham/Test/master/Frame.png"
										alt="UPI Scanner">
									<p class="text-grey my-2" style="font-size: 12px;">Scan at
										this link to pay</p>
								</div>

								<!-- Submit Button -->
								<button type="submit" class="button w-100">Submit
									Payment</button>
							</form>

						</div>
					</div>
				</div>
			</div>
		</div>
	</section>

	<script>
		function togglePaymentForms() {
			var cardForm = document.getElementById('cardForm');
			var upiForm = document.getElementById('upiForm');
			var cardRadio = document.getElementById('card');
			var upiRadio = document.getElementById('upi');
			var cashRadio = document.getElementById('cash');

			// Show card form if card is selected
			if (cardRadio.checked) {
				cardForm.style.display = 'block';
				upiForm.style.display = 'none';
				
			}
			// Show UPI form if UPI is selected
			else if (upiRadio.checked) {
				upiForm.style.display = 'block';
				cardForm.style.display = 'none';
			}
			// Hide both if cash is selected
			else if (cashRadio.checked) {
				cardForm.style.display = 'none';
				upiForm.style.display = 'none';
			}
		}

		document.getElementById('expiry').addEventListener('input',
				function(e) {
					const input = e.target;
					let value = input.value.replace(/\D/g, ''); // Remove non-numeric characters

					if (value.length > 2) {
						value = value.slice(0, 2) + '/' + value.slice(2); // Insert the slash after the month
					}

					input.value = value;
				});
		
		document.getElementById('cardnumber').addEventListener('input', function(e) {
		    const input = e.target;
		    let value = input.value.replace(/\D/g, ''); // Remove non-numeric characters

		    if (value.length > 16) {
		        value = value.slice(0, 16); // Limit to 16 digits
		    }

		    // Add a space after every 4 digits
		    value = value.replace(/(.{4})/g, '$1 ').trim();

		    input.value = value;
		});


		
		document.getElementById('cvv').addEventListener('input',
				function(e) {
					const input = e.target;
					let value = input.value.replace(/\D/g, ''); // Remove non-numeric characters


					input.value = value;
				});
		
		
		document.addEventListener('DOMContentLoaded', () => {
		    const form = document.getElementById('paymentForm');
		    const cardForm = document.getElementById('cardForm');
		    const cardnumberInput = document.getElementById('cardnumber');
		    const cvvInput = document.getElementById('cvv');
		    const expiryInput = document.getElementById('expiry');
		    const cardnumberError = document.getElementById('cardnumberError');
		    const cvvError = document.getElementById('cvvError');
		    const expiryError = document.getElementById('expiryError');

		    function togglePaymentForms() {
		        const selectedPaymentMethod = document.querySelector('input[name="paymentMethod"]:checked').value;
		        cardForm.style.display = selectedPaymentMethod === 'Card' ? 'block' : 'none';
		    }

		    function validateField(input, errorElement) {
		        errorElement.style.display = input.value.trim() === '' ? 'block' : 'none';
		    }

		    cardnumberInput.addEventListener('input', () => validateField(cardnumberInput, cardnumberError));
		    cvvInput.addEventListener('input', () => validateField(cvvInput, cvvError));
		    expiryInput.addEventListener('input', () => validateField(expiryInput, expiryError));

		    form.addEventListener('submit', (event) => {
		        if (cardForm.style.display === 'block') {
		            // Validate all fields before submitting
		            const isCardnumberValid = cardnumberInput.value.trim() !== '';
		            const isCvvValid = cvvInput.value.trim() !== '';
		            const isExpiryValid = expiryInput.value.trim() !== '';

		            cardnumberError.style.display = isCardnumberValid ? 'none' : 'block';
		            cvvError.style.display = isCvvValid ? 'none' : 'block';
		            expiryError.style.display = isExpiryValid ? 'none' : 'block';

		            if (!isCardnumberValid || !isCvvValid || !isExpiryValid) {
		                event.preventDefault();
		                return false;
		            }
		        }
		    });
		});

		function validateExpiry() {
		    const expiryInput = document.getElementById('expiry');
		    const expiryError = document.getElementById('expiryError');
		    const value = expiryInput.value;
		    
		    // Regex to match MM/YY format
		    const regex = /^(\d{2})\/(\d{2})$/;
		    const match = value.match(regex);
		    
		    if (match) {
		        const month = parseInt(match[1], 10);
		        const year = parseInt(match[2], 10);
		        
		        // Validate month and year
		        if (month >= 1 && month <= 12 && year >= 0 && year <= 99) {
		            expiryError.style.display = 'none';
		            expiryInput.classList.remove('is-invalid');
		        } else {
		            expiryError.style.display = 'block';
		            expiryInput.classList.add('is-invalid');
		        }
		    } else {
		        expiryError.style.display = 'block';
		        expiryInput.classList.add('is-invalid');
		    }
		}
		
	</script>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
