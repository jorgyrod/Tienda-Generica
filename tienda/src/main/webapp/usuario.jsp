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
            <li class="nav__li"><a href="usuario.jsp"><i class="fas fa-users"></i>Usuarios</a></li>
            <li class="nav__li"><a href="cliente.jsp"><i class="fas fa-cart-arrow-down"></i>Clientes</a></li>
            <li class="nav__li"><a href="proveedor.jsp"><i class="fas fa-shopping-basket" ></i>Proveedores</a></li>
            <li class="nav__li"><a href="productos.jsp"><i class="fas fa-gifts" ></i>Productos</a></li>
            <li class="nav__li"><a href="#"><i class="fas fa-coins"></i>Ventas</a></li>
            <li class="nav__li"><a href="reportes.jsp"><i class="fas fa-list-alt"></i>Reportes</a></li>
        </ul>
    </nav>

    <div class="form-container">
        <form method="POST" action="./UsuarioServlet" class="form">
            <div class="ingresoDatos">
                <div class="datos-container">
                    <div class="datosform">
                        <input type="text" class="form-input" placeholder="Cedula" name="cedula">
                    </div>
                    <div class="datosform">
                        <input type="text" class="form-input" placeholder="Nombre Completo" name="nombre">
                    </div>
                    <div class="datosform">
                        <input type="text" class="form-input" placeholder="Correo" name="email">
                    </div>
                </div>
                <div class="datos-container2">
                    <div class="datosform">
                        <input type="text" class="form-input" placeholder="Usuario" name="usuario">
                    </div>
                    <div class="datosform">
                        <input type="password" class="form-input" placeholder="Contrase�a" name="password">
                    </div>
                </div>
            </div>
            <div class="form-buttons">
                <input type="submit" class="form-button-consultar" value="Crear" name="Agregar">
                <input type="submit" class="form-button-actualizar" value="Actualizar" name="Actualizar">
                <input type="submit" class="form-button-buscar" value="Buscar" name="Listar">
                <input type="submit" class="form-button-eliminar" value="Eliminar" name="Eliminar">
            </div>
            <div class="div-parrafo">
        		 <p class="${claseUsu}" id="parrafo">${mensajeUsu}</p> 
            </div>
        </form>
    </div>
    
</body>
</html>