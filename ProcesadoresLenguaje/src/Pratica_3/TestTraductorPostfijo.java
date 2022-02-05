package Pratica_3;

import nebrija.traductor1.Lexico;
public class TestTraductorPostfijo {

	public static void main(String[] args) {
		String expresion = "(25 * (2 + 2)) / 2 * 3";
		
		TraductorExpresionPostfijo expr = new TraductorExpresionPostfijo(new Lexico(expresion));
		
		System.out.println("Conversion de una espresion aritmetica usando traduccion dirigida por la sintaxis \n");
		
		System.out.println("La expresion " + expresion + " en notacion postfija es " + expr.getPostfijo() + " y su valor es " );
		

	}

}
