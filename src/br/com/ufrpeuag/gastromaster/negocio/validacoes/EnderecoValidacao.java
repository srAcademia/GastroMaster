package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.interfaces.EnderecoDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;

public class EnderecoValidacao {
	private EnderecoDao repEnd;

	public EnderecoValidacao() {
		repEnd = new RepositorioEndereco();
	}

	public void enderecoCadastroValidacao(Endereco endereco) throws BairroInvalidoException, CEPInvalidoException,
			CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException {
		if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
			throw new BairroInvalidoException();
		}
		if (endereco.getCep() == null || endereco.getCep().isEmpty()) {
			throw new CEPInvalidoException();
		}
		if (endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
			throw new CidadeInvalidaException();
		}
		if (endereco.getNumero() <= 0) {
			throw new NumeroInvalidoException();
		}
		if (endereco.getRua() == null || endereco.getRua().isEmpty()) {
			throw new RuaInvalidaException();
		}
		repEnd.inserir(endereco);
	}

	public void enderecoRemocaoValidacao(Endereco endereco) throws EnderecoInexistenteException {
		// Necess�rio um m�todo que verifique a inexistencia do endereco
		if (repEnd.recuperar(endereco.getId_endereco()) == null) {
			throw new EnderecoInexistenteException();
		}
		repEnd.deletar(endereco);
	}

	public void enderecoAlteracaoValidacao(Endereco endereco) throws BairroInvalidoException, CEPInvalidoException,
	CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException {
		// Necess�rio um m�todo que verifique a inexistencia do endereco
		if (endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
			throw new BairroInvalidoException();
		}
		if (endereco.getCep() == null || endereco.getCep().isEmpty()) {
			throw new CEPInvalidoException();
		}
		if (endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
			throw new CidadeInvalidaException();
		}
		if (endereco.getNumero() <= 0) {
			throw new NumeroInvalidoException();
		}
		if (endereco.getRua() == null || endereco.getRua().isEmpty()) {
			throw new RuaInvalidaException();
		}
		repEnd.alterar(endereco);
	}

	public Endereco enderecoRecuperarValidacao(Integer codigo) throws EnderecoInexistenteException {
		List<Endereco> end = new ArrayList<>();
		end = this.repEnd.listarTodos();
		for (int i = 0; i < end.size(); i++) {
			if(end.get(i).getId_endereco() == codigo) {
				return repEnd.recuperar(codigo);
			}
		}
		throw new EnderecoInexistenteException();
	}

	public int enderecoRecuperarUltimoIDValidacao() throws IDInexistenteException {
		return repEnd.recuperarUltimoID();
	}

}
