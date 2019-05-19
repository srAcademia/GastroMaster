package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class EnderecoVazioException extends Exception{
	
	public EnderecoVazioException() {
		super("O formulário de endereço deve ser preenchido primeiro.");
	}
}
