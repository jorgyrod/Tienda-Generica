package com.tiendagenerica.tienda.Entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="detalle_productos")
public class DetalleProducto {

	@Id
	private int codigo_detalleVenta;
	private int cantidad_Producto;
	private int codigo_producto;
	private int codigo_venta;
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
	
	public int getCodigo_producto() {
		return codigo_producto;
	}
	
	public void setCodigo_producto(int codigo_producto) {
		this.codigo_producto = codigo_producto;
	}
	
	public int getCodigo_venta() {
		return codigo_venta;
	}
	
	public void setCodigo_venta(int codigo_venta) {
		this.codigo_venta = codigo_venta;
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
