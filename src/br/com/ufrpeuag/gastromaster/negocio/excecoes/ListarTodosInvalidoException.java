package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class ListarTodosInvalidoException extends Exception{
	
	public ListarTodosInvalidoException() {
		super("Impossível listar todos.");
	}

}
