/*package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioMesa;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperarMesaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaCadastradaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaDisponibilidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public class MainMesa {

	public static void main(String[] args) throws SQLException {
	
		ConfiguracoesBanco.getSingleton().getConnection();
		RepositorioMesa rm = new RepositorioMesa();
		//Mesa mesa = new Mesa(1, 1);
		Mesa mesa = new Mesa();
		
		//TESTE DE INSERCAO
		/*
		try {
			Fachada.getSingleton().mesaCadastroMesaValidacao(mesa);
		}catch(MesaCadastradaException | NumeroInvalidoException |MesaDisponibilidadeInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		//*/
		
		//TESTE DE RECUPERAR E ALTERAR
		
		/*/
		try {
			mesa = Fachada.getSingleton().mesaRecuperarValidacao(1);
			mesa.setDisponibilidade(1);
			Fachada.getSingleton().mesaAlteracaoValidacao(mesa);
		}catch(IDRecuperarMesaException | MesaDisponibilidadeInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		//*/
		//TESTE DE REMOCAO SEMPRE PRITANDO QUE MESA N EXISTE
		/*
		try {
			mesa = Fachada.getSingleton().mesaRecuperarValidacao(2);
			Fachada.getSingleton().mesaRemocaoValidacao(mesa);
		}catch(IDRecuperarMesaException | MesaInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		
		//*/
		/*
		//TESTE LISTAR TODOS
		try {
			System.out.println(Fachada.getSingleton().mesaListarTodosValidacao());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		//*/
		
	//}

//}