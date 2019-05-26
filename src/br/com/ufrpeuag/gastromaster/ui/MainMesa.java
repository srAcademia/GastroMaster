package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaCadastradaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaDisponibilidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public class MainMesa {

	//INSERCAO
	public void gerenciarCadastroMesa(int numero, int disponibilidade) throws SQLException, MesaCadastradaException, NumeroInvalidoException, MesaDisponibilidadeInvalidaException{
		try {
			ConfiguracoesBanco.getSingleton().getConnection();
			Mesa mesa = new Mesa(numero, disponibilidade);
			Fachada.getSingleton().mesaCadastroMesaValidacao(mesa);
		}catch(MesaCadastradaException | NumeroInvalidoException |MesaDisponibilidadeInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		
	}
	
	//REMOCAO
	public void gerenciarRemocaoMesa(int numero) throws SQLException, MesaInexistenteException {
		try {
			ConfiguracoesBanco.getSingleton().getConnection();
			Mesa mesa = new Mesa();
			mesa = Fachada.getSingleton().mesaRecuperarNumeroValidacao(numero);
			Fachada.getSingleton().mesaRemocaoValidacao(mesa);
		}catch(MesaInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		
	}
	
	public Mesa gerenciarGerenciamentoMesa(int numero) throws SQLException, MesaInexistenteException {
		try {
			ConfiguracoesBanco.getSingleton().getConnection();
			Mesa mesa = new Mesa();
			mesa = Fachada.getSingleton().mesaRecuperarNumeroValidacao(numero);
			return mesa;
		}catch(MesaInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return null;
		
	}
	
	
	//ALTERAR
	public void gerenciarAlteracaoMesa(int numero, int novoNumero) throws SQLException, MesaCadastradaException{
		try {
			ConfiguracoesBanco.getSingleton().getConnection();
			Mesa mesa = new Mesa();
			mesa = Fachada.getSingleton().mesaRecuperarNumeroValidacao(numero);
			Fachada.getSingleton().mesaAlteracaoValidacao(mesa, novoNumero);
		}catch(MesaCadastradaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//MUDAR DISPONIBILIDADE
	public void gerenciarMudarDisponibilidadeMesa(int numero) throws SQLException, MesaInexistenteException {
		try {
			ConfiguracoesBanco.getSingleton().getConnection();
			Mesa mesa = new Mesa();
			mesa = Fachada.getSingleton().mesaRecuperarNumeroValidacao(numero);
			Fachada.getSingleton().mesaMudarDisponibilidadeValidacao(mesa);;
		}catch(MesaInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
		
	//LISTAR TODOS
	public void gerenciarListarMesa() throws SQLException, ListarTodosInvalidoException{
		try {
			ConfiguracoesBanco.getSingleton().getConnection();
			System.out.println(Fachada.getSingleton().mesaListarTodosValidacao());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
}