package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class ContaGerarException extends Exception{
	
	public ContaGerarException() {
		super("Impossível gerar conta da mesa.");
	}
}
