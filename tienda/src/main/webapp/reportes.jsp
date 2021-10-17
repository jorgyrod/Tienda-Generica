<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tienda Generica</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="stylesheet" type="text/css" href="css/estilos.css">
	<link rel="stylesheet" href="https://bootswatch.com/5/lux/bootstrap.min.css">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Playball&display=swap" rel="stylesheet">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link href="https://fonts.googleapis.com/css2? family=Josefin+Sans&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/62ea397d3a.js" crossorigin="anonymous"></script>
</head>
<body>
	<header class="header">
        <div class="header-container">
            <div class="header__li">
                <i class="fas fa-store"></i>
                <h1><font color="white" style = style = "font-family:helvetica;">Tienda Genérica</font></h1>
            </div>
           <div class="header__li__salir">
                <a href="Login.jsp" class="a_salir">
	                <h2 class="exit"><font size="4">Salir</font></h2>
	                <i class="fas fa-sign-out-alt"></i>
                </a>
            </div>
        </div>
        
    </header>
    <nav class="nav">
        <ul class="nav__ul">
            <li class="nav__li"><a href="usuario.jsp" id="btn_usuarios"><i class="fas fa-users"></i>Usuarios</a></li>
            <li class="nav__li"><a href="cliente.jsp"  id="btn_clientes"><i class="fas fa-cart-arrow-down"></i>Clientes</a></li>
            <li class="nav__li"><a href="proveedor.jsp" id="btn_proveedor"><i class="fas fa-shopping-basket" ></i>Proveedores</a></li>
            <li class="nav__li"><a href="productos.jsp"><i class="fas fa-gifts"></i>Productos</a></li>
            <li class="nav__li"><a href="#"><i class="fas fa-coins"></i>Ventas</a></li>
            <li class="nav__li"><a href="reportes.jsp"><i class="fas fa-list-alt"></i>Reportes</a></li>
        </ul>
    </nav>
    <div class="form-container" >
    	<form method="POST" action="./ReportesServlet">  	
	        <div class="row align-items-center mt-4 text-center justify-content-center">
		        	<div class="d-grid gap-2">
		        		<input type="submit" class="btn btn-lg btn-primary" name="usuarios" value="Listado de usuarios">
					  	<input type="submit" class="btn btn-lg btn-primary" role="button" aria-pressed="true" name="clientes" value="Listado de clientes">
					  	<input type="submit" class="btn btn-lg btn-primary" role="button" aria-pressed="true" name="ventas" value="Ventas por cliente">
					 </div>
	        </div>
        </form>
    </div>