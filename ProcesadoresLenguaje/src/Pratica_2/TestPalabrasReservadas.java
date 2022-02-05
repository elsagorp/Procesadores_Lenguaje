package Pratica_2;

public class TestPalabrasReservadas {

	public static void main(String[] args) {
		ComponentesLexicos palabrasReservadas = new ComponentesLexicos("lexico.txt");

		System.out.println(">   \t" + palabrasReservadas.getEtiqueta(">"));
		System.out.println(">=   \t" + palabrasReservadas.getEtiqueta(">="));
		System.out.println("<   \t" + palabrasReservadas.getEtiqueta("<"));
		System.out.println("<=   \t" + palabrasReservadas.getEtiqueta("<="));
		System.out.println("=   \t" + palabrasReservadas.getEtiqueta("="));
		System.out.println("==   \t" + palabrasReservadas.getEtiqueta("=="));
		System.out.println("!=   \t" + palabrasReservadas.getEtiqueta("!="));

		System.out.println("+   \t" + palabrasReservadas.getEtiqueta("+"));
		System.out.println("-   \t" + palabrasReservadas.getEtiqueta("-"));
		System.out.println("*   \t" + palabrasReservadas.getEtiqueta("*"));
		System.out.println("/   \t" + palabrasReservadas.getEtiqueta("/"));
		System.out.println("%   \t" + palabrasReservadas.getEtiqueta("%"));

		System.out.println("&&   \t" + palabrasReservadas.getEtiqueta("&&"));
		System.out.println("||   \t" + palabrasReservadas.getEtiqueta("||"));
		System.out.println("!   \t" + palabrasReservadas.getEtiqueta("!"));

		System.out.println("(   \t" + palabrasReservadas.getEtiqueta("("));
		System.out.println(")   \t" + palabrasReservadas.getEtiqueta(")"));

		System.out.println("do   \t" + palabrasReservadas.getEtiqueta("do"));
		System.out.println("while \t" + palabrasReservadas.getEtiqueta("while"));
		System.out.println("if   \t" + palabrasReservadas.getEtiqueta("if"));
		System.out.println("else   \t" + palabrasReservadas.getEtiqueta("else"));

		System.out.println("true   \t" + palabrasReservadas.getEtiqueta("true"));
		System.out.println("false  \t" + palabrasReservadas.getEtiqueta("false"));

		System.out.println("int   \t" + palabrasReservadas.getEtiqueta("int"));
		System.out.println("float \t" + palabrasReservadas.getEtiqueta("float"));

		System.out.println("for   \t" + palabrasReservadas.getEtiqueta("for"));

	}

}
