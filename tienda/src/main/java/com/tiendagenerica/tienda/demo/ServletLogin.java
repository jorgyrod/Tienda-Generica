package com.tiendagenerica.tienda.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

import com.tiendagenerica.tienda.DTO.UsuarioDTO;
import com.tiendagenerica.tienda.Entidades.Usuario;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet("/ServletLogin")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String mensaje = "Faltan datos de Usuario";
		String clase = "parrafo-red";
		
		String agregar = request.getParameter("Agregar");
		String listar = request.getParameter("Listar");
		String actualizar = request.getParameter("Actualizar");
		String eliminar = request.getParameter("Eliminar");

		//Validamos que boton fue precionado...
		
		if(agregar != null) {
			if(validar(request,response)) {
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/index.jsp");
			}else {
				agregarUsuario(request,response);
			}
			
		}
		
		if(actualizar != null) {
			if(validar(request,response)) {
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/index.jsp");
			}else {
				actualizarUsuario(request,response);
			}
			
		}
		
		if(listar != null) {
			/* Para listar todos los usuarios
			 *
			*/
			//listarUsuario(request,response);
			
				if(validarCedula(request,response)) {
					mensaje = "Es necesario ingresar una cedula";
					getServletContext().setAttribute("mensaje", mensaje);
					getServletContext().setAttribute("clase", clase);
					response.sendRedirect("/tienda/index.jsp");
				}else {
					buscarUsuario(request,response);
				}
			
			
		}
		
		if(eliminar != null) {
			if(validarCedula(request,response)) {
				mensaje = "Es necesario ingresar una cedula";
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/index.jsp");
			}else {
				eliminarUsuario(request,response);
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
				|| request.getParameter("email").isBlank() || request.getParameter("usuario").isBlank()
				|| request.getParameter("password").isBlank()) {
	
			resultado = true;
		}
		
		return resultado;
	}

	//Agregar
	//----------------------------------
	
	public void agregarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		Usuario usuario = new Usuario();
		//Capturamos los datos y los enviamos al objeto usuario
		usuario.setCedula(Integer.parseInt(request.getParameter("cedula")));
		usuario.setNombre(request.getParameter("nombre"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setUsername(request.getParameter("usuario"));
		usuario.setPassword(request.getParameter("password"));
		String mensaje = "Usuario Creado";
		String clase = "parrafo-green";
		int respuesta=0;
		
		try {
			respuesta = TestJSONUsuarios.postJSON(usuario);
			//PrintWriter writer = response.getWriter();
			if(respuesta == 200) {
				//writer.println("Registro Agregado!");
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/index.jsp");

			} else {
				//writer.println("Error "+respuesta);
				response.sendRedirect("/tienda/index.jsp");
			}
			//writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Buscar por id
	//----------------------------------
	public void buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		String mensaje = "Usuario Inexistente";
		String clase = "parrafo-red";
		try {
			UsuarioDTO usuario = TestJSONUsuarios.getJSONId(cedula);
			//Le indicamos a que pagina se redigira
			String pagina ="/resultado.jsp";
			//Enviamos un atributo "user" para que reciba el objeto usuario
			request.setAttribute("user", usuario);
			//Esto es un disparador que nos envia a la pagina que indicamos antes
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
			dispatcher.forward(request, response);
		}catch(Exception e) {
			
			getServletContext().setAttribute("mensaje", mensaje);
			getServletContext().setAttribute("clase", clase);
			response.sendRedirect("/tienda/index.jsp");
		}
	}
	
	//Buscar todos los usuarios
	//----------------------------------
	public void listarUsuario(HttpServletRequest request, HttpServletResponse response){
		try {
			ArrayList<UsuarioDTO> lista = TestJSONUsuarios.getJSON();
			String pagina ="/resultado.jsp";
			request.setAttribute("lista", lista);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Actualizar
	//----------------------------------
	public void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Usuario usuario = new Usuario();
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		//Capturamos los datos y los enviamos al objeto usuario
		usuario.setNombre(request.getParameter("nombre"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setUsername(request.getParameter("usuario"));
		usuario.setPassword(request.getParameter("password"));
		
		String mensaje = "Datos del usuario Actualizados";
		String clase = "parrafo-green";
		
		int respuesta=0;
		try {
			respuesta = TestJSONUsuarios.putJSON(usuario,cedula);
			if(respuesta == 200) {
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/index.jsp");
			}

		} catch (IOException e) {
			mensaje = "Usuario Inexistente";
			clase = "parrafo-red";
			getServletContext().setAttribute("mensaje", mensaje);
			getServletContext().setAttribute("clase", clase);
			response.sendRedirect("/tienda/index.jsp");
		}
	}
	
	//Eliminar
	//----------------------------------
	public void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		String mensaje = "Usuario Inexistente";
		String clase = "parrafo-red";
		int respuesta=0;
		try {
			respuesta = TestJSONUsuarios.deleteJSON(cedula);
			if(respuesta == 200) {
				mensaje = "Usuario Eliminado Correctamente!";
				clase = "parrafo-green";
				getServletContext().setAttribute("mensaje", mensaje);
				getServletContext().setAttribute("clase", clase);
				response.sendRedirect("/tienda/index.jsp");
			} else {
				response.sendRedirect("/tienda/index.jsp");
			}
		} catch (IOException e) {
			getServletContext().setAttribute("mensaje", mensaje);
			getServletContext().setAttribute("clase", clase);
			response.sendRedirect("/tienda/index.jsp");
		}
	}
}
