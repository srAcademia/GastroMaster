package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeProdutoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
public class MainProduto {
	
	//INSERCAO
	public void gerenciarCadastroProduto(String nome, int quantidade, double preco) throws SQLException, NomeInvalidoException, PrecoInvalidoException, ProdutoExistenteException, QuantidadeProdutoInvalidaException {
		try {
			Produto produto = new Produto(nome, quantidade, preco);
			Fachada.getSingleton().produtoCadastroValidacao(produto);
		}catch(NomeInvalidoException | QuantidadeProdutoInvalidaException | ProdutoExistenteException | PrecoInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
		
	//REMOCAO
	public void gerenciarRemocaoProduto(String nome) throws SQLException, ProdutoInexistenteException, NomeInvalidoException {
		try {
			Produto produto = new Produto();
			produto = Fachada.getSingleton().produtoRetornarProdutoValidacao(nome);
			Fachada.getSingleton().produtoRemocaoValidacao(produto);
		}catch(ProdutoInexistenteException | NomeInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//ALTERACAO
	public void gerenciarAlteracaoProduto(String nome, String novoNome, int quantidade, double preco)throws SQLException, NomeInvalidoException, ProdutoInexistenteException, ProdutoExistenteException{
		try {
			Produto produto = new Produto();
			produto = Fachada.getSingleton().produtoRetornarProdutoValidacao(nome);
			Fachada.getSingleton().produtoAlteracaoValidacao(produto, nome, novoNome, quantidade, preco);
		}catch(NomeInvalidoException | ProdutoExistenteException | ProdutoInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//RECUPERAR
	public void gerenciarRecuperarProduto(Integer codigo) throws SQLException, IDRecuperacaoItemInvalidoException {
		try {	
			Produto produto = new Produto();
			produto = Fachada.getSingleton().produtoRecuperarValidacao(codigo);
			System.out.println(produto);
		}catch(IDRecuperacaoItemInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//RECUPERAR ID
	public Integer gerenciarRecuperarIDProduto(String nome) throws SQLException, NomeInvalidoException, ProdutoInexistenteException{
		try {
			Integer id = Fachada.getSingleton().produtoRetornarIDValidacao(nome);
			return id;
		}catch(NomeInvalidoException | ProdutoInexistenteException ex) {
			System.out.println(ex.getMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return null;
	}
	
	//RETORNAR QUANTIDADE 
	public void gerenciarVerificarQuantidadeProduto(String nome)throws SQLException, ProdutoInexistenteException, NomeInvalidoException{
		try {
			Produto produto = new Produto();
			produto = Fachada.getSingleton().produtoRetornarProdutoValidacao(nome);
			int quant = Fachada.getSingleton().produtoRetornarQuantidadeProdutoValidacao(produto);
			System.out.println(quant);
		}catch(ProdutoInexistenteException | NomeInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//REMOVER ALGUMA QUANTIDADE
	public void gerenciarRemoverQuantidadeProduto(Integer codigo, int quantidade) throws SQLException, QuantidadeInvalidaException, QuantidadeProdutoInvalidaException, IDRecuperacaoItemInvalidoException {
		try {
			Produto produto = new Produto();
			produto = Fachada.getSingleton().produtoRecuperarValidacao(codigo);
			Fachada.getSingleton().produtoRemoverQuantProdutoValidacao(produto, quantidade);
		}catch(IDRecuperacaoItemInvalidoException | QuantidadeInvalidaException | QuantidadeProdutoInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//ADICIONAR ALGUMA QUANTIDADE
	public void gerenciarAdcionarQuantidadeProduto(Integer codigo, int quantidade) throws SQLException, QuantidadeProdutoInvalidaException, IDRecuperacaoItemInvalidoException {
		try {
			Produto produto = new Produto();
			produto = Fachada.getSingleton().produtoRecuperarValidacao(codigo);
			Fachada.getSingleton().produtoAdicionarQuantProdutoValidacao(produto, quantidade);
		}catch(IDRecuperacaoItemInvalidoException | QuantidadeProdutoInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
		
	//TESTE DE MOSTRAR TODOS
	public void gerenciarListarProduto() throws SQLException, ListarTodosInvalidoException{
		try {
			System.out.println(Fachada.getSingleton().produtoListarTodosValidacao());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
}