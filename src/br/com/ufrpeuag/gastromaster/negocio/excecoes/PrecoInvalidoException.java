package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class PrecoInvalidoException extends Exception{
	
	public PrecoInvalidoException() {
		super("Preço inválido.");
	}
}
