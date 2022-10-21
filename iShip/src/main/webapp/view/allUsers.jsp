<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="Model.User"%>
    <%@ page import="Model.City"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

        <link rel="stylesheet" href="css/myDeliveries.css" type="text/css">
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>

    <body>
        <!--Naviggation Bar -->
        <% 
        	User user=(User)session.getAttribute("user"); String user_firstName=user.getFirstName();
        	int inProgressOrders=(Integer)session.getAttribute("inProgressOrders");
        	String userType = user.getType();
       	%>
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
                                <a class="nav-link text-yellow ahover" href="allOrders">All Orders</a>
                            </li>
                            
                            <li class="nav-item ">
                                <a class="nav-link text-yellow ahover" href="allShipments">All Shipments</a>
                            </li>
                            
                            <li class="nav-item ">
                                <a class="nav-link text-yellow ahover" href="allUsers">All Users</a>
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
                </div>
            </nav>
            
            <div class="center padding-top100 height200px">
                <h1>All Users</h1>

            </div>


            


            <div class="height20px"></div>
            
 
            <table class="table table-hover">
                <thead>
                    <tr class="bg-black text-white">
                        <th scope="col">#</th>

                        <th scope="col">Name</th>
                        <th scope="col">Age</th>
                        <th scope="col">Phone Number</th>
                        <th scope="col">Email</th>
                        <th scope="col">Password</th>
                        <th scope="col">Registration Date</th>
                        <th scope="col">Update</th>
                        <th scope="col">Delete</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${allUsers}" var="user">
                        <tr class="bg-dark text-white">
							<td>${user.getUserId() }</td>
                            <td>${user.getName() }</td>
                            <td>${user.getAge() }</td>
                            <td>${user.getPhoneNumber() }</td>
                            <td>${user.getEmail() }</td>
                            <td>${user.getPassword() }</td>
                            <td>${user.getRegistrationDate() }</td>

                            <td>
                                <button class="btn btn-warning" data-toggle="modal"
                                    data-target="#delete${user.getUserId() }">
                                    Delete
                                </button>
                                <form action="deleteUser?userId=<c:out value = '${user.getUserId() }' />"
                                    method="post">

                                    <div class="modal fade" id="delete${user.getUserId() }" tabindex="-1"
                                        role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title text-dark" id="exampleModalLongTitle">Delete
                                                        User</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body text-dark">
                                                    Are you sure you want to delete this User
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-warning">Yes</button>
                                                    <button type="button" class="btn btn-warning"
                                                        data-dismiss="modal">No</button>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </td>
                            <td>
                            	<button class="btn btn-warning" data-toggle="modal"
                                    data-target="#editUser${user.getUserId() }">
                                    Edit
                                </button>
                                
                                <form action="editUser?userId=<c:out value = '${user.getUserId() }' />"
                                    method="post">

                                    <div class="modal fade" id="editUser${user.getUserId() }" tabindex="-1"
                                        role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title text-dark" id="exampleModalLongTitle">Edit
                                                        User</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body text-dark">
                                                    <label for="account-fn">First Name</label>
	                                				<input class="form-control " type="text" id="account-fn" name = "firstName" value='${user.getFirstName()}' required>
	                                				<div class = "height20px"></div>
	                                				<label for="account-fn">Last Name</label>
	                                				<input class="form-control " type="text" id="account-fn" name = "lastName" value='${user.getLastName()}' required>
	                                				<div class = "height20px"></div>
	                                				<label for="account-fn">Phone Number</label>
	                                				<input class="form-control " type="number" id="account-fn" name = "phoneNumber" value='${user.getPhoneNumber()}' required>
	                                				<div class = "height20px"></div>
	                                				<label for="account-fn">Age</label>
	                                				<input class="form-control " type="number" id="account-fn" name = "age" value='${user.getAge()}' required>
	                                				<div class = "height20px"></div>
	                                				<label for="account-fn">Email</label>
	                                				<input class="form-control " type="text" id="account-fn" name = "email" value='${user.getEmail()}' required>
	                                				<div class = "height20px"></div>
	                                				<label for="account-fn">Password</label>
	                                				<input class="form-control " type="text" id="account-fn" name = "password" value='${user.getPassword()}' required>
	                                				<div class = "height20px"></div>
                                                </div>
                                                <div class="modal-footer">
                                                    <button type="submit" class="btn btn-warning">Yes</button>
                                                    <button type="button" class="btn btn-warning"
                                                        data-dismiss="modal">No</button>

                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </td>

                        </tr>

                    </c:forEach>
                </tbody>
            </table>
</body>
</html>