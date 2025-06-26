package ar.edu.unq.po2.Sistema;

import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.Muestra.Opinion;
import ar.edu.unq.po2.Usuario.Usuario;

public class GestorDeUsuarios {
	
	private Sistema sistema;
	
	public GestorDeUsuarios(Sistema sistema) {
		this.sistema = sistema;
	}

	public void ActualizarNivelesDeUsuario() {
		for (Usuario u: sistema.getUsuarios()) {
			this.actualizarNivelDeUsuario(u);
		}
	}

	private void actualizarNivelDeUsuario(Usuario u) {
		if (this.usuarioCumpleCondicionesDeExperto(u)) {
			u.promoverAExperto();
		}
		else {
			u.degradarABasico();
		}
	}

	private boolean usuarioCumpleCondicionesDeExperto(Usuario u) {
		long cantidadDeMuestrasEnviadas = sistema.getMuestras().stream().filter(m -> m.getUsuario().equals(u)).count();
		//CANTIDAD DE MUESTRAS DEL USUARIO EN EL SISTEMA
		List<Opinion> opinionesEntreMuestras = sistema.getMuestras().stream().flatMap(m -> m.getOpiniones().stream())  
			    .collect(Collectors.toList());
		//TODAS LAS OPINIONES EN EL SISTEMA
		long cantidadDeOpinionesDadas = opinionesEntreMuestras.stream().filter(o -> o.getUsuario().equals(u)).count();
		//TODAS LAS OPINIONES HECHAS POR EL USUARIO EN EL SISTEMA
		
		return u.esEspecialista() || (cantidadDeOpinionesDadas >= 20 && cantidadDeMuestrasEnviadas >= 10);
	}

}
