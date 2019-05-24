package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class MesaInexistenteException extends Exception{
	
	public MesaInexistenteException() {
		super("Mesa inexistente.");
	}
}
