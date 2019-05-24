/*package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioProduto;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoProdutoInvalidoException;
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
	public static void main(String[] args) throws SQLException, NomeInvalidoException, PrecoInvalidoException, ProdutoExistenteException, QuantidadeProdutoInvalidaException, ProdutoInexistenteException, IDRecuperacaoProdutoInvalidoException{
		
		ConfiguracoesBanco.getSingleton().getConnection();
		RepositorioProduto rp= new RepositorioProduto();
		//Produto produto = new Produto("coca-cola 150ml", 0, 2);
		Produto produto = new Produto();
		/*
		TESTE DE INSERCAO
		try {
			Fachada.getSingleton().produtoCadastroValidacao(produto);
		}catch(NomeInvalidoException | QuantidadeProdutoInvalidaException | ProdutoExistenteException | PrecoInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//*/
		
		/*
		TESTE TE REMOCAO
		try {
			produto = Fachada.getSingleton().produtoRetornarProdutoValidacao("macarracao");
			Fachada.getSingleton().produtoRemocaoValidacao(produto);
		}catch(ProdutoInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//*/
		//TESTE DE RECUPERAR COM CODIGO E ALTERAR
		/*
		try {
			produto = Fachada.getSingleton().produtoRecuperarValidacao(2);
			produto.setNome("coca cola 200ml");
			produto.setQuantidade(7);
			Fachada.getSingleton().produtoAlteracaoValidacao(produto);
		}catch(IDRecuperacaoProdutoInvalidoException | NomeInvalidoException | QuantidadeProdutoInvalidaException | PrecoInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
<<<<<<< HEAD
		}
		//*/
		//TESTE RETORNAR QUANTIDADE
=======

		}*/

		
>>>>>>> 24b432e43882c6c72498c73e4b591cef420532cf
		/*
		try {
			produto = Fachada.getSingleton().produtoRecuperarValidacao(2);
			int quant = Fachada.getSingleton().produtoRetornarQuantidadeProdutoValidacao(produto);
			System.out.println(quant);
		}catch(ProdutoInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//*/
		//TESTE DE REMOVER ALGUMA QUANTIDADE
		/*
		try {
			produto = Fachada.getSingleton().produtoRecuperarValidacao(2);
			Fachada.getSingleton().produtoRemoverQuantProdutoValidacao(produto, 2);
		}catch(ProdutoInexistenteException | QuantidadeInvalidaException | QuantidadeProdutoInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//*/
		//TESTE DE MOSTRAR TODOS
		/*
		try {
			System.out.println(Fachada.getSingleton().produtoListarTodosValidacao());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//*/
	//}
//}