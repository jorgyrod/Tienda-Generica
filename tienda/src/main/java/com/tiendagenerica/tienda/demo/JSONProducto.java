package com.tiendagenerica.tienda.demo;



import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.tiendagenerica.tienda.Entidades.Producto;



public class JSONProducto {
	private static URL url;
	private static String sitio = "http://localhost:5000/";
	
	// Buscar Productos por codigo
	//---------------------------------------
	
	
	public static Producto getJSONProducto(int codigo) throws IOException, ParseException{
		url = new URL(sitio+"productos/buscar/"+codigo);
		
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		//Esto nos convertira los datos a string
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		
		Producto producto = new Producto();
		producto = parsingProducto(json);
		return producto;
	}
	
	public static Producto parsingProducto (String json) throws ParseException{
		//Creamos un convertidor JSON
		JSONParser jsonParser = new JSONParser();
				
		//En un objeto json guardamos el string que recibimos y lo convertimos a json
		JSONObject innerObj = (JSONObject) jsonParser.parse(json);
		//Creamos un nuevo objeto y le enviamos los datos que obtuvimos del 
		//parametro String json que recibimos
		Producto producto = new Producto();
		
		producto.setCodigo_producto(Integer.parseInt(innerObj.get("codigo_producto").toString()));
		producto.setNombre_producto(innerObj.get("nombre_producto").toString());
		producto.setNitproveedor(Integer.parseInt(innerObj.get("nitproveedor").toString()));
		producto.setPrecio_compra(Double.parseDouble(innerObj.get("precio_compra").toString()));
		producto.setIvacompra(Double.parseDouble(innerObj.get("ivacompra").toString()));
		producto.setPrecio_venta(Double.parseDouble(innerObj.get("precio_venta").toString()));
		
		return producto;

	}
}
