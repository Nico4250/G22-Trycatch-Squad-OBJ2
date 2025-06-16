package ar.edu.unq.po2.Organizacion;
import ar.edu.unq.po2.Muestra.*;

public class Organizacion implements IObserver {
    private String nombre;
	private Ubicacion ubicacion;
	private TipoDeOrganizacion tipo;
	private FuncionalidadExterna cargaMuestra;
	private FuncionalidadExterna validacionMuestra;
	
	
	public Organizacion(String nombre, Ubicacion ubicacion, TipoDeOrganizacion tipo, FuncionalidadExterna funcionalidadCarga, FuncionalidadExterna funcionalidadValidacion) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.tipo = tipo;
		this.cargaMuestra = funcionalidadCarga;
		this.validacionMuestra = funcionalidadValidacion;
		
	}
	public String getNombre() { 
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public TipoDeOrganizacion getTipoOrganizacion() {
		return tipo;
	}
	
	public void setTipoOrganizacion(TipoDeOrganizacion tipo) {
		this.tipo = tipo;
	}
	
	public FuncionalidadExterna getFuncionalidadCarga() {
		return cargaMuestra;
	}
	
	public void setFuncionalidadCarga(FuncionalidadExterna funcionalidad) {
		this.cargaMuestra = funcionalidad;
	}
	
	public FuncionalidadExterna getFuncionalidadValidacion() {
		return validacionMuestra;
	}
	
	public void setFuncionalidadValidacion(FuncionalidadExterna funcionalidad) {
		this.validacionMuestra = funcionalidad;
	}
	
	@Override
	public void actualizarPorCargaNueva(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		this.getFuncionalidadCarga().nuevoEvento(this, zonaDeCobertura, muestra);
	}
 
	@Override
	public void actualizarPorValidacion(ZonaDeCobertura zonaDeCobertura, Muestra muestra) {
		this.getFuncionalidadValidacion().nuevoEvento(this, zonaDeCobertura, muestra);
	}
}
