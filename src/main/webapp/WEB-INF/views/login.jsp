<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Tenant Login</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css"
	integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
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
<body class="bg-light" style="overflow-x: hidden;">

	<!-- Display success message if present -->
	<c:if test="${param.success != null}">
		<div class="bg-success text-white font-weight-bold py-2 text-center"
			role="alert">Please login to continue</div>
	</c:if>

	<!-- Display error message if present -->
	<c:if test="${not empty error}">
		<div class="bg-danger text-white font-weight-bold py-2 text-center"
			role="alert">${error}</div>
	</c:if>

	<!-- --------------------BODY-------------------- -->
	<section>
		<div class="row">
			<div class="col-4 hero p-5">
				<a class="navbar-brand" href="${pageContext.request.contextPath}/">
					<svg width="140" height="43" viewBox="0 0 140 43" fill="none"
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
				<h1 class="my-5">Easy on the pocket, easy on the mind.</h1>

				<div class="row mb-3 text-center">
					<div
						class="col border fw-semibold border-2 border-white rounded mx-1 py-3">
						Loaded<br />Amenities
					</div>
					<div
						class="col border fw-semibold border-2 border-white  rounded mx-1 py-3">
						Safe and<br />Secure
					</div>
				</div>
				<div class="row text-center">
					<div
						class="col border fw-semibold border-2 border-white  rounded mx-1 py-3">
						Zero<br />Brokerage
					</div>
					<div
						class="col border fw-semibold border-2 border-white rounded mx-1 py-3">
						No<br />Deposit
					</div>
				</div>

			</div>
			<div class="col-8 d-flex flex-column justify-content-center">
				<h2 class="text-center">Login Here</h2>
				<div class="row justify-content-center">
					<div class="col-md-6">


						<form action="${pageContext.request.contextPath}/tenant/login"
							method="post">
							<div class="form-group mt-3 mb-2">
								<label for="username" class="fw-semibold">Email</label> <input
									type="text" class="form-control" id="email" name="tenantEmail"
									placeholder="" required>
							</div>

							<div class="form-group">
								<div class="d-flex justify-content-between">
									<label for="password" class="fw-semibold">Password</label><a
										href="${pageContext.request.contextPath}/tenant/forgotPassword" class="text-dark" style="font-size:12px;">Forgot
										password?</a>
								</div>

								<div class="input-group mb-3">
									<input type="password" class="form-control" id="password"
										name="tenantPassword" placeholder="" required>
									<button class="btn btn-outline-primary" type="button"
										id="button-addon2">
										<i class="fa-solid fa-eye"></i>
									</button>
								</div>
							</div>

							<button type="submit" class="button w-100 btn-block mb-3">Login</button>
						</form>


						<p class="text-center">
							Don't have an account? <a
								href="${pageContext.request.contextPath}/tenant/registertenantform"
								class="text-dark">Sign up</a>
						</p>
					</div>
				</div>
			</div>

		</div>
	</section>

	<script>
		const passwordField = document.getElementById('password');
		const togglePasswordBtn = document.getElementById('button-addon2');

		togglePasswordBtn
				.addEventListener(
						'click',
						function() {
							const type = passwordField.getAttribute('type') === 'password' ? 'text'
									: 'password';
							passwordField.setAttribute('type', type);
							this.innerHTML = type === 'password' ? '<i class="fa-solid fa-eye"></i>'
									: '<i class="fa-solid fa-eye-slash"></i>';
						});
	</script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

