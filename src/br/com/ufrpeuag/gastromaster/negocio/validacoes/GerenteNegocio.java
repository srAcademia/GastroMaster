package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGerente;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IEnderecoDao;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IGerenteDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.LoginInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RecuperarCPFException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SenhaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;

public class GerenteNegocio {
	private IGerenteDao repGerente;
	private IEnderecoDao repEndereco;

	public GerenteNegocio() throws SQLException {
		repGerente = new RepositorioGerente();
		repEndereco = new RepositorioEndereco();
	}

	public void cadastrarGerente(Gerente gerente)
			throws CPFInvalidoException, DataNascimentoInvalidaException, NomeInvalidoException,
			SalarioInvalidoException, GerenteExistenteException, SenhaInvalidaException, BairroInvalidoException,
			CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException {

		if (CpfNegocio.isCPF(gerente.getCpf()) == false) {
			throw new CPFInvalidoException();
		}
		if (repGerente.recuperarCPF(gerente.getCpf()) != null) {
			throw new GerenteExistenteException();
		}
		if (gerente.getNome() == null || gerente.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}
		if (gerente.getDataNasc() == null) {
			throw new DataNascimentoInvalidaException();
		}
		if (gerente.getSalario() <= 0) {
			throw new SalarioInvalidoException();
		}
		if (gerente.getSenha() == null || gerente.getSenha().isEmpty()) {
			throw new SenhaInvalidaException();
		}
		if (gerente.getEndereco().getBairro() == null || gerente.getEndereco().getBairro().isEmpty()) {
			throw new BairroInvalidoException();
		}
		if (gerente.getEndereco().getCep() == null || gerente.getEndereco().getCep().isEmpty()) {
			throw new CEPInvalidoException();
		}
		if (gerente.getEndereco().getCidade() == null || gerente.getEndereco().getCidade().isEmpty()) {
			throw new CidadeInvalidaException();
		}
		if (gerente.getEndereco().getNumero() <= 0) {
			throw new NumeroInvalidoException();
		}
		if (gerente.getEndereco().getRua() == null || gerente.getEndereco().getRua().isEmpty()) {
			throw new RuaInvalidaException();
		}
		repEndereco.inserir(gerente.getEndereco());
		gerente.getEndereco().setId_endereco(repEndereco.recuperarUltimoID());
		gerente.setIdentificador(gerente.gerarIdentificador());
		repGerente.inserir(gerente);
	}

	public void deletarGerente(Gerente gerente) throws GerenteInexistenteException {
		if (gerente == null) {
			throw new GerenteInexistenteException();
		}
		if (repGerente.recuperarCPF(gerente.getCpf()) == null) {
			throw new GerenteInexistenteException();
		}

		repGerente.deletar(gerente);
	}

	public void alterarGerente(Gerente gerente) throws GerenteExistenteException, CPFInvalidoException {
		if (gerente.getNome().isEmpty() == false) {
			gerente.setNome(gerente.getNome());
		}

		if (gerente.getCpf().isEmpty() == false) {
			if (CpfNegocio.isCPF(gerente.getCpf()) == false) {
				throw new CPFInvalidoException();
			}
			if (repGerente.recuperarCPF(gerente.getCpf()) != null) {
				throw new GerenteExistenteException();
			}
			gerente.setCpf(gerente.getCpf());
		}

		if (gerente.getDataNasc() == null) {
			gerente.setDataNasc(gerente.getDataNasc());
		}

		if (gerente.getTelefone().isEmpty() == false) {
			gerente.setTelefone(gerente.getTelefone());
		}

		if (gerente.getEmail().isEmpty() == false) {
			gerente.setEmail(gerente.getEmail());
		}

		if (gerente.getSalario() > 0) {
			gerente.setSalario(gerente.getSalario());
		}
		if (gerente.getSenha().isEmpty() == false) {
			gerente.setSenha(gerente.getSenha());
		}

		if (gerente.getEndereco().getBairro().isEmpty() == false) {
			gerente.getEndereco().setBairro(gerente.getEndereco().getBairro());

		}
		if (gerente.getEndereco().getCidade().isEmpty() == false) {
			gerente.getEndereco().setCidade(gerente.getEndereco().getCidade());

		}
		if (gerente.getEndereco().getRua().isEmpty() == false) {
			gerente.getEndereco().setRua(gerente.getEndereco().getRua());

		}
		if (gerente.getEndereco().getNumero() > 0) {
			gerente.getEndereco().setNumero(gerente.getEndereco().getNumero());

		}
		if (gerente.getEndereco().getCep().isEmpty() == false) {
			gerente.getEndereco().setCep(gerente.getEndereco().getCep());

		}

		repEndereco.alterar(gerente.getEndereco());
		repGerente.alterar(gerente);

	}

	public Gerente recuperarGerenteID(Integer codigo) throws IDRecuperacaoInvalidaException {

		if (codigo == null || repGerente.recuperar(codigo) == null) {
			throw new IDRecuperacaoInvalidaException();
		}

		return repGerente.recuperar(codigo);
	}

	public Gerente recuperarCpfPorGerente(String CPF) throws CPFInvalidoException, RecuperarCPFException {

		if (CPF.equals(null) || CPF.isEmpty()) {
			throw new CPFInvalidoException();
		}
		if (repGerente.recuperarCPF(CPF) == null) {
			throw new RecuperarCPFException();
		}
		return repGerente.recuperarCPF(CPF);

	}

	public List<Gerente> listarTodosGerentes() {
		return repGerente.listarTodos();
	}

	public Gerente verificarIdentificadorGerente(String identificador) throws LoginInvalidoException {

		if (identificador.equals(null) || identificador.isEmpty()) {
			throw new LoginInvalidoException();
		}
		if (repGerente.verificarIdentificador(identificador) == null) {
			throw new LoginInvalidoException();
		}
		return repGerente.verificarIdentificador(identificador);
	}

	public Gerente logarGerente(String senha) throws SenhaInvalidaException {
		if (senha.equals(null) || senha.isEmpty()) {
			throw new SenhaInvalidaException();
		}
		if (repGerente.logar(senha) == null) {
			throw new SenhaInvalidaException();
		}
		return repGerente.logar(senha);
	}

}
