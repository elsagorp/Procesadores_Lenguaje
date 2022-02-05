package Pratica_1;

public class TestLexicoBasico {

	public static void main(String[] args) {
		ComponenteLexicoBasico etiquetaLexica;
		String programa = "int k; for (int i=0; i<10; i=i+1) k=k*2;";
		LexicoBasico lexico = new LexicoBasico(programa);
		int c = 0;
		System.out.println("Test léxico basico \t" + programa + "\n");
		do {
			etiquetaLexica = lexico.getComponenteLexico();
			System.out.println("<" + etiquetaLexica.toString() + ">");
			c++;
		} while (!etiquetaLexica.getEtiqueta().equals("end_program"));
		System.out.println("\nComponentes léxicos: " + c +
				", lineas: " + lexico.getLineas());

	}

}
