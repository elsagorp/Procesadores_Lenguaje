package Caso_Pratico;


import java.util.Hashtable;

import java.util.Map;
import java.util.Set;
import nebrija.traductor.ComponenteLexico;
import nebrija.traductor.Lexico;
import nebrija.traductor.Identificador;
import nebrija.traductor.NumeroReal;
import nebrija.traductor.NumeroEntero;
import nebrija.traductor.TipoDato;
import nebrija.traductor.TipoPrimitivo;
import nebrija.traductor.TipoArray;
import java.util.Vector;

public class AnalizadorSintactico {
	private String comparacionTipo=null; 
	private ComponenteLexico componenteLexico;
	private Lexico lexico;
	private Hashtable<String, TipoDato> simbolos;
	private Vector<String> errores;

	public AnalizadorSintactico(Lexico lexico) {
		this.simbolos = new Hashtable<String, TipoDato>();
		this.lexico = lexico;
		this.componenteLexico = this.lexico.getComponenteLexico();
		this.errores = new Vector<String>();
	}

	public String tablaSimbolos() {
		String simbolos = "\n";

		Set<Map.Entry<String, TipoDato>> s = this.simbolos.entrySet();

		for(Map.Entry<String, TipoDato> m : s) {
			simbolos = simbolos + "<'" + m.getKey() + "', " + m.getValue().toString() + "> \n" ;
		}

		return simbolos + "\n";

	}


	//Programa
	public void programa() {
		compara("void");
		compara("main");
		compara("open_bracket");

		declaraciones();
		instrucciones(); 

		compara("closed_bracket");
		System.out.print(" halt " + "\n" + "\n");

	}

	//Declaraciones
	private void declaraciones() {
		String etiqueta = this.componenteLexico.getEtiqueta();

		if(etiqueta.equals("int") || etiqueta.equals("float") || etiqueta.equals("boolean")) {
			declaracion_variable();
			declaraciones();
		}

	}


	//Declaracion-variable
	public void declaracion_variable() {
		String tipo = tipo_primitivo();
		int tamaño = 1; 

		if (this.componenteLexico.getEtiqueta().equals("open_square_bracket")){ 
			

			compara("open_square_bracket"); 

			if(this.componenteLexico.getEtiqueta().equals("int")) {
				NumeroEntero numero = (NumeroEntero) this.componenteLexico; 
				tamaño = numero.getValor();
			}
			compara("int");
			compara("closed_square_bracket");  

			if(this.componenteLexico.getEtiqueta().equals("id")) {
				Identificador id = (Identificador) this.componenteLexico;
				this.simbolos.put(id.getLexema(), new TipoArray(tipo,tamaño));
			}
			Identificador id = (Identificador) this.componenteLexico;
			System.out.print(" lvalue " + id.getLexema() + "\n");
			compara("id");
			compara("semicolon"); // ;

		}else { 
			this.comparacionTipo = tipo;
			lista_identificadores(tipo);
			compara("semicolon");
			this.comparacionTipo = null; 

		}
	}


	//Tipo-primitivo 
	private String tipo_primitivo() {
		String tipo = this.componenteLexico.getEtiqueta(); 

		if(tipo.equals("int")) {
			compara("int");
		}
		else if(tipo.equals("float")) {
			compara("float");
		}else if(tipo.equals("boolean")) {
			compara("boolean");
		}else{
			System.out.println("Error, se esperaba un tipo de dato");
		}
		return tipo;
	}


	//Lista-identificadores 
	private void lista_identificadores(String tipo) {
		if(this.componenteLexico.getEtiqueta().equals("id")) {

			Identificador id = (Identificador) this.componenteLexico; 
			if(this.simbolos.get(id.getLexema()) != null) {
				this.errores.add("Error en la linea "+this.lexico.getLineas()+", varibale '"+
						id.getLexema() +"' ya ha sido declarada");
			}else {
				simbolos.put(id.getLexema(), new TipoPrimitivo(tipo));
			}

			System.out.print(" lvalue " + id.getLexema() + "\n");
			compara("id");
			asignacion();
			mas_identificadores(tipo); 

		}
	}

	//Más-identificadores 
	private void mas_identificadores(String tipo) {

		if(this.componenteLexico.getEtiqueta().equals("comma")) {
			compara("comma"); // , 



			Identificador id = (Identificador) this.componenteLexico; 
			this.simbolos.put(id.getLexema(), new TipoPrimitivo(tipo)); 
			System.out.print(" lvalue " + id.getLexema() + "\n");
			compara("id");
			asignacion();
			mas_identificadores(tipo); 


		}
	}


	//Asignacion 
	private void asignacion() {
		if(this.componenteLexico.getEtiqueta().equals("assignment")) {
			compara("assignment");
			expresion_logica(); 
			System.out.println(" = " + "\n");
		}

	}



