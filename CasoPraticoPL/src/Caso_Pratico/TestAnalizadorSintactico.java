package Caso_Pratico;

import java.nio.charset.StandardCharsets;

import nebrija.traductor.Lexico;
import nebrija.traductor.ComponenteLexico;

public class TestAnalizadorSintactico {

	public static void main(String[] args) {

		//PROGRAMAS DE PRUEBA PARA EL ANALISIS SINTACTICO Y SEMANTICO
		/*
		//Programa 1		
		String fichero="programa1.txt";
		Lexico lexico= new Lexico(fichero,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador= new AnalizadorSintactico(new Lexico(fichero,StandardCharsets.UTF_8));

		System.out.println("Programa 1: \n ");
		System.out.println(lexico.getPrograma()+"\n");

		compilador.programa();

		System.out.println(compilador.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador.tablaSimbolos());

		//Programa 2
		String fichero2="programa2.txt";
		Lexico lexico2= new Lexico(fichero,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador2= new AnalizadorSintactico(new Lexico(fichero2,StandardCharsets.UTF_8));

		System.out.println("Programa 2: \n ");
		System.out.println(lexico2.getPrograma()+"\n");

		compilador2.programa();

		System.out.println(compilador2.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador2.tablaSimbolos());

		//Programa 3
		String fichero3="programa3.txt";
		Lexico lexico3= new Lexico(fichero3,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador3= new AnalizadorSintactico(new Lexico(fichero3,StandardCharsets.UTF_8));

		System.out.println("Programa 3: \n ");
		System.out.println(lexico3.getPrograma()+"\n");

		compilador3.programa();

		System.out.println(compilador3.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador3.tablaSimbolos());

		//Programa 4
		String fichero4="programa4.txt";
		Lexico lexico4= new Lexico(fichero4,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador4= new AnalizadorSintactico(new Lexico(fichero4,StandardCharsets.UTF_8));

		System.out.println("Programa 4: \n ");
		System.out.println(lexico4.getPrograma()+"\n");

		compilador4.programa();

		System.out.println(compilador4.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador4.tablaSimbolos());

		//Programa 5
		String fichero5="programa5.txt";
		Lexico lexico5= new Lexico(fichero5,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador5= new AnalizadorSintactico(new Lexico(fichero5,StandardCharsets.UTF_8));

		System.out.println("Programa 5: \n ");
		System.out.println(lexico5.getPrograma()+"\n");

		compilador5.programa();

		System.out.println(compilador5.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador5.tablaSimbolos());

		//Programa 6
		String fichero6="programa6.txt";
		Lexico lexico6= new Lexico(fichero6,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador6= new AnalizadorSintactico(new Lexico(fichero6,StandardCharsets.UTF_8));

		System.out.println("Programa 6: \n ");
		System.out.println(lexico6.getPrograma()+"\n");

		compilador6.programa();

		System.out.println(compilador6.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador6.tablaSimbolos());
	 
*/	
		//PROGRAMAS DE PRUEBA PARA LA GENERACION DE CODIGO

		//Programa 7
		String fichero7="programa7.txt";
		Lexico lexico7= new Lexico(fichero7,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador7= new AnalizadorSintactico(new Lexico(fichero7,StandardCharsets.UTF_8));

		System.out.println("Programa 7: \n ");
		System.out.println(lexico7.getPrograma()+"\n");

		compilador7.programa();

		System.out.println(compilador7.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador7.tablaSimbolos());

/*		//Programa 8
		String fichero8="programa8.txt";
		Lexico lexico8= new Lexico(fichero8,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador8= new AnalizadorSintactico(new Lexico(fichero8,StandardCharsets.UTF_8));

		System.out.println("Programa 8: \n ");
		System.out.println(lexico8.getPrograma()+"\n");

		compilador8.programa();

		System.out.println(compilador8.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador8.tablaSimbolos());


		//Programa 9
		String fichero9="programa9.txt";
		Lexico lexico9= new Lexico(fichero9,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador9= new AnalizadorSintactico(new Lexico(fichero9,StandardCharsets.UTF_8));

		System.out.println("Programa 9: \n ");
		System.out.println(lexico9.getPrograma()+"\n");

		compilador9.programa();

		System.out.println(compilador9.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador9.tablaSimbolos());


		//Programa 10
		String fichero10="programa10.txt";
		Lexico lexico10= new Lexico(fichero10,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador10= new AnalizadorSintactico(new Lexico(fichero10,StandardCharsets.UTF_8));

		System.out.println("Programa 10: \n ");
		System.out.println(lexico10.getPrograma()+"\n");

		compilador10.programa();

		System.out.println(compilador10.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador10.tablaSimbolos());


		//Programa 11
		String fichero11="programa11.txt";
		Lexico lexico11= new Lexico(fichero11,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador11= new AnalizadorSintactico(new Lexico(fichero11,StandardCharsets.UTF_8));

		System.out.println("Programa 11: \n ");
		System.out.println(lexico11.getPrograma()+"\n");

		compilador11.programa();

		System.out.println(compilador11.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador11.tablaSimbolos());



		//Programa 12
		String fichero12="programa12.txt";
		Lexico lexico12= new Lexico(fichero12,StandardCharsets.UTF_8);

		AnalizadorSintactico compilador12= new AnalizadorSintactico(new Lexico(fichero12,StandardCharsets.UTF_8));

		System.out.println("Programa 12: \n ");
		System.out.println(lexico12.getPrograma()+"\n");

		compilador12.programa();

		System.out.println(compilador12.errores());
		System.out.println("\n Tabla de simbolos \n\n"+compilador12.tablaSimbolos());

*/	
	}
}
