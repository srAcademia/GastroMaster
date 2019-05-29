package br.com.ufrpeuag.gastromaster.ui;
import java.sql.SQLException;
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
	
	//TESTE DE CADASTRO
	public void gerenciarCadastroGarcom (String cidade, String bairro, String rua, int numero, String cep, String nome, String cpf, String dataNasc, String telefone, String email, double salario, String identificador)
			throws SQLException, BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException, CPFInvalidoException, DataNascimentoInvalidaException, NomeInvalidoException, GarcomExistenteException, IDInexistenteException, SalarioInvalidoException, IDRecuperacaoInvalidaException{
		try {
			Endereco end  = new Endereco(cidade, bairro, rua, numero, cep);
			Garcom garcom = new Garcom(nome, cpf, dataNasc, telefone, email, salario, identificador, end);
			Fachada.getSingleton().enderecoCadastroValidacao(end);
			int id = Fachada.getSingleton().enderecoRecuperarUltimoIDValidacao();
			garcom.getEndereco().setId_endereco(id);
			garcom.setIdentificador(garcom.gerarIdentificador());
			Fachada.getSingleton().garcomCadastroValidacao(garcom);
			System.out.println("Gar�om cadastrado.");
			System.out.println("ID gerado do garçom: "+garcom.getIdentificador());
		}catch(BairroInvalidoException | CEPInvalidoException | CidadeInvalidaException | NumeroInvalidoException | RuaInvalidaException | CPFInvalidoException | DataNascimentoInvalidaException | NomeInvalidoException | GarcomExistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}

	//RECUPERAR
	public void gerenciarRecuperarGarcom(Integer codigo) throws SQLException, IDRecuperacaoInvalidaException {
		try{
			Garcom garcom = new Garcom();
			garcom = Fachada.getSingleton().garcomRecuperarValidacao(codigo);
			System.out.println(garcom);
		}catch(IDRecuperacaoInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//REMOCAO
	public void gerenciarRemocaoGarcom(String CPF) throws SQLException, GarcomInexistenteException, EnderecoInexistenteException, CPFInvalidoException, RecuperarCPFException {
		try {
			Garcom garcom = new Garcom();
			Endereco end = new Endereco();
			garcom = Fachada.getSingleton().garcomRecuperarCPFValidacao(CPF);
			end = Fachada.getSingleton().enderecoRecuperarValidacao(garcom.getEndereco().getId_endereco());
			Fachada.getSingleton().garcomRemocaoValidacao(garcom);
			Fachada.getSingleton().enderecoRemocaoValidacao(end);
			System.out.println("Gar�om removido.");
		}catch(GarcomInexistenteException | EnderecoInexistenteException | CPFInvalidoException | RecuperarCPFException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
		
	//TESTE ALTERACAO	
	public void gerenciarAlteracaoGarcom(String cidade, String bairro, String rua, int numero, String cep, String nome, String cpf, String novoCPF, String dataNasc, String telefone, String email, double salario)
			throws SQLException, CPFInvalidoException, DataNascimentoInvalidaException, RecuperarCPFException, GarcomExistenteException {
		try {
			Garcom garcom = new Garcom();
			Endereco end = new Endereco();
			garcom = Fachada.getSingleton().garcomRecuperarCPFValidacao(cpf);
			end = Fachada.getSingleton().enderecoRecuperarValidacao(garcom.getEndereco().getId_endereco());		
			Fachada.getSingleton().enderecoAlteracaoValidacao(end, cidade, bairro, rua, numero, cep);
			Fachada.getSingleton().garcomAlteracaoValidacao(garcom, nome, cpf, novoCPF, dataNasc, telefone, email, salario);
			System.out.println("Gar�om alterado.");
		}catch(CPFInvalidoException | RecuperarCPFException | DataNascimentoInvalidaException | GarcomExistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
		
	//TESTE LISTAR TODOS
	public void gerenciarListarGarcom() throws SQLException, ListarTodosInvalidoException{
		try {
			ConfiguracoesBanco.getSingleton().getConnection();
			System.out.println(Fachada.getSingleton().garcomListarTodosValidacao());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//VERIFICA SE E GARCOM
	public Garcom gerenciarVerificarGarcom(String identificador) throws SQLException, LoginInvalidoException{
		try {
			Garcom garcom = new Garcom();
			garcom = Fachada.getSingleton().garcomVerificarValidacao(identificador);
			return garcom;
		}catch(LoginInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return null;
	}
}