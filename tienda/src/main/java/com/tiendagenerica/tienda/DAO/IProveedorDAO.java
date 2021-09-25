package com.tiendagenerica.tienda.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendagenerica.tienda.Entidades.Proveedor;

@Repository
public interface IProveedorDAO extends JpaRepository<Proveedor, Integer>{

}
