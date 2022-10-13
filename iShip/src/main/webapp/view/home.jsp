<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css"
	        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	    <link rel="stylesheet" href="css/style.css" type="text/css">
	    <meta charset="UTF-8">
	    <title>iShip</title>
	
	
	</head>
	
	<body class="bg-warning">
	
	    <!--Naviggation Bar -->
	    <nav class="navbar navbar-expand-lg bg-black">
	        <div class="container-fluid">
	            <a class="navbar-brand text-yellow " href="home">iShip</a>
	            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
	                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	                <span class="navbar-toggler-icon"></span>
	            </button>
	            <div class="collapse navbar-collapse" id="navbarNav">
	                <ul class="nav nav-tabs">
	                    <li class="nav-item ">
	                        <a class="nav-link text-yellow " href="faqs">FAQs</a>
	                    </li>
	                    <li class="nav-item ">
	                        <a class="nav-link  text-yellow " href="contactUs">Contact</a>
	                    </li>
	
	                </ul>
	
	            </div>
	            <div class="text-end">
	                <form action="login" method="get" class="floatleft">
	                    <button type="submit" class="btn btn-outline-light me-2">Login</button>&nbsp;
	                </form>
	
	                <form action="register" method="get" class="floatleft">
	                    <button type="submit" class="btn bg-warning btnhover ">Sign-up</button>
	                </form>
	            </div>
	        </div>
	    </nav>
	
	    <!--Main Part -->
	    <form action="register" method="get">
	        <div class="container py-4">
	            <div class="p-5 mb-4 bg-dark text-white rounded-3 borderradius10">
	                <div class="container-fluid py-5">
	                    <h1 class="display-5 fw-bold ">You rest, we deliver.</h1>
	                    <p class="col-md-8 fs-4">This is the new opportunity you were waiting for.
	                        Send and track your items throughout the whole process of delivery.
	                        Choose who delivers your items from the list, and pick the most convenient dates.
	                        We Guarantee that your package will deliver on time. </p>
	                    <button class="btn btn-warning btn-lg btnhover" type="submit">Get Started</button>
	                </div>
	            </div>
	    </form>
	
	    <form action="login" method="get">
	        <div class="row align-items-md-stretch">
	            <div class="col-md-6">
	                <div class="h-100 p-5 text-white bg-dark rounded-3 borderradius10">
	                    <h2>Send Items</h2>
	                    <div class="frontpagediv">
	                        <p>Send your items to your desired destination across the globe with the person you choose. </p>
	                    </div>
	                    <button class="btn btn-warning btnhover" type="submit">Send</button>
	                </div>
	            </div>
	
	            <div class="col-md-6">
	                <div class="h-100 p-5 bg-dark text-white border rounded-3 borderradius10">
	                    <h2>Deliver Items</h2>
	                    <div class="frontpagediv">
	                        <p>Deliver the items you want to the destination you are headed. Earn and make others happy.</p>
	                    </div>
	                    <button class="btn btn-warning btnhover" type="submit">Deliver</button>
	                </div>
	            </div>
	        </div>
	        <br>
	    </form>
	</body>

</html>
