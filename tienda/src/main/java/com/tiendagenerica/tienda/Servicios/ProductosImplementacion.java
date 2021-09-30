package com.tiendagenerica.tienda.Servicios;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.tiendagenerica.tienda.DAO.IProductosDAO;
import com.tiendagenerica.tienda.Entidades.Producto;



@Component
public class ProductosImplementacion implements IProductosService{

	@Autowired
	IProductosDAO productoDAO;
	
	//Listar todos los productos
	/*---------------------------------------------*/
	
	@Override
	public List<Producto> listar() {

		//Creammos una nueva lista
		List<Producto> listaProductos = new ArrayList<>();
		//Como el findAll nos devuelve un iterable con todos los usuarios 
		//se lo asignamos a un nuevo iterable
		
		Iterable<Producto> productosBD = this.productoDAO.findAll();
		
		//Hacemos un ciclo para añadir esa lista de productos de la base de datos
		//a la lista que creamos al principio
		for(Producto prod : productosBD) {
			//Por cada producto encontrado en productosBD se lo añadimos a la listaProductos
			listaProductos.add(prod);
		}
		return listaProductos;
	}

	//Buscar un producto por id
	/*---------------------------------------------*/
	
	@Override
	public Producto buscarProducto(int id) {
		Optional<Producto> producto = this.productoDAO.findById(id);
		
		if(!producto.isPresent()) {
			return null;
		}
		return producto.get();
	}

	//Guardar los productos del CSV en la base de datos
	/*---------------------------------------------*/
	
	@Override
	public void guardar(MultipartFile file) {
		try {
			List<Producto> listaProductos = CSVProducto.csvProductos(file.getInputStream());
			productoDAO.saveAll(listaProductos);
		}catch(IOException e) {
			throw new RuntimeException("Fallo al guardar datos del csv: " + e.getMessage());
		}
	}

}
