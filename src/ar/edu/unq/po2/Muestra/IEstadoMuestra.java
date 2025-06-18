package ar.edu.unq.po2.Muestra;

import ar.edu.unq.po2.Usuario.Usuario;

public interface IEstadoMuestra {

	public abstract boolean esVerificada();

	public abstract void agregarOpinion(Opinion opinion);

	public abstract void actualizarOpinion();
	
	public abstract boolean puedeOpinar(Usuario usuario);
}
