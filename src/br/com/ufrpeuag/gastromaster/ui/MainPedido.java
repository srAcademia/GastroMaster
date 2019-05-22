/*package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import java.util.Scanner;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioCardapio;
import br.com.ufrpeuag.gastromaster.dados.RepositorioPedido;
import br.com.ufrpeuag.gastromaster.dados.RepositorioProduto;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class MainPedido {

	private static Scanner src;

	public static void main(String[] args) throws SQLException {
		ConfiguracoesBanco.getSingleton().getConnection();

		src = new Scanner(System.in);
		RepositorioProduto rp = new RepositorioProduto();

		RepositorioCardapio rc = new RepositorioCardapio();
		RepositorioPedido rpedido = new RepositorioPedido();

		Cardapio card = new Cardapio();
		Produto prod = new Produto();

		System.out.println(rc.listarTodos());
		System.out.println("Digite um  cardapio:");
		int cod_produto = src.nextInt();
		System.out.println(rp.listarTodos());
		System.out.println("Digite um produto:");
		int cod_cardapio = src.nextInt();
		Pedido pedido = null;
		card = rc.recuperar(cod_cardapio);
		prod = rp.recuperar(cod_produto);
		double valor = 0;
		pedido = new Pedido(card, prod, valor);
		pedido.setValor(pedido.calcularValorPedido(card.getPreco(), prod.getPreco()));
		pedido.getCardapio().setId_cardapio(cod_cardapio);
		pedido.getProduto().setId_produto(cod_produto);
		rpedido.inserir(pedido);

	}

}
*/