package com.tiendagenerica.tienda.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendagenerica.tienda.Entidades.Ventas;

@Repository
public interface IVentaDAO extends JpaRepository<Ventas, Integer>{
	
}
