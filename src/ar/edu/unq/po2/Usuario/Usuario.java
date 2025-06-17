package ar.edu.unq.po2.Usuario;

import ar.edu.unq.po2.Muestra.Muestra;
import ar.edu.unq.po2.Muestra.Opinion;
import ar.edu.unq.po2.Muestra.OpinionImagen;
import ar.edu.unq.po2.Sistema.Sistema;

public class Usuario {
	private int    			id;
	private String 			nombreUsuario;
	private Boolean 		esExperto;
	private Boolean			esEspecialista;
	
	public Usuario(int id, String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.id = id;
		this.esExperto = false;
		this.esEspecialista = false;
	}
	
	public int getId() {
		return  id;
	}
	
	public String nombreUsuario() {
		return nombreUsuario;
	}

	public Boolean esExperto() {
		// TODO Auto-generated method stub
		return esExperto;
	}
	
	public void subirMuestraA (Muestra unaMuestra, Sistema sistema) {
		sistema.agregarMuestra(unaMuestra);
	}

	public void opinarSobreMuestra(Muestra unaMuestra, OpinionImagen unaOpinion) {
		// TODO Auto-generated method stub
		Opinion opinion = new Opinion(this, unaOpinion);
		unaMuestra.agregarOpinion(opinion);
	}

	public boolean esEspecialista() {
		return esEspecialista;
	}

	public void promoverAExperto() {
		// TODO Auto-generated method stub
		this.esExperto = true;
	}

	public void degradarABasico() {
		// TODO Auto-generated method stub
		this.esExperto = false;
	}
	
}
