package com.tiendagenerica.tienda.Servicios;

import java.util.List;

import com.tiendagenerica.tienda.DTO.UsuarioDTO;
import com.tiendagenerica.tienda.Entidades.Usuario;

public interface IUsuarioServicios {

	//PAra mapear sin contraseña (los datos que realmente quiero mostrar)
	List<UsuarioDTO> listar();
	
	//Para buscar por ID
	UsuarioDTO buscarID(int cedula);
	
	//Crear
	void crear(Usuario usuario);
	
	//Actualizar
	void actualizar(Usuario usuario, int id);
	
	//Eliminar
	void eliminar(int id);
	
}
