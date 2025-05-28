public class Usuario {

	private int    			id;
	private String 			nombreUsuario;
	private IestadoUsuario 	estado;
	
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
	
	
}
