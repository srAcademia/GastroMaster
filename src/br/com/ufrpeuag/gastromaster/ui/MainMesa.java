package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

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
			Mesa mesa = new Mesa(numero, disponibilidade);
			Fachada.getSingleton().cadastrarMesa(mesa);
			System.out.println("Mesa cadastrado.");
		}catch(MesaCadastradaException | NumeroInvalidoException |MesaDisponibilidadeInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		
	}
	
	//REMOCAO
	public void gerenciarRemocaoMesa(int numero) throws SQLException, MesaInexistenteException {
		try {
			Mesa mesa = new Mesa();
			mesa = Fachada.getSingleton().recuperarMesaPorNumero(numero);
			Fachada.getSingleton().deletarMesa(mesa);
			System.out.println("Mesa removida.");
		}catch(MesaInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		
	}
	
	public Mesa gerenciarGerenciamentoMesa(int numero) throws SQLException, MesaInexistenteException {
		try {
			Mesa mesa = new Mesa();
			mesa = Fachada.getSingleton().recuperarMesaPorNumero(numero);
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
			Mesa mesa = new Mesa();
			mesa = Fachada.getSingleton().recuperarMesaPorNumero(numero);
			Fachada.getSingleton().alterarMesa(mesa, novoNumero);
			System.out.println("Mesa alterada.");
		}catch(MesaCadastradaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//MUDAR DISPONIBILIDADE
	public void gerenciarMudarDisponibilidadeMesa(int numero) throws SQLException, MesaInexistenteException {
		try {
			Mesa mesa = new Mesa();
			mesa = Fachada.getSingleton().recuperarMesaPorNumero(numero);
			Fachada.getSingleton().mudarDisponibilidadeMesa(mesa);
		}catch(MesaInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
		
	//LISTAR TODOS
	/*public void gerenciarListarMesa() throws SQLException, ListarTodosInvalidoException{
		try {
			System.out.println(Fachada.getSingleton().listarTodasMesas());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}*/
}