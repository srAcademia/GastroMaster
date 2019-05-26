package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioProduto;
import br.com.ufrpeuag.gastromaster.dados.interfaces.ProdutoDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeProdutoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class ProdutoValidacao {
	private ProdutoDao repProduto;
	
	public ProdutoValidacao() {
		repProduto = new RepositorioProduto();
	}
	
	public void produtoCadastroValidacao(Produto produto) throws PrecoInvalidoException, ProdutoExistenteException, QuantidadeProdutoInvalidaException, NomeInvalidoException {
		if(repProduto.retornarProduto(produto.getNome()) != null) {
			throw new ProdutoExistenteException();
		}
		if(produto.getNome() == null || produto.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}
		if(produto.getQuantidade() < 0) {
			throw new QuantidadeProdutoInvalidaException();
		}
		if(produto.getPreco() <= 0) {
			throw new PrecoInvalidoException();
		}
		repProduto.inserir(produto);
	}
	
	public void produtoRemocaoValidacao(Produto produto) throws ProdutoInexistenteException{
		if(repProduto.retornarProduto(produto.getNome()) == null) {
			throw new ProdutoInexistenteException();
		}
		repProduto.deletar(produto);
	}
	
	public void produtoAlteracaoValidacao(Produto produto, String nome, String novoNome, int quantidade, double preco)throws ProdutoExistenteException{
		if(novoNome.isEmpty() == false) {
			if(repProduto.retornarProduto(novoNome) != null) {
				throw new ProdutoExistenteException();
			}
			produto.setNome(novoNome);
		}
		if(quantidade > 0) {
			produto.setQuantidade(quantidade);
		}
		if(preco > 0) {
			produto.setPreco(preco);
		}
		repProduto.alterar(produto);
	}
	
	public Produto produtoRetornarProdutoValidacao(String nome)throws ProdutoInexistenteException, NomeInvalidoException{
		if(nome.equals(null)) {
			throw new NomeInvalidoException();
		}
		
		List<Produto> produto = new ArrayList<>();
		produto = this.repProduto.listarTodos();
		for (int i = 0; i < produto.size(); i++) {
			if(produto.get(i).getNome().equals(nome)) {
				return repProduto.retornarProduto(nome);
			}
		}
		throw new ProdutoInexistenteException();
	}
	
	public Produto produtoRecuperarValidacao(Integer codigo) throws IDRecuperacaoItemInvalidoException{
		List<Produto> produto = new ArrayList<>();
		produto = this.repProduto.listarTodos();
		for (int i = 0; i < produto.size(); i++) {
			if(produto.get(i).getId_produto() == (codigo)) {
				return repProduto.recuperar(codigo);
			}
		}
		throw new IDRecuperacaoItemInvalidoException();
	}
	
	public int produtoRetornarQuantidadeProdutoValidacao(Produto produto) throws ProdutoInexistenteException{
		if(repProduto.retornarProduto(produto.getNome()) == null) {
			throw new ProdutoInexistenteException();
		}
		return repProduto.retornarQuantidadeProduto(produto);
	}
	
	public void produtoRemoverQuantProdutoValidacao(Produto produto, Integer quantidade) throws QuantidadeInvalidaException, QuantidadeProdutoInvalidaException {
		if(produto.getQuantidade() < quantidade) {
			throw new QuantidadeInvalidaException();
		}
		if(quantidade.equals(null) || quantidade < 0) {
			throw new QuantidadeProdutoInvalidaException();
		}
		repProduto.removerQuantProduto(produto, quantidade);
	}
	public void produtoAdicionarQuantProdutoValidacao(Produto produto, Integer quantidade) throws QuantidadeProdutoInvalidaException {
		if(quantidade.equals(null) || quantidade < 0) {
			throw new QuantidadeProdutoInvalidaException();
		}
		repProduto.adicionarQuantProduto(produto, quantidade);
	}
	
	public List<Produto> produtoListarTodosValidacao() throws ListarTodosInvalidoException{
		if (repProduto.listarTodos() == null) {
			throw new ListarTodosInvalidoException();
		}
		return repProduto.listarTodos();
	}
}
