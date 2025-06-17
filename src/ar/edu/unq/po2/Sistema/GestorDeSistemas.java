package ar.edu.unq.po2.Sistema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ar.edu.unq.po2.Muestra.Opinion;
import ar.edu.unq.po2.Usuario.Usuario;

public class GestorDeSistemas {

	public void ActualizarNivelesDeUsuarioEn(Sistema s) {
		for (Usuario u: s.getUsuarios()) {
			this.actualizarNivelDeUsuarioEn(u, s);
		}
	}

	private void actualizarNivelDeUsuarioEn(Usuario u, Sistema s) {
		if (this.usuarioCumpleCondicionesDeExpertoEn(u, s)) {
			u.promoverAExperto();
		}
		else {
			u.degradarABasico();
		}
	}

	private boolean usuarioCumpleCondicionesDeExpertoEn(Usuario u, Sistema s) {
		long cantidadDeMuestrasEnviadas = s.getMuestras().stream().filter(m -> m.getUsuario().equals(u)).count();
		//CANTIDAD DE MUESTRAS DEL USUARIO EN EL SISTEMA
		List<Opinion> opinionesEntreMuestras = s.getMuestras().stream().flatMap(m -> m.getOpiniones().stream())  
			    .collect(Collectors.toList());
		//TODAS LAS OPINIONES EN EL SISTEMA
		long cantidadDeOpinionesDadas = opinionesEntreMuestras.stream().filter(o -> o.getUsuario().equals(u)).count();
		//TODAS LAS OPINIONES HECHAS POR EL USUARIO EN EL SISTEMA
		
		return u.esEspecialista() || (cantidadDeOpinionesDadas >= 20 && cantidadDeMuestrasEnviadas >= 10);
	}

}
