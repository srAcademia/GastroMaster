package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class EnderecoInexistenteException extends Exception{
	
	public EnderecoInexistenteException() {
		super("Endereço não existe");
	}
}
