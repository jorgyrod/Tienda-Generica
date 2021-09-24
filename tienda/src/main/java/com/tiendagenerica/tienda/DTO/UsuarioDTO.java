package com.tiendagenerica.tienda.DTO;

/*
 * Esta clase nos permitira mostrar informacion sin que se filtre la 
 * contraseña, esto mas que todo por buenas practicas.
 * 
 * Evita mostrar datos sensibles.
 */
public class UsuarioDTO {

	private int cedula;
	private String nombre;
	private String email;
	private String username;
	
	public int getCedula() {
		return cedula;
	}
	
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
