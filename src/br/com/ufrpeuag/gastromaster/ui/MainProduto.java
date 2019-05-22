package br.com.ufrpeuag.gastromaster.ui;

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

	public static void main(String[] args) throws SQLException, NomeInvalidoException, PrecoInvalidoException,
			ProdutoExistenteException, QuantidadeProdutoInvalidaException {

		RepositorioProduto rp = new RepositorioProduto();

		Produto p = new Produto("Coca-cola", 2, 2.5);
		Produto p2 = new Produto("Fanta", 10, 5);

		/*try {
			ConfiguracoesBanco.getSingleton().getConnection();
			Fachada.getSingleton().produtoCadastroValidacao(p);
		} catch (NomeInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		} catch (QuantidadeProdutoInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}*/

		// Inserir

		rp.inserir(p);
		rp.inserir(p2);

		/*// Recuperar e alterar
		p = rp.recuperar(1);
		p.setNome("Refrigerante2");
		rp.alterar(p);

		// Recuperar Por nome
		p = rp.retornarProduto("Fanta");
		System.out.println(p);

		// Deletar
		p = rp.recuperar(2);
		rp.deletar(p);

		// Retornar quantidade de Produtos
		p = rp.recuperar(3);
		int quant = rp.retornarQuantidadeProduto(p);
		System.out.println(quant);

		// Remover uma quantidade
		p = rp.recuperar(4);
		rp.removerQuantProduto(p, 5);

		// Listar Todos
		// System.out.println(rp.listarTodos());
*/	}
}