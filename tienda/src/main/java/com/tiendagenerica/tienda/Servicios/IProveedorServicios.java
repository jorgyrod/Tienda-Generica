package com.tiendagenerica.tienda.Servicios;

import com.tiendagenerica.tienda.Entidades.Proveedor;

public interface IProveedorServicios {

	void crear(Proveedor proveedor);
	
	void actualizar(Proveedor proveedor, int nit);
	
	Proveedor buscarProveedor(int nit);
	
	void eliminar(int nit);
}
