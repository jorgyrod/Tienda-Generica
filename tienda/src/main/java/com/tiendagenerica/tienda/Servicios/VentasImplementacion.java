package com.tiendagenerica.tienda.Servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiendagenerica.tienda.DAO.IDetalle_ProductoDAO;
import com.tiendagenerica.tienda.DAO.IProductosDAO;
import com.tiendagenerica.tienda.DAO.IVentaDAO;
import com.tiendagenerica.tienda.Entidades.DetalleProducto;
import com.tiendagenerica.tienda.Entidades.Producto;
import com.tiendagenerica.tienda.Entidades.Ventas;

@Component
public class VentasImplementacion implements IVentaServicios{
	@Autowired
	IProductosDAO productoDAO;
	
	@Autowired
	IDetalle_ProductoDAO detalleDAO;
	
	@Autowired
	IVentaDAO ventaDAO;
	//Añadir Descripcion de producto
	
	@Override
	public void añadirDetalle(DetalleProducto detalleP) {
		double subtotal,iva,total;
		//Buscamos el producto mediante el codigo_producto de DetalleProducto
		Optional<Producto> producto = this.productoDAO.findById(detalleP.getCodigo_producto());
		
		//Hacemos la operacion del subtotal
		subtotal = detalleP.getCantidad_Producto() * producto.get().getPrecio_venta();
		
		//Hacemos la operacion del IVA
		iva = (subtotal * producto.get().getIvacompra()) / 100;
		
		//Hacemos la operacion del subtotal
		total = subtotal + iva;
		
		detalleP.setValor_subtotal(subtotal);
		detalleP.setValor_iva(iva);
		detalleP.setValor_total(total);
		
		this.detalleDAO.save(detalleP);
	}
	
	//Añadir la venta

	@Override
	public void crearVenta(Ventas venta) {
		this.ventaDAO.save(venta);
	}

}
