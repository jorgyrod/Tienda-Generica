package com.tiendagenerica.tienda.Servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiendagenerica.tienda.DAO.IClientesDAO;
import com.tiendagenerica.tienda.Entidades.Cliente;
import com.tiendagenerica.tienda.Utilidades.MHelpers;

@Component
public class ClienteImplementacionService implements IClientesServicios{

	@Autowired
	private IClientesDAO clienteDAO;
	
	//Buscar cliente por cedula
	//-----------------------------------
	
	@Override
	public Cliente buscarId(int cedula) {
		// TODO Auto-generated method stub
		// Nos retorna un optional this.clienteDAO.findById(cedula);
		Optional<Cliente> cliente = this.clienteDAO.findById(cedula);
		
		if(!cliente.isPresent()) {
			return null;
		}
		return MHelpers.modelMapper().map(cliente.get(), Cliente.class);
	}

	//Crear Cliente
	//-----------------------------------
	
	@Override
	public void crear(Cliente cliente) {
		this.clienteDAO.save(cliente);
	}

	//Actualizar Cliente
	//-----------------------------------
	
	@Override
	public void actualizar(Cliente cliente, int cedula) {
		Optional<Cliente> getCliente = this.clienteDAO.findById(cedula);
		
		Cliente clienteUpdate = getCliente.get();
		
		//Actualizamos los campos
		clienteUpdate.setNombre(cliente.getNombre());
		clienteUpdate.setDireccion(cliente.getDireccion());
		clienteUpdate.setTelefono(cliente.getTelefono());
		clienteUpdate.setEmail(cliente.getEmail());
		
		this.clienteDAO.save(clienteUpdate);
	}

	//Eliminar Cliente
	//-----------------------------------
	
	@Override
	public void eliminar(int cedula) {
		this.clienteDAO.deleteById(cedula);
	}

}
