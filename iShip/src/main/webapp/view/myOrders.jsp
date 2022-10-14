<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Model.User"%>
<%@ page import="Model.Order"%>
<%@ page import="Model.Shipment"%>
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
        <link rel="stylesheet" href="css/myOrders.css" type="text/css">
        <meta charset="UTF-8">
        <title>Insert title here</title>
    </head>

    <body>
        <!--Naviggation Bar -->
        <% User user=(User)session.getAttribute("user"); String user_firstName=user.getFirstName(); int
            inProgressOrders=(Integer)session.getAttribute("inProgressOrders"); %>
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
                </div>
            </nav>


            <div class="center padding-top100 height200px">
                <h1>Order list</h1>

            </div>


            <button class=" ml10 btn bg-black text-white btnhover floatleft" data-toggle="modal" data-target="#NewOrder">
                Create New Order
            </button>
            &nbsp;



            <div class="height20px"></div>



            <table class="table table-hover">
                <thead>
                    <tr class="bg-black text-white">
                        <th scope="col">#</th>
                        <th scope="col">Departure Location</th>
                        <th scope="col">Departure Date</th>
                        <th scope="col">Arrival Location</th>
                        <th scope="col">Arrival Date</th>
                        <th scope="col">Weight</th>
                        <th scope="col">Price</th>
                        <th scope="col">Status</th>
                        <th scope="col">Order Date</th>
                        <th scope="col">Shipper</th>
                        <th scope="col">Cancel</th>

                    </tr>
                </thead>
                <tbody>


                    <c:forEach var="order" items="${currentUserOrders}">

                        <tr class="bg-dark text-white">
                            <td>${order.getOrderId()}</td>
                            <td>${order.getShipmentDepartureLocation()}</td>
                            <td>${order.getShipmentDepartureDate()}</td>
                            <td>${order.getShipmentArrivalLocation()}</td>
                            <td>${order.getShipmentArrivalDate()}</td>
                            <td>${order.getOrderWeight()}kg</td>
                            <td>$ ${order.getOrderPrice()}</td>
                            <td>
                                <c:choose>

                                    <c:when test="${order.getOrderStatus() == 'In Progress'}">
                                        <div class="center">
                                            <span class=" badge badge-info">${order.getOrderStatus()}</span>
                                        </div>
                                    </c:when>

                                    <c:when test="${order.getOrderStatus() == 'Cancelled'}">
                                        <div class="center">
                                            <span class=" badge badge-secondary">${order.getOrderStatus()}</span>
                                        </div>
                                    </c:when>

                                    <c:otherwise>
                                        <div class="center">
                                            <span class=" badge badge-success">${order.getOrderStatus()}</span>
                                        </div>
                                    </c:otherwise>
                                </c:choose>

                            </td>
                            <td>${order.getOrderDate()}</td>

                            <td>
                                <button class="btn btn-warning" data-toggle="modal"
                                    data-target="#shipper${order.getShipperId()}">
                                    Shipper Info
                                </button>
                            </td>

                            <td>
                                <button class="btn btn-warning" data-toggle="modal"
                                    data-target="#cancelOrder${order.getOrderId() }">
                                    Cancel
                                </button>

                            </td>


                        </tr>

                        <form action="cancelOrder?orderId=<c:out value = '${order.getOrderId() }' />" method="post">

                            <div class="modal fade" id="cancelOrder${order.getOrderId() }" tabindex="-1" role="dialog"
                                aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered" role="document">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLongTitle">Cancel Order</h5>
                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                <span aria-hidden="true">&times;</span>
                                            </button>
                                        </div>
                                        <div class="modal-body">
                                            Are you sure you want to cancel the order
                                        </div>
                                        <div class="modal-footer">
                                            <button type="submit" class="btn btn-warning">Yes</button>
                                            <button type="button" class="btn btn-warning" data-dismiss="modal">No</button>

                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <!--Shipper Modal -->
                        <div class="modal fade" id="shipper${order.getShipperId()}" tabindex="-1" role="dialog"
                            aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                            <div class="modal-dialog modal-dialog-centered" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="exampleModalLongTitle">${order.getShipperName()}</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <label>Phone Number</label>
                                        <input class="form-control  " type="text"
                                            placeholder="${order.getShipperPhoneNumber()}" disabled>
                                        <div class="height20px"></div>
                                        <label>Email</label>
                                        <input class="form-control" type="number" placeholder="${order.getShipperEmail()}"
                                            disabled>
                                        <div class="height20px"></div>
                                        <label>Age</label>
                                        <input class="form-control" type="number" placeholder="${order.getShipperAge()}"
                                            disabled>

                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-warning" data-dismiss="modal">Close</button>
                                    </div>
                                </div>
                            </div>
                        </div>



                    </c:forEach>


                </tbody>
            </table>


            <form action="getOrderDetails" method="post">
                <div class="modal fade" id="NewOrder" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
                    aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLongTitle">New Order</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <label class="form-label">From*</label>
                                <input class="form-control  " type="text" name="departureLocation" required>
                                <div class="height20px"></div>

                                <label class="form-label">To*</label>
                                <input class="form-control" type="text" name="arrivalLocation" required>
                                <div class="height20px"></div>

                                <label class="form-label  ">Between Dates*</label>
                                <input class="form-control  " type="date" placeholder="Start Date" name="departureDate"
                                    required>
                                <div class="height20px"></div>
                                <input class="form-control  " type="date" placeholder="End Date" name="arrivalDate"
                                    required>
                                <div class="height20px"></div>

                                <label class="form-label">Weight(kg)</label>
                                <input class="form-control " type="number" name="weight">
                                <div class="height20px"></div>

                            </div>
                            <div class="modal-footer">
                                <button type="button" class="btn btn-warning" data-dismiss="modal">Cancel</button>
                                <button type="submit" class="btn btn-warning">Find</button>

                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <!-- Modal For Choice -->
            <div class="modal fade " id="chooseDeliveryOption" tabindex="-1" role="dialog"
                aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
                <div class="modal-dialog modal-lg modal-dialog-centered " role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLongTitle">${message}</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">#</th>
                                        <th scope="col">Departure Date</th>
                                        <th scope="col">Arrival Date</th>
                                        <th scope="col">Max Weight</th>
                                        <th scope="col">Price Per Kg</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="shipment" items="${availableShipments }">
                                        <tr>
                                            <td> ${shipment.getShipmentId() }</td>
                                            <td> ${shipment.getDepartureDate() }</td>
                                            <td> ${shipment.getArrivalDate() }</td>
                                            <td> ${shipment.getMaxWeight() }</td>
                                            <td> ${shipment.getPricePerKg() }</td>
                                            <td>
                                                <form
                                                    action="placeOrder?shipmentId=<c:out value = '${order.getshipmentId() }'/>"
                                                    method="post">
                                                    <button type="button" class="btn btn-warning">Choose</button>
                                                </form>
                                            </td>
                                        </tr>

                                        <!-- Modal For Creation -->

                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <div class="modal-footer">
                            <button type="button" class="btn btn-warning" data-dismiss="modal">Cancel</button>
                        </div>
                    </div>
                </div>
            </div>
    </body>

</html>