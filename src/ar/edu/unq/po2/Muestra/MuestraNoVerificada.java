package ar.edu.unq.po2.Muestra;

import java.util.ArrayList;

import ar.edu.unq.po2.Usuario.Usuario;

public class MuestraNoVerificada implements IEstadoMuestra{


	public MuestraNoVerificada() {

	}

	@Override
	public boolean esVerificada() {
		return false;
	}

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		
		if (!this.puedeOpinar(muestra, opinion.getUsuario())) {
			//LO MANEJO CON EXCEPCIONES PARA TESTS, SI ES NECESARIO LO CAMBIAMOS
			throw new RuntimeException("No se puede opinar sobre esta muestra");
		}
		
			muestra.agregarOpinionDe(opinion);
			muestra.actualizarOpinion();
	 
		if (opinion.getUsuario().esExperto()) {
			muestra.cambiarEstado(new MuestraEnVerificacion());
			//recomparar en cambio de estado
		}
	}
		
	@Override
	public boolean puedeOpinar(Muestra muestra, Usuario usuario) {
		//PUEDE OPINAR SOLO SI EL USUARIO NO OPINO AUN, Y SI NO ES EL AUTOR DE LA MUESTRA
		return (muestra.elUsuarioNoOpino(usuario) && muestra.getUsuario() != usuario) ;
	}	
		

	@Override
	public void actualizarOpinion(Muestra muestra) {
			//TOMA EN CUENTA TODAS LAS OPINIONES
			ArrayList<OpinionImagen> opinionesDeImagen = new ArrayList<OpinionImagen>();
			muestra.getOpiniones().stream().forEach(opinion -> opinionesDeImagen.add(opinion.getOpinion()));
			muestra.actualizarOpinionActual(opinionesDeImagen);
		
	}

}
