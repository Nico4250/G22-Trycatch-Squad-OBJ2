package ar.edu.unq.po2.Muestra;

import java.time.LocalDate;

import ar.edu.unq.po2.Usuario.Usuario;

public class Opinion {
	
 private  OpinionImagen opinion;
 private  LocalDate fechaPublicacion;
 private  Usuario usuario;
		
 public Opinion(Usuario usuario, OpinionImagen opinion) {
	 	this.setOpinion(opinion);
		this.setFechaPublicacion(LocalDate.now());
		this.setUsuario(usuario);
		
		}
		
		
 private void setUsuario(Usuario usuario) {
	 this.usuario = usuario;
}


public LocalDate getFechaPublicacion() {
		return fechaPublicacion;
		}
		
 public OpinionImagen getOpinion() {
		return opinion;
		}

public void setOpinion(OpinionImagen opinion) {
		this.opinion = opinion;
		}

public void setFechaPublicacion(LocalDate fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
		}
		
 public Usuario getUsuario() {
		return this.usuario;
		}

}
