package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class PedidoInvalidoException extends Exception{
	
	public PedidoInvalidoException() {
		super("Pedido inválido.");
	}
}
