package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import br.com.ufrpeuag.gastromaster.dados.RepositorioGerente;
import br.com.ufrpeuag.gastromaster.dados.interfaces.GerenteDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;

public class GerenteValidacao {
	private GerenteDao repGerente;
	
	public GerenteValidacao() {
		repGerente = new RepositorioGerente();
	}
	
	public void gerenteCadastroValidacao(Gerente gerente) throws CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GerenteExistenteException{
		if(gerente.getCpf() == null) {
			throw new CPFInvalidoException();
		}
		if(gerente.getDataNasc() == null || gerente.getDataNasc().isEmpty()) {
			throw new DataNascimentoInvalidaException();
		}
		if(gerente.getEndereco().equals(null)) {
			throw new EnderecoVazioException();
		}
		if(gerente.getNome() == null || gerente.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}
		//Falta tratar o CPF e a data de Nascimento da forma correta
		//Verificações para saber se o gerente pode ser cadastrado
		//Necessário um método que verifique a já existencia do funcionario
		repGerente.inserir(gerente);
	}
	
	public void gerenteRemocaoValidacao(Gerente gerente) throws GerenteInexistenteException{
		//Necessário um método que verifique a inexistencia do funcionario
		repGerente.deletar(gerente);
	}
	
	public void gerenteAlteracaoValidacao(Gerente gerente) throws GerenteInexistenteException{
		//Necessário um método que verifique a inexistencia do funcionario
		repGerente.alterar(gerente);
		}
	

}
