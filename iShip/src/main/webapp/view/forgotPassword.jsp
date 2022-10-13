<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css"
            integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/login.css" type="text/css">
        <meta charset="UTF-8">
        <title>iShip</title>
    </head>

    <body>
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
                            <a class="nav-link text-yellow " href="contactUs">Contact</a>
                        </li>
                    </ul>

                </div>
                <div class="text-end">
                    <form action="login" method="get" class="floatleft">
                        <button type="submit" class="btn btn-outline-light me-2">Login</button>&nbsp;
                    </form>

                    <form action="register" method="get" class="floatleft">
                        <button type="submit" class="btn bg-warning btnhover">Sign-up</button>
                    </form>
                </div>
            </div>
        </nav>
        <div class="main">
            <p class="sign" align="center">Verification</p>
            <form action="forgotPassword" method="post" class="form1">
                <label for="exampleInputEmail1" class="form-label">Email address</label>
                <input type="email" class="email" id="exampleInputEmail1" aria-describedby="emailHelp" name="email"
                    required>

                <button class="submit" type="submit">Send Verification Email </button>
            </form>
        </div>
    </body>

</html>