package com.tiendagenerica.tienda.demo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tiendagenerica.tienda.Entidades.Clientes;

/**
 * Servlet implementation class ClienteServlet
 */
@WebServlet("/ClienteServlet")
public class ClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteServlet() {
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
		
		String mensaje = "Faltan datos del Cliente";
		String clase = "parrafo-red";
		
		String agregar = request.getParameter("Agregar");
		String listar = request.getParameter("Listar");
		String actualizar = request.getParameter("Actualizar");
		String eliminar = request.getParameter("Eliminar");
		
		if(agregar != null) {
			if(validar(request,response)) {
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/cliente.jsp");
			}else {
				agregarCliente(request,response);
			}
		}
		
		if(actualizar != null) {
			if(validar(request,response)) {
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/cliente.jsp");
			}else {
				actualizarCliente(request,response);
			}
		}
		
		if( listar != null) {
			if(validarCedula(request,response)) {
				mensaje = "Cedula Errada!!";
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/cliente.jsp");
			}else {
				buscarCliente(request,response);
			}
		}
		
		if(eliminar != null) {
			if(validarCedula(request,response)) {
				mensaje = "Cedula Errado!!";
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/cliente.jsp");
			}else {
				eliminarCliente(request,response);
			}
		}
	}
	
	//Validar solo el campo cedula
	//----------------------------------
		
	public boolean validarCedula(HttpServletRequest request, HttpServletResponse response){
			boolean resultado = false;
			
			if(request.getParameter("cedula").isEmpty()) {		
				resultado = true;
			}
			
			return resultado;
	}
		
	//Validar todos los Campos
	//----------------------------------
	
	public boolean validar(HttpServletRequest request, HttpServletResponse response){
		boolean resultado = false;

		if(request.getParameter("cedula").isEmpty() || request.getParameter("nombre").isBlank()
				|| request.getParameter("direccion").isBlank() || request.getParameter("telefono").isBlank()
				|| request.getParameter("email").isBlank()) {
	
			resultado = true;
		}
		
		return resultado;
	}
	
	//Agregar
	//----------------------------------
	public void agregarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException{
		Clientes cliente = new Clientes();
		//Capturamos los datos y los enviamos al objeto proveedor
		
		cliente.setCedula(Integer.parseInt(request.getParameter("cedula")));
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setDireccion(request.getParameter("direccion"));
		cliente.setTelefono(Integer.parseInt(request.getParameter("telefono")));
		cliente.setEmail(request.getParameter("email"));
	
		String mensaje = "Cliente Creado!";
		String clase = "parrafo-green";
		
		//Variable donde guardaremos que codigo nos trae el JSON
		int respuesta = 0;
		

		try {
			respuesta = JSONCliente.postJSONCliente(cliente);
			if(respuesta == 200) {
				
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/cliente.jsp");
			}else {
				response.sendRedirect("/tienda/cliente.jsp");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	//Actualizar
	//----------------------------------
	public void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
	
		Clientes cliente = new Clientes();
		
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		
		cliente.setNombre(request.getParameter("nombre"));
		cliente.setDireccion(request.getParameter("direccion"));
		cliente.setTelefono(Integer.parseInt(request.getParameter("telefono")));
		cliente.setEmail(request.getParameter("email"));
		
		String mensaje = "Cliente Inexistente";
		String clase = "parrafo-red";
		
		int respuesta = 0;
		
		try {
			respuesta = JSONCliente.putJSONCliente(cliente, cedula);
			if(respuesta == 200) {
				
				mensaje = "Datos del Cliente Actualizados";
				clase = "parrafo-green"; 
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/cliente.jsp");
			}
		}catch(IOException e) {
			getServletContext().setAttribute("mensaje", mensaje);
			getServletContext().setAttribute("clase", clase);
			response.sendRedirect("/tienda/cliente.jsp");
		}
	}
	
	//Buscar
	//----------------------------------
	public void buscarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		
		String mensaje = "Cliente Inexistente";
		String clase = "parrafo-red";
		
		try {
			Clientes cliente = JSONCliente.getJSONCliente(cedula);
			//Le indicamos a que pagina se redigira
			String pagina ="/respuestaCliente.jsp";
			//Enviamos un atributo "user" para que reciba el objeto usuario
			request.setAttribute("cliente", cliente);
			//Esto es un disparador que nos envia a la pagina que indicamos antes
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
			dispatcher.forward(request, response);
			
		}catch(Exception e) {
			getServletContext().setAttribute("mensaje", mensaje);
			getServletContext().setAttribute("clase", clase);
			response.sendRedirect("/tienda/cliente.jsp");
		}
	}
	
	//Eliminar
	//----------------------------------
	public void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		String mensaje = "Cliente Inexistente";
		String clase = "parrafo-red";
				
		int respuesta = 0;
				
		try {
			respuesta = JSONCliente.deleteJSONCliente(cedula);
				if(respuesta == 200) {						
					mensaje = "Cliente Eliminado Correctamente!";
					clase = "parrafo-green";
					getServletContext().setAttribute("mensaje", mensaje);
					getServletContext().setAttribute("clase", clase);
					response.sendRedirect("/tienda/cliente.jsp");
				}				
					
		}catch(IOException e) {
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/cliente.jsp");
			}
	}
}
