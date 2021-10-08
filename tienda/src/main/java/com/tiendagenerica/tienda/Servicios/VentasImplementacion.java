package com.tiendagenerica.tienda.Servicios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tiendagenerica.tienda.DAO.IClientesDAO;
import com.tiendagenerica.tienda.DAO.IDetalle_ProductoDAO;
import com.tiendagenerica.tienda.DAO.IProductosDAO;
import com.tiendagenerica.tienda.DAO.IUsuarioDAO;
import com.tiendagenerica.tienda.DAO.IVentaDAO;
import com.tiendagenerica.tienda.DTO.DetalleDTO;
import com.tiendagenerica.tienda.DTO.VentaDTO;
import com.tiendagenerica.tienda.Entidades.Cliente;
import com.tiendagenerica.tienda.Entidades.DetalleProducto;
import com.tiendagenerica.tienda.Entidades.Producto;
import com.tiendagenerica.tienda.Entidades.Usuario;
import com.tiendagenerica.tienda.Entidades.Ventas;


@Component
public class VentasImplementacion implements IVentaServicios{
	@Autowired
	IProductosDAO productoDAO;
	
	@Autowired
	IDetalle_ProductoDAO detalleDAO;
	
	@Autowired
	IVentaDAO ventaDAO;
	
	@Autowired
	IUsuarioDAO usuarioDAO;
	
	@Autowired
	IClientesDAO clienteDAO;
	
	
	/*
	 * EL DTO NOS PERMITE TRANSPORTAR LOS DATOS A LAS ENTIDADES
	 * YA QUE CON ELLO, PODREMOS BUSCAR LOS OBJETOS QUE NECITEMOS EJEMPLO
	 * SI QUEREMOS PASAR UN PRODUCTO PRIMERO LO BUSCAMOS Y LUEGO SE LO PASAMOS
	 * A LA ENTIDAD, PERO PARA BUSCARLO NECESITAMOS EL DATO QUE NOS TRAE EL DTO
	 */
	
	
	//Añadir Descripcion de producto
	
	@Override
	public void añadirDetalle(DetalleDTO detalleDTO) {
		double subtotal,iva,total;
		DetalleProducto detalleP = new DetalleProducto();
		
		detalleP.setCodigo_detalleVenta(detalleDTO.getCodigo_detalleVenta());
		detalleP.setCantidad_Producto(detalleDTO.getCantidad_Producto());
		
		//Buscamos el producto mediante el codigo_producto de DetalleProducto
		Optional<Producto> producto = this.productoDAO.findById(detalleDTO.getCodigo_producto());
		
		Optional<Ventas> venta = this.ventaDAO.findById(detalleDTO.getCodigo_venta());
		
		//Si se encontro el producto procedemos a hacer operaciones con el
		
		//Por ahora requiere que la venta se haya guardado primero en la base de datos
		if(producto.isPresent() && venta.isPresent()) {
			//Hacemos la operacion del subtotal
			subtotal = detalleDTO.getCantidad_Producto() * producto.get().getPrecio_venta();
			//Hacemos la del iva
			iva = (subtotal * producto.get().getIvacompra()) / 100;
			//HAcemos la operacion del total
			total = subtotal + iva;
			
			//Enviamos esos datos calculados al objeto entidad DetalleProduct
			detalleP.setValor_subtotal(subtotal);
			detalleP.setValor_iva(iva);
			detalleP.setValor_total(total);
			
			//Le pasamos el producto al detalle
			detalleP.setProducto(producto.get());
			//Le pasamos La venta al detalle
			detalleP.setVenta(venta.get());
		}
		
		

		this.detalleDAO.save(detalleP);
	}
	
	//Añadir la venta

	@Override
	public void crearVenta(VentaDTO ventaDTO) {
		//Creamos un objeto de tipo entidad
		Ventas venta = new Ventas();
		
		//Pasamos los valores
		venta.setIva_venta(ventaDTO.getIva_venta());
		venta.setValor_venta(ventaDTO.getValor_venta());
		venta.setValor_total(ventaDTO.getValor_total());
		
		//Buscamos tanto usuario como cliente y los guardamos
		Optional<Cliente> cliente = this.clienteDAO.findById(ventaDTO.getCedula_cliente());
		Optional<Usuario> usuario = this.usuarioDAO.findById(ventaDTO.getCedula_usuario());

		//Verificamos que si existan, y si es asi se envian al objeto creado
		if(cliente.isPresent() && usuario.isPresent()) {
			venta.setCliente(cliente.get());
			venta.setUsuario(usuario.get());
		}
		//Lo guardamos en la base de datos
		this.ventaDAO.save(venta);
	}

}
