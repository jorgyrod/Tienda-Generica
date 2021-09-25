package com.tiendagenerica.tienda.Servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiendagenerica.tienda.DAO.IProveedorDAO;
import com.tiendagenerica.tienda.Entidades.Proveedor;
import com.tiendagenerica.tienda.Utilidades.MHelpers;

@Component
public class ProveedorImplementServices implements IProveedorServicios{

	@Autowired
	private IProveedorDAO proveedorDAO;
	
	//Crear Proveedor
	//-----------------------------------
	
	@Override
	public void crear(Proveedor proveedor) {
		this.proveedorDAO.save(proveedor);
		
	}

	//Actualizar Proveedor
	//-----------------------------------
	@Override
	public void actualizar(Proveedor proveedor, int nit) {
		Optional<Proveedor> getProveedor = this.proveedorDAO.findById(nit);
		
		Proveedor proveedorUpdate = getProveedor.get();
		
		//Actualizamos datos
		
		proveedorUpdate.setNombre(proveedor.getNombre());
		proveedorUpdate.setDireccion(proveedor.getDireccion());
		proveedorUpdate.setTelefono(proveedor.getTelefono());
		proveedorUpdate.setCiudad(proveedor.getCiudad());
		
		this.proveedorDAO.save(proveedorUpdate);
		
	}

	//Buscar Proveedor
	//-----------------------------------
	
	@Override
	public Proveedor buscarProveedor(int nit) {
		Optional<Proveedor> proveedor = this.proveedorDAO.findById(nit);
		
		if(!proveedor.isPresent()) {
			return null;
		}
		return MHelpers.modelMapper().map(proveedor.get(), Proveedor.class);
	}
	
	//Eliminar Proveedor
	//-----------------------------------

	@Override
	public void eliminar(int nit) {
		this.proveedorDAO.deleteById(nit);
		
	}

}
