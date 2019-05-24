package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class IDRecuperarMesaException extends Exception{
	
	public IDRecuperarMesaException() {
		super("Impossível acessar a mesa");
	}
}
