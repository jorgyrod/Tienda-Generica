package com.tiendagenerica.tienda.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tiendagenerica.tienda.Entidades.Ventas;

@Repository
public interface IVentaDAO extends JpaRepository<Ventas, Integer>{
	
	//@Query(value = "SELECT * FROM ventas where ventas.cedula = id", nativeQuery=true)
    //List<Ventas> ventaPorCliente (int id);
}
