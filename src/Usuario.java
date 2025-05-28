public class Usuario {

	private int    			id;
	private String 			nombreUsuario;
	private EstadoUsuario 	estado;
	
	public Usuario(int id, String nombreUsuario) {
		this.id = id;
		this.nombreUsuario = nombreUsuario;
		this.estado = new UsuarioBasico(this);
	}
	
	public int getId() {
		return  id;
	}
	
	public String nombreUsuario() {
		return nombreUsuario;
	}
	
	public estadoUsuario estado() {
		return estado;
	}
}
