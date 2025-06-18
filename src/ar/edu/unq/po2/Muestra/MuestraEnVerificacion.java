package ar.edu.unq.po2.Muestra;

import java.util.ArrayList;

import ar.edu.unq.po2.Usuario.Usuario;

public class MuestraEnVerificacion implements IEstadoMuestra {
	private Muestra muestra;

	public MuestraEnVerificacion(Muestra muestra) {
		this.muestra = muestra;
	}

	@Override
	public boolean esVerificada() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void agregarOpinion(Opinion opinion) {
		// TODO Auto-generated method stub
		boolean otroExpertoEstaDeAcuerdo = muestra.opinionesExpertos().stream().anyMatch(o -> o.getOpinion() == opinion.getOpinion());
		
		if (!this.puedeOpinar(opinion.getUsuario())) {
			//LO MANEJO CON EXCEPCIONES PARA TESTS, SI ES NECESARIO LO CAMBIAMOS
			throw new RuntimeException("No se puede opinar sobre esta muestra");
		}
			this.muestra.agregarOpinionDe(opinion);
			this.muestra.actualizarOpinion();
	 
		if (otroExpertoEstaDeAcuerdo) {
			this.muestra.cambiarEstado(new MuestraVerificada(this.muestra));
			//recomparar en cambio de estado
		}
	}

	@Override
	public void actualizarOpinion() {
		// TODO Auto-generated method stub
		//SOLAMENTE TOMA EN CUENTA OPINIONES DE EXPERTO
		ArrayList<OpinionImagen> opinionesDeImagen = new ArrayList<OpinionImagen>();
		muestra.opinionesExpertos().stream().forEach(opinion -> opinionesDeImagen.add(opinion.getOpinion()));
		muestra.actualizarOpinionActual(opinionesDeImagen);
	
	}

	@Override
	public boolean puedeOpinar(Usuario usuario) {
		// TODO Auto-generated method stub
		//PUEDE OPINAR SOLO SI EL USUARIO NO OPINO AUN, SI NO ES EL AUTOR DE LA MUESTRA Y SI ES UN EXPERTO
		return (this.muestra.elUsuarioNoOpino(usuario) && muestra.getUsuario() != usuario && usuario.esExperto()) ;
	}

}
