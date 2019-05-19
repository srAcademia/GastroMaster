package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class GarcomInexistenteException extends Exception{
	
	public GarcomInexistenteException() {
		super("Garcom não encontrado.");
	}
}
