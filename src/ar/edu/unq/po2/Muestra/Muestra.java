package ar.edu.unq.po2.Muestra;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import ar.edu.unq.po2.Organizacion.Ubicacion;
import ar.edu.unq.po2.Usuario.Usuario;

public class Muestra {
    private Ubicacion 			ubicacion;
    private Usuario 			usuario;
    private OpinionImagen       tipoInsecto;
    private LocalDate			fechaCreacion;
    private ArrayList<Opinion> 	opiniones; 
    private IEstadoMuestra      estado;
    
    
    public Muestra(Ubicacion ubicacion, Opinion opinion) {
    	this.ubicacion = ubicacion;
    	this.usuario = opinion.getUsuario();
    	this.opiniones = new ArrayList<Opinion>();
    	this.tipoInsecto = opinion.getOpinion();
    	this.estado = (IEstadoMuestra) new MuestraNoVerificada(this);
    	this.fechaCreacion = LocalDate.now();
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
	
	public void cambiarEstado(IEstadoMuestra nuevoEstado) {
		this.estado = nuevoEstado;
	}
	
	public void agregarOpinion(Opinion opinion) {
			estado.agregarOpinion(opinion);
	}
	
	public void actualizarOpinion() {
		estado.actualizarOpinion();
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
    
    public void validarOpinionConMayorCantidad(OpinionImagen opinion, HashMap<OpinionImagen, Integer> mapOpiniones, int cantidad) {
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
    
    public void definirTipoDeInsecto(boolean esEmpate, OpinionImagen opinion) {
    	if(esEmpate) {
    		this.tipoInsecto = OpinionImagen.NO_DEFINIDA;
    	} else {
    		this.tipoInsecto = opinion;
    	}
    }
    
	public boolean elUsuarioNoOpino(Usuario usuario) {
		// TODO Auto-generated method stub
		return this.getOpiniones().stream().noneMatch(opinion -> opinion.getUsuario().getId() == usuario.getId());
	}


	public void agregarOpinionDe(Opinion opinion) {
		// TODO Auto-generated method stub
		this.opiniones.add(opinion);
	}
	

//PARA TESTS
	public Integer cantidadDeOpiniones() {
		// TODO Auto-generated method stub
		return opiniones.size();
	}



}
	 
	 
 


