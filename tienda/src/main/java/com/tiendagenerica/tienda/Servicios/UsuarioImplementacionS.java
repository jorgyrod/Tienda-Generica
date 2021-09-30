package com.tiendagenerica.tienda.Servicios;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiendagenerica.tienda.DAO.IUsuarioDAO;
import com.tiendagenerica.tienda.DTO.UsuarioDTO;
import com.tiendagenerica.tienda.Entidades.Usuario;
import com.tiendagenerica.tienda.Utilidades.MHelpers;

//Nos permite que podamos inyectar dependencias en otra parte del proyecto
@Component
public class UsuarioImplementacionS implements IUsuarioServicios{

	@Autowired
	private IUsuarioDAO usuarioDAO;
	
	//Listar todos los usuarios
	//-----------------------------------
	
	@Override
	public List<UsuarioDTO> listar() {
		
		//Creamos una lista a la cual le asignamos un nuevo arraylist
		List<UsuarioDTO> dto = new ArrayList<>();
		
		Iterable<Usuario> usuarios = this.usuarioDAO.findAll();
		
		//Recorremos el iterable
		for(Usuario user : usuarios) {
			//Por cada usuario que vayamos leyendo del iterable(usuarios) 
			//Se lo asignamos a un nuevo objeto tipo UsuarioDTO
			
			//Esto convertira una entidad user a un objeto UsuarioDTO 
			UsuarioDTO userDTO = MHelpers.modelMapper().map(user, UsuarioDTO.class);
			
			//Añadimos el objeto que acabamos de guardar en la lista dto
			dto.add(userDTO);
		}
		
		//Retornamos la lista
		return dto;
	}
	
	//Buscar usuario por id
	//-----------------------------------
	
	@Override
	public UsuarioDTO buscarID(int cedula) {
		//como el findById nos trae toda la informacion, se la pasamos a un optional usuario
		Optional<Usuario> usuario = this.usuarioDAO.findById(cedula);
		
		if(!usuario.isPresent()) {
			return null;
		}
		//Convertimos la entidad a un objeto
		//Retornamos el objeto
		return MHelpers.modelMapper().map(usuario.get(), UsuarioDTO.class);

	}

	//Crear un usuario
	//-----------------------------------
	
	@Override
	public void crear(Usuario usuario) {
		//Antes de almacenar los datos en la bd vamos a encriptar la password
		//Como parametros le damos...
		//Nos pide la contraseña, el segundo sera el nivel de dificultad de la contraseña
		//usuario.setPassword(BCrypt.hashpw(usuario.getPassword(), BCrypt.gensalt()));
		this.usuarioDAO.save(usuario);
	}

	//Actualizar un usuario
	//-----------------------------------
	
	@Override
	public void actualizar(Usuario usuario, int id) {
		//Primero guardamos el usuario que encuentre por la id
		Optional<Usuario> user = this.usuarioDAO.findById(id);
		
		Usuario userActualizar = user.get();
		
		//Actualizamos los campos
		userActualizar.setNombre(usuario.getNombre());
		userActualizar.setEmail(usuario.getEmail());
		userActualizar.setUsername(usuario.getUsername());
		
		//Evaluamos que la contraseña no este vacia
		if(!usuario.getPassword().isEmpty()) {
			//Aqui debe ir el proceso de encriptado luego se hace
			userActualizar.setPassword(usuario.getPassword());
		}
		
		this.usuarioDAO.save(userActualizar);
		
	}

	//Eliminar un usuario por id
	//-----------------------------------
	
	@Override
	public void eliminar(int id) {
		this.usuarioDAO.deleteById(id);
		
	}

	@Override
	public Usuario buscarUsername(String username) {
		return this.usuarioDAO.findByUsername(username);
	}
	
	//Este metodo nos retornara un objeto de tipo UsuariosDTO por si lo llegamos a necesitar
	/*
	private UsuarioDTO convertToUsuariosDTO(final Usuario usuario) {
		return MHelpers.modelMapper().map(usuario, UsuarioDTO.class);
	}*/

}
