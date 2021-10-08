package com.tiendagenerica.tienda.Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name ="detalle_productos")
public class DetalleProducto {

	@Id
	private int codigo_detalleVenta;
	private int cantidad_Producto;
	
	@ManyToOne
	@JoinColumn(name="cod_producto", referencedColumnName ="codigo_producto")
	private Producto producto;
	
	//Referenciamos que es una relacion de muchos a uno
	@ManyToOne
	//Le damos el nombre de la clave foranea y lo refenciamos con la clave primaria de Venta
	@JoinColumn(name="cod_venta", referencedColumnName ="codigo_venta")
	private Ventas venta;
	
	private double valor_subtotal;
	private double valor_iva;
	private double valor_total;
	
	
	public int getCodigo_detalleVenta() {
		return codigo_detalleVenta;
	}
	
	public void setCodigo_detalleVenta(int codigo_detalleVenta) {
		this.codigo_detalleVenta = codigo_detalleVenta;
	}
	
	public int getCantidad_Producto() {
		return cantidad_Producto;
	}
	
	public void setCantidad_Producto(int cantidad_Producto) {
		this.cantidad_Producto = cantidad_Producto;
	}
	
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Ventas getVenta() {
		return venta;
	}

	public void setVenta(Ventas venta) {
		this.venta = venta;
	}

	public double getValor_subtotal() {
		return valor_subtotal;
	}
	
	public void setValor_subtotal(double valor_subtotal) {
		this.valor_subtotal = valor_subtotal;
	}
	
	public double getValor_iva() {
		return valor_iva;
	}
	
	public void setValor_iva(double valor_iva) {
		this.valor_iva = valor_iva;
	}
	
	public double getValor_total() {
		return valor_total;
	}
	
	public void setValor_total(double valor_total) {
		this.valor_total = valor_total;
	}
	
	
	
}
