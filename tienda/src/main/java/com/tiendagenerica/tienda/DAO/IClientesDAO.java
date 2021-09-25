package com.tiendagenerica.tienda.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendagenerica.tienda.Entidades.Clientes;

@Repository
public interface IClientesDAO extends JpaRepository<Clientes, Integer>{

}
