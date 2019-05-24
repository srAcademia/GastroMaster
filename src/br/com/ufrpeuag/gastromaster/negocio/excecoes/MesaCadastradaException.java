package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class MesaCadastradaException extends Exception{
	
	public MesaCadastradaException() {
		super("Mesa já pertence ao sistema.");
	}
}
