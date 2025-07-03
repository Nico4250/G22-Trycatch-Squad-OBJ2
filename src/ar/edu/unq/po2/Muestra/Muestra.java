package ar.edu.unq.po2.Muestra;
import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import ar.edu.unq.po2.Organizacion.Ubicacion;
import ar.edu.unq.po2.Usuario.Usuario;
import ar.edu.unq.po2.Sistema.*;

public class Muestra {
    private Ubicacion 			ubicacion;
    private Usuario 			usuario;
    private OpinionImagen       tipoInsecto;
    private LocalDate			fechaCreacion;
    private ArrayList<Opinion> 	opiniones; 
    private IEstadoMuestra      estado;
    private String 				nombreDeFoto;
    private Sistema             sistema;
    
    
    public Muestra(Ubicacion ubicacion, Opinion opinion, String nombreDeFoto, Sistema sistema) {
    	this.ubicacion = ubicacion;
    	this.usuario = opinion.getUsuario();
    	this.opiniones = new ArrayList<Opinion>();
    	this.tipoInsecto = opinion.getOpinion();
    	this.estado = (IEstadoMuestra) new MuestraNoVerificada();
    	this.fechaCreacion = LocalDate.now();
    	this.nombreDeFoto = nombreDeFoto;
    	this.sistema = sistema;
    }
    
	
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public LocalDate getFechaCreacion (){
		return fechaCreacion;
	}
	
	public ArrayList<Opinion> getOpiniones() {
		return opiniones;
	}
	
	public IEstadoMuestra getEstadoMuestra() {
		return this.estado;
	}
 
	public OpinionImagen getTipoInsecto() {
		return tipoInsecto;
	}
	
	public String getFoto() {
		return this.nombreDeFoto + ".JPG";
	}
	
	public Sistema getSistema() {
		return this.sistema;
	}
	
	public void cambiarEstado(IEstadoMuestra nuevoEstado) {
		this.estado = nuevoEstado;
	}
	
	public void agregarOpinion(Opinion opinion) {
			estado.agregarOpinion(this, opinion);
	}
	
	public void actualizarOpinion() {
		estado.actualizarOpinion(this);
	}
	
	public boolean elUsuarioNoOpino(Usuario usuario) {
		return this.getOpiniones().stream().noneMatch(opinion -> opinion.getUsuario().getId() == usuario.getId());
	}
	
	public List<Opinion> opinionesExpertos() {
		Stream <Opinion> opinionesExpertos = this.opiniones.stream().filter(opinion -> opinion.getUsuario().esExperto());
		return opinionesExpertos.toList();
	}
	
	public void  actualizarOpinionActual(List<OpinionImagen> opiniones){
        HashMap<OpinionImagen, Integer> recuento = new HashMap<>();

        for (OpinionImagen opinion : opiniones) {
            recuento.put(opinion, recuento.getOrDefault(opinion, 0) + 1);
        }

        OpinionImagen opinionConMayorCantidad = null;
        int cantidadMaxima = 0;

        for (Map.Entry<OpinionImagen, Integer> entry : recuento.entrySet()) {
        	OpinionImagen opinion = entry.getKey();
            int cantidad = entry.getValue();

            if (cantidad > cantidadMaxima) {
                cantidadMaxima = cantidad;
                opinionConMayorCantidad = opinion;
            }
        }
        this.validarOpinionConMayorCantidad(opinionConMayorCantidad, recuento, cantidadMaxima);
        // this.tipoInsecto = opinionConMayorCantidad;
	}
    
    private void validarOpinionConMayorCantidad(OpinionImagen opinion, HashMap<OpinionImagen, Integer> mapOpiniones, int cantidad) {
    	int cantidadDeValoresIguales = 0;
    	for(Map.Entry<OpinionImagen, Integer> entry : mapOpiniones.entrySet()) {
    	    OpinionImagen opinionDeImagen = entry.getKey();
    	    int cantidadDeVotaciones = entry.getValue();
			if (cantidad == cantidadDeVotaciones) {
				cantidadDeValoresIguales += 1;
    	    }   
    	}
    	
    	this.definirTipoDeInsecto(cantidadDeValoresIguales > 1, opinion);
    }
    
    private void definirTipoDeInsecto(boolean esEmpate, OpinionImagen opinion) {
    	if(esEmpate) {
    		this.tipoInsecto = OpinionImagen.NO_DEFINIDA;
    	} else {
    		this.tipoInsecto = opinion;
    	}
    }

	public void agregarOpinionDe(Opinion opinion) {
		opiniones.add(opinion);
	}
	
	public boolean esVerificada() {
		return estado.esVerificada();
	}
	
	public boolean puedeOpinar(Usuario usuario) {
		return estado.puedeOpinar(this, usuario);
	}


}
	 
	 
 



 


