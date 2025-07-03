package ar.edu.unq.po2.Muestra;

import java.util.ArrayList;

import ar.edu.unq.po2.Usuario.Usuario;

public class MuestraEnVerificacion implements IEstadoMuestra {


	public MuestraEnVerificacion() {

	}

	@Override
	public boolean esVerificada() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void agregarOpinion(Muestra muestra, Opinion opinion) {
		// TODO Auto-generated method stub
				
		if (!muestra.puedeOpinar(opinion.getUsuario())) {
		//LO MANEJO CON EXCEPCIONES PARA TESTS, SI ES NECESARIO LO CAMBIAMOS
			throw new RuntimeException("No se puede opinar sobre esta muestra");
		}
		muestra.agregarOpinionDe(opinion);
		muestra.actualizarOpinion();
					
		boolean otroExpertoEstaDeAcuerdo = muestra.opinionesExpertos().stream().anyMatch(o -> o.getOpinion() == opinion.getOpinion());
			 
		if (otroExpertoEstaDeAcuerdo) {
			muestra.cambiarEstado(new MuestraVerificada());
			muestra.getSistema().muestraFueValidada(muestra);
		}
	}

	@Override
	public void actualizarOpinion(Muestra muestra) {
		// TODO Auto-generated method stub
		//SOLAMENTE TOMA EN CUENTA OPINIONES DE EXPERTO
		ArrayList<OpinionImagen> opinionesDeImagen = new ArrayList<OpinionImagen>();
		muestra.opinionesExpertos().stream().forEach(opinion -> opinionesDeImagen.add(opinion.getOpinion()));
		muestra.actualizarOpinionActual(opinionesDeImagen);
		
	}

	@Override
	public boolean puedeOpinar(Muestra muestra, Usuario usuario) {
		// TODO Auto-generated method stub
		//PUEDE OPINAR SOLO SI EL USUARIO NO OPINO AUN, SI NO ES EL AUTOR DE LA MUESTRA Y SI ES UN EXPERTO
		return (muestra.usuarioPuedeOpinar(usuario) && usuario.esExperto()) ;
	}


}
