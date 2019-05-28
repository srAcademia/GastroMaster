package br.com.ufrpeuag.gastromaster.negocio.excecoes;

public class RelatorioVazioException extends Exception {
	
	public RelatorioVazioException() {
		super("Nenhum dado para ser exibido.");
	}

}
