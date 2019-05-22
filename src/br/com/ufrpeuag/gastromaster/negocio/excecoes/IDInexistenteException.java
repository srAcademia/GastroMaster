package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class IDInexistenteException extends Exception{
	
	public IDInexistenteException(){
		super("Último ID não existe.");
	}

}
