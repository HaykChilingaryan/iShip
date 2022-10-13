<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Model.User"%>
    
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
	
	    <link rel="stylesheet" href="css/style.css" type="text/css">
	    <meta charset="UTF-8">
	    <title>iShip</title>
	</head>
	
	<body class="bg-warning">
	    <!--Naviggation Bar -->
	    <% User user=(User)session.getAttribute("user"); String user_email=user.getEmail(); String
	        user_firstName=user.getFirstName(); String user_lastName=user.getLastName(); String user_fullName=user_firstName
	        +" " + user_lastName;
	      	java.sql.Date user_regDate = user.getRegistrationDate();
	      	long user_phoneNumber = user.getPhoneNumber();
	      	int inProgressOrders = (Integer)session.getAttribute("inProgressOrders"); %>
	        <nav class="navbar navbar-expand-lg bg-black">
	            <div class="container-fluid">
	                <a class="navbar-brand text-yellow ahover" href="myProfile">
	                    <%=user_firstName %>
	                </a>
	                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
	                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	                    <span class="navbar-toggler-icon"></span>
	                </button>
	                <div class="collapse navbar-collapse" id="navbarNav">
	                    <ul class="nav nav-tabs">
	
	
	
	                        <li class="nav-item ">
	                            <a class="text-yellow ahover nav-link dropdown-toggle" href="#" id="navbarDropdown1"
	                                role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                                My Orders&nbsp;<span class="badge badge-danger">
	                                    <%=inProgressOrders %>
	                                </span>
	                            </a>
	                            <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
	
	                                <a class="dropdown-item" href="myOrders">OngoingOrders</a>
	                                <div class="dropdown-divider"></div>
	                                <a class="dropdown-item" href="myCancelledOrders">Cancelled Orders</a>
	                            </div>
	                        </li>
	
	                        <li class="nav-item ">
	                            <a class="nav-link text-yellow ahover" href="myDeliveries">My Deliveries</a>
	                        </li>
	
	                        <li class=" nav-item dropdown floatright">
	                            <a class="text-yellow ahover nav-link dropdown-toggle" href="#" id="navbarDropdown"
	                                role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
	                                Settings
	                            </a>
	                            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
	
	                                <a class="dropdown-item" href="accountInfo">Account Settings</a>
	                                <div class="dropdown-divider"></div>
	                                <a class="dropdown-item" href="logout">Log Out</a>
	                            </div>
	                        </li>
	
	                    </ul>
	                </div>
	        </nav>
	
	        <div class="container mt-5">
	            <div class="row">
	                <div class="col-lg-4 pb-5">
	                    <!-- Account Sidebar-->
	                    <div class="author-card pb-3">
	                        <div class="author-card-cover">
	                            <a class="btn btn-style-1 btn-white btn-sm" href="#" data-toggle="tooltip" title=""
	                                data-original-title="Y"></a>
	                        </div>
	                        <div class="author-card-profile">
	
	                            <div class="author-card-details">
	                            </div>
	                        </div>
	                    </div>
	                    <div class=" wizard">
	                        <nav class=" borderRadius10 list-group list-group-flush">
	                            <a class="list-group-item bg-dark text-white" href="accountInfo"><i
	                                    class="fe-icon-user text-muted"></i>Profile Settings</a>
	                            <a class="awhover btnhover  list-group-item" href="myOrders">
	                                <div class="d-flex justify-content-between align-items-center">
	                                    <div><i class="fe-icon-shopping-bag mr-1 text-muted"></i>
	                                        <div class=" d-inline-block awhover font-weight-medium">Orders List</div>
	                                    </div>&nbsp;<span class="badge badge-danger">
	                                        <%=inProgressOrders %>
	                                    </span>
	                                </div>
	                            </a>
	
	                        </nav>
	                    </div>
	                </div>
	                <!-- Profile Settings-->
	                <div class="col-lg-8 pb-5">
	                    <form class="row" action="updateAccountInfo" method="post">
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label for="account-fn">First Name</label>
	                                <input class="form-control " type="text" id="account-fn" value=<%=user_firstName%>
	                                required="">
	                            </div>
	                        </div>
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label for="account-ln">Last Name</label>
	                                <input class="form-control " type="text" id="account-ln" value=<%=user_lastName%>
	                                required="">
	                            </div>
	                        </div>
	                        <div class="col-md-6">
	                            <div class="form-group ">
	                                <label for="account-email">E-mail Address</label>
	                                <input class="form-control " type="email" id="account-email" value=<%=user_email%>
	                                disabled>
	                            </div>
	                        </div>
	
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label for="account-phone">Account Creation Date</label>
	                                <input class="form-control " type="number" id="account-regDate" value=<%=user_regDate%>
	                                required="" disabled>
	                            </div>
	                        </div>
	
	
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label for="account-pass">New Password</label>
	                                <input class="form-control " type="password" id="account-pass" name="newPassword">
	                            </div>
	                        </div>
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label for="account-confirm-pass">Confirm Password</label>
	                                <input class="form-control " type="password" id="account-confirm-pass"
	                                    name="confirmNewPassword">
	                            </div>
	                        </div>
	                        <div class="col-md-6">
	                            <div class="form-group">
	                                <label for="account-phone">Phone Number</label>
	                                <input class="form-control " type="number" id="account-phone" name="newPhoneNumber"
	                                    value=<%=user_phoneNumber%> required="">
	                            </div>
	                        </div>
	                        <div class="col-12">
	                            <hr class="mt-2 mb-3">
	                            <div class="d-flex flex-wrap justify-content-between align-items-center ">
	                                <div class="custom-control custom-checkbox d-block">
	
	                                </div>
	
	                                <button class="btn btnhovergreen  btn-style-1 bg-dark text-white" type="button"
	                                    data-toggle="modal" data-target="#updateAccount">Update Profile</button>
	                            </div>
	                        </div>
	                        <!-- MODAL -->
	                        <div class="modal fade" id="updateAccount" tabindex="-1" role="dialog"
	                            aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
	                            <div class="modal-dialog modal-dialog-centered" role="document">
	                                <div class="modal-content">
	                                    <div class="modal-header">
	                                        <h5 class="modal-title" id="exampleModalLongTitle">Update Account</h5>
	                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	                                            <span aria-hidden="true">&times;</span>
	                                        </button>
	                                    </div>
	                                    <div class="modal-body">
	                                        If the passwords you entered match, you'll be redirected to the login page<br>
	                                        If your passwords don't much no changes will be applied
	                                    </div>
	                                    <div class="modal-footer">
	                                        <button type="submit" class="btn btn-warning">Ok</button>
	                                        <button type="button" class="btn btn-warning"
	                                            data-dismiss="modal">Cancel</button>
	
	                                    </div>
	                                </div>
	                            </div>
	                        </div>
	                    </form>
	                </div>
	            </div>
	        </div>
	</body>

</html>