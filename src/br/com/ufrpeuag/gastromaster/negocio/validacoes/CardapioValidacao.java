package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioCardapio;
import br.com.ufrpeuag.gastromaster.dados.interfaces.ICardapioDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;

public class CardapioValidacao {
	private ICardapioDao repCardapio;
	
	public CardapioValidacao() throws SQLException {
		repCardapio = new RepositorioCardapio();
	}
	
	public void cardapioCadastroValidacao(Cardapio cardapio) throws PratoExistenteException, NomeInvalidoException, PrecoInvalidoException {
		if(repCardapio.recuperar(cardapio.getPrato()) != null) {
			throw new PratoExistenteException();
		}
		if(cardapio.getPrato() == null || cardapio.getPrato().isEmpty()) {
			throw new NomeInvalidoException();
		}
		if(cardapio.getPreco() <= 0) {
			throw new PrecoInvalidoException();
		}
		repCardapio.inserir(cardapio);
	}
	
	public void cardapioRemocaoValidacao(Cardapio cardapio) throws PratoInexistenteException {
		if(repCardapio.recuperar(cardapio.getPrato()) == null) {
			throw new PratoInexistenteException();
		}
		repCardapio.deletar(cardapio);
	}
	
	public void cardapioAlteracaoValidacao(Cardapio cardapio, String nome, String novoNome, double preco) throws PratoExistenteException {
		if(novoNome.isEmpty() == false) {
			if(repCardapio.recuperar(novoNome) != null) {
				throw new PratoExistenteException();
			}
			cardapio.setPrato(novoNome);
		}
		if(preco > 0) {
			cardapio.setPreco(preco);
		}
		repCardapio.alterar(cardapio);
	}
	
	public Cardapio cardapioRecuperarValidacao(String nome) throws PratoInexistenteException, NomeInvalidoException {
		if(nome.equals(null)) {
			throw new NomeInvalidoException();
		}
		List<Cardapio> cardapio = new ArrayList<>();
		cardapio = this.repCardapio.listarTodos();
		for (int i = 0; i < cardapio.size(); i++) {
			if(cardapio.get(i).getPrato().equals(nome)) {
				return repCardapio.recuperar(nome);
			}
		}
		throw new PratoInexistenteException();
	}
	
	public Integer cardapioRetornarIDValidacao(String nome) throws NomeInvalidoException, PratoInexistenteException{
		if(nome== null || nome.isEmpty()) {
			throw new NomeInvalidoException();
		}
		int id = repCardapio.retornarID(nome);
		if(id == 0) {
			throw new PratoInexistenteException();
		}
		return id;
	}
	
	public Cardapio cardapioRecuperarValidacao(Integer codigo) throws IDRecuperacaoItemInvalidoException{
		List<Cardapio> cardapio = new ArrayList<>();
		cardapio = this.repCardapio.listarTodos();
		for (int i = 0; i < cardapio.size(); i++) {
			if(cardapio.get(i).getId_cardapio() == (codigo)) {
				return repCardapio.recuperar(codigo);
			}
		}
		throw new IDRecuperacaoItemInvalidoException();
	}
	
	public List<Cardapio> cardapioListarTodosValidacao() throws ListarTodosInvalidoException {
		if(repCardapio.listarTodos() == null || repCardapio.listarTodos().isEmpty()) {
			throw new ListarTodosInvalidoException();
		}
		return repCardapio.listarTodos();
	}

}
