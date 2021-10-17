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
                <h1>Tienda Generica</h1>
            </div>
           <div class="header__li__salir">
                <a href="Login.jsp" class="a_salir">
	                <h2 class="exit">Salir</h2>
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
            <li class="nav__li"><a href="ventas.jsp"><i class="fas fa-coins"></i>Ventas</a></li>
            <li class="nav__li"><a href="reportes.jsp"><i class="fas fa-list-alt"></i>Reportes</a></li>
        </ul>
    </nav>
  <form method="POST" action="./VentaServlet" class="form">
  <br><br>
  		<div class="ventas-container">
            <div class="ingresoDatosVentasCenter">
                  <input type="text" class="form-input" placeholder="Cedula" name="cedula" style="width: 150px">
                  <input type="submit" class="form-button-buscar" value="Consultar" name="consultar">
                  <input type="text" class="form-input" placeholder="Cliente" name="cliente" style="width: 250px">
                  <input type="text" class="form-input" placeholder="Consec." name="consecutivo" style="width: 200px">
                            
            </div>
            <br>
            <div class="ingresoDatosVentas">
            <input type="text" class="form-input" placeholder="Cod. Producto 1" name="codProd1" style="width: 150px">
            <input type="submit" class="form-button-buscar" value="Consultar" name="consultarProd1">
            <input type="text" class="form-input" placeholder="Nombre Producto 1" name="nomProd1" style="width: 300px">
            <input type="text" class="form-input" placeholder="Cant." name="cantprod1" style="width: 50px">
            <input type="text" class="form-input" placeholder="Valor Total" name="valrTotalProd1" style="width: 200px">
            </div>
             <div class="ingresoDatosVentas">
            <input type="text" class="form-input" placeholder="Cod. Producto 2" name="codProd2" style="width: 150px">
            <input type="submit" class="form-button-buscar" value="Consultar" name="consultarProd2">
            <input type="text" class="form-input" placeholder="Nombre Producto 2" name="nomProd2" style="width: 300px">
            <input type="text" class="form-input" placeholder="Cant." name="cantprod2" style="width: 50px">
            <input type="text" class="form-input" placeholder="Valor Total" name="valrTotalProd2" style="width: 200px">
            </div>
             <div class="ingresoDatosVentas">
            <input type="text" class="form-input" placeholder="Cod. Producto 3" name="codProd3" style="width: 150px">
            <input type="submit" class="form-button-buscar" value="Consultar" name="consultarProd3">
            <input type="text" class="form-input" placeholder="Nombre Producto 3" name="nomProd3" style="width: 300px">
            <input type="text" class="form-input" placeholder="Cant." name="cantprod3" style="width: 50px">
            <input type="text" class="form-input" placeholder="Valor Total" name="valrTotalProd3" style="width: 200px">
            </div>
             <div class="ingresoDatosVentas">
            <input type="text" class="form-input" placeholder="Cod. Producto 4" name="codProd4" style="width: 150px">
            <input type="submit" class="form-button-buscar" value="Consultar" name="consultarProd4">
            <input type="text" class="form-input" placeholder="Nombre Producto 4" name="nomProd4" style="width: 300px">
            <input type="text" class="form-input" placeholder="Cant." name="cantprod4" style="width: 50px">
            <input type="text" class="form-input" placeholder="Valor Total" name="valrTotalProd4" style="width: 200px">
            </div>
            
            <br>
            
            <div class="ingresoTotal">
            <span class="label">
            Total Venta: 
           			<input type="text" class="form-input" placeholder="" name="totalVenta" style="width: 200px" disabled>
            </span>
            </div>
             <div class="ingresoTotal">
            <span class="label">
            Total IVA: 
            	<input type="text" class="form-input" placeholder="" name="totalIVA" style="width: 200px" disabled>
            </span>
            </div>
            <div class="ingresoTotal">
            <span class="label">
            Total con IVA: 
            	<input type="text" class="form-input" placeholder="" name="totalConIVA" style="width: 200px" disabled>
            </span>
            </div>
            
            <div class="ingresoDatosVentasCenter">
            	<input type="submit" class="form-button-consultar" value="Confirmar" name="confirmar">
            </div>
			<div class="div-parrafo">
        		 <p class="${claseProv}" id="parrafo">${mensajeProv}</p> 
            </div>
        </div>
 </form>
<br><br><br><br>


<script src="JavaScript/limpiar.js"></script>
</body>
</html>