package ar.edu.unq.po2.Sistema;

import java.util.ArrayList;
import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Organizacion.Organizacion;
import ar.edu.unq.po2.Usuario.Usuario;

public class Sistema {
	
	private ArrayList<Muestra> muestras;
	private ArrayList<Usuario> usuarios;
	private ArrayList<Organizacion> organizaciones;
	private GestorDeUsuarios gestor;
	
	public Sistema() {
		muestras = new ArrayList<>();
		usuarios = new ArrayList<>();
		organizaciones = new ArrayList<>();
		gestor = new GestorDeUsuarios();
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

	public void agregarMuestra(Muestra unaMuestra) {
		// TODO Auto-generated method stub
		if (!muestras.contains(unaMuestra)) {
			muestras.add(unaMuestra);
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
	
	public void actualizarNivelesDeUsuarios() {
		gestor.ActualizarNivelesDeUsuarioEn(this);
	}
	
	






}
