package com.tiendagenerica.tienda.Entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ventas")
public class Ventas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo_venta;
	private int cedula_cliente;
	private int cedula_usuario;
	private double iva_venta;
	private double valor_venta;
	private double valor_total;
	
	public int getCodigo_venta() {
		return codigo_venta;
	}
	
	public void setCodigo_venta(int codigo_venta) {
		this.codigo_venta = codigo_venta;
	}
	
	public int getCedula_cliente() {
		return cedula_cliente;
	}
	
	public void setCedula_cliente(int cedula_cliente) {
		this.cedula_cliente = cedula_cliente;
	}
	
	public int getCedula_usuario() {
		return cedula_usuario;
	}
	
	public void setCedula_usuario(int cedula_usuario) {
		this.cedula_usuario = cedula_usuario;
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
