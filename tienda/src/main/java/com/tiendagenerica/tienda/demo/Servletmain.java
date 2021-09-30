package com.tiendagenerica.tienda.demo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tiendagenerica.tienda.Entidades.Usuario;

/**
 * Servlet implementation class Servletmain
 */
@WebServlet("/Servletmain")
public class Servletmain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Servletmain() {
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
		String ingresar = request.getParameter("ingresar");
		
		if(ingresar != null) {
			buscarUsuario(request,response);
		}

	}
	
	public void buscarUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException{
		String username = request.getParameter("usuario");
		String password = request.getParameter("contrasena");
		String mensaje = "Usuario o Contraseña Incorrecta";
		String clase = "parrafo-red";
		try {
			Usuario user = TestJSONUsuarios.getJSONUsername(username);

			if(user.getUsername().equals(username) && user.getPassword().equals(password)) {
				response.sendRedirect("/tienda/usuario.jsp");
			}
			
		}catch(Exception e) {
			getServletContext().setAttribute("mensajeLogin", mensaje);
			getServletContext().setAttribute("claseLogin", clase);
			response.sendRedirect("/tienda/Login.jsp");
		}
	}
	

}
