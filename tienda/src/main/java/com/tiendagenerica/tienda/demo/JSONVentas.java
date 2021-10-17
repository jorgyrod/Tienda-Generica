package com.tiendagenerica.tienda.demo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import com.tiendagenerica.tienda.DTO.DetalleDTO;
import com.tiendagenerica.tienda.DTO.VentaDTO;


public class JSONVentas {
	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	
	//Detalle Productos
	
	// Crear detalle
	//---------------------------------------
	
	/*
	 * postJSON: este método realiza la operación POST a la URL de la API para agregar 
	 * un nuevo usuario. Recibe como parámetro un objeto de tipo Usuario (con el usuario 
	 * que se quiere agregar), y retorna una respuesta HTTP de la URL: si es exitosa 
	 * la operación, será 200, de lo contrario, serán respuestas (401, 403, y 404).
	 */
	
	public static int postJSONDetalle(DetalleDTO detalleP) throws IOException {
		url = new URL(sitio+"ventas/crearDetalle");
		
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		
		String data = "{"
				+ "\"cantidad_Producto\":\""+ detalleP.getCantidad_Producto()
				+"\",\"codigo_producto\": \""+ detalleP.getCodigo_producto()
				+ "\"}";
		
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
		
	}
	
	//Venta Productos
	
	// Crear Venta
	//---------------------------------------
	
	public static int postJSONVenta(VentaDTO venta) throws IOException {
		url = new URL(sitio+"ventas/crearVenta");
		
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		try {
			http.setRequestMethod("POST");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		
		String data = "{"
				+ "\"cedula_cliente\":\""+ venta.getCedula_cliente()
				+"\",\"cedula_usuario\": \""+ venta.getCedula_usuario()
				+ "\"}";
		
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
		
	}
}
