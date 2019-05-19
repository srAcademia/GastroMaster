package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class ProdutoExistenteException extends Exception{
	
	public ProdutoExistenteException() {
		super("Produto já pertence ao sistema.");
	}

}
