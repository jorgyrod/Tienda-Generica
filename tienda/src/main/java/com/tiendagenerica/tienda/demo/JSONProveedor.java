package com.tiendagenerica.tienda.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.tiendagenerica.tienda.Entidades.Proveedor;

public class JSONProveedor {
	private static URL url;
	private static String sitio = "http://localhost:5000/";

	
	//Buscar proveedor por cedula
	//---------------------------------------
	
	public static Proveedor getJSONProvId(int nit) throws IOException, ParseException {
		url = new URL(sitio+"proveedor/buscar/"+nit);
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
		//al proveedor que creamos le enviamos el string para que lo convierta en JSON
		Proveedor prove = new Proveedor();
		prove = parsingProveedorNit(json);
		return prove;
	}
	
	//Obtenemos el proveedor y lo retornamos convertido 
	public static Proveedor parsingProveedorNit(String json) throws ParseException {
		//Creamos un convertidor JSON
		JSONParser jsonParser = new JSONParser();
				
		//En un objeto json guardamos el string que recibimos y lo convertimos a json
		JSONObject innerObj = (JSONObject) jsonParser.parse(json);
		//Creamos un nuevo objeto y le enviamos los datos que obtuvimos del 
		//parametro String json que recibimos
		
		Proveedor proveedor = new Proveedor();
		
		proveedor.setNit(Integer.parseInt(innerObj.get("nit").toString()));
		proveedor.setNombre(innerObj.get("nombre").toString());
		proveedor.setDireccion(innerObj.get("direccion").toString());
		proveedor.setTelefono(Integer.parseInt(innerObj.get("telefono").toString()));
		proveedor.setCiudad(innerObj.get("ciudad").toString());
		
		//Retornamos el proveedor
		return proveedor;
				
	}
	
	// Crear Proveedor
		//---------------------------------------
		
		/*
		 * postJSON: este método realiza la operación POST a la URL de la API para agregar 
		 * un nuevo usuario. Recibe como parámetro un objeto de tipo Usuario (con el usuario 
		 * que se quiere agregar), y retorna una respuesta HTTP de la URL: si es exitosa 
		 * la operación, será 200, de lo contrario, serán respuestas (401, 403, y 404).
		 */
	
	public static int postJSONProveedor(Proveedor proveedor) throws IOException{
		url = new URL(sitio+"proveedor/crear");
		
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
		+ "\"nit\":\""+ proveedor.getNit()
		+"\",\"nombre\": \""+proveedor.getNombre()
		+"\",\"direccion\": \""+proveedor.getDireccion()
		+"\",\"telefono\":\""+proveedor.getTelefono()
		+"\",\"ciudad\":\""+proveedor.getCiudad()
		+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	// Actualizar Proveedor mediante el nit
	//---------------------------------------
	
	public static int putJSONProveedor(Proveedor proveedor, int nit) throws IOException{
		url = new URL(sitio+"proveedor/actualizar/"+nit);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		try {
			http.setRequestMethod("PUT");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		String data = "{"
			+ "\"nombre\": \""+proveedor.getNombre()
			+ "\",\"direccion\": \""+proveedor.getDireccion()
			+ "\",\"telefono\": \""+proveedor.getTelefono()
			+ "\",\"ciudad\": \""+proveedor.getCiudad()
			+ "\"}";
		
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	// Eliminar Proveedor
	//---------------------------------------
	
	public static int deleteJSONProveedor(int nit) throws IOException{
		url = new URL(sitio+"proveedor/eliminar/"+nit);
		HttpURLConnection http;
		http = (HttpURLConnection)url.openConnection();
		try {
			http.setRequestMethod("DELETE");
		} catch (ProtocolException e) {
			e.printStackTrace();
		}
		http.setDoOutput(true);
		http.setRequestProperty("Accept", "application/json");
		http.setRequestProperty("Content-Type", "application/json");
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	
	
}
