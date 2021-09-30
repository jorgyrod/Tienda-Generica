package com.tiendagenerica.tienda.demo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.tiendagenerica.tienda.Entidades.Proveedor;

/**
 * Servlet implementation class ProveedorServlet
 */
@WebServlet("/ProveedorServlet")
public class ProveedorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProveedorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String mensaje = "Faltan datos del Proveedor";
		String clase = "parrafo-red";
		
		String agregar = request.getParameter("Agregar");
		String listar = request.getParameter("Listar");
		String actualizar = request.getParameter("Actualizar");
		String eliminar = request.getParameter("Eliminar");
		
		if(agregar != null) {
			if(validar(request,response)) {
				getServletContext().setAttribute("mensajeProv", mensaje);
				getServletContext().setAttribute("claseProv", clase);
				response.sendRedirect("/tienda/proveedor.jsp");
			}else {
				agregarProveedor(request,response);
			}
		}
		
		if(actualizar != null) {
			if(validar(request,response)) {
				getServletContext().setAttribute("mensajeProv", mensaje);
				getServletContext().setAttribute("claseProv", clase);
				response.sendRedirect("/tienda/proveedor.jsp");
			}else {
				actualizarProveedor(request,response);
			}
		}
		
		if( listar != null) {
			if(validarNit(request,response)) {
				mensaje = "Nit Errado!!";
				getServletContext().setAttribute("mensajeProv", mensaje);
				getServletContext().setAttribute("claseProv", clase);
				response.sendRedirect("/tienda/proveedor.jsp");
			}else {
				buscarProveedor(request,response);
			}
		}
		
		if(eliminar != null) {
			if(validarNit(request,response)) {
				mensaje = "Nit Errado!!";
				getServletContext().setAttribute("mensajeProv", mensaje);
				getServletContext().setAttribute("claseProv", clase);
				response.sendRedirect("/tienda/proveedor.jsp");
			}else {
				eliminarProveedor(request,response);
			}
		}
		
		
	}
	
	//Validar solo el campo cedula
		//----------------------------------
			
		public boolean validarNit(HttpServletRequest request, HttpServletResponse response){
				boolean resultado = false;
				
				if(request.getParameter("nit").isEmpty()) {		
					resultado = true;
				}
				
				return resultado;
		}
			
		//Validar todos los Campos
		//----------------------------------
		
		public boolean validar(HttpServletRequest request, HttpServletResponse response){
			boolean resultado = false;

			if(request.getParameter("nit").isEmpty() || request.getParameter("nombre").isBlank()
					|| request.getParameter("direccion").isBlank() || request.getParameter("telefono").isBlank()
					|| request.getParameter("ciudad").isBlank()) {
		
				resultado = true;
			}
			
			return resultado;
		}
		
		//Agregar
		//----------------------------------
		
		public void agregarProveedor(HttpServletRequest request, HttpServletResponse response) throws ServletException{
			Proveedor proveedor = new Proveedor();
			//Capturamos los datos y los enviamos al objeto proveedor
			
			proveedor.setNit(Integer.parseInt(request.getParameter("nit")));
			proveedor.setNombre(request.getParameter("nombre"));
			proveedor.setDireccion(request.getParameter("direccion"));
			proveedor.setTelefono(Integer.parseInt(request.getParameter("telefono")));
			proveedor.setCiudad(request.getParameter("ciudad"));
			
			String mensaje = "Proveedor Creado!";
			String clase = "parrafo-green";
			
			//Variable donde guardaremos que codigo nos trae el JSON
			int respuesta = 0;
			
			try {
				respuesta = JSONProveedor.postJSONProveedor(proveedor);
				if(respuesta == 200) {
					
					getServletContext().setAttribute("mensajeProv", mensaje);
					getServletContext().setAttribute("claseProv", clase);
					response.sendRedirect("/tienda/proveedor.jsp");
				}else {
					response.sendRedirect("/tienda/proveedor.jsp");
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
		
		//Actualizar
		//----------------------------------
		public void actualizarProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException {
			Proveedor proveedor = new Proveedor();
			int nit = Integer.parseInt(request.getParameter("nit"));
			
			proveedor.setNombre(request.getParameter("nombre"));
			proveedor.setDireccion(request.getParameter("direccion"));
			proveedor.setTelefono(Integer.parseInt(request.getParameter("telefono")));
			proveedor.setCiudad(request.getParameter("ciudad"));
			
			String mensaje = "Proveedor Inexistente";
			String clase = "parrafo-red";
			
			int respuesta = 0;
			
			try {
				respuesta = JSONProveedor.putJSONProveedor(proveedor, nit);
				if(respuesta == 200) {
					
					mensaje = "Datos del Proveedor Actualizados";
					clase = "parrafo-green"; 
					getServletContext().setAttribute("mensajeProv", mensaje);
					getServletContext().setAttribute("claseProv", clase);
					response.sendRedirect("/tienda/proveedor.jsp");
				}
			}catch(IOException e) {
				getServletContext().setAttribute("mensajeProv", mensaje);
				getServletContext().setAttribute("claseProv", clase);
				response.sendRedirect("/tienda/proveedor.jsp");
			}
		}
		
		//Buscar
		//----------------------------------
		public void buscarProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException {
			int nit = Integer.parseInt(request.getParameter("nit"));
			String mensaje = "Proveedor Inexistente";
			String clase = "parrafo-red";
			
			
			try {
				Proveedor proveedor = JSONProveedor.getJSONProvId(nit);
				//Le indicamos a que pagina se redigira
				String pagina ="/respuesta.jsp";
				//Enviamos un atributo "user" para que reciba el objeto usuario
				request.setAttribute("prov", proveedor);
				//Esto es un disparador que nos envia a la pagina que indicamos antes
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
				dispatcher.forward(request, response);
				
			}catch(Exception e) {
				getServletContext().setAttribute("mensajeProv", mensaje);
				getServletContext().setAttribute("claseProv", clase);
				response.sendRedirect("/tienda/proveedor.jsp");
			}
		}
		
		//Eliminar
		//----------------------------------
		public void eliminarProveedor(HttpServletRequest request, HttpServletResponse response) throws IOException {
			int nit = Integer.parseInt(request.getParameter("nit"));
			String mensaje = "Proveedor Inexistente";
			String clase = "parrafo-red";
			
			int respuesta = 0;
			
			try {
				respuesta = JSONProveedor.deleteJSONProveedor(nit);
				if(respuesta == 200) {
					mensaje = "Proveedor Eliminado Correctamente!";
					clase = "parrafo-green";
					getServletContext().setAttribute("mensajeProv", mensaje);
					getServletContext().setAttribute("claseProv", clase);
					response.sendRedirect("/tienda/proveedor.jsp");
				}
				
				
			} catch(IOException e) {
				getServletContext().setAttribute("mensajeProv", mensaje);
				getServletContext().setAttribute("claseProv", clase);
				response.sendRedirect("/tienda/proveedor.jsp");
			}
		}
}
