package com.tiendagenerica.tienda.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


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
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String agregar = request.getParameter("Agregar");
		String listar = request.getParameter("Listar");
		String actualizar = request.getParameter("Actualizar");
		String eliminar = request.getParameter("Eliminar");
		
		//Validamos que boton fue precionado...
		
		if(agregar != null) {
			agregarUsuario(request,response);
		}
		
		if(actualizar != null) {
			actualizarUsuario(request,response);
		}
		
		if(listar != null) {
			listarUsuario(request,response);
		}
		
		if(eliminar != null) {
			
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	public void agregarUsuario(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuario = new Usuario();
		//Capturamos los datos y los enviamos al objeto usuario
		usuario.setCedula(Integer.parseInt(request.getParameter("cedula")));
		usuario.setNombre(request.getParameter("nombre"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setUsername(request.getParameter("usuario"));
		usuario.setPassword(request.getParameter("password"));
		
		int respuesta=0;
		try {
			respuesta = TestJSONUsuarios.postJSON(usuario);
			PrintWriter writer = response.getWriter();
			if(respuesta == 200) {
				writer.println("Registro Agregado!");
			} else {
				writer.println("Error "+respuesta);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
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
	
	public void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
		Usuario usuario = new Usuario();
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		//Capturamos los datos y los enviamos al objeto usuario
		usuario.setNombre(request.getParameter("nombre"));
		usuario.setEmail(request.getParameter("email"));
		usuario.setUsername(request.getParameter("usuario"));
		usuario.setPassword(request.getParameter("password"));
		
		int respuesta=0;
		try {
			respuesta = TestJSONUsuarios.putJSON(usuario,cedula);
			PrintWriter writer = response.getWriter();
			if(respuesta == 200) {
				writer.println("Registro Actualizado!");
			} else {
				writer.println("Error "+respuesta);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
