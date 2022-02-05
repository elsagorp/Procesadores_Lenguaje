package Pratica_3;

import nebrija.traductor1.ComponenteLexico;
import nebrija.traductor1.Lexico;

public class TestTraductorRecursivo {

	public static void main(String[] args) {
		boolean mostrarComponentesLexicos = true;
		
		String expresion = "(25 * (2 + 2)) / 2 * 3";
		
		ComponenteLexico etiquetaLexica;
		
		Lexico lexico = new Lexico (expresion);

		if(mostrarComponentesLexicos) {
			do {
				etiquetaLexica = lexico.getComponenteLexico();
				System.out.println("<" + etiquetaLexica.toString() + ">");
			}while(!etiquetaLexica.getEtiqueta().equals("end_program"));
			
			System.out.println("");
		}
		
		//TraductorExpresionRecursivo expr = new TraductorExpresionRecursivo(new Lexico(expresion));
		
		System.out.println("");
		System.out.println("La expresión " + expresion + "en notación postfija es " );
		
	}

}
