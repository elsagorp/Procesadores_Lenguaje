package Pratica_4;

import nebrija.traductor1.ComponenteLexico;
import nebrija.traductor1.Lexico;

public class TestAnalizadorSintactico1 {

	public static void main(String[] args) {
		
		boolean mostrarComponentesLexicos = false;
		
		String expresion = "int a, b, c, d, e, f, g, h, i, s; float x, y, z;";
	
		ComponenteLexico etiquetaLexica;
		
		Lexico lexico = new Lexico(expresion);
		
		if(mostrarComponentesLexicos) {
			do {
				etiquetaLexica = lexico.getComponenteLexico();
				System.out.println("<" + etiquetaLexica.toString() + ">");
			}while(!etiquetaLexica.getEtiqueta().equals("end_program"));
			
			System.out.println("");
		}
		
		AnalizadorSintactico1 compilador = new AnalizadorSintactico1(new Lexico(expresion));
		
		System.out.println("compilacion de sentencia de declaracion de variables\n");
		System.out.println(expresion + "\n");
		
		compilador.declaracionTipos();
		
		System.out.println("tabla de simbolos \n\n" + compilador.tablaSimbolos());
	
	}

}

