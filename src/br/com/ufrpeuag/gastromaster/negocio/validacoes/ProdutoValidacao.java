package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import br.com.ufrpeuag.gastromaster.dados.RepositorioProduto;
import br.com.ufrpeuag.gastromaster.dados.interfaces.ProdutoDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeProdutoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class ProdutoValidacao {
	private ProdutoDao repProduto;
	
	public ProdutoValidacao() {
		repProduto = new RepositorioProduto();
	}
	
	public void produtoCadastroValidacao(Produto produto) throws PrecoInvalidoException, ProdutoExistenteException, QuantidadeProdutoInvalidaException, NomeInvalidoException {
		if(produto.getQuantidade() < 0) {
			throw new QuantidadeProdutoInvalidaException();
		}
		if(produto.getNome() == null || produto.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}
		//Necessário criar um metódo que verifica existência
		repProduto.inserir(produto);
	}
	
	public void produtoRemocaoValidacao(Produto produto) throws ProdutoInexistenteException{
		//Necessário um método que verifique a inexistencia do produto
		repProduto.deletar(produto);
	}
	public void produtoAlteracaoValidacao(Produto produto) throws ProdutoInexistenteException{
		//Necessário um método que verifique a inexistencia do produto
		repProduto.alterar(produto);
	}
}
