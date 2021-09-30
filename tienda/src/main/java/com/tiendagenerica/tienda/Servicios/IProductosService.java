package com.tiendagenerica.tienda.Servicios;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tiendagenerica.tienda.Entidades.Producto;



public interface IProductosService {
	
	//Listar todos los productos
	List<Producto> listar();
	
	//Para buscar un producto mediante ID
	Producto buscarProducto(int id);
	
	//Agregar Productos mediante un CSV
	void guardar(MultipartFile file);
	
	
}
