package ar.edu.unq.po2.Muestra;

import java.util.ArrayList;

import ar.edu.unq.po2.Usuario.Usuario;

public class MuestraNoVerificada implements IEstadoMuestra{
	private Muestra muestra;

	public MuestraNoVerificada(Muestra muestra) {
		this.muestra = muestra;
	}

	@Override
	public boolean esVerificada() {
		return false;
	}

	@Override
	public void agregarOpinion(Opinion opinion) {
		
		if (!this.puedeOpinar(opinion.getUsuario())) {
			//LO MANEJO CON EXCEPCIONES PARA TESTS, SI ES NECESARIO LO CAMBIAMOS
			throw new RuntimeException("No se puede opinar sobre esta muestra");
		}
		
			this.muestra.agregarOpinionDe(opinion);
			this.muestra.actualizarOpinion();
	 
		if (opinion.getUsuario().esExperto()) {
			this.muestra.cambiarEstado(new MuestraEnVerificacion(this.muestra));
			//recomparar en cambio de estado
		}
	}
		
	@Override
	public boolean puedeOpinar(Usuario usuario) {
		//PUEDE OPINAR SOLO SI EL USUARIO NO OPINO AUN, Y SI NO ES EL AUTOR DE LA MUESTRA
		return (this.muestra.elUsuarioNoOpino(usuario) && muestra.getUsuario() != usuario) ;
	}	
		

	@Override
	public void actualizarOpinion() {
			//TOMA EN CUENTA TODAS LAS OPINIONES
			ArrayList<OpinionImagen> opinionesDeImagen = new ArrayList<OpinionImagen>();
			muestra.getOpiniones().stream().forEach(opinion -> opinionesDeImagen.add(opinion.getOpinion()));
			muestra.actualizarOpinionActual(opinionesDeImagen);
		
	}



}
