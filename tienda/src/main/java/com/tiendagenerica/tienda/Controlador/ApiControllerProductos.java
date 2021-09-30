package com.tiendagenerica.tienda.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.tiendagenerica.tienda.Servicios.CSVProducto;
import com.tiendagenerica.tienda.Servicios.IProductosService;



@RestController
@RequestMapping("/productos")
public class ApiControllerProductos {
	
	@Autowired
	IProductosService productoServicio;
	
	//Cargar archivo csv
	//-----------------------------------------------------
	
	@PostMapping("/cargar")
	 public ResponseEntity<Object> subirArchivo(@RequestParam("file") MultipartFile file){
		if(CSVProducto.formatoCSV(file)) {
			
			this.productoServicio.guardar(file);
		}
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Object> buscarProducto(@PathVariable int id){
		return ResponseEntity.ok(this.productoServicio.buscarProducto(id));
	}
	
	@GetMapping("/listar")
	public ResponseEntity<Object> listarProductos(){
		return ResponseEntity.ok(this.productoServicio.listar());
	}
}
