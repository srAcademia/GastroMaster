package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class PedidoVazioException extends Exception{
	
	public PedidoVazioException(){
		super("Algo deve ser pedido para efetuar seu pedido.");
	}
}
