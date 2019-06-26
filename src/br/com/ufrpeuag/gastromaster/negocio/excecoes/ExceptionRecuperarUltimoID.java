package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class ExceptionRecuperarUltimoID extends Exception {

	public ExceptionRecuperarUltimoID() {
		super("Erro ao recuperar último ID de pedido, tabela Vazia! ");
	}
}
