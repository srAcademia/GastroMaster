package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class GarcomExistenteException extends Exception{
	
	public GarcomExistenteException() {
		super("Garçom já pertence ao sistema.");
	}
}
