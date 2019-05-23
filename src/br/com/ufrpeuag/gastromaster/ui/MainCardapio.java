/*package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioCardapio;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;

public class MainCardapio {

	public static void main(String[] args) throws SQLException {

		ConfiguracoesBanco.getSingleton().getConnection();

		//Cardapio cardapio = new Cardapio("Porcao media de batata", 12);
		Cardapio cardapio = new Cardapio();
		// TESTE DE INSERCAO
		/*
		try {
			Fachada.getSingleton().cardapioCadastroValidacao(cardapio);
		}catch(PratoExistenteException | NomeInvalidoException | PrecoInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//*/
		
		// TESTE DE REMOCAO E RECUPERAR PELO NOME
		/*
		try {
			cardapio = Fachada.getSingleton().cardapioRecuperarValidacao("Pizza");
			Fachada.getSingleton().cardapioRemocaoValidacao(cardapio);
		}catch(PratoInexistenteException | NomeInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//*/
		
		/*/
		//TESTE DE ALTERACAO E RECUPERAR PELO CODIGO
		try {
			cardapio = Fachada.getSingleton().cardapioRecuperarValidacao(2);
			cardapio.setPrato("Porcao grande de batata");
			Fachada.getSingleton().cardapioAlteracaoValidacao(cardapio);
		}catch(IDRecuperacaoItemInvalidoException | NomeInvalidoException | PrecoInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//*/
		
		/*
		//TESTE DE LISTAR TODOS
		try {
			System.out.println(Fachada.getSingleton().cardapioListarTodosValidacao());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		
		//*/
	//}

//}
