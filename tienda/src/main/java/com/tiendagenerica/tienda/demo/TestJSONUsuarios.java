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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.tiendagenerica.tienda.DTO.UsuarioDTO;
import com.tiendagenerica.tienda.Entidades.Usuario;

public class TestJSONUsuarios {

	private static URL url;
	private static String sitio = "http://localhost:5000/";

	/*
	 * Buscar usuario por su nombre de usuario
	 * ----------------------------------------------------------------------
	 */
	public static Usuario getJSONUsername(String username) throws IOException, ParseException{
		url = new URL(sitio+"usuarios/buscarUser/"+username);
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
		Usuario usuario = new Usuario();
		//al usuario que creamos le enviamos el string para que lo convierta en JSON
		usuario = parsingUsuarioUsername(json);
		http.disconnect();
		return usuario;
	}
	
	public static Usuario parsingUsuarioUsername(String json) throws ParseException {
		//Creamos un convertidor JSON
		JSONParser jsonParser = new JSONParser();
		
		//En un objeto json guardamos el string que recibimos y lo convertimos a json
		JSONObject innerObj = (JSONObject) jsonParser.parse(json);
		//Creamos un nuevo objeto dto y le enviamos los datos que obtuvimos del 
		//parametro String json que recibimos
		Usuario usuario = new Usuario();
	 
		usuario.setCedula(Integer.parseInt(innerObj.get("cedula").toString()));
		usuario.setNombre(innerObj.get("nombre").toString());
		usuario.setEmail(innerObj.get("email").toString());
		usuario.setUsername(innerObj.get("username").toString());
		usuario.setPassword(innerObj.get("password").toString());

		//IMPORTANTE: no enviamos la contrase??a ya que es un dato vulnerable

		//Retornamos el usuario
		return usuario;
	}
	
	
	//Buscar usuario por cedula
	//---------------------------------------
	
	
	public static UsuarioDTO getJSONId(int cedula) throws IOException, ParseException{
		url = new URL(sitio+"usuarios/buscar/"+cedula);
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
		UsuarioDTO usuario = new UsuarioDTO();
		//al usuario que creamos le enviamos el string para que lo convierta en JSON
		usuario = parsingUsuarioId(json);
		http.disconnect();
		return usuario;
	}
	
	public static UsuarioDTO parsingUsuarioId(String json) throws ParseException {
		//Creamos un convertidor JSON
		JSONParser jsonParser = new JSONParser();
		
		//En un objeto json guardamos el string que recibimos y lo convertimos a json
		JSONObject innerObj = (JSONObject) jsonParser.parse(json);
		//Creamos un nuevo objeto dto y le enviamos los datos que obtuvimos del 
		//parametro String json que recibimos
		UsuarioDTO usuario = new UsuarioDTO();
	 
		usuario.setCedula(Integer.parseInt(innerObj.get("cedula").toString()));
		usuario.setNombre(innerObj.get("nombre").toString());
		usuario.setEmail(innerObj.get("email").toString());
		usuario.setUsername(innerObj.get("username").toString());

		//IMPORTANTE: no enviamos la contrase??a ya que es un dato vulnerable

		//Retornamos el usuario
		return usuario;
	}
	
	// Listar Usuarios
	//---------------------------------------
	/*
	 * getJSON: este m??todo realiza la operaci??n GET desde la URL de la API para 
	 * listar todos los usuarios, y obtiene el mensaje JSON, el cual es procesado 
	 * en por el m??todo parsingUsuarios(), y devuelve un ArrayList de tipo Usuarios 
	 * con los usuarios encontrados en el JSON
	 */
	public static ArrayList<UsuarioDTO> getJSON() throws IOException, ParseException{
		url = new URL(sitio+"usuarios/listar");
		HttpURLConnection http = (HttpURLConnection)url.openConnection();
		http.setRequestMethod("GET");
		http.setRequestProperty("Accept", "application/json");
		InputStream respuesta = http.getInputStream();
		byte[] inp = respuesta.readAllBytes();
		String json = "";
		for (int i = 0; i<inp.length ; i++) {
			json += (char)inp[i];
		}
		ArrayList<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		lista = parsingUsuarios(json);
		http.disconnect();
		return lista;
	}
	/*
	 * parsingUsuarios: este m??todo recibe un dato de tipo String correspondiente 
	 * al mensaje JSON recibido de la API de listar usuarios (recordar ejemplo de 
	 * JSON de arriba), y devuelve un ArrayList de tipo Usuarios con los usuarios 
	 * encontrados en el JSON.
	 */
	public static ArrayList<UsuarioDTO> parsingUsuarios(String json) throws ParseException {
		//En esta parte no sera la entidad Usuario sino UsuarioDTO ya que no queremos mostrar
		//Datos sensibles como la contrase??a
		JSONParser jsonParser = new JSONParser();
		ArrayList<UsuarioDTO> lista = new ArrayList<UsuarioDTO>();
		JSONArray usuarios = (JSONArray) jsonParser.parse(json);
		Iterator i = usuarios.iterator();
		while (i.hasNext()) {
			JSONObject innerObj = (JSONObject) i.next();
			UsuarioDTO usuario = new UsuarioDTO();
			usuario.setCedula(Integer.parseInt(innerObj.get("cedula").toString()));
			usuario.setNombre(innerObj.get("nombre").toString());
			usuario.setEmail(innerObj.get("email").toString());
			usuario.setUsername(innerObj.get("username").toString());
			lista.add(usuario);
		}
		return lista;
	}
	
	// Crar Usuario
	//---------------------------------------
	
	/*
	 * postJSON: este m??todo realiza la operaci??n POST a la URL de la API para agregar 
	 * un nuevo usuario. Recibe como par??metro un objeto de tipo Usuario (con el usuario 
	 * que se quiere agregar), y retorna una respuesta HTTP de la URL: si es exitosa 
	 * la operaci??n, ser?? 200, de lo contrario, ser??n respuestas (401, 403, y 404).
	 */
	
	
	public static int postJSON(Usuario usuario) throws IOException {
		url = new URL(sitio+"usuarios/crear");
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
		+ "\"cedula\":\""+ usuario.getCedula()
		+"\",\"nombre\": \""+usuario.getNombre()
		+"\",\"email\": \""+usuario.getEmail()
		+"\",\"username\":\""+usuario.getUsername()
		+"\",\"password\":\""+usuario.getPassword()
		+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	// Actualizar Usuario
	//---------------------------------------
	
	public static int putJSON(Usuario usuario, int cedula) throws IOException {
		
		url = new URL(sitio+"usuarios/actualizar/"+cedula);
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
		+ "\"nombre\": \""+usuario.getNombre()
		+"\",\"email\": \""+usuario.getEmail()
		+"\",\"username\":\""+usuario.getUsername()
		+"\",\"password\":\""+usuario.getPassword()
		+ "\"}";
		byte[] out = data.getBytes(StandardCharsets.UTF_8);
		OutputStream stream = http.getOutputStream();
		stream.write(out);
		int respuesta = http.getResponseCode();
		http.disconnect();
		return respuesta;
	}
	
	// Eliminar Usuario
	//---------------------------------------
	
	public static int deleteJSON(int cedula) throws IOException{
		url = new URL(sitio+"usuarios/eliminar/"+cedula);
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
