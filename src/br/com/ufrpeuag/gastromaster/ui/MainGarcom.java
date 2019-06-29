package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import java.time.LocalDate;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.LoginInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RecuperarCPFException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public class MainGarcom {

	// TESTE DE CADASTRO
	public void gerenciarCadastroGarcom(String cidade, String bairro, String rua, int numero, String cep, String nome,
			String cpf, LocalDate dataNasc, String telefone, String email, double salario, String identificador)
			throws SQLException, BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException,
			NumeroInvalidoException, RuaInvalidaException, CPFInvalidoException, DataNascimentoInvalidaException,
			NomeInvalidoException, GarcomExistenteException, IDInexistenteException, SalarioInvalidoException,
			IDRecuperacaoInvalidaException {
		try {
			Endereco end = new Endereco(cidade, bairro, rua, numero, cep);
			Garcom garcom = new Garcom(nome, cpf, dataNasc, telefone, email, salario, identificador, end);
			
			garcom.setIdentificador(garcom.gerarIdentificador());
			Fachada.getSingleton().cadastrarGarcom(garcom);
			
			System.out.println("Gar�om cadastrado.");
			System.out.println("ID gerado do garçom: " + garcom.getIdentificador());
		} catch (BairroInvalidoException | CEPInvalidoException | CidadeInvalidaException | NumeroInvalidoException
				| RuaInvalidaException | CPFInvalidoException | DataNascimentoInvalidaException | NomeInvalidoException
				| GarcomExistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		} catch (Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}

	// RECUPERAR
	public void gerenciarRecuperarGarcom(Integer codigo) throws SQLException, IDRecuperacaoInvalidaException {
		try {
			Garcom garcom = new Garcom();
			garcom = Fachada.getSingleton().recuperarGarcomID(codigo);
			System.out.println(garcom);
		} catch (IDRecuperacaoInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		} catch (Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}

	// REMOCAO
	public void gerenciarRemocaoGarcom(String CPF) throws SQLException, GarcomInexistenteException,
			EnderecoInexistenteException, CPFInvalidoException, RecuperarCPFException {
		try {
			Garcom garcom = new Garcom();

			garcom = Fachada.getSingleton().recuperarGarcomPorCPF(CPF);
			Fachada.getSingleton().deletarGarcom(garcom);
			System.out.println("Gar�om removido.");
		} catch (GarcomInexistenteException | CPFInvalidoException | RecuperarCPFException ex) {
			System.out.println(ex.getLocalizedMessage());
		} catch (Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}

	/*// TESTE ALTERACAO
	public void gerenciarAlteracaoGarcom(String cidade, String bairro, String rua, int numero, String cep, String nome,
			String cpf, String novoCPF, LocalDate dataNasc, String telefone, String email, double salario)
			throws SQLException, CPFInvalidoException, DataNascimentoInvalidaException, RecuperarCPFException,
			GarcomExistenteException {
		try {
			
			Endereco end = new Endereco(cidade, bairro, rua, numero, cep);
			Garcom garcom = new Garcom(nome, cpf, novoCPF, dataNasc, telefone, email,salario,,end);
			garcom = Fachada.getSingleton().recuperarGarcomPorCPF(cpf);
			
			Fachada.getSingleton().alterarGarcom(garcom);
			System.out.println("Gar�om alterado.");
		} catch (CPFInvalidoException | RecuperarCPFException | DataNascimentoInvalidaException
				| GarcomExistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		} catch (Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}*/

	/* TESTE LISTAR TODOS
	public void gerenciarListarGarcom() throws SQLException,  {
		try {
			ConfiguracoesBanco.getSingleton().getConnection();
			System.out.println(Fachada.getSingleton().ListarTodosGarcons());
		} catch (ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		} catch (Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}*/

	 //VERIFICA SE E GARCOM
	/*ublic Garcom gerenciarVerificarGarcom(String identificador) throws SQLException, LoginInvalidoException {
		try {
			Garcom garcom = new Garcom();
			garcom = Fachada.getSingleton().verificarIdentificadorGarcom(identificador);
			return garcom;
		} catch (LoginInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		} catch (Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return null;
	}*/
}