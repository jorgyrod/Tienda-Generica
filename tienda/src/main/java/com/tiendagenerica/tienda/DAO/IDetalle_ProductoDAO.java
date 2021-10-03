package com.tiendagenerica.tienda.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendagenerica.tienda.Entidades.DetalleProducto;

@Repository
public interface IDetalle_ProductoDAO extends JpaRepository<DetalleProducto, Integer>{

}
