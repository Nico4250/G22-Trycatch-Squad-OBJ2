package ar.edu.unq.po2.Muestra;

import ar.edu.unq.po2.Usuario.Usuario;

public class MuestraVerificada implements IEstadoMuestra{
	
	private Muestra muestra;

	public MuestraVerificada(Muestra muestra) {
		// TODO Auto-generated constructor stub
		this.setMuestra(muestra);
	}
	
	

	@Override
	public boolean esVerificada() {
		return true;
	}
	
	@Override
	public void agregarOpinion(Opinion opinion) {
		//YA NO SE PUEDE OPINAR
		if (!this.puedeOpinar(opinion.getUsuario())) {
			throw new RuntimeException("No se puede opinar sobre esta muestra, ya se encuentra verificada");
		}
		
	}

	@Override
	public void actualizarOpinion() {
		//NO HACE NADA YA QUE MUESTRA EN VERIFICACION SE ENCARGA DE HACER LA ULTIMA ACTUALIZACION
	}

	@Override
	public boolean puedeOpinar(Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}


	private void setMuestra(Muestra muestra) {
		this.muestra = muestra;
	}

}
