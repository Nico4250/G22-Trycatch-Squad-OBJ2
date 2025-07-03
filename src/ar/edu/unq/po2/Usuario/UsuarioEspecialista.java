package ar.edu.unq.po2.Usuario;

public class UsuarioEspecialista extends Usuario {

	public UsuarioEspecialista(int id, String nombreUsuario) {
		super(id, nombreUsuario);
		this.promoverAExperto();
	}

@Override
	//degradar a basico ya no le afecta
	public void degradarABasico() {
		// TODO Auto-generated method stub
	}


}

