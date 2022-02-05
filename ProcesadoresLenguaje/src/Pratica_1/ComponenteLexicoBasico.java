package Pratica_1;

public class ComponenteLexicoBasico {
	private String etiqueta; // etiqueta del token
	private String valor; // valor asociado a un token id o num
	public ComponenteLexicoBasico(String etiqueta) {
		this.etiqueta = etiqueta;
		this.valor = "";
	}
	public ComponenteLexicoBasico(String etiqueta, String valor) {
		this.etiqueta = etiqueta;
		this.valor = valor;
	}
	public String getEtiqueta() {
		return this.etiqueta;
	}
	// toString() devuelve una cadena con el contenido del token
	public String toString() {
		if (this.valor.length() == 0)
			return this.etiqueta;
		else
			return this.etiqueta + ", " + this.valor;
	}
}
