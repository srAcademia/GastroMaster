/*package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioMesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public class MainMesa {

	public static void main(String[] args) throws SQLException {
	
		ConfiguracoesBanco.getSingleton().getConnection();

		RepositorioMesa rm = new RepositorioMesa();

		Mesa m = new Mesa(2, 0);

		// Inserir
		rm.inserir(m);

		// Recuperar
		m = rm.recuperar(1);
		System.out.println(m);

		// Alterar
		m.setDisponibilidade(1);
		rm.alterar(m);

		// Deletar
		m = rm.recuperar(1);
		rm.deletar(m);

		// ListarTodos
		System.out.println(rm.listarTodos());

	}

}
*/