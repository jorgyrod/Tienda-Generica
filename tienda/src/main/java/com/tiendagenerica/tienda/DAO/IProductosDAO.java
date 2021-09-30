package com.tiendagenerica.tienda.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiendagenerica.tienda.Entidades.Producto;



@Repository
public interface IProductosDAO extends JpaRepository<Producto, Integer>{

}
