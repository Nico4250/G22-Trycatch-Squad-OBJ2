package ar.edu.unq.po2.Organizacion;
import ar.edu.unq.po2.Muestra.*;

public interface IObserver {
	public void actualizarPorCargaNueva(ZonaDeCobertura zona, Muestra muestra);

	public void actualizarPorValidacion(ZonaDeCobertura zona, Muestra muestra);
}
