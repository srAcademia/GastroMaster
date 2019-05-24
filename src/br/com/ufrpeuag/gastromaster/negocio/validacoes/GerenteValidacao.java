package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioGerente;
import br.com.ufrpeuag.gastromaster.dados.interfaces.GerenteDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;

public class GerenteValidacao {
	private GerenteDao repGerente;
	
	public GerenteValidacao() {
		repGerente = new RepositorioGerente();
	}
	
	public void gerenteCadastroValidacao(Gerente gerente) throws CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, SalarioInvalidoException, GerenteExistenteException{
		if(gerente.getEndereco().equals(null)) {
			throw new EnderecoVazioException();
		}
		if(repGerente.recuperarCPF(gerente.getCpf()) != null) {
			throw new GerenteExistenteException();
		}
		if(gerente.getCpf() == null) {
			throw new CPFInvalidoException();
		}
		if(gerente.getNome() == null || gerente.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}
		if(gerente.getDataNasc() == null || gerente.getDataNasc().isEmpty()) {
			throw new DataNascimentoInvalidaException();
		}
		if(gerente.getSalario() <= 0) {
			throw new SalarioInvalidoException();
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
	
	public void gerenteAlteracaoValidacao(Gerente gerente) throws CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, SalarioInvalidoException{
		if(gerente.getEndereco().equals(null)) {
			throw new EnderecoVazioException();
		}
		if(gerente.getCpf() == null) {
			throw new CPFInvalidoException();
		}
		if(gerente.getNome() == null || gerente.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}
		if(gerente.getDataNasc() == null || gerente.getDataNasc().isEmpty()) {
			throw new DataNascimentoInvalidaException();
		}
		if(gerente.getSalario() <= 0) {
			throw new SalarioInvalidoException();
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
	
	public List<Gerente> gerenteListarTodosValidacao() throws ListarTodosInvalidoException{
		if (repGerente.listarTodos() == null) {
			throw new ListarTodosInvalidoException();
		}
		return repGerente.listarTodos();
	}

}
