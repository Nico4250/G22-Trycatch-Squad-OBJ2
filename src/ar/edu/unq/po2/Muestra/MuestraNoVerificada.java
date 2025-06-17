package ar.edu.unq.po2.Muestra;

public class MuestraNoVerificada implements IEstadoMuestra{
	Muestra muestra;

	public MuestraNoVerificada(Muestra muestra) {
		this.muestra = muestra;
	}

	@Override
	public boolean esVerificada() {
		return false;
	}

	public void agregarOpinion(Opinion opinion) {
		this.muestra.agregarOpinionDe(opinion);
		this.muestra.actualizarOpinion();
	}

}
