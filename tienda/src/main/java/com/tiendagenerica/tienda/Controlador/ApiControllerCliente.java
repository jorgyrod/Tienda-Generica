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

import com.tiendagenerica.tienda.Entidades.Clientes;
import com.tiendagenerica.tienda.Servicios.IClientesServicios;

//Permite conexiones desde diferentes puntos a nuestra api
@CrossOrigin("*")
@RestController
@RequestMapping("/clientes")
public class ApiControllerCliente {

	@Autowired
	private IClientesServicios clienteService;
	
	//Crear cliente
	//(En este caso en vez de producir JSON consume JSON)
	//---------------------------
	
	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE)
	//El responsenetity nos traera una respuesta http ejemplo 200,404 etc
	//Ademas utilizamos RequestBody porque sera una peticion que vamos a enviar en el cuerpo 
	
	public ResponseEntity<Object> crearCliente(@RequestBody Clientes cliente){
		this.clienteService.crear(cliente);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	//Actualizar
	//(En este caso en vez de producir JSON consume JSON)
	//---------------------------
	
	@PutMapping(value = "/actualizar/{cedula}")
	/*
	 * En el PathVariable podemos colocarlo como cedula ya que asi lo definimos en el putmapping
	 * pero si lo queremos hacer diferente seria asi: @PathVariable("cedula") int id
	 * id tomaria el valor para cedula
	 */
	public ResponseEntity<Object> actualizarCliente(@PathVariable int cedula, @RequestBody Clientes cliente){
		this.clienteService.actualizar(cliente, cedula);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	//Buscar usuario por id
	//(En este caso en vez de producir JSON)
	//---------------------------
	
	@GetMapping(value = "/buscar/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> buscarCedula(@PathVariable int cedula){
		return ResponseEntity.ok(this.clienteService.buscarId(cedula));
	}
	
	//Eliminar usuario
	//---------------------------
	
	@DeleteMapping(value = "/eliminar/{cedula}")
	public ResponseEntity<Object> eliminarCliente(@PathVariable int cedula){
		this.clienteService.eliminar(cedula);
		return ResponseEntity.ok(Boolean.TRUE);
	}
}
