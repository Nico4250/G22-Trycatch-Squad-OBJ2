package ar.edu.unq.po2.Muestra;

import ar.edu.unq.po2.Usuario.Usuario;

public class MuestraVerificada implements IEstadoMuestra{
	

	public MuestraVerificada() {
	}
	
	

	@Override
	public boolean esVerificada() {
		return true;
	}
	
	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
			
	}

	@Override
	public void actualizarOpinion(Muestra muestra) {
	}

	@Override
	public boolean puedeOpinar(Muestra muestra, Usuario usuario) {
		return false;
	}

}
