package com.tiendagenerica.tienda.Servicios;

import com.tiendagenerica.tienda.Entidades.Clientes;

public interface IClientesServicios {
	
	Clientes buscarId(int cedula);
	
	void crear(Clientes cliente);
	
	void actualizar(Clientes cliente, int cedula);
	
	void eliminar(int cedula);
}
