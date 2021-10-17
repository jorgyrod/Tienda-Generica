package com.tiendagenerica.tienda.Entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "clientes")
public class Cliente {
	
	@Id
	private int cedula_cli;
	
	@Column(name="NOMBRE", length = 255)
	private String nombre;
	@Column(name="DIRECCION", length = 255)
	private String direccion;
	@Column(name="TELEFONO")
	private int telefono;
	@Column(name="EMAIL", length = 255)
	private String email;
	
	@OneToMany(mappedBy="cliente")
	private List<Ventas> ventas;
	
	
	
	public void setVentas(List<Ventas> ventas) {
		this.ventas = ventas;
	}

	public List<Ventas> getVentas() {
		return ventas;
	}

	public int getCedula() {
		return cedula_cli;
	}
	
	public void setCedula(int cedula) {
		this.cedula_cli = cedula;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDireccion() {
		return direccion;
	}
	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public int getTelefono() {
		return telefono;
	}
	
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
