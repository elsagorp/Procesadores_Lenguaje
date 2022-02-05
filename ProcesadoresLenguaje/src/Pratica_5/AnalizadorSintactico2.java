package Pratica_5;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import nebrija.traductor1.ComponenteLexico;
import nebrija.traductor1.Identificador;
import nebrija.traductor1.Lexico;
import nebrija.traductor1.NumeroEntero;
import nebrija.traductor1.NumeroReal;
import nebrija.traductor1.TipoArray;
import nebrija.traductor1.TipoDato;
import nebrija.traductor1.TipoPrimitivo;

/*
import Pratica_2.NumeroEntero;
import Pratica_2.Identificador;
import Pratica_2.ComponenteLexico;
import Pratica_2.Lexico;
 */
public class AnalizadorSintactico2 {

	private ComponenteLexico componenteLexico;
	private Lexico lexico;
	private Hashtable<String, TipoDato> simbolos;

	public AnalizadorSintactico2(Lexico lexico) {
		this.simbolos = new Hashtable<String, TipoDato>();
		this.lexico = lexico;
		this.componenteLexico = this.lexico.getComponenteLexico();
	}

	public String tablaSimbolos() {
		String simbolos = "";

		Set<Map.Entry<String, TipoDato>> s = this.simbolos.entrySet();

		for(Map.Entry<String, TipoDato> m : s)
			simbolos = simbolos + "<'" + m.getKey() + "', " + m.getValue().toString() + "> \n";

		return simbolos;
	}


	public void programa() {
		compara("void");
		compara("main");
		compara("open_bracket");

		declaraciones();

		compara("closed_bracket");
	}

	private void declaraciones() {
		String etiqueta = this.componenteLexico.getEtiqueta();
		if(etiqueta .equals("int") || etiqueta.equals("float")) {
			declaracion();
			declaraciones();
		}
	}

	private void declaracion() {
		String tipo = tipo(); //TipoPrimitivo
		int tamaño = 1;
		
		//si después del tipo viene un 'open_square_bracket' es una declaración de vector
		//en caso contrario es una lista de indentificadores de tipos promitivos int o float

		if(this.componenteLexico.getEtiqueta().equals("open_square_bracket")) {

			compara("open_square_bracket");

			if(this.componenteLexico.getEtiqueta().equals("int")) {
				NumeroEntero  numero = (NumeroEntero) this.componenteLexico;

				tamaño = numero.getValor();

			}
			compara("int");
			compara("closed_square_bracket");

			if(this.componenteLexico.getEtiqueta().equals("id")) {
				Identificador id = (Identificador) this.componenteLexico;

				simbolos.put(id.getLexema(), new TipoArray(tipo, tamaño));
			}

			compara("id");
			compara("semicolon");

		} else {
			// tipo primitivo int o float

			identificadores(tipo);
			compara("semicolon");
		}
	}

	private void identificadores(String tipo) {
		String etiqueta = this.componenteLexico.getEtiqueta();
		if(etiqueta.equals("id")) {
			Identificador id = (Identificador) this.componenteLexico;

			//añade el lexema del identificador y su tipo a la tabla de simbolos
			this.simbolos.put(id.getLexema(), new TipoPrimitivo(tipo));

			compara("id");
			masIdentificadores(tipo);
		}
		else {
			System.out.println("Error, se esperaba un identificador");
		}
	}

	private void masIdentificadores(String tipo) {
		String etiqueta = this.componenteLexico.getEtiqueta();
		if(etiqueta.equals("comma")) {
			compara("comma");

			Identificador id = (Identificador) this.componenteLexico;

			simbolos.put(id.getLexema(), new TipoPrimitivo(tipo));

			compara("id");
			masIdentificadores(tipo);
		}
	}

	private String tipo() {
		String tipo = this.componenteLexico.getEtiqueta();

		if(tipo.equals("int")) {
			compara("int");
		}else if(tipo.equals("float")){
			compara("float");
		}else {
			System.out.println("Error, se esperaba int o float");
		}

		return tipo;

	}

	private void compara(String etiqueta) {
		if(this.componenteLexico.getEtiqueta().equals(etiqueta))
			this.componenteLexico = this.lexico.getComponenteLexico();
		else
			System.out.println("Error, se esperaba " + etiqueta);
	}
}
