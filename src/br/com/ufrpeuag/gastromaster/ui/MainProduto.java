package br.com.ufrpeuag.gastromaster.ui;
/*
import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioProduto;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class MainProduto {

	public static void main(String[] args) throws SQLException {
		
		ConfiguracoesBanco.getSingleton().getConnection();
		RepositorioProduto rp= new RepositorioProduto();
		
		Produto p = new Produto("nome",10);
		
		//Inserir
		rp.inserir(p);
		
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
		System.out.println(rp.listarTodos());
	}

}
*/