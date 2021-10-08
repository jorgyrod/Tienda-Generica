package com.tiendagenerica.tienda.Entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="ventas")
public class Ventas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo_venta;
	
	@ManyToOne
	@JoinColumn(name="ced_cliente", referencedColumnName="cedula_cli")
	private Cliente cliente;
	
	//Referenciamos que es una relacion de muchos a uno
	//Le damos el nombre de la clave foranea y lo refenciamos con la clave primaria de Usuario
	@ManyToOne
	@JoinColumn(name="cedula_usuario", referencedColumnName ="cedula")
	private Usuario usuario;
	
	private double iva_venta;
	private double valor_venta;
	private double valor_total;
	
	@OneToMany(mappedBy="venta")
	private List<DetalleProducto> detalleProductos;

	public int getCodigo_venta() {
		return codigo_venta;
	}
	
	public void setCodigo_venta(int codigo_venta) {
		this.codigo_venta = codigo_venta;
	}
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getIva_venta() {
		return iva_venta;
	}
	
	public void setIva_venta(double iva_venta) {
		this.iva_venta = iva_venta;
	}
	
	public double getValor_venta() {
		return valor_venta;
	}
	
	public void setValor_venta(double valor_venta) {
		this.valor_venta = valor_venta;
	}
	
	public double getValor_total() {
		return valor_total;
	}
	
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	
	
	
	
	
}
