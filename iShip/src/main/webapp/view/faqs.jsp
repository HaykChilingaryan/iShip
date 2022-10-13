<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

	<head>
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css" 
	    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	    <!-- THIS LINE -->
	    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
	    <script src="http://netdna.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	    <link rel="stylesheet" href="css/faqs.css" type="text/css">
	    <meta charset="UTF-8">
	    <title>iShip</title>
	</head>
	
	<body class="main">
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
	                    <li class="nav-item lihover">
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
	
	    <div class="height200px bg-dark">
	        <div class="height40px"></div>
	        <h2 class="text-align text-white">How can we help you?</h2>
	
	        <div class="input-group width40 center">
	            <input placeHolder="Search" type="search" id="form1" class=" form-control" />
	            <button type="button" class="btn btn-warning">
	                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search"
	                    viewBox="0 0 16 16">
	                    <path
	                        d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001c.03.04.062.078.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1.007 1.007 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0z">
	                    </path>
	                </svg>
	            </button>
	        </div>
	    </div>
	
	    <div class="text-align">
	        <h3>Frequently Asked Question</h3>
	
	        <div class="accordion width90percent borderRadius10" id="accordionExample">
	            <div class="card">
	                <div class="card-header bg-dark" id="headingOne">
	                    <h2 class="mb-0">
	                        <button class="btn achover text-white btn-warning btn-block  text-left" type="button"
	                            data-toggle="collapse" data-target="#collapseOne" aria-expanded="true"
	                            aria-controls="collapseOne">
	                            <img class="rounded-lg-3 sizedown floatleft" src="images/airplane.png" alt="" width="720">
	                            &nbsp;&nbsp;Question
	                        </button>
	                    </h2>
	                </div>
	
	                <div id="collapseOne" class="collapse bg-dark" aria-labelledby="headingOne"
	                    data-parent="#accordionExample">
	                    <div class="card-body bg-dark text-white">
	                        Some placeholder content for the first accordion panel. This panel is shown by default, thanks
	                        to the <code>.show</code> class.
	                    </div>
	                </div>
	            </div>
	
	            <div class="card">
	                <div class="card-header bg-dark" id="headingTwo">
	                    <h2 class="mb-0">
	                        <button class="btn achover text-white btn-warning btn-block text-left collapsed" type="button"
	                            data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false"
	                            aria-controls="collapseTwo">
	                            <img class="rounded-lg-3 sizedown floatleft" src="images/airplane.png" alt="" width="720">
	                            &nbsp;&nbsp;Question
	                        </button>
	                    </h2>
	                </div>
	                <div id="collapseTwo" class="collapse bg-dark" aria-labelledby="headingTwo"
	                    data-parent="#accordionExample">
	                    <div class="card-body bg-dark text-white">
	                        Some placeholder content for the second accordion panel. This panel is hidden by default.
	                    </div>
	                </div>
	            </div>
	
	            <div class="card">
	                <div class="card-header bg-dark" id="headingThree">
	                    <h2 class="mb-0">
	                        <button class="btn achover text-white btn-warning btn-block text-left collapsed" type="button"
	                            data-toggle="collapse" data-target="#collapseThree" aria-expanded="false"
	                            aria-controls="collapseThree">
	                            <img class="rounded-lg-3 sizedown floatleft" src="images/airplane.png" alt="" width="720">
	                            &nbsp;&nbsp;Question
	                        </button>
	                    </h2>
	                </div>
	                <div id="collapseThree" class="collapse bg-dark" aria-labelledby="headingThree"
	                    data-parent="#accordionExample">
	                    <div class="card-body bg-dark text-white">
	                        And lastly, the placeholder content for the third and final accordion panel. This panel is
	                        hidden by default.
	                    </div>
	                </div>
	            </div>
	            <div class="card">
	                <div class="card-header bg-dark" id="headingTwo">
	                    <h2 class="mb-0">
	                        <button class="btn achover text-white btn-warning btn-block text-left collapsed" type="button"
	                            data-toggle="collapse" data-target="#collapseFour" aria-expanded="false"
	                            aria-controls="collapseTwo">
	                            <img class="rounded-lg-3 sizedown floatleft" src="images/airplane.png" alt="" width="720">
	                            &nbsp;&nbsp;Question
	                        </button>
	                    </h2>
	                </div>
	                <div id="collapseFour" class="collapse bg-dark" aria-labelledby="headingTwo"
	                    data-parent="#accordionExample">
	                    <div class="card-body bg-dark text-white">
	                        Some placeholder content for the second accordion panel. This panel is hidden by default.
	                    </div>
	                </div>
	            </div>
	
	
	        </div>
	    </div>
	
	</body>

</html>