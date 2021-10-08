package com.tiendagenerica.tienda.Servicios;

import com.tiendagenerica.tienda.DTO.DetalleDTO;
import com.tiendagenerica.tienda.DTO.VentaDTO;


public interface IVentaServicios {
	
	//Añadir Producto
	//En este caso retornamos un objeto para poder hacer las operaciones
	//de iva, subtotal, y total
	void añadirDetalle(DetalleDTO detalleDTO);
	
	void crearVenta(VentaDTO ventaDTO);
}
