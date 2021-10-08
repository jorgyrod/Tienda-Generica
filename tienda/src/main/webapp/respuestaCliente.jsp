<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- Importamos los paquetes -->
<%@ page import='java.util.Date' %>
<%@ page import='com.tiendagenerica.tienda.Entidades.Cliente' %>
<%@ page import='java.util.ArrayList'%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta de Cliente</title>
</head>
<body>
	<p>LISTADO DE PROVEEDOR</p>
	<p>Hora servidor es <%=new Date()%></p>
	<table>
		<tr>
			<td><label>Cedula</label></td>
			<td><label>Nombre</label></td>
			<td><label>Direccion</label></td>
			<td><label>Telefono</label></td>
			<td><label>Correo</label></td>
		</tr>
		<%
		/*
				Recibimos el atributo "user" que enviamos en el Servlet
			*/
			Cliente client = (Cliente) request.getAttribute("cliente");
		%>
			<tr>
				<td><%=client.getCedula() %></td>
				<td><%=client.getNombre()%></td>
				<td><%=client.getDireccion()%></td>
				<td><%=client.getTelefono()%></td>
				<td><%=client.getEmail()%></td>
			</tr>
			<%
			//}
			%>
	</table>
</body>
</html>