package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class NomeInvalidoException extends Exception{
	public NomeInvalidoException() {
		super("Nome não pode ser vazio.");
	}
}