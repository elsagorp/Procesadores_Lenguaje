package Pratica_3;

import java.util.Stack;

import nebrija.traductor1.ComponenteLexico;
import nebrija.traductor1.Lexico;


public class TraductorExpresionPostfijo {
	private ComponenteLexico componenteLexico;
	private Lexico lexico;
	private Stack<Integer> pila;
	private String postfijo;

	
	public String getPostfijo() {
		return postfijo;
	}
	public void setPostfijo(String postfijo) {
		this.postfijo = postfijo;
	}
	public TraductorExpresionPostfijo(Lexico lexico) {
		this.lexico = lexico;
		this.componenteLexico = this.lexico.getComponenteLexico();
		this.pila = new Stack<Integer>();
		this.postfijo = "";
	}
	
	
	public void expresion() {
		termino();
		masTerminos();
	}
	private void masTerminos() {
		if (this.componenteLexico.getEtiqueta().equals("add")) {
			compara("add");
			termino();
			System.out.print(" + ");
			masTerminos();
		}
		else if (this.componenteLexico.getEtiqueta().equals("subtract")) {
			compara("subtract");
			termino();
			System.out.print(" - ");
			masTerminos();
		}
	}

	private void termino() {
		factor();
		masFactores();
	}
	private void masFactores() {
		if (this.componenteLexico.getEtiqueta().equals("multiply")) {
			compara("multiply");
			factor();
			System.out.print(" * ");
			masFactores();
		}
		else if (this.componenteLexico.getEtiqueta().equals("divide")) {
			compara("divide");
			factor();
			System.out.print(" / ");
			masFactores();
		}
	}

	/*private void factor() {
		if (this.componenteLexico.getEtiqueta().equals("open_parenthesis")) {
			compara("open_parenthesis");
			expresion();
			compara("closed_parenthesis");
		}
		else if (this.componenteLexico.getEtiqueta().equals("int")) {
			NumeroEntero numero = (NumeroEntero) this.componenteLexico;
			System.out.print(" " + numero.getValor() + " ");
			compara("int");
		}
		else
			System.out.println("Error, se esperaba paréntesis abierto o número");
	}*/

	public void factor() {
		if(this.componenteLexico.getEtiqueta().equals("open_parenthesis")) {
			compara("open_parenthesis");
			expresion();
			compara("closed_parenthesis");
		}else if(this.componenteLexico.getEtiqueta().equals("integer")) {
			System.out.print(" " + this.componenteLexico.getEtiqueta() + " ");
			compara("integer");
		}
	}
	private void compara(String etiqueta) {
		if (this.componenteLexico.getEtiqueta().equals(etiqueta))
			this.componenteLexico = this.lexico.getComponenteLexico();
		else
			System.out.println("Error, se esperaba " + etiqueta);
	}
}
