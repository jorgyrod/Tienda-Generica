<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- Importamos los paquetes -->
<%@ page import='java.util.Date' %>
<%@ page import='com.tiendagenerica.tienda.DTO.UsuarioDTO' %>
<%@ page import='java.util.ArrayList'%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta de Usuario</title>
</head>
<body>
	<p>LISTADO DE USUARIOS</p>
	<p>Hora servidor es <%=new Date()%></p>
	<table>
		<tr>
			<td><label>Cedula</label></td>
			<td><label>Nombre</label></td>
			<td><label>Correo</label></td>
			<td><label>Usuario</label></td>
		</tr>
		<%
			/*
				Recibimos el atributo "user" que enviamos en el Servlet
			*/
			UsuarioDTO usuario = (UsuarioDTO) request.getAttribute("lista");
			//for(UsuarioDTO usuario : lista){
		%>
			<tr>
				<td><%=usuario.getCedula()%></td>
				<td><%=usuario.getNombre()%></td>
				<td><%=usuario.getEmail()%></td>
				<td><%=usuario.getUsername()%></td>
			</tr>
			<%
			//}
			%>
	</table>
</body>
</html>