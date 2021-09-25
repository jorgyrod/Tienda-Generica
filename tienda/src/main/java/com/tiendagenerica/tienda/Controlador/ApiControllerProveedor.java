package com.tiendagenerica.tienda.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendagenerica.tienda.Entidades.Proveedor;
import com.tiendagenerica.tienda.Servicios.IProveedorServicios;

//Permite conexiones desde diferentes puntos a nuestra api
@CrossOrigin("*")
@RestController
@RequestMapping("/proveedor")
public class ApiControllerProveedor {

	@Autowired
	private IProveedorServicios proveedorService;
	
	//Crear Proveedor
	//(En este caso en vez de producir JSON consume JSON)
	//---------------------------
	
	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE)
	//El responsenetity nos traera una respuesta http ejemplo 200,404 etc
	//Ademas utilizamos RequestBody porque sera una peticion que vamos a enviar en el cuerpo 
	public ResponseEntity<Object> crearProveedor(@RequestBody Proveedor proveedor){
		this.proveedorService.crear(proveedor);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	//Actualizar Proveedor
	//(En este caso en vez de producir JSON consume JSON)
	//---------------------------
	
	@PutMapping(value = "/actualizar/{nit}")
	/*
	 * En el PathVariable podemos colocarlo como cedula ya que asi lo definimos en el putmapping
	 * pero si lo queremos hacer diferente seria asi: @PathVariable("cedula") int id
	 * id tomaria el valor para cedula
	 */
	
	public ResponseEntity<Object> actualizarProveedor(@PathVariable int nit, @RequestBody Proveedor proveedor){
		this.proveedorService.actualizar(proveedor, nit);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	//Buscar Proveedor por nit
	//(En este caso en vez de producir JSON)
	//---------------------------
	
	@GetMapping(value = "/buscar/{nit}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> buscarProveedor(@PathVariable int nit){
		return ResponseEntity.ok(this.proveedorService.buscarProveedor(nit));
	}
	
	//Eliminar Proveedor por nit
	//---------------------------
	
	@DeleteMapping(value = "/eliminar/{nit}")
	public ResponseEntity<Object> eliminarProveedor(@PathVariable int nit){
		this.proveedorService.eliminar(nit);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	
}
