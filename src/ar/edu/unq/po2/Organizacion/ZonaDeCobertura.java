package ar.edu.unq.po2.Organizacion;
import ar.edu.unq.po2.Muestra.*;

import java.util.ArrayList;

public class ZonaDeCobertura implements ISujeto {
	private ArrayList<IObserver> subscritosAValidacion = new ArrayList<>();
	private ArrayList<IObserver> subscritosACarga = new ArrayList<>();
	private ArrayList<Muestra> muestrasEnZona = new ArrayList<Muestra>();
	
	private String nombre;
	private int radio;
	private Ubicacion epicentro;
	
	public ZonaDeCobertura(String nombre, int radio, Ubicacion epicentro) {
		this.nombre = nombre;
		this.radio = radio;
		this.epicentro = epicentro;
		
	}
	
	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public Ubicacion getEpicentro() {
		return epicentro;
	}


	public void setEpicentro(Ubicacion epicentro) {
		this.epicentro = epicentro;
	}


	public int getRadio() {
		return radio;
	}


	public void setRadio(int distancia) {
		this.radio = distancia;
	}
	
	public ArrayList<Muestra> getMuestrasEnZona() {
		return muestrasEnZona;
	}
	public ArrayList<IObserver> getSubscritosAValidacion() {
		return subscritosAValidacion;
	}
	
	public ArrayList<IObserver> getSubscritosACarga() {
		return subscritosACarga;
	}
	
    public void suscribirParaCarga(IObserver organizacion) {
    	if (!this.getSubscritosACarga().contains(organizacion)) {
    		this.getSubscritosACarga().add(organizacion);
    	}
    }
	
	public void suscribirParaValidacion(IObserver organizacion) {
		if (!this.getSubscritosAValidacion().contains(organizacion)) {
			this.getSubscritosAValidacion().add(organizacion);
		}
	}
	
	public void desuscribirParaCarga(IObserver organizacion) {
		this.getSubscritosACarga().remove(organizacion);
	}
	
	public void desuscribirParaValidacion(IObserver organizacion) {
		this.getSubscritosAValidacion().remove(organizacion);
	}
	
	public void agregarMuestra(Muestra muestra) {
		this.getMuestrasEnZona().add(muestra);
		this.notificarCargaMuestra(muestra);
    }

	public void notificarCargaMuestra(Muestra muestra) {
		for (IObserver organizacion : this.getSubscritosACarga()) {
	        organizacion.actualizarPorCargaNueva(this, muestra);
	    }
	}
	
	
	public void notificarValidacionMuestra(Muestra muestra) {
		for (IObserver organizacion : this.getSubscritosAValidacion()) {
	        organizacion.actualizarPorValidacion(this, muestra);
	    }
	}
	
	public ArrayList<ZonaDeCobertura> zonasSolapadas(ArrayList<ZonaDeCobertura> zonasAComparar) {
		ArrayList<ZonaDeCobertura> zonasSolapadas = new ArrayList<>();
		for (ZonaDeCobertura zona : zonasAComparar) {
	        double distancia = this.epicentro.distanciaEntre(zona.epicentro);
	        double distanciaLimite = this.radio + zona.radio;

	        if (!zona.equals(this) && distancia <= distanciaLimite) { // el zona equals evita que zona se considere solapada consigo misma
	            zonasSolapadas.add(zona);
	        }
	    }

	    return zonasSolapadas;
	}


}
