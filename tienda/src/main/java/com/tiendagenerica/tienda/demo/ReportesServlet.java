package com.tiendagenerica.tienda.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.parser.ParseException;

import com.tiendagenerica.tienda.DTO.ClienteReporteDTO;
import com.tiendagenerica.tienda.DTO.UsuarioDTO;
import com.tiendagenerica.tienda.Entidades.Cliente;
import com.tiendagenerica.tienda.Entidades.Ventas;

/**
 * Servlet implementation class ReportesServlet
 */
@WebServlet("/ReportesServlet")
public class ReportesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReportesServlet() {
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
		String listar = request.getParameter("usuarios");
		String clientes = request.getParameter("clientes");
		String ventas = request.getParameter("ventas");
		
		
		if(listar != null) {
			listarUsuario(request,response);
		}
		
		if(clientes != null) {
			listarClientes(request,response);
		}
		
		if(ventas != null) {
			listarVentasPorCliente(request,response);
		}
	}

	//Buscar todos los usuarios
	//----------------------------------
	public void listarUsuario(HttpServletRequest request, HttpServletResponse response){
		try {
			ArrayList<UsuarioDTO> lista = TestJSONUsuarios.getJSON();
			String pagina ="/listadoUsuarios.jsp";
			request.setAttribute("lista", lista);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
			dispatcher.forward(request, response);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Buscar todos los clientes
	public void listarClientes(HttpServletRequest request, HttpServletResponse response) {
		try {
			ArrayList<Cliente> listadoCliente = JSONCliente.getJSONLista();
			String pagina ="/listadoClientes.jsp";
			request.setAttribute("lista", listadoCliente);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Listaremos los clientes y sus ventas
	public void listarVentasPorCliente(HttpServletRequest request, HttpServletResponse response) {
		try {
			ArrayList<ClienteReporteDTO> listado = listaClienteDTO();
			String pagina = "/ventasCliente.jsp";
			request.setAttribute("lista", listado);
			request.setAttribute("total", calcularTotal(listado));
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(pagina);
			dispatcher.forward(request, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//Esto calculara el Total de todas las ventas hechas en la tienda
	private double calcularTotal(ArrayList<ClienteReporteDTO> clienteDTO) {
		double total=0;
		for(ClienteReporteDTO cliente : clienteDTO) {
			total+=cliente.getTotal();
		}
		return total;
	}
	
	//Con este metodo optenemos una lista de ClientesDTO
	//En el cual estan la cedula, el nombre y el total por cliente
	private ArrayList<ClienteReporteDTO> listaClienteDTO(){
		ArrayList<ClienteReporteDTO> clienteDTO = new ArrayList<>();
		
		try {
			//Obtenemos la lista de clientes
			ArrayList<Cliente> listadoCliente = JSONCliente.getJSONLista();
			//Recorremos esa lista...
			for(Cliente cliente : listadoCliente) {
				ClienteReporteDTO reporteDTO = new ClienteReporteDTO();
				
				//Pasamos los datos
				reporteDTO.setCedula(cliente.getCedula());
				reporteDTO.setNombre(cliente.getNombre());
				//Obtenemos el valor total de las ventas hechas del cliente y 
				//las guardamos en el DTO
				reporteDTO.setTotal(total(cliente.getCedula()));
				
				//Añadimos ese cliente con su total de ventas a la lista
				clienteDTO.add(reporteDTO);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return clienteDTO;
	}
	
	//Metodo para calcular el total de las ventas por Cliente
	private double total(int cedula) {
		double total=0;
		try {
			Cliente cliente = JSONCliente.getJSONCliente(cedula);
			
			//Si el cliente ha hecho alguna compra y la lista de ventas no esta vacia
			//entones calculamos el total de la venta
			if(!cliente.getVentas().isEmpty()) {
				List<Ventas> ventasCliente = cliente.getVentas();
				
				for(Ventas venta : ventasCliente) {
					total+= venta.getValor_total();
				}
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
		return total;
		
	}
	
}
