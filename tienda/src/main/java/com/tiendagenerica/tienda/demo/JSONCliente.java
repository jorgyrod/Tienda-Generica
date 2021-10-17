package com.tiendagenerica.tienda.demo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.tiendagenerica.tienda.Entidades.Cliente;
import com.tiendagenerica.tienda.Entidades.Ventas;

public class JSONCliente {
	private static URL url;
	private static String sitio = "http://localhost:5000/";

	
	
	//Listar todos los clientes
	public static ArrayList<Cliente> getJSONLista() throws IOException, ParseException{
		url = new URL(sitio+"clientes/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		lista = parsingClientes(json);
		http.disconnect();
		return lista;
	}
	
	public static ArrayList<Cliente> parsingClientes(String json) throws ParseException{
		JSONParser jsonParser = new JSONParser();
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		JSONArray clientes = (JSONArray) jsonParser.parse(json);
		Iterator i = clientes.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Cliente cliente = new Cliente();
			
			cliente.setCedula(Integer.parseInt(innerObj.get("cedula").toString()));
			cliente.setNombre(innerObj.get("nombre").toString());
			cliente.setDireccion(innerObj.get("direccion").toString());
			cliente.setTelefono(Integer.parseInt(innerObj.get("telefono").toString()));
			cliente.setEmail(innerObj.get("email").toString());
			lista.add(cliente);
		}
		return lista;
	}
	
	//Buscar cliente por cedula
	//---------------------------------------
	public static Cliente getJSONCliente(int cedula) throws IOException, ParseException {
		url = new URL(sitio+"clientes/buscar/"+cedula);
		
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
		//al cliente que creamos le enviamos el string para que lo convierta en un objeto
		//Clientes ya que viene en formato JSON
		Cliente cliente = new Cliente();
		cliente = parsingCliente(json);
		return cliente;
	}
	
	//Obtenemos el cliente y lo retornamos convertido 
	public static Cliente parsingCliente(String json) throws ParseException {
			//Creamos un convertidor JSON
			JSONParser jsonParser = new JSONParser();
					
			//En un objeto json guardamos el string que recibimos y lo convertimos a json
			JSONObject innerObj = (JSONObject) jsonParser.parse(json);
			//Creamos un nuevo objeto y le enviamos los datos que obtuvimos del 
			//parametro String json que recibimos
			
			Cliente cliente = new Cliente();
			
			cliente.setCedula(Integer.parseInt(innerObj.get("cedula").toString()));
			cliente.setNombre(innerObj.get("nombre").toString());
			cliente.setDireccion(innerObj.get("direccion").toString());
			cliente.setTelefono(Integer.parseInt(innerObj.get("telefono").toString()));
			cliente.setEmail(innerObj.get("email").toString());
			
			ArrayList<Ventas> listaVenta = new ArrayList<>();
			//Llamamos a parsingVentasClientes para que nos traiga la lista de ventas
			//que ha hecho el cliente
			listaVenta = parsingVentasClientes(innerObj.get("ventas").toString());
			//Enviamos esas ventas como lista para que podamos operar con ellas en el frontend(Calcular Total)
			cliente.setVentas(listaVenta);
			
			
			return cliente;
	}
	
	//Este metodo esta enlazado con el de parsingCliente, ya que nos traera la lista de ventas
	//que  tiene el cliente buscado
	public static ArrayList<Ventas> parsingVentasClientes(String json) throws ParseException{
		JSONParser jsonParser = new JSONParser();
		ArrayList<Ventas> lista = new ArrayList<Ventas>();
		JSONArray ventas = (JSONArray) jsonParser.parse(json);
		Iterator i = ventas.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			Ventas venta = new Ventas();
			
			//Enviamos los datos que necesitamos
			venta.setCodigo_venta(Integer.parseInt(innerObj.get("codigo_venta").toString()));
			venta.setIva_venta(Double.parseDouble(innerObj.get("iva_venta").toString()));
			venta.setValor_venta(Double.parseDouble(innerObj.get("valor_venta").toString()));
			venta.setValor_total(Double.parseDouble(innerObj.get("valor_total").toString()));

			lista.add(venta);
		}
		return lista;
	}
		
		// Crear Cliente
				//---------------------------------------
				
				/*
				 * postJSON: este método realiza la operación POST a la URL de la API para agregar 
				 * un nuevo usuario. Recibe como parámetro un objeto de tipo Usuario (con el usuario 
				 * que se quiere agregar), y retorna una respuesta HTTP de la URL: si es exitosa 
				 * la operación, será 200, de lo contrario, serán respuestas (401, 403, y 404).
				 */
			
		public static int postJSONCliente(Cliente cliente) throws IOException{
			url = new URL(sitio+"clientes/crear");
			
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
			+ "\"cedula\":\""+ cliente.getCedula()
			+"\",\"nombre\": \""+cliente.getNombre()
			+"\",\"direccion\": \""+cliente.getDireccion()
			+"\",\"telefono\":\""+cliente.getTelefono()
			+"\",\"email\":\""+cliente.getEmail()
			+ "\"}";
			byte[] out = data.getBytes(StandardCharsets.UTF_8);
			OutputStream stream = http.getOutputStream();
			stream.write(out);
			int respuesta = http.getResponseCode();
			http.disconnect();
			return respuesta;
		}
		
		// Actualizar Cliente mediante la cedula
		//---------------------------------------
		
		public static int putJSONCliente(Cliente cliente, int cedula) throws IOException{
			url = new URL(sitio+"clientes/actualizar/"+cedula);
			
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
				+ "\"nombre\": \""+cliente.getNombre()
				+"\",\"direccion\": \""+cliente.getDireccion()
				+"\",\"telefono\":\""+cliente.getTelefono()
				+"\",\"email\":\""+cliente.getEmail()
				+ "\"}";
			
			byte[] out = data.getBytes(StandardCharsets.UTF_8);
			OutputStream stream = http.getOutputStream();
			stream.write(out);
			int respuesta = http.getResponseCode();
			http.disconnect();
			return respuesta;
		}
		

		// Eliminar Cliente
		//---------------------------------------
		
		public static int deleteJSONCliente(int cedula) throws IOException{
			url = new URL(sitio+"clientes/eliminar/"+cedula);
			
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
