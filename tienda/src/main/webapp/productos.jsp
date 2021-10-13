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

    <link rel="stylesheet" href="css/estilosProd.css">
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
                <a href="" class="a_salir">
                <h2 class="exit">Salir</h2>
                <i class="fas fa-sign-out-alt"></i></a>
            </div>
        </div>
        
    </header>
    <nav class="nav">
        <ul class="nav__ul">
            <li class="nav__li"><a href="usuario.jsp"><i class="fas fa-users"></i>Usuarios</a></li>
            <li class="nav__li"><a href="clinte.jsp"><i class="fas fa-cart-arrow-down"></i>Clientes</a></li>
            <li class="nav__li"><a href="proveedor.jsp"><i class="fas fa-shopping-basket"></i>Proveedores</a></li>
            <li class="nav__li"><a href="productos.jsp"><i class="fas fa-gifts"></i>Productos</a></li>
            <li class="nav__li"><a href="ventas.jsp"><i class="fas fa-coins"></i>Ventas</a></li>
            <li class="nav__li"><a href="reportes.jsp"><i class="fas fa-list-alt"></i>Reportes</a></li>
        </ul>
    </nav>

    <div class="form-container">
        <form class="form" method="POST" id="form-archivo">
            <div class="contenedor-archivo">
                <div class="ingresoDatos">
                    <div class="datos-container">
                        <div class="datosform">
                            <input type="file" class="form-input" placeholder="Archivo.csv" name="file" id="input_archivo">
                        </div>
                    </div>
                </div>
                <div class="form-buttons">
                    <input type="submit" class="form-button-consultar" value="Cargar" name="Cargar">
                </div>
            </div>
            
            <div class="div-parrafo">
                <p id="parrafo"></p>
            </div>
        </form>
    </div>
    <script src="js/enviar.js" ></script>
</body>
</html>