<!DOCTYPE html>
<%@ page import="Model.User" %>
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
                </div>
            </nav>

            <div class="container my-5 bg-black text-white">
                <div class="row p-4 pb-0 pe-lg-0 pt-lg-5 align-items-center rounded-3 border shadow-lg">
                    <div class="col-lg-7 p-3 p-lg-5 pt-lg-3">
                        <h1 class="display-4 fw-bold lh-1">You rest, we deliver</h1>
                        <p class="lead">This is the new opportunity you were waiting for.
                            Send and track your items throughout the whole process of delivery.
                            Choose who delivers your items from the list, and pick the most convenient dates.
                            We Guarantee that your package will deliver on time.</p>
                        <div class="d-grid gap-2 d-md-flex justify-content-md-start mb-4 mb-lg-3">
                            <a class="text-dark btn btn-warning btn-lg px-4 me-md-2 fw-bold" href="myOrders">Send</a>
                            <br>
                            <a class="text-dark btn btn-warning btn-lg px-4 btnmarginleft" href="myDeliveries">Register
                                a Delivery</a>
                        </div>
                    </div>

                    <img class="rounded-lg-3 sizedown" src="images/airplane.png" alt="" width="720">

                </div>
            </div>
    </body>

</html>