	//Instrucciones
	private void instrucciones() { 

		String etiqueta = this.componenteLexico.getEtiqueta();
		if(etiqueta.equals("int")||etiqueta.equals("float")||etiqueta.equals("boolean") ||etiqueta.equals("id")||etiqueta.equals("if")||etiqueta.equals("while")||etiqueta.equals("do")||etiqueta.equals("print")||etiqueta.equals("open_bracket")) {
			instruccion();
			instrucciones();
		}


	}


	//Instruccion
	private void instruccion() { 

		if(this.componenteLexico.getEtiqueta().equals("int")||this.componenteLexico.getEtiqueta().equals("float")
				||this.componenteLexico.getEtiqueta().equals("boolean")) {
			declaracion_variable();
		}else if(this.componenteLexico.getEtiqueta().equals("id")){
			Identificador id =(Identificador) this.componenteLexico;
			variable();
			compara("assignment");

			if(this.simbolos.get(id.getLexema())==null){
				this.comparacionTipo=null;
			}else{
				this.comparacionTipo=this.simbolos.get(id.getLexema()).getTipo();
			}
			this.comparacionTipo=null; 
			expresion_logica();
			compara("semicolon");
			System.out.print(" = " + "\n");
		}else if(this.componenteLexico.getEtiqueta().equals("if")) {
			compara("if");
			compara("open_parenthesis");
			expresion_logica();
			compara("closed_parenthesis");
			String out = this.componenteLexico.getEtiqueta();
			System.out.print(" gofalse "+ out  + "\n");
			instruccion();


			if(this.componenteLexico.getEtiqueta().equals("else")) {
				System.out.print(" goto " + out + "\n");
				compara("else");
				String els= this.componenteLexico.getEtiqueta();
				instruccion();
				System.out.print(" label " + els + "\n");
			}else {

				System.out.print(" label " + out + "\n");
			}
		}else if(this.componenteLexico.getEtiqueta().equals("while")) {
			compara("while");
			String test = this.componenteLexico.getEtiqueta();
			System.out.print(" label "+ test  + "\n");
			compara("open_parenthesis");
			expresion_logica();
			compara("closed_parenthesis");
			String out = this.componenteLexico.getEtiqueta();
			System.out.print(" gofalse "+ out  + "\n");
			if(this.componenteLexico.getEtiqueta().equals("semicolon")) {
				compara("semicolon");
			}else {
				instruccion();
				System.out.print(" goto "+ test  + "\n");
				System.out.print(" label "+ out  + "\n");
			}
		}else if(this.componenteLexico.getEtiqueta().equals("do")) {
			compara("do");
			String test = this.componenteLexico.getEtiqueta();
			System.out.print(" label "+ test  + "\n");
			instruccion();
			compara("while");
			String out = this.componenteLexico.getEtiqueta();
			System.out.print(" gofalsse "+ out  + "\n");
			System.out.print(" goto "+ test  + "\n");
			System.out.print(" label "+ out  + "\n");

		}else if(this.componenteLexico.getEtiqueta().equals("print")) {
			compara("print");
			compara("open_parenthesis");
			variable();
			compara("closed_parenthesis");
			compara("semicolon");
		}else if(this.componenteLexico.getEtiqueta().equals("open_bracket")) {
			compara("open_bracket");
			instrucciones();
			compara("closed_bracket");
		}
	}


	//Variable 
	private void variable() {
		if(this.componenteLexico.getEtiqueta().equals("id")) {
			Identificador id= (Identificador) this.componenteLexico;
			if(this.simbolos.get(id.getLexema())==null) {

				this.errores.add("Error en la linea "+this.lexico.getLineas()+" , varibale '"+id.getLexema() +"' no ha sido declarada");
			}else {
				System.out.print(" rvalue " + id.getLexema() + "\n");
				compara("id");
			}
			if (this.componenteLexico.getEtiqueta().equals("open_square_bracket")) {
				compara("open_square_bracket");  
				expresion();
				compara("closed_square_bracket");  
			}
			if(this.comparacionTipo!=null) { 
				if(this.simbolos.get(id.getLexema()).getTipo()!= this.comparacionTipo.toString()) {
					this.errores.add("Eror en la linea "+this.lexico.getLineas()+", incompatibilidad de tipos en la instruccion de asignacion");
				}
			}
		}
	}


	//Expresion-logico
	private void expresion_logica(){
		termino_logico();
		masexpresion_logica();
	}
	private void masexpresion_logica(){
		if (this.componenteLexico.getEtiqueta().equals("or")) {
			compara("or");
			termino_logico();
			System.out.print(" || "  + "\n");
			masexpresion_logica();

			
		}else {
			termino_logico();
		}	
	}


	//Termino-logico
	private void termino_logico() {
		factor_logico();
		mastermino_logico();
	}
	private void mastermino_logico() {
		if (this.componenteLexico.getEtiqueta().equals("and")) {

			compara("and");
			factor_logico();
			System.out.print(" && "  + "\n");
			mastermino_logico();

			

		}else {
			factor_logico();
		}		
	}


