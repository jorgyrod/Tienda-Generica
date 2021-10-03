package com.tiendagenerica.tienda.Servicios;

import com.tiendagenerica.tienda.Entidades.DetalleProducto;
import com.tiendagenerica.tienda.Entidades.Ventas;

public interface IVentaServicios {
	
	//Añadir Producto
	//En este caso retornamos un objeto para poder hacer las operaciones
	//de iva, subtotal, y total
	void añadirDetalle(DetalleProducto detalleP);
	
	void crearVenta(Ventas venta);
}
