package ar.edu.unq.po2.Usuario;

public class UsuarioEspecialista extends Usuario {

	public UsuarioEspecialista(int id, String nombreUsuario) {
		super(id, nombreUsuario);
		this.esEspecialista = true;
		this.promoverAExperto();
	}

}
