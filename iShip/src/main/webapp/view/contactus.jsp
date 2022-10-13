<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>

	<head>
	    <link rel="stylesheet" href="css/contact.css" type="text/css">
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css"
	        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	
	    <meta charset="UTF-8">
	    <title>iShip</title>
	
	</head>
	
	<body class="bg-warning">
	
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
	                    <li class="nav-item lihover">
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
	
	    <section class="ftco-section">
	        <div class="container text-white">
	            <div class="row justify-content-center">
	                <div class=" col-md-6 text-center mb-5">
	                    <h2 class="  heading-section">Contact Us</h2>
	                </div>
	            </div>
	            <div class="row bg-black justify-content-center">
	                <div class=" col-lg-10 col-md-12">
	                    <div class="wrapper">
	                        <div class="row no-gutters">
	                            <div class="col-md-7 d-flex align-items-stretch">
	                                <div class="contact-wrap w-100 p-md-5 p-4">
	                                    <h3 class="mb-4 text-yelloww">Get in touch</h3>
	                                    <div id="form-message-warning" class="mb-4"></div>
	
	                                    <form method="POST" id="contactUs" name="contactForm" novalidate="novalidate">
	                                        <div class="row">
	                                            <div class="col-md-6">
	                                                <div class="form-group">
	                                                    <input type="text" class="form-control" name="name" id="name"
	                                                        placeholder="Name">
	                                                </div>
	                                            </div>
	                                            <div class="col-md-6">
	                                                <div class="form-group">
	                                                    <input type="email" class="form-control" name="email" id="email"
	                                                        placeholder="Email">
	                                                </div>
	                                            </div>
	                                            <div class="col-md-12">
	                                                <div class="form-group">
	                                                    <input type="text" class="form-control" name="subject" id="subject"
	                                                        placeholder="Subject">
	                                                </div>
	                                            </div>
	                                            <div class="col-md-12">
	                                                <div class="form-group">
	                                                    <textarea name="message" class="form-control" id="message" cols="30"
	                                                        rows="7" placeholder="Message"></textarea>
	                                                </div>
	                                            </div>
	                                            <div class="col-md-12">
	                                                <div class="form-group">
	                                                    <input type="submit" value="Send Message" class="btn bg-warning">
	                                                    <div class="submitting"></div>
	                                                </div>
	                                            </div>
	                                        </div>
	                                    </form>
	                                </div>
	                            </div>
	                            <div class="col-md-5 d-flex align-items-stretch">
	                                <div class="info-wrap bg-dark w-100 p-lg-5 p-4">
	                                    <h3 class="mb-4 mt-md-4">Contact us</h3>
	                                    <div class="dbox w-100 d-flex align-items-start">
	                                        <div class="icon d-flex align-items-center justify-content-center">
	                                            <span class="fa fa-map-marker"></span>
	                                        </div>
	                                        <div class="text pl-3">
	                                            <span class="text-yellow">Address:</span>
	                                            <p class="text-white"> 198 West 21th Street, Suite 721 New York NY 10016</p>
	                                        </div>
	                                    </div>
	                                    <div class="dbox w-100 d-flex align-items-center">
	                                        <div class="icon d-flex align-items-center justify-content-center">
	                                            <span class="fa fa-phone"></span>
	                                        </div>
	                                        <div class=" text-yellow text pl-3">
	                                            <p><span>Phone:</span> <a class="text-white"
	                                                    href="tel://1234567920">+1(818)858-9476</a></p>
	                                        </div>
	                                    </div>
	                                    <div class="dbox w-100 d-flex align-items-center">
	                                        <div class="icon d-flex align-items-center justify-content-center">
	                                            <span class="fa fa-paper-plane"></span>
	                                        </div>
	                                        <div class="text pl-3 text-yellow">
	                                            <p><span>Email:</span> <a class="text-white"
	                                                    href="mailto:info@yoursite.com">info@iship.com</a></p>
	                                        </div>
	                                    </div>
	
	                                </div>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </section>
</body>

</html>