	//Factor-logico
	private void factor_logico() {
		if (this.componenteLexico.getEtiqueta().equals("not")) {

			compara("not");
			factor_logico();
			System.out.print(" ! "  + "\n");

		}else if(this.componenteLexico.getEtiqueta().equals("true")) {

			compara("true");
		}else if(this.componenteLexico.getEtiqueta().equals("false")) {

			compara("false");
		}else {
			expresion_relacional();
		}
	}

	//Expresion-relacional
	private void expresion_relacional() { 
		expresion();
		if( 
				this.componenteLexico.getEtiqueta().equals("greater_than") ||
				this.componenteLexico.getEtiqueta().equals("greater_equals") ||
				this.componenteLexico.getEtiqueta().equals("less_than") ||
				this.componenteLexico.getEtiqueta().equals("less_equals") ||
				this.componenteLexico.getEtiqueta().equals("equals") ||
				this.componenteLexico.getEtiqueta().equals("not_equals")
				) {
			operador_relacional();
			expresion();
		}
	}

	//Operador-relacional 
	private void operador_relacional() {
		if (this.componenteLexico.getEtiqueta().equals("less_than")) {

			compara("less_than");
			System.out.print(" < " + "\n");

		}
		else if (this.componenteLexico.getEtiqueta().equals("less_equals")) {

			compara("less_equals");
			System.out.print(" <= " + "\n");

		}
		else if (this.componenteLexico.getEtiqueta().equals("greater_than")) {

			compara("greater_than");
			System.out.print(" > " + "\n");

		}
		else if (this.componenteLexico.getEtiqueta().equals("greater_equals")) {

			compara("greater_equals");
			System.out.print(" >= " + "\n");

		}
		else if (this.componenteLexico.getEtiqueta().equals("equals")) {

			compara("equals");
			System.out.print(" == " + "\n");

		}
		else if (this.componenteLexico.getEtiqueta().equals("not_equals")) {

			compara("not_equals");
			System.out.print(" != " + "\n");

		}
	}


	//Expresion
	private void expresion() {
		termino();
		masexpresion();
	}
	private void masexpresion() {
		termino();
		if(this.componenteLexico.getEtiqueta().equals("add")) {
			
			compara("add");
			termino();
			System.out.print(" + " + "\n");
			masexpresion();
			

		}else if(this.componenteLexico.getEtiqueta().equals("subtract")) {

			compara("subtract");
			termino();
			System.out.print(" - " + "\n");
			masexpresion();

			

		}else {
			termino();
		}
	}



	//Termino
	private void termino() {
		factor();
		mastermino();
	}
	private void mastermino() {
		factor();
		if(this.componenteLexico.getEtiqueta().equals("divide")) {

			compara("divide");
			factor();
			System.out.print(" / "  + "\n");
			mastermino();

		}else if(	this.componenteLexico.getEtiqueta().equals("multiply")){

			compara("multiply");
			factor();
			System.out.print(" * "  + "\n");
			mastermino();

			



		}else if(this.componenteLexico.getEtiqueta().equals("remainder") ) {

			compara("remainder");
			factor();
			System.out.print(" % "  + "\n");
			mastermino();

			

		}else {
			factor();
		}
	}

	//Factor
	private void factor() {

		if (this.componenteLexico.getEtiqueta().equals("int")) {
			if(this.comparacionTipo != null) {
				if(!this.comparacionTipo.equals("int")) {
					this.errores.add("Error en la linea " + this.lexico.getLineas() + ""
							+ " se intenta asignar un " + this.comparacionTipo + " con un int");
				}
			}
			NumeroEntero  numero = (NumeroEntero) this.componenteLexico;
			System.out.print(" push " + numero.getValor() + "\n");
			compara("int");
		}else if(this.componenteLexico.getEtiqueta().equals("float")) { 
			if(this.comparacionTipo != null) {
				if(!this.comparacionTipo.equals("float")) {
					this.errores.add("Error en la linea " + this.lexico.getLineas() 
					+ " se intenta asignar un " + this.comparacionTipo + " con un float");
				}
			}
			NumeroReal  numeroR = (NumeroReal) this.componenteLexico;
			System.out.print(" push " + numeroR.getValor()  + "\n");
			compara("float");
		}else if (this.componenteLexico.getEtiqueta().equals("open_parenthesis")) {
			compara("open_parenthesis");
			expresion();
			compara("closed_parenthesis");
		}else if(this.componenteLexico.getEtiqueta().equals("id")){
			variable();
		}	
	}


	private void compara(String etiqueta) {

		if(this.componenteLexico.getEtiqueta().equals(etiqueta))
			this.componenteLexico = this.lexico.getComponenteLexico(); // AVANZA 
		else
			System.out.println("Error, se esperaba " + etiqueta);
	}

	public String errores() {

		if(this.errores.isEmpty()) {
			return "Programa compilado correctamente";
		}else {
			String s = "";
			for(String elem: this.errores) {
				s = s + elem + "\n";
			}

			return s;
		}
	}
}
