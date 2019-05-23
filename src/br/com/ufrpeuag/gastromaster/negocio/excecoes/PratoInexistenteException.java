package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class PratoInexistenteException extends Exception {
	
	public PratoInexistenteException() {
		super("Prato não encontrado.");
	}
}
