package br.com.ufrpeuag.gastromaster.ui;
/*
import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioProduto;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeProdutoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class MainProduto {

	public static void main(String[] args) throws SQLException, NomeInvalidoException, PrecoInvalidoException, ProdutoExistenteException, QuantidadeProdutoInvalidaException{
		
		ConfiguracoesBanco.getSingleton().getConnection();
		RepositorioProduto rp= new RepositorioProduto();

<<<<<<< HEAD
=======

		/*try {
			ConfiguracoesBanco.getSingleton().getConnection();

>>>>>>> b01750888d483d95cca928715b4022e99e98ec95
		Produto p = new Produto("macarracao", -1, 2.5);
		
		try {

			Fachada.getSingleton().produtoCadastroValidacao(p);
		}catch(NomeInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(QuantidadeProdutoInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());

		}*/

		}
		/*
		
		Produto p = new Produto("nome",10,5);


		//Inserir
		rp.inserir(p);

		rp.inserir(p2);

		// Recuperar e alterar

		
		//Recuperar e alterar

		p = rp.recuperar(1);
		p.setNome("Refrigerante2");
		rp.alterar(p);
		
		//Deletar
		p = rp.recuperar(2);
		rp.deletar(p);
		
		// Retornar quantidade de Produtos
		p = rp.recuperar(3);
		int quant = rp.retonarQuantidadeProduto(p);
		System.out.println(quant);
		
		//Remover uma quantidade
		p = rp.recuperar(4);
		rp.removerQuantProduto(p, 5);
	
		// Listar Todos

		// System.out.println(rp.listarTodos());
	}
	

}*/