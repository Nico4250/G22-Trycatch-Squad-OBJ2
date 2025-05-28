package ar.edu.unq.po2.Muestra;
import java.util.ArrayList;

import ar.edu.unq.po2.Organizacion.Ubicacion;
import ar.edu.unq.po2.Usuario.Usuario;

public class Muestra {
 private Ubicacion          ubicacion;
 private Usuario  		    usuario;
 private OpinionImagen 		tipoInsecto;
 private LocalDate			fechaCreacion;
 private ArrayList<Opinion> opiniones;
 private IEstadoMuestra		estado;
 
 public Ubicacion getUbicacion() {
	 return ubicacion;
 }
 
 public Usuario getUsuario() {
	 return usuario;
 }
 
 public LocalDate getFechaCreacion() {
	 return fechaCreacion;
 }
 
 public ArrayList<Opinion> getOpiniones() {
	 return opiniones;
 }
 
 public IEstadoMuestra getEstadoMuestra() {
	 return estado;
 }

 
 
	 
	 
 
}

