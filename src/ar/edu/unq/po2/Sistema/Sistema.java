package ar.edu.unq.po2.Sistema;

import java.util.ArrayList;
import java.util.function.BooleanSupplier;

import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Usuario.Usuario;

public class Sistema {
	
	private ArrayList<Muestra> muestras;
	private ArrayList<Usuario> usuarios;
	
	public Sistema() {
		muestras = new ArrayList<>();
		usuarios = new ArrayList<>();
	}
	
	 public ArrayList<Usuario> getUsuarios() {
	        return usuarios;
	    }
	 
	 public ArrayList<Muestra> getMuestras() {
	        return muestras;
	    }

	public void agregarMuestra(Muestra unaMuestra) {
		// TODO Auto-generated method stub
		muestras.add(unaMuestra);
	}
	
	public void registrarUsuario(Usuario unUsuario) {
		if (!usuarios.contains(unUsuario)) {
			usuarios.add(unUsuario);
		}
	}
	

	//ESTOS METODOS SON PARA ENCAPSULAMIENTO DE TESTS

	public boolean contieneMuestra(Muestra muestra) {
		// TODO Auto-generated method stub
		return muestras.contains(muestra);
	}




}
