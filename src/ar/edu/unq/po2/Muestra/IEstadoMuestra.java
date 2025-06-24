package ar.edu.unq.po2.Muestra;

import ar.edu.unq.po2.Usuario.Usuario;

public interface IEstadoMuestra {

	public abstract boolean esVerificada();

	public abstract void agregarOpinion(Muestra muestra, Opinion opinion);

	public abstract void actualizarOpinion(Muestra muestra);
	
	public abstract boolean puedeOpinar(Muestra muestra, Usuario usuario);

	public abstract void agregarOpinionDe(Muestra muestra, Opinion opinion);
}

