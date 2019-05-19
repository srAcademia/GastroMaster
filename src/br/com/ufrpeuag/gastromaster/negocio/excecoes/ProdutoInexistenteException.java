package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class ProdutoInexistenteException extends Exception{
	
	public ProdutoInexistenteException() {
		super("Produto não cadastrado.");
	}
}
