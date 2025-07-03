package ar.edu.unq.po2.Sistema;

import java.util.ArrayList;
import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Organizacion.Organizacion;
import ar.edu.unq.po2.Organizacion.ZonaDeCobertura;
import ar.edu.unq.po2.Usuario.Usuario;

public class Sistema {
	
	private ArrayList<Muestra> muestras;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Organizacion> organizaciones;
	private ArrayList<ZonaDeCobertura> zonasDeCobertura;
	
	public Sistema() {
		muestras = new ArrayList<>();
		usuarios = new ArrayList<>();
		organizaciones = new ArrayList<>();
		zonasDeCobertura = new ArrayList<>();
	}
	
	public ArrayList<Usuario> getUsuarios() {
	        return usuarios;
	    }
	 
	public ArrayList<Muestra> getMuestras() {
	        return muestras;
	    }
	 
	public ArrayList<Organizacion> getOrganizaciones() {
			return organizaciones;
		}
	
	public ArrayList<ZonaDeCobertura> getZonasDeCobertura() {
		return zonasDeCobertura;
	}

	public void agregarMuestra(Muestra unaMuestra) {
		if (!muestras.contains(unaMuestra)) {
			muestras.add(unaMuestra);
			this.muestraFueCargada(unaMuestra);
		}
	}
	
	public void registrarUsuario(Usuario unUsuario) {
		if (!usuarios.contains(unUsuario)) {
			usuarios.add(unUsuario);
		}
	}
	
	public void registrarOrganizacion(Organizacion unaOrganizacion) {
		if (!organizaciones.contains(unaOrganizacion)) {
			organizaciones.add(unaOrganizacion);
		}
	}
	
	public void registrarZonaDeCobertura(ZonaDeCobertura unaZona) {
		if (!zonasDeCobertura.contains(unaZona)) {
			zonasDeCobertura.add(unaZona);
		}
	}
	
	private void muestraFueCargada(Muestra muestra) {
		for (ZonaDeCobertura zona : zonasDeCobertura) {
			if (zona.getEpicentro().distanciaEntre(muestra.getUbicacion()) <= zona.getRadio()) {
				zona.notificarCargaMuestra(muestra); //delega notificacion a la zona si la muestra es parte de la misma
			}
		}
	}
	
	public void muestraFueValidada(Muestra muestra) {
		for (ZonaDeCobertura zona : zonasDeCobertura) {
			if (zona.getEpicentro().distanciaEntre(muestra.getUbicacion()) <= zona.getRadio()) {
				zona.notificarValidacionMuestra(muestra);
			}
		}
	}
}
