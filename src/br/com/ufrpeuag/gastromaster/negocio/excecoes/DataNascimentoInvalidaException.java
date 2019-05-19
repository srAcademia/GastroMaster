package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class DataNascimentoInvalidaException extends Exception{
	
	public DataNascimentoInvalidaException() {
		super("Data de nascimento inválida.");
	}
}
