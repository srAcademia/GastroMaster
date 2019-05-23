package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class PratoExistenteException extends Exception{
	
	public PratoExistenteException() {
		super ("Prato já pertence ao sistema.");
	}
}
