package Pratica_4;

import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import nebrija.traductor1.ComponenteLexico;
import nebrija.traductor1.Identificador;
import nebrija.traductor1.Lexico;

/*declaraciones => declaracion declaraciones | epsilon
 * declaracion => tipo declaraciones ; 
 * tipo => int | float
 * identificadores => id mas-identificadores
 * mas-identificadores => , id mas-identificadores | epsilon
 */


public class AnalizadorSintactico1 {

	private ComponenteLexico componenteLexico;
	private Lexico lexico;
	private Hashtable<String, String> simbolos;
	
	//constructor
	public AnalizadorSintactico1(Lexico lexico) {
		this.simbolos = new Hashtable<String, String>();
		this.lexico = lexico;
		this.componenteLexico = this.lexico.getComponenteLexico();
	}
	
	public String tablaSimbolos() {
		String simboloss = "";
		
		Set<Map.Entry<String, String>> s = this.simbolos.entrySet();
		
		for(Map.Entry<String, String> m: s) {
			simboloss = simboloss + "<" + m.getKey() + "," + m.getValue().toString() + "> \n";
		}
		return simboloss;
	}

	public void declaracionTipos() {
		declaraciones();
	}

	private void declaraciones() {
		if(this.componenteLexico.getEtiqueta().equals("int") || this.componenteLexico.getEtiqueta().equals("float")) {
			declaracion();
			declaraciones();
		}
	} 
	//OTRA FORMA (JORGE) 
	/*
	private void declaraciones() {
		String etiqueta = this.componenteLexico.getEtiqueta();
		
		if(etiqueta.equals("int") || etiqueta.equals("float")) {
			declaracion();
			declaraciones();
		}
	}*/
	
	private void declaracion() {
		String tipo = tipo();
		
		//el atributo sintetizado tipo se pasa como atributo heredado a identificadores
		
		identificadores(tipo);
		compara("semicolon");
	
	}

	private void identificadores(String tipo) {
		
		if(this.componenteLexico.getEtiqueta().equals("id")) {
			Identificador id = (Identificador) this.componenteLexico;
			
			//añade el lexema del id y su tipo a la tabla de simbolos 
			this.simbolos.put(id.getLexema(), tipo);
			
			compara("id");
			masIdentificadores(tipo);
		}else {
			System.out.println("error, se esperaba un id");
		}
	}
	
	/*private void masIdentificadores(String tipo) {
		if(this.componenteLexico.getEtiqueta().equals("comma")) {
			compara("comma");

			Identificador id = (Identificador) this.componenteLexico;

			simbolos.put(id.getLexema(), tipo);

			compara("id");
			masIdentificadores(tipo);
		}else {
			//epsilon
		}
	}*/
	
	private void masIdentificadores(String tipo) {
		
		if(this.componenteLexico.getEtiqueta().equals("comma")) {
			compara("comma");
			
			if(this.componenteLexico.getEtiqueta().equals("id")) {
				Identificador id = (Identificador) this.componenteLexico;
				
				//añade el lexema del id y su tipo a la tabla de simbolos 
				simbolos.put(id.getLexema(), tipo);
				
				compara("id");
				masIdentificadores(tipo);
			}
		}else {
			//epsilon
		}
	}
    
	private String tipo() {
		String tipo = this.componenteLexico.getEtiqueta();
		
		if(tipo.equals("int")) {
			compara("int");
		}
		else if(tipo.equals("float")) {
			compara("float");
		}else {
			System.out.println("Error, se esperaba un int o float. ");
		}
		return tipo;
	}

	private void compara(String etiqueta) {
		
		if(this.componenteLexico.getEtiqueta().equals(etiqueta))
			this.componenteLexico = this.lexico.getComponenteLexico(); // AVANZA 
		else
			System.out.println("Error, se esperaba " + etiqueta);
	}
	
}

