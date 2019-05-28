package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGerente;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IEnderecoDao;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IGerenteDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.LoginInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RecuperarCPFException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SenhaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;

public class GerenteValidacao {
	private IGerenteDao repGerente;
	private IEnderecoDao repEndereco;
	Endereco end = new Endereco();
	int id;
	
	public GerenteValidacao() throws SQLException {
		repGerente = new RepositorioGerente();
		repEndereco = new RepositorioEndereco();
	}
	
	public void gerenteCadastroValidacao(Gerente gerente) throws CPFInvalidoException, DataNascimentoInvalidaException, NomeInvalidoException, SalarioInvalidoException, GerenteExistenteException{
		if(CpfValidacao.isCPF(gerente.getCpf()) == false) {
			id = repEndereco.recuperarUltimoID();
			end = repEndereco.recuperar(id);
			repEndereco.deletar(end);
			throw new CPFInvalidoException();
		}
		if(repGerente.recuperarCPF(gerente.getCpf()) != null) {
			id = repEndereco.recuperarUltimoID();
			end = repEndereco.recuperar(id);
			repEndereco.deletar(end);
			throw new GerenteExistenteException();
		}
		if(gerente.getNome() == null || gerente.getNome().isEmpty()) {
			id = repEndereco.recuperarUltimoID();
			end = repEndereco.recuperar(id);
			repEndereco.deletar(end);
			throw new NomeInvalidoException();
		}
		if(DataValidacao.ValidarData(gerente.getDataNasc()) == false) {
			id = repEndereco.recuperarUltimoID();
			end = repEndereco.recuperar(id);
			repEndereco.deletar(end);
			throw new DataNascimentoInvalidaException();
		}
		if(gerente.getSalario() <= 0) {
			id = repEndereco.recuperarUltimoID();
			end = repEndereco.recuperar(id);
			repEndereco.deletar(end);
			throw new SalarioInvalidoException();
		}
		
		repGerente.inserir(gerente);
	}
	
	public void gerenteRemocaoValidacao(Gerente gerente) throws GerenteInexistenteException{
		if(repGerente.recuperarCPF(gerente.getCpf()) == null) {
			throw new GerenteInexistenteException();
		}
		
		repGerente.deletar(gerente);
	}
	
	public void gerenteAlteracaoValidacao(Gerente gerente, String nome, String cpf, String novoCPF, String dataNasc, String telefone, String email, double salario, String senha) throws CPFInvalidoException, DataNascimentoInvalidaException, GerenteExistenteException{
		if(nome.isEmpty() == false) {
			gerente.setNome(nome);
		}
		if(novoCPF.isEmpty() == false) {
			if(CpfValidacao.isCPF(novoCPF) == false) {
				throw new CPFInvalidoException();
			}
			if(repGerente.recuperarCPF(novoCPF) != null) {
				throw new GerenteExistenteException();
			}
			gerente.setCpf(novoCPF);
		}
		if(dataNasc.isEmpty() == false) {
			if(DataValidacao.ValidarData(dataNasc) == false) {
				throw new DataNascimentoInvalidaException();
			}
		}
		if(telefone.isEmpty() == false) {
			gerente.setTelefone(telefone);
		}
		if(email.isEmpty() == false) {
			gerente.setEmail(email);
		}
		if(salario > 0 ) {
			gerente.setSalario(salario);
		}
		if(senha.isEmpty() == false) {
			gerente.setSenha(senha);
		}
		repGerente.alterar(gerente);
	}
	
	public Gerente gerenteRecuperarValidacao(Integer codigo) throws IDRecuperacaoInvalidaException{
		List<Gerente> gerente = new ArrayList<>();
		gerente = this.repGerente.listarTodos();
		for (int i = 0; i < gerente.size(); i++) {
			if(gerente.get(i).getId_gerente() == codigo) {
				return repGerente.recuperar(codigo);
			}
		}
		throw new IDRecuperacaoInvalidaException();
	}
	
	public Gerente gerenteRecuperarCPFValidacao(String CPF) throws CPFInvalidoException, RecuperarCPFException{
		if(CPF.equals(null) || CPF.isEmpty()) {
			throw new CPFInvalidoException();
		}
		List<Gerente> gerente =  new ArrayList<>();
		gerente = this.repGerente.listarTodos();
		for(int i = 0; i < gerente.size(); i++) {
			if(gerente.get(i).getCpf().equals(CPF)) {
				return repGerente.recuperarCPF(CPF);
			}
		}
		throw new RecuperarCPFException();
	}
	
	public List<Gerente> gerenteListarTodosValidacao() throws ListarTodosInvalidoException{
		if (repGerente.listarTodos() == null || repGerente.listarTodos().isEmpty()) {
			throw new ListarTodosInvalidoException();
		}
		return repGerente.listarTodos();
	}
	
	public Gerente gerenteVerificarValidacao(String identificador) throws LoginInvalidoException {
		if(identificador.equals(null) || identificador.isEmpty()) {
			throw new LoginInvalidoException();
		}
		if(repGerente.verificar(identificador) == null) {
			throw new LoginInvalidoException();
		}
		return repGerente.verificar(identificador);
	}
	
	public Gerente gerenteLogarValidacao(String senha) throws SenhaInvalidaException {
		if(senha.equals(null) || senha.isEmpty()) {
			throw new SenhaInvalidaException();
		}
		if(repGerente.logar(senha) == null) {
			throw new SenhaInvalidaException();
		}
		return repGerente.logar(senha);
	}

}
