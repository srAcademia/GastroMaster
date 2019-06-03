package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import java.time.LocalDate;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.LoginInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RecuperarCPFException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SenhaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;

public class MainGerente {
	
	//CADASTRAR
	public void gerenciarCadastroGerente(String cidade, String bairro, String rua, int numero, String cep, String nome, String cpf, LocalDate dataNasc, String telefone, String email, double salario, String senha, String identificador)
			throws SQLException, BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException, CPFInvalidoException, DataNascimentoInvalidaException, NomeInvalidoException, GerenteExistenteException, IDInexistenteException, SalarioInvalidoException, IDRecuperacaoInvalidaException{
		try {
			Endereco end  = new Endereco(cidade, bairro, rua, numero, cep);
			Gerente gerente = new Gerente(nome, cpf, dataNasc, telefone, email, salario, senha, identificador, end);
			
			Fachada.getSingleton().cadastrarGerente(gerente);
			System.out.println("Gerente cadastrado.");
			System.out.println("ID gerado do gerente: "+gerente.getIdentificador());
		}catch(BairroInvalidoException | CEPInvalidoException | CidadeInvalidaException | NumeroInvalidoException | RuaInvalidaException | CPFInvalidoException | DataNascimentoInvalidaException | NomeInvalidoException | GerenteExistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
			
	}
	//VERIFICAR SE E GERENTE
	public Gerente gerenciarVerificarGerente(String identificador) throws SQLException, LoginInvalidoException{
		try {
			Gerente gerente = new Gerente();
			gerente = Fachada.getSingleton().verificarIdentificadorGerente(identificador);
			return gerente;
		}catch(LoginInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return null;
	}
	//LOGAR
	
	public Gerente gerenciarLogarGerente(String senha) throws SQLException, SenhaInvalidaException {
		try {
			Gerente gerente = new Gerente();
			gerente = Fachada.getSingleton().logarGerente(senha);
			return gerente;
		}catch(SenhaInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return null;
	}
		
	//REMOCAO
	public void gerenciarRemocaoGerente(String CPF) throws SQLException, GerenteInexistenteException, CPFInvalidoException, RecuperarCPFException {
		try {
			Gerente gerente = new Gerente();
			gerente = Fachada.getSingleton().recuperarCpfPorGerente(CPF);
			Fachada.getSingleton().deletarGerente(gerente);
			
			System.out.println("Gerente removido.");
		}catch(GerenteInexistenteException | CPFInvalidoException | RecuperarCPFException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//ALTERACAO
	/*public void gerenciarAlteracaoGerente(String cidade, String bairro, String rua, int numero, String cep, String nome, String cpf, String novoCPF, String dataNasc, String telefone, String email, double salario, String senha)
			throws SQLException, CPFInvalidoException, RecuperarCPFException, DataNascimentoInvalidaException, GerenteExistenteException {
		try {
			Gerente gerente = new Gerente();
			Endereco end = new Endereco();
			gerente = Fachada.getSingleton().gerenteRecuperarCPFValidacao(cpf);
			end = Fachada.getSingleton().enderecoRecuperarValidacao(gerente.getEndereco().getId_endereco());
			Fachada.getSingleton().enderecoAlteracaoValidacao(end, cidade, bairro, rua, numero, cep);
			Fachada.getSingleton().gerenteAlteracaoValidacao(gerente, nome, cpf, novoCPF, dataNasc, telefone, email, salario, senha);
			System.out.println("Gerente alterado.");
		}catch( CPFInvalidoException | RecuperarCPFException | DataNascimentoInvalidaException | GerenteExistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}*/
	
	//RECUPERAR
	public void gerenciarRecuperarGerente(Integer codigo) throws SQLException, IDRecuperacaoInvalidaException {
		try{
			Gerente gerente = new Gerente();
			gerente = Fachada.getSingleton().recuperarGerenteID(codigo);
			System.out.println(gerente);;
		}catch(IDRecuperacaoInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//LISTAR TODOS
	public void gerenciarListarGerente() throws SQLException, ListarTodosInvalidoException {
		try {
			System.out.println(Fachada.getSingleton().listarTodosGerentes());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}

}