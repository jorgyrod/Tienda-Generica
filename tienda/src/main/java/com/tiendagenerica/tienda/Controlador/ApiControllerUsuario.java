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


import com.tiendagenerica.tienda.Entidades.Usuario;
import com.tiendagenerica.tienda.Servicios.IUsuarioServicios;

//Permite conexiones desde diferentes puntos a nuestra api
@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
public class ApiControllerUsuario {
	
	@Autowired
	private IUsuarioServicios userService;
	
	//Crear usuario 
	//(En este caso en vez de producir JSON consume JSON)
	//---------------------------
	
	@PostMapping(value = "/crear", consumes = MediaType.APPLICATION_JSON_VALUE)
	//El responsenetity nos traera una respuesta http ejemplo 200,404 etc
	//Ademas utilizamos RequestBody porque sera una peticion que vamos a enviar en el cuerpo 
	
	public ResponseEntity<Object> crearUsuario(@RequestBody Usuario usuario){
		//Si nos apareceerror en usuario le damos click y añadimos los throws
		//Post es unicamente para almacenar datos es decir
		//no debemos retornar ningun objeto 
		this.userService.crear(usuario);
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
	public ResponseEntity<Object> actualizarUsuario(@PathVariable int cedula, @RequestBody Usuario usuario){
		this.userService.actualizar(usuario, cedula);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	//Buscar usuario por id
	//(En este caso en vez de producir JSON)
	//---------------------------
	
	@GetMapping(value = "/buscar/{cedula}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> buscarUsuarioId(@PathVariable int cedula){
		return ResponseEntity.ok(this.userService.buscarID(cedula));
	}
	
	//Buscar todos los usuarios
	//(En este caso en vez de producir JSON)
	//---------------------------
	
	@GetMapping(value = "/listar", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> listarUsuarios(){
		return ResponseEntity.ok(this.userService.listar());
	}
	
	//Eliminar usuario
	//---------------------------
	
	@DeleteMapping(value = "/eliminar/{cedula}")
	public ResponseEntity<Object> eliminarUsuario(@PathVariable int cedula){
		this.userService.eliminar(cedula);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	
	
}
