package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class PedidoInexistenteException extends Exception{
	
	public PedidoInexistenteException() {
		super("Pedido não foi efetuado.");
	}
}
