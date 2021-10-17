package com.tiendagenerica.tienda.Servicios;

import java.util.List;

import com.tiendagenerica.tienda.DTO.DetalleDTO;
import com.tiendagenerica.tienda.DTO.VentaDTO;
import com.tiendagenerica.tienda.Entidades.DetalleProducto;
import com.tiendagenerica.tienda.Entidades.Ventas;


public interface IVentaServicios {
	
	//Añadir Producto
	//En este caso retornamos un objeto para poder hacer las operaciones
	//de iva, subtotal, y total
	void añadirDetalle(DetalleDTO detalleDTO);
	
	void crearVenta(VentaDTO ventaDTO);
	
	//List<Ventas> ventasCliente(int cedula);
}
