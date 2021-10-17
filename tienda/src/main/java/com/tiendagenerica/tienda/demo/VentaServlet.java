package com.tiendagenerica.tienda.demo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

import com.tiendagenerica.tienda.DTO.DetalleDTO;
import com.tiendagenerica.tienda.DTO.VentaDTO;
import com.tiendagenerica.tienda.Entidades.Cliente;
import com.tiendagenerica.tienda.Entidades.Producto;

/**
 * Servlet implementation class VentaServlet
 */
@WebServlet("/VentaServlet")
public class VentaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private double subto=0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VentaServlet() {
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
		String cliente = request.getParameter("consultar");
		String confirmar = request.getParameter("confirmar");
		
		if(cliente != null) obtenerCliente(request,response);
		
		try {
			agregarDetalle(request,response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(confirmar != null) agregarVenta(request,response);
		
		
	}
	
	public void agregarVenta(HttpServletRequest request, HttpServletResponse response) {
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		VentaDTO venta = new VentaDTO();
		
		venta.setCedula_cliente(cedula);
		venta.setCedula_usuario(1013);
		
		String mensaje = "Venta Creada!";
		String clase = "parrafo-green";
		
		//Variable donde guardaremos que codigo nos trae el JSON
		int respuesta = 0;
		

		try {
			respuesta = JSONVentas.postJSONVenta(venta);
			if(respuesta == 200) {
				
				getServletContext().setAttribute("mensajeVent", mensaje);
				getServletContext().setAttribute("claseVent", clase);
				response.sendRedirect("/tienda/ventas.jsp");
				limpiar(request,response);
			}else {
				response.sendRedirect("/tienda/ventas.jsp");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void limpiar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		getServletContext().setAttribute("nombreC", "");
		getServletContext().setAttribute("cedulaC", "");
		
		getServletContext().setAttribute("codigo1", "");
		getServletContext().setAttribute("nombre1", "");
		getServletContext().setAttribute("cantidad1", "");
		getServletContext().setAttribute("valor1", "");
		subto = 0;
		getServletContext().setAttribute("subtotal", subto);
		
		getServletContext().setAttribute("codigo2", "");
		getServletContext().setAttribute("nombre2", "");
		getServletContext().setAttribute("cantidad2", "");
		getServletContext().setAttribute("valor2", "");
		
		getServletContext().setAttribute("codigo3", "");
		getServletContext().setAttribute("nombre3", "");
		getServletContext().setAttribute("cantidad3", "");
		getServletContext().setAttribute("valor3", "");
		
		getServletContext().setAttribute("codigo4", "");
		getServletContext().setAttribute("nombre4", "");
		getServletContext().setAttribute("cantidad4", "");
		getServletContext().setAttribute("valor4", "");
		
		getServletContext().setAttribute("iva", "");
		getServletContext().setAttribute("total", "");
		
		response.sendRedirect("/tienda/ventas.jsp");
		
	}
	public void obtenerCliente(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		int cedula = Integer.parseInt(request.getParameter("cedula"));
		
		String mensaje = "Cliente Inexistente";
		String clase = "parrafo-red";
		
		try {
			Cliente cliente = JSONCliente.getJSONCliente(cedula);
			//Le indicamos a que pagina se redigira
			getServletContext().setAttribute("nombreC", cliente.getNombre());
			getServletContext().setAttribute("cedulaC", cedula);
			response.sendRedirect("/tienda/ventas.jsp");
			
			
		}catch(Exception e) {
			getServletContext().setAttribute("mensajeVent", mensaje);
			getServletContext().setAttribute("claseVent", clase);
			response.sendRedirect("/tienda/ventas.jsp");
		}
	}
	
	public void calcularValores(HttpServletRequest request) {
		Double subtotal, iva, total;
		
		subtotal = subto;
		iva = (subtotal *  19) / 100;
		total = subtotal + iva;
		
		getServletContext().setAttribute("iva", iva);
		getServletContext().setAttribute("total", total);
		
	}
	//Agregar Detalle
	//-----------------------
	
	public void agregarDetalle(HttpServletRequest request, HttpServletResponse response) throws ParseException {
		
		int cantidad=0, codigoProducto=0;
		
		switch(verificacion(request)) {
			case 1:
				cantidad = Integer.parseInt(request.getParameter("cantprod1"));
				codigoProducto = Integer.parseInt(request.getParameter("codProd1"));		
				break;
			case 2:
				cantidad = Integer.parseInt(request.getParameter("cantprod2"));
				codigoProducto = Integer.parseInt(request.getParameter("codProd2"));		
				break;
			case 3:
				cantidad = Integer.parseInt(request.getParameter("cantprod3"));
				codigoProducto = Integer.parseInt(request.getParameter("codProd3"));		
				break;
			case 4:
				cantidad = Integer.parseInt(request.getParameter("cantprod4"));
				codigoProducto = Integer.parseInt(request.getParameter("codProd4"));		
				break;
				
		}
		agregarDetalleProducto(request,response,cantidad,codigoProducto);
		
	}
	
	
	//Metodo para agregar 1 solo producto
	public void agregarDetalleProducto(HttpServletRequest request,HttpServletResponse response,int cantidad, int codigoProducto) throws ParseException {
		DetalleDTO detalle = new DetalleDTO();

		detalle.setCantidad_Producto(cantidad);
		detalle.setCodigo_producto(codigoProducto);
		
		//Variable donde guardaremos que codigo nos trae el JSON
		int respuesta = 0;
		
		try {
			respuesta = JSONVentas.postJSONDetalle(detalle);
			if(respuesta == 200) {
				Producto producto = JSONProducto.getJSONProducto(codigoProducto);
			
				servletContext(request,producto,cantidad);
				calcularValores(request);
				response.sendRedirect("/tienda/ventas.jsp");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void servletContext(HttpServletRequest request, Producto producto, int cantidad) {
		
		double subtotal;
		
		subtotal = producto.getPrecio_venta()*cantidad;
	
		switch(verificacion(request)) {
			case 1:
				getServletContext().setAttribute("codigo1", producto.getCodigo_producto());
				getServletContext().setAttribute("nombre1", producto.getNombre_producto());
				getServletContext().setAttribute("cantidad1", cantidad);
				getServletContext().setAttribute("valor1", subtotal);
				subto += subtotal;
				getServletContext().setAttribute("subtotal", subto);
				break;
			case 2:
				getServletContext().setAttribute("codigo2", producto.getCodigo_producto());
				getServletContext().setAttribute("nombre2", producto.getNombre_producto());
				getServletContext().setAttribute("cantidad2", cantidad);
				getServletContext().setAttribute("valor2", subtotal);
				subto += subtotal;
				getServletContext().setAttribute("subtotal", subto);
				break;
			case 3:
				getServletContext().setAttribute("codigo3", producto.getCodigo_producto());
				getServletContext().setAttribute("nombre3", producto.getNombre_producto());
				getServletContext().setAttribute("cantidad3", cantidad);
				getServletContext().setAttribute("valor3", subtotal);
				subto += subtotal;
				getServletContext().setAttribute("subtotal", subto);
				break;
			case 4:
				getServletContext().setAttribute("codigo4", producto.getCodigo_producto());
				getServletContext().setAttribute("nombre4", producto.getNombre_producto());
				getServletContext().setAttribute("cantidad4", cantidad);
				getServletContext().setAttribute("valor4", subtotal);
				subto += subtotal;
				getServletContext().setAttribute("subtotal", subto);
				break;
				
		}
		
	}
	
	public int verificacion(HttpServletRequest request) {
		String detalle1 = request.getParameter("consultarProd1");
		String detalle2 = request.getParameter("consultarProd2");
		String detalle3 = request.getParameter("consultarProd3");
		String detalle4 = request.getParameter("consultarProd4");
		
		if(detalle1 != null) return 1;

		if(detalle2 != null) return 2;
		
		if(detalle3 != null) return 3;
		
		if(detalle4 != null) return 4;
		
		return 0;
		
	}


}
