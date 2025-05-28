package ar.edu.unq.po2.Organizacion;

public class Organizacion {

	private Ubicacion ubicacion;
	private TipoDeOrganizacion tipo;
	private int cantidadPersonas;
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public TipoDeOrganizacion getTipo() {
		return tipo;
	}
	
	public TipoDeOrganizacion SetTipo(TipoDeOrganizacion tipo) {
		this.tipo = tipo;
	}
	
	public int getCantidadPersonas() {
		return cantidadPersonas;
	}
	
	
	public void setCantidadPersonas(int cantidadPersonas) {
		this.cantidadPersonas = cantidadPersonas;
	}
	
	
}
