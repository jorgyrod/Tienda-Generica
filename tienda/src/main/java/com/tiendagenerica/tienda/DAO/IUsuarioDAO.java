package com.tiendagenerica.tienda.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendagenerica.tienda.Entidades.Usuario;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Integer>{
	//Creamos un metodo personalizado que nos hara una busqueda por nombre
	//y nos devolvera un objeto de tipo usuario
	Usuario findByUsername(String username);
}
