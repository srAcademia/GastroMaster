package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;

public class MainCardapio {
	
	//INSERCAO
	public void gerenciarCadastroCardapio(String nome, double preco) throws SQLException, NomeInvalidoException, PrecoInvalidoException{
		try {
			Cardapio cardapio = new Cardapio(nome, preco);
			Fachada.getSingleton().cardapioCadastroValidacao(cardapio);
		}catch(PratoExistenteException | NomeInvalidoException | PrecoInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//REMOCAO
	public void gerenciarRemocaoCardapio(String nome) throws SQLException, NomeInvalidoException, PratoInexistenteException {
		try {
			Cardapio cardapio = new Cardapio();
			cardapio = Fachada.getSingleton().cardapioRecuperarValidacao(nome);
			Fachada.getSingleton().cardapioRemocaoValidacao(cardapio);
		}catch(PratoInexistenteException | NomeInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//ALTERACAO
	public void gerenciarAlteracaoCardapio(String nome, String novoNome, double preco) throws SQLException, PratoInexistenteException, NomeInvalidoException, PratoExistenteException {
		try {
			Cardapio cardapio = new Cardapio();
			cardapio = Fachada.getSingleton().cardapioRecuperarValidacao(nome);
			Fachada.getSingleton().cardapioAlteracaoValidacao(cardapio, nome, novoNome, preco);
		}catch(PratoInexistenteException | NomeInvalidoException | PratoExistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//RECUPERAR
	public void gerenciarRecuperarCardapio(Integer codigo) throws SQLException, IDRecuperacaoItemInvalidoException {
		try {
			Cardapio cardapio = new Cardapio();
			cardapio = Fachada.getSingleton().cardapioRecuperarValidacao(codigo);
			System.out.println(cardapio);
		}catch(IDRecuperacaoItemInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//RECUPERAR ID
	public Integer gerenciarRecuperarIDCardapio(String nome) throws SQLException, NomeInvalidoException, PratoInexistenteException{
		try {
			Integer id = Fachada.getSingleton().cardapioRetornarIDValidacao(nome);
			return id;
		}catch(NomeInvalidoException | PratoInexistenteException ex) {
			System.out.println(ex.getMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return null;
	}
	
	//LISTAR TODOS
	public void gerenciarListarCardapio() throws SQLException, ListarTodosInvalidoException {
		try {
			System.out.println(Fachada.getSingleton().cardapioListarTodosValidacao());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
}
