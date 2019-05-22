/*package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioCardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;

public class MainCardapio {

	public static void main(String[] args) throws SQLException {

		ConfiguracoesBanco.getSingleton().getConnection();

		RepositorioCardapio repCardapio = new RepositorioCardapio();
		Cardapio cardapio = new Cardapio("Pizza", 50);
		Cardapio cardapio1 = new Cardapio("Lasanha", 50);

		// Inserir
		repCardapio.inserir(cardapio);
		repCardapio.inserir(cardapio1);
		// Recuperar
		cardapio = repCardapio.recuperar(3);
		System.out.println(cardapio.getId_cardapio());

		// Recuperar nome:
		cardapio = repCardapio.recuperar("Lasanha");
		System.out.println(cardapio);
		// Alterar
		cardapio = repCardapio.recuperar(3);
		cardapio.setPrato("Lasanha");
		repCardapio.alterar(cardapio);

		// Deletar
		cardapio = repCardapio.recuperar(4);
		cardapio.setPrato("Lasanha");
		repCardapio.deletar(cardapio);

		// Recuperar Todos
		System.out.println(repCardapio.listarTodos());

	}

}
*/