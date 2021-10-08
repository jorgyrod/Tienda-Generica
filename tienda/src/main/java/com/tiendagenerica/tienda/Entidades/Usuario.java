package com.tiendagenerica.tienda.Entidades;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {

	@Id
	private int cedula;
	
	@Column(name="NOMBRE", length = 255)
	private String nombre;
	@Column(name="EMAIL", length = 255)
	private String email;
	@Column(name="USERNAME", length = 255)
	private String username;
	@Column(name="PASSWORD", length = 255)
	private String password;
	
	/*Como el Usuario puede hacer varias ventas entonces..
	 *Es un relacion de uno a muchos, donde guardaremos esa lista de ventas
	 *El mappedBy = usuario es como lo definimos en la Entidad Ventas
	 *ya que nos mapeara con el atributo usuario definido en esa entidad
	*/
	
	
	public Usuario() {

	}
	
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
