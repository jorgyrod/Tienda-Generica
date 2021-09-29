<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login Tienda Generica</title>
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/estiloLogin.css">
</head>
<body>
    <div class="div-total">

        <div class="imagen"></div>
        <div class="contenidos">

            <div class="container">
                <div class="container-fila">
                    <div class="container-columna">
                        <h3>Tienda Generica</h3>
                        <form action="./Servletmain" method="POST">
                            <div class="grupo-usuario">
                                <label for="username">Username</label>
                                <input type="text" class="form-control" placeholder="Tu usuario" id="username" name="usuario">
                            </div>
                            <div class="grupo-contraseña">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" placeholder="Tu contraseña" id="password" name="contrasena">
                            </div>

                            <input type="submit" value="Ingresar" class="btn_ingresar" name="ingresar">
                        </form>
                        <div class="div-parrafo">
                            <p>${mensaje}</p> 
                        </div>
                    </div>
                </div>
            </div>

        </div>

    </div>
</body>
</html>