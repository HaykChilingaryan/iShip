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
                        <%
                        	if(userType.equals("Admin")){
                        %>
                        	<li class="nav-item ">
                                <a class="nav-link text-yellow ahover" href="allOrders">All Orders</a>
                            </li>
                            
                            <li class="nav-item ">
                                <a class="nav-link text-yellow ahover" href="allShipments">All Shipments</a>
                            </li>
                            
                            <li class="nav-item ">
                                <a class="nav-link text-yellow ahover" href="allUsers">All Users</a>
                            </li>
                        		
                        <%
                        	}else{
                        %>
                            <li class="nav-item ">
                                <a class="text-yellow ahover nav-link dropdown-toggle" href="#" id="navbarDropdown1"
                                    role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    My Orders&nbsp;<span class="badge badge-danger">
                                        <%=inProgressOrders %>
                                    </span>
                                </a>
                                <div class="dropdown-menu" aria-labelledby="navbarDropdown1">

                                    <a class="dropdown-item" href="myOrders">Ongoing Orders</a>
                                    <div class="dropdown-divider"></div>
                                    <a class="dropdown-item" href="myCancelledOrders">Cancelled Orders</a>
                                </div>
                            </li>

                            <li class="nav-item ">
                                <a class="nav-link text-yellow ahover" href="myDeliveries">My Deliveries</a>
                            </li>

                            
                            <%
                        		}
                        	%>
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
                <h1>Delivery list</h1>
            </div>


            &nbsp;&nbsp;
			<%
                    	if(userType.equals("User")){
            %>
            <button class="btn bg-black text-white btnhover" data-toggle="modal" data-target="#CreateDelivery">
                Set New Delivery Option
            </button>
            <%} %>

            <!-- Modal -->
            <form action="createDelivery" method="post">
                <div class="modal fade" id="CreateDelivery" tabindex="-1" role="dialog"
                    aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">New Delivery</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <label class="form-label">From*</label>
                                <select class="form-control" type="text" name="departureLocation" placeholder = "Select a City" id="depLocs">
                   				  <option  disabled selected hidden>Choose City</option>
                                
								  <c:forEach items = "${allCities }" var = "city">
								  		<option value="${city.toString() }">${city.toString() }</option>
								  </c:forEach>
								</select>
								
								
                                <!--<input class="form-control  " type="text" name="departureLocation" required>  -->
                                <div class="height10px"></div>

                                <label class="form-label">To*</label>
                                <select class="form-control" type="text" name="arrivalLocation" id="depLocs">
                                  <option  disabled selected hidden>Choose City</option>
                                
								  <c:forEach items = "${allCities }" var = "city">
								  		<option value="${city.toString() }">${city.toString() }</option>
								  </c:forEach>
								</select>
                                <!--  <input class="form-control  "  name="arrivalLocation" required>-->
                                <div class="height10px"></div>

                                <label class="form-label">Departure Date*</label>
                                <input class="form-control" type="date" name="departureDate" required>
                                <div class="height10px"></div>

                                <label class="form-label">Arrival Date*</label>
                                <input class="form-control" type="date" name="arrivalDate" required>
                                <div class="height10px"></div>

                                <label class="form-label">Max Weight (kg)*</label>
                                <input class="form-control" type="number" name="maxWeight" required>
                                <div class="height10px"></div>

                                <label class="form-label">Price Per Kg ($)*</label>
                                <input class="form-control" type="number" name="pricePerKg" required>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-warning" data-dismiss="modal">Cancel</button>

                                <button type="submit" class="btn btn-warning">Create</button>


                            </div>
                        </div>
                    </div>
                </div>
            </form>



            <div class="height20px"></div>
            <table class="table table-hover">
                <thead>
                    <tr class="bg-black text-white">
                        <th scope="col">#</th>

                        <th scope="col">Departure Location</th>
                        <th scope="col">Departure Date</th>
                        <th scope="col">Arrival Location</th>
                        <th scope="col">Arrival Date</th>
                        <th scope="col">Max Weight</th>
                        <th scope="col">Price/KG</th>
                        <th scope="col">Status</th>
                        <%
							if(userType.equals("Admin")){							
						%>
							<th scope="col">Shipper</th>
								
						<%	
							}
                        %>
                        <th scope="col">Sender</th>
                        <th scope="col">Cancel</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${currentUserShipments}" var="shipment">
                        <tr class="bg-dark text-white">

                            <td>${shipment.getShipmentId() }</td>
                            <td>${shipment.getDepartureLocation() }</td>
                            <td>${shipment.getDepartureDate() }</td>
                            <td>${shipment.getArrivalLocation() }</td>
                            <td>${shipment.getArrivalDate() }</td>
                            <td>${shipment.getMaxWeight() }kg</td>
                            <td>$${shipment.getPricePerKg() }</td>
                            <c:choose>

                                <c:when test="${shipment.getSenderById(shipment.getSenderId()) == null }">
                                    <td>
                                        <div class="center">
                                            <span class=" badge badge-success">Available</span>

                                        </div>
                                    </td>
                                </c:when>

                                <c:otherwise>
                                    <td>
                                        <div class="center">
                                            <span class=" badge badge-danger">Booked</span>

                                        </div>
                                    </td>
                                </c:otherwise>
                            </c:choose>

                            <c:choose>
                            
                                <c:when test="${shipment.getSenderById(shipment.getSenderId()) == null }">
                                    <td>
                                        <button class="btn btn-secondary" disabled>
                                            Sender Info
                                        </button>
                                    </td>
                                </c:when>
                                <c:otherwise>
                                    <td>
                                        <button class="btn btn-warning" data-toggle="modal"
                                            data-target="#Sender${shipment.getSenderId() } ">
                                            Sender Info
                                        </button>
                                    </td>
                                </c:otherwise>
                                
                                
                            </c:choose>
                            <div class="modal fade" id="Sender${shipment.getSenderId() }" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle">${shipment.getSenderName() }</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            
                                            <div class="height20px"></div>
                                            <label class = "form-label">Phone Number</label>
                                            <input class="form-control  " type="text"
                                         placeholder="${shipment.getSenderPhoneNumber() }" disabled>
                                            <div class="height20px"></div>
                                            <label class = "form-label">Email</label>
                                            <input class="form-control" type="number"
                                                placeholder="${shipment.getSenderEmail() }" disabled>
                                            <div class="height20px"></div>
                                            <label class = "form-label">Age</label>
                                            <input class="form-control" type="number"
                                                placeholder="${shipment.getSenderAge() }" disabled>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-warning"
                                                data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <%
								if(userType.equals("Admin")){							
							%>
							<td>
                                <button class="btn btn-warning" data-toggle="modal"
                                    data-target="#Shipper${shipment.getUserId()}">
                                    Shipper Info
                                </button>
                                <div class="modal fade" id="Shipper${shipment.getUserId() }" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title text-dark" id="exampleModalLongTitle">${shipment.getShipperName() }</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            
                                            <div class="height20px"></div>
                                            <label class = "form-label text-dark">Phone Number</label>
                                            <input class="form-control  " type="text"
                                         		placeholder="${shipment.getShipperPhoneNumber() }" disabled>
                                            <div class="height20px"></div>
                                            <label class = "form-label text-dark">Email</label>
                                            <input class="form-control" type="number"
                                                placeholder="${shipment.getShipperEmail() }" disabled>
                                            <div class="height20px"></div>
                                            <label class = "form-label text-dark">Age</label>
                                            <input class="form-control" type="number"
                                                placeholder="${shipment.getShipperAge() }" disabled>

                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-warning"
                                                data-dismiss="modal">Close</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                                
                            
                            </td>
								
							<%} %>


                            


                            <td>
                                <button class="btn btn-warning" data-toggle="modal"
                                    data-target="#cancelOrder${shipment.shipmentId }">
                                    Delete
                                </button>
                                <form action="deleteShipment?shipmentId=<c:out value = '${shipment.shipmentId }' />"
                                    method="post">

                                    <div class="modal fade" id="cancelOrder${shipment.shipmentId }" tabindex="-1"
                                        role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title text-dark" id="exampleModalLongTitle">Delete
                                                        Shipment</h5>
                                                    <button type="button" class="close" data-dismiss="modal"
                                                        aria-label="Close">
                                                        <span aria-hidden="true">&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body text-dark">
                                                    Are you sure you want to delete this shipment
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