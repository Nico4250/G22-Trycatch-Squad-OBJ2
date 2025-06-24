package ar.edu.unq.po2.Muestra;

import ar.edu.unq.po2.Usuario.Usuario;

public class MuestraVerificada implements IEstadoMuestra{
	

	public MuestraVerificada() {
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public boolean esVerificada() {
		return true;
	}
	
	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		//YA NO SE PUEDE OPINAR, NO HACE NADA AGREGAR UNA NUEVA OPINION
			
	}

	@Override
	public void actualizarOpinion(Muestra muestra) {
		//NO HACE NADA YA QUE MUESTRA EN VERIFICACION SE ENCARGA DE HACER LA ULTIMA ACTUALIZACION
	}

	@Override
	public boolean puedeOpinar(Muestra muestra, Usuario usuario) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void agregarOpinionDe(Muestra muestra, Opinion opinion) {
		// TODO Auto-generated method stub
		//TAMPOCO HACE NADA
		
	}

}
