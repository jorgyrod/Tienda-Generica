package com.tiendagenerica.tienda.Servicios;

import java.util.List;

import com.tiendagenerica.tienda.Entidades.Cliente;

public interface IClientesServicios {
	
	Cliente buscarId(int cedula);
	
	void crear(Cliente cliente);
	
	void actualizar(Cliente cliente, int cedula);
	
	void eliminar(int cedula);
	
	List<Cliente> listar();
	
	
}
