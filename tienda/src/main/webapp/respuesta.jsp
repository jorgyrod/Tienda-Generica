<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<!-- Importamos los paquetes -->
<%@ page import='java.util.Date' %>
<%@ page import='com.tiendagenerica.tienda.Entidades.Proveedor' %>
<%@ page import='java.util.ArrayList'%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Consulta de Proveedor</title>
</head>
<body>
	<p>LISTADO DE PROVEEDOR</p>
	<p>Hora servidor es <%=new Date()%></p>
	<table>
		<tr>
			<td><label>Nit</label></td>
			<td><label>Nombre</label></td>
			<td><label>Direccion</label></td>
			<td><label>Telefono</label></td>
			<td><label>Ciudad</label></td>
		</tr>
		<%
			/*
				Recibimos el atributo "user" que enviamos en el Servlet
			*/
			Proveedor proveedor = (Proveedor) request.getAttribute("prov");
		%>
			<tr>
				<td><%=proveedor.getNit()%></td>
				<td><%=proveedor.getNombre()%></td>
				<td><%=proveedor.getDireccion()%></td>
				<td><%=proveedor.getTelefono()%></td>
				<td><%=proveedor.getCiudad()%></td>
			</tr>
			<%
			//}
			%>
	</table>
</body>
</html>