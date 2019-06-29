package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IEnderecoDao;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IGarcomDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RecuperarCPFException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public class GarcomNegocio {

	private IGarcomDao repGarcom;
	private IEnderecoDao repEndereco;

	public GarcomNegocio() throws SQLException {
		repGarcom = new RepositorioGarcom();
		repEndereco = new RepositorioEndereco();
	}

	public void cadastrarGarcom(Garcom garcom) throws CPFInvalidoException, DataNascimentoInvalidaException,
			NomeInvalidoException, SalarioInvalidoException, GarcomExistenteException, RuaInvalidaException,
			NumeroInvalidoException, CidadeInvalidaException, CEPInvalidoException, BairroInvalidoException {

		if (CpfNegocio.isCPF(garcom.getCpf()) == false) {
			throw new CPFInvalidoException();
		}
		if (repGarcom.recuperarCPF(garcom.getCpf()) != null) {
			throw new GarcomExistenteException();
		}
		if (garcom.getNome() == null || garcom.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}

		if (garcom.getDataNasc() == null) {
			throw new DataNascimentoInvalidaException();
		}

		if (garcom.getSalario() <= 0) {
			throw new SalarioInvalidoException();
		}
		if (garcom.getEndereco().getBairro() == null || garcom.getEndereco().getBairro().isEmpty()) {
			throw new BairroInvalidoException();
		}
		if (garcom.getEndereco().getCep() == null || garcom.getEndereco().getCep().isEmpty()) {
			throw new CEPInvalidoException();
		}
		if (garcom.getEndereco().getCidade() == null || garcom.getEndereco().getCidade().isEmpty()) {
			throw new CidadeInvalidaException();
		}
		if (garcom.getEndereco().getNumero() <= 0) {
			throw new NumeroInvalidoException();
		}
		if (garcom.getEndereco().getRua() == null || garcom.getEndereco().getRua().isEmpty()) {
			throw new RuaInvalidaException();
		}

		repEndereco.inserir(garcom.getEndereco());
		garcom.getEndereco().setId_endereco(repEndereco.recuperarUltimoID());
		garcom.setIdentificador(garcom.gerarIdentificador());
		repGarcom.inserir(garcom);

	}

	public void deletarGarcom(Garcom garcom) throws GarcomInexistenteException {
		if (garcom == null) {
			throw new GarcomInexistenteException();
		}
		if (repGarcom.recuperarCPF(garcom.getCpf()) == null) {
			throw new GarcomInexistenteException();
		}
		repEndereco.deletar(garcom.getEndereco());
		repGarcom.deletar(garcom);
	}

	public void alterarGarcom(Garcom garcom)
			throws GarcomExistenteException, DataNascimentoInvalidaException, CPFInvalidoException {

		if (garcom.getNome().isEmpty() == false) {
			garcom.setNome(garcom.getNome());
		}

		if (garcom.getCpf().isEmpty() == false) {
			if (CpfNegocio.isCPF(garcom.getCpf()) == false) {
				throw new CPFInvalidoException();
			}
			if (repGarcom.recuperarCPF(garcom.getCpf()) != null) {
				throw new GarcomExistenteException();
			}
			garcom.setCpf(garcom.getCpf());
		}

		if (garcom.getDataNasc() == null) {
			garcom.setDataNasc(garcom.getDataNasc());
		}

		if (garcom.getTelefone().isEmpty() == false) {
			garcom.setTelefone(garcom.getTelefone());
		}

		if (garcom.getEmail().isEmpty() == false) {
			garcom.setEmail(garcom.getEmail());
		}

		if (garcom.getSalario() > 0) {
			garcom.setSalario(garcom.getSalario());
		}

		if (garcom.getEndereco().getBairro().isEmpty() == false) {
			garcom.getEndereco().setBairro(garcom.getEndereco().getBairro());

		}
		if (garcom.getEndereco().getCidade().isEmpty() == false) {
			garcom.getEndereco().setCidade(garcom.getEndereco().getCidade());

		}
		if (garcom.getEndereco().getRua().isEmpty() == false) {
			garcom.getEndereco().setRua(garcom.getEndereco().getRua());

		}
		if (garcom.getEndereco().getNumero() > 0) {
			garcom.getEndereco().setNumero(garcom.getEndereco().getNumero());

		}
		if (garcom.getEndereco().getCep().isEmpty() == false) {
			garcom.getEndereco().setCep(garcom.getEndereco().getCep());

		}

		repEndereco.alterar(garcom.getEndereco());
		repGarcom.alterar(garcom);

	}

	public Garcom recuperarGarcomID(Integer codigo) throws IDRecuperacaoInvalidaException {
		if (codigo == null || repGarcom.recuperar(codigo) == null) {
			throw new IDRecuperacaoInvalidaException();
		}

		return repGarcom.recuperar(codigo);
	}

	public List<Garcom> listarTodosGarcons() {
		return repGarcom.listarTodos();
	}

	public Garcom recuperarPorCpfGarcom(String CPF) throws CPFInvalidoException, RecuperarCPFException {
		if (CPF.equals(null) || CPF.isEmpty()) {
			throw new CPFInvalidoException();
		}
		if (repGarcom.recuperarCPF(CPF) == null) {
			throw new RecuperarCPFException();
		}

		return repGarcom.recuperarCPF(CPF);

	}

	public Garcom verificarIndetificadorGarcom(String identificador) {
		return repGarcom.verificarIdentificador(identificador);
	}

}
