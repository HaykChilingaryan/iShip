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

                                <a class="dropdown-item" href="myOrders">Ongoing Orders</a>
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


        <div class="center padding-top100 height200px">
            <h1>Canceled Order List</h1>
        </div>

        <div class="height20px"></div>



        <table class="table table-hover">
            <thead>
                <tr class="bg-black text-white">
                    <th scope="col">#</th>
                    <th scope="col">Weight</th>
                    <th scope="col">Price</th>
                    <th scope="col">Status</th>
                    <th scope="col">Order Date</th>

                </tr>
            </thead>
            <tbody>


                <c:forEach var="order" items="${currentUserCanceledOrders}">

                    <tr class="bg-dark text-white">
                        <td>${order.getOrderId()}</td>
                        <td>${order.getOrderWeight()}kg</td>
                        <td>$ ${order.getOrderPrice()}</td>
                        <td>
                            <span class=" badge badge-secondary">${order.getOrderStatus()}</span>
                        </td>
                        <td>${order.getOrderDate()}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

</body>

</html>