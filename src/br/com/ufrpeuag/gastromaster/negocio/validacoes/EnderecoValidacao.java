package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.interfaces.EnderecoDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;

public class EnderecoValidacao {
	private EnderecoDao repEnd;
	
	public EnderecoValidacao() {
		repEnd = new RepositorioEndereco();
	}
	
	public void enderecoCadastroValidacao(Endereco endereco) throws BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException{
		if(endereco.getBairro() == null || endereco.getBairro().isEmpty()) {
			throw new BairroInvalidoException();
		}
		if(endereco.getCep() == null || endereco.getCep().isEmpty()) {
			throw new CEPInvalidoException();
		}
		if(endereco.getCidade() == null || endereco.getCidade().isEmpty()) {
			throw new CidadeInvalidaException();
		}
		if(endereco.getNumero() <= 0) {
			throw new NumeroInvalidoException();
		}
		if(endereco.getRua() == null || endereco.getRua().isEmpty()) {
			throw new RuaInvalidaException();
		}
		repEnd.inserir(endereco);
	}
	
	public void enderecoRemocaoValidacao(Endereco endereco) throws EnderecoInexistenteException{
		//Necessário um método que verifique a inexistencia do endereco
		repEnd.deletar(endereco);
	}
	
	public void enderecoAlteracaoValidacao(Endereco endereco) throws EnderecoInexistenteException{
		//Necessário um método que verifique a inexistencia do endereco
		repEnd.alterar(endereco);
	}

}
