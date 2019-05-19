package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class BairroInvalidoException extends Exception{
	public BairroInvalidoException() {
		super("Bairro não poder ser vazio.");
	}
}
