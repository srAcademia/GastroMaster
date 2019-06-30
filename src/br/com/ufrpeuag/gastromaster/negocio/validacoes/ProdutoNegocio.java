package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioProduto;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IProdutoDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeProdutoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class ProdutoNegocio {
	private IProdutoDao repProduto;

	public ProdutoNegocio() throws SQLException {
		repProduto = new RepositorioProduto();
	}

	public void cadastrarProduto(Produto produto) throws PrecoInvalidoException, ProdutoExistenteException,
			QuantidadeProdutoInvalidaException, NomeInvalidoException {
		
		if (produto == null) {
			throw new NomeInvalidoException();
		}
		
		if (repProduto.retornarProduto(produto.getNome()) != null) {
			throw new ProdutoExistenteException();
		}
		if (produto.getNome() == null || produto.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}
		if (produto.getQuantidade() < 0) {
			throw new QuantidadeProdutoInvalidaException();
		}
		if (produto.getPreco() <= 0) {
			throw new PrecoInvalidoException();
		}
		repProduto.inserir(produto);
	}

	public void deletarProduto(Produto produto) throws ProdutoInexistenteException, NomeInvalidoException {
		if (produto == null) {
			throw new NomeInvalidoException();
		}
		if (repProduto.retornarProduto(produto.getNome()) == null) {
			throw new ProdutoInexistenteException();
		}
		repProduto.deletar(produto);
	}

	public void alterarProduto(Produto produto, String novoNome, int quantidade, double preco)
			throws ProdutoExistenteException {
		
		if (novoNome.isEmpty() == false) {
			if (repProduto.retornarProduto(novoNome) != null) {
				throw new ProdutoExistenteException();
			}
			produto.setNome(novoNome);
		}
		if (quantidade > 0) {
			produto.setQuantidade(quantidade);
		}
		if (preco > 0) {
			produto.setPreco(preco);
		}
		repProduto.alterar(produto);
	}

	public Produto recuperarProdutoNome(String nome) throws ProdutoInexistenteException, NomeInvalidoException {

		if (nome.equals(null)) {
			throw new NomeInvalidoException();
		}
		if (repProduto.retornarProduto(nome) == null) {
			throw new ProdutoInexistenteException();
		}
		return repProduto.retornarProduto(nome);
	}

	public Produto recuperarProdutoPorID(Integer codigo) throws IDRecuperacaoItemInvalidoException {

		if (repProduto.recuperar(codigo) == null) {
			throw new IDRecuperacaoItemInvalidoException();
		}
		return repProduto.recuperar(codigo);
	}

	public int recuperarQuantidadeProduto(Produto produto) throws ProdutoInexistenteException {

		if (repProduto.retornarProduto(produto.getNome()) == null) {
			throw new ProdutoInexistenteException();
		}
		return repProduto.retornarQuantidadeProduto(produto);
	}

	public Integer recuperarIDPeloNome(String nome) throws NomeInvalidoException, ProdutoInexistenteException {

		if (nome == null || nome.isEmpty()) {
			throw new NomeInvalidoException();
		}

		if ((int) repProduto.retornarID(nome) == 0) {
			throw new ProdutoInexistenteException();
		}
		return repProduto.retornarID(nome);
	}

	public void removerQuantidadeProduto(Produto produto, Integer quantidade)
			throws QuantidadeInvalidaException, QuantidadeProdutoInvalidaException {

		if (produto.getQuantidade() < quantidade) {
			throw new QuantidadeInvalidaException();
		}
		if (quantidade.equals(null) || quantidade < 0) {
			throw new QuantidadeProdutoInvalidaException();
		}
		repProduto.removerQuantProduto(produto, quantidade);
	}

	public void adicionarQuantidadeProduto(Produto produto, Integer quantidade)
			throws QuantidadeProdutoInvalidaException {

		if (quantidade.equals(null) || quantidade < 0) {
			throw new QuantidadeProdutoInvalidaException();
		}
		repProduto.adicionarQuantProduto(produto, quantidade);
	}

	public List<Produto> listarTodosProdutos() {
		return repProduto.listarTodos();
	}
}
