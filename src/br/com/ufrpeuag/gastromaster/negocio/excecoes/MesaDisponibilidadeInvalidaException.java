package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class MesaDisponibilidadeInvalidaException extends Exception{
	
	public MesaDisponibilidadeInvalidaException() {
		super("Disponibilidade inválida para a mesa.");
	}
}
