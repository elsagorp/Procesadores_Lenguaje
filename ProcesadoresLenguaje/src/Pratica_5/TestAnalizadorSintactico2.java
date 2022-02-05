package Pratica_5;

import nebrija.traductor1.ComponenteLexico;
import nebrija.traductor1.Lexico;

/*
import Pratica_2.ComponenteLexico;
import Pratica_2.Lexico;*/
public class TestAnalizadorSintactico2 {

	public static void main(String[] args) {
		
		boolean mostrarComponentesLexicos = false;
		
		String expresion = "void main { int a, b, c, d, e, f; float x, y, z; float [10] v; }";
		
	
		ComponenteLexico etiquetaLexica;
		
		Lexico lexico = new Lexico(expresion);
		
		if(mostrarComponentesLexicos) {
			do {
				etiquetaLexica = lexico.getComponenteLexico();
				System.out.println("<" + etiquetaLexica.toString() + ">");
			}while(!etiquetaLexica.getEtiqueta().equals("end_program"));
			
			System.out.println("");
		}
		
		AnalizadorSintactico2 compilador = new AnalizadorSintactico2(new Lexico(expresion));
		
		System.out.println(expresion + "\n");
		System.out.println("Compilacion de sentencia de declaracion de variables\n");

		
		compilador.programa();
		
		System.out.println("\n Tabla de simbolos \n\n" + compilador.tablaSimbolos());
	
	}
}
