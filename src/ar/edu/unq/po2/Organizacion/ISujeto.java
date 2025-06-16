package ar.edu.unq.po2.Organizacion;
import ar.edu.unq.po2.Muestra.*;

public interface ISujeto {
	
	public void suscribirParaCarga(IObserver organizacion);
	
	public void suscribirParaValidacion(IObserver organizacion);
	
	public void desuscribirParaCarga(IObserver organizacion);
	
	public void desuscribirParaValidacion(IObserver organizacion);
	
	public void notificarCargaMuestra(Muestra muestra);
	
	public void notificarValidacionMuestra(Muestra muestra);

}