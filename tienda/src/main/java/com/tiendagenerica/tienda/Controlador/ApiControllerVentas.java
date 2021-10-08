package com.tiendagenerica.tienda.Controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiendagenerica.tienda.DTO.DetalleDTO;
import com.tiendagenerica.tienda.DTO.VentaDTO;
import com.tiendagenerica.tienda.Servicios.IVentaServicios;

//Permite conexiones desde diferentes puntos a nuestra api
@CrossOrigin("*")
@RestController
@RequestMapping("/ventas")
public class ApiControllerVentas {

	@Autowired
	private IVentaServicios ventaService;
	
	//Detalle Producto
	//------------------------------------
	
	//Añadir detalle
	
	@PostMapping(value = "/crearDetalle", consumes = MediaType.APPLICATION_JSON_VALUE)
	//El responsenetity nos traera una respuesta http ejemplo 200,404 etc
	//Ademas utilizamos RequestBody porque sera una peticion que vamos a enviar en el cuerpo 
	
	public ResponseEntity<Object> crearDetalle (@RequestBody DetalleDTO detalleDTO){
		this.ventaService.añadirDetalle(detalleDTO);
		return ResponseEntity.ok(Boolean.TRUE);
	}
	
	//Venta
	//------------------------------------
	
	//Crear Venta
	
	@PostMapping(value = "/crearVenta", consumes = MediaType.APPLICATION_JSON_VALUE)
	//El responsenetity nos traera una respuesta http ejemplo 200,404 etc
	//Ademas utilizamos RequestBody porque sera una peticion que vamos a enviar en el cuerpo 
	
	public ResponseEntity<Object> crearVenta (@RequestBody VentaDTO ventaDTO){
		this.ventaService.crearVenta(ventaDTO);
		return ResponseEntity.ok(Boolean.TRUE);
	}
}
