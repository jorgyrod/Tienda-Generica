package com.tiendagenerica.tienda.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendagenerica.tienda.Entidades.Usuario;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario, Integer>{

}
