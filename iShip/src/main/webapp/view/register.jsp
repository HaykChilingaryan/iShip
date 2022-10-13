<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/css/bootstrap.min.css"
       	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link rel = "stylesheet" href = "css/register.css" type = "text/css">
	<meta charset="UTF-8">
	<title>iShip</title>
</head>
<body>
	<!--Naviggation Bar -->
	<nav class="navbar navbar-expand-lg bg-black">
	  <div class="container-fluid">
	    <a class="navbar-brand text-yellow " href="home">iShip</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarNav">
	      <ul class="nav nav-tabs">
			   <li class="nav-item ">
			    <a class="nav-link text-yellow " href="faqs">FAQs</a>
			  </li>
			  <li class="nav-item ">
			    <a  class="nav-link text-yellow " href="contactUs">Contact</a>
			  </li>
		  </ul>
		  
	    </div>
	    <div class="text-end">
	    <form action = "login" method = "get" class = "floatleft">
	         <button type="submit" class="btn btn-outline-light me-2">Login</button>&nbsp;
	    </form>
	    
	    <form action = "register" method = "get" class = "floatleft">
	    	 <button type="submit" class="btn bg-warning btnhover">Sign-up</button>
	    </form >
        </div>
	  </div>
	</nav>
	
	<div class="main">
      <% 
			String message = (String)request.getAttribute("message");
			String firstName = (String)request.getAttribute("firstName");
			String lastName = (String)request.getAttribute("lastName");
			int age = -1;
			long phoneNumber = -1;
			if(request.getAttribute("age") != null){
				 age = (int)request.getAttribute("age");
			}
			if((request.getAttribute("phoneNumber")) != null){
				 phoneNumber = (long)request.getAttribute("phoneNumber");
			}
			String email = (String)request.getAttribute("email");

	    	if(message != null){
		%>
		<p class="sign" align="center"><%= message %></p>
	    <%
	    	}else{
	    %>
	    <p class="sign" align="center">Sign Up</p>
	    <%
	    	}
	    %>   
    <form action = "register" method = "post" class="form1">
	   
	    <label for="exampleInputFirstName" class="form-label">First Name</label>
	    <input type="text" class="email" id="exampleInputFirstName" aria-describedby="FirstNameHelp"
	    <%if(firstName != null){ %>
	    value = <%=firstName %> 
	    <%} %>
	     name = "firstName" required >
	    
	    <label for="exampleInputLastName" class="form-label">Last Name</label>
	    <input type="text" class="email" id="exampleInputLastName" aria-describedby="LastNameHelp"
	    <%if(lastName != null){ %>
	    value = <%=lastName %> 
	    <%} %>
	     name = "lastName" required>
	    
	    <label for="exampleInputAge" class="form-label">Age</label>
	    <input type="number" class="email" id="exampleInputEmail1" aria-describedby="ageHelp" 
	    <%if(age > 0){ %>
	    value = <%=age %> 
	    <%} %>
	    name = "age" required>
	    
	    <label for="exampleInputPhoneNumber" class="form-label">Phone Number</label>
	    <input type="number" class="email" id="exampleInputEmail1" aria-describedby="numberHelp" 
	    <%if(phoneNumber > 0){ %>
	    value = <%=phoneNumber %> 
	    <%} %>
	    name = "phoneNumber" required>
	    
	    <label for="exampleInputEmail1" class="form-label">Email address</label>
	    <input type="email" class="email" id="exampleInputEmail1" aria-describedby="emailHelp" 
	    <%if(email != null){ %>
	    value = <%=email %> 
	    <%} %>
	    name = "email" required>
	    
	    <label for="exampleInputPassword1" class="form-label">Password</label>
	    <input type="password" class="pass" id="exampleInputPassword1" name = "password" required>
	    
	     <label for="exampleInputPassword1" class="form-label">Repeat Password</label>
	    <input type="password" class="pass" id="exampleInputPassword1" name = "repeatPassword" required>
	    
	    <input class = "submit" type = "submit" value = "Sign Up">
	    <p class="existinguser" align="center"><a href="login" class = "ahover">Have an account? Log in</p>      
 	</form>         
    </div>
</body>
</html>