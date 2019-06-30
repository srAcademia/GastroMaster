package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioCardapio;
import br.com.ufrpeuag.gastromaster.dados.interfaces.ICardapioDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;

public class CardapioNegocio {
	private ICardapioDao repCardapio;

	public CardapioNegocio() throws SQLException {
		repCardapio = new RepositorioCardapio();
	}

	public void cadastrarCardapio(Cardapio cardapio)
			throws PratoExistenteException, NomeInvalidoException, PrecoInvalidoException {
		if (cardapio == null){
			throw new NomeInvalidoException();
		}
		
		if (repCardapio.recuperarPorNome(cardapio.getPrato()) != null) {
			throw new PratoExistenteException();
		}
		if (cardapio.getPrato() == null || cardapio.getPrato().isEmpty()) {
			throw new NomeInvalidoException();
		}
		if (cardapio.getPreco() <= 0) {
			throw new PrecoInvalidoException();
		}
		repCardapio.inserir(cardapio);
	}

	public void deletarCardapio(Cardapio cardapio) throws PratoInexistenteException {
		if(cardapio == null) {
			throw new PratoInexistenteException();
		}
		if (repCardapio.recuperarPorNome(cardapio.getPrato()) == null) {
			throw new PratoInexistenteException();
		}
		repCardapio.deletar(cardapio);
	}

	public void alterarCardapio(Cardapio cardapio, String novoNome, double preco) throws PratoExistenteException {
		if(novoNome.isEmpty() == false) {
			if(repCardapio.recuperarPorNome(novoNome) != null) {
				throw new PratoExistenteException();
			}
			cardapio.setPrato(novoNome);
		}
		if(preco > 0) {
			cardapio.setPreco(preco);
		}
		repCardapio.alterar(cardapio);
	}
	

	public Cardapio recuperarCardapioPeloNome(String nome) throws PratoInexistenteException, NomeInvalidoException {

		if (nome.equals(null)) {
			throw new NomeInvalidoException();
		}
		if (repCardapio.recuperarPorNome(nome) == null) {
			throw new PratoInexistenteException();
		}
		return repCardapio.recuperarPorNome(nome);
	}

	public Integer recuperarIDPeloNomeCardapio(String nome) throws NomeInvalidoException, PratoInexistenteException {
		if (nome == null || nome.isEmpty()) {
			throw new NomeInvalidoException();
		}

		if (repCardapio.retornarID(nome) == 0) {
			throw new PratoInexistenteException();
		}
		return repCardapio.retornarID(nome);
	}

	public Cardapio recuperarCardapioPorID(Integer codigo) throws PratoInexistenteException {
		if (repCardapio.recuperar(codigo) == null) {
			throw new PratoInexistenteException();
		}
		return repCardapio.recuperar(codigo);

	}

	public List<Cardapio> listarTodosCardapios() {
		return repCardapio.listarTodos();
	}
}
