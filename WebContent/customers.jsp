<%@page import="com.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.0.min.js"></script>
<script src="Components/customers.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Customer Management V10.1</h1>
				 <form id="formCus" name="formCus">
					FirstName: <input id="firstName" name="firstName" type="text"
						class="form-control form-control-sm"> <br> LastName:
					<input id="lastName" name="lastName" type="text"
						class="form-control form-control-sm"> <br> nic
					price: <input id="nic" name="nic" type="text"
						class="form-control form-control-sm"> <br> Phone
					Number: <input id="phone" name="phone" type="text"
						class="form-control form-control-sm"> <br>email: 
						<input id="email" name="email" type="text"
						class="form-control form-control-sm"> <br>
						
						 <input
						id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
				</form> 
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divCustomersGrid">
					<%
					Customer customerObj = new Customer();
					out.print(customerObj.readCustomers());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</body>
</html>