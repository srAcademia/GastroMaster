package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.dados.interfaces.GarcomDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RecuperarCPFException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public class GarcomValidacao {
	private GarcomDao repGarcom;
	
	public GarcomValidacao() {
		repGarcom = new RepositorioGarcom();
	}
	
	public void garcomCadastroValidacao(Garcom garcom) throws CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, SalarioInvalidoException, GarcomExistenteException{
		
		if(garcom.getEndereco().equals(null)) {
			throw new EnderecoVazioException();
		}
		if(garcom.getCpf() == null) {
			throw new CPFInvalidoException();
		}
		if(repGarcom.recuperar(garcom.getCpf()) != null) {
			throw new GarcomExistenteException();
		}
		if(garcom.getNome() == null || garcom.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}
		if(garcom.getDataNasc() == null || garcom.getDataNasc().isEmpty()) {
			throw new DataNascimentoInvalidaException();
		}
		if(garcom.getSalario() <= 0) {
			throw new SalarioInvalidoException();
		}
		repGarcom.inserir(garcom);	
	}
	
	public void garcomRemocaoValidacao(Garcom garcom) throws GarcomInexistenteException{
		if(repGarcom.recuperar(garcom.getCpf()) == null) {
			throw new GarcomInexistenteException();
		}
		repGarcom.deletar(garcom);
	}
	
	public void garcomAlteracaoValidacao(Garcom garcom) throws CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, SalarioInvalidoException {
		if(garcom.getEndereco().equals(null)) {
			throw new EnderecoVazioException();
		}
		if(garcom.getCpf() == null) {
			throw new CPFInvalidoException();
		}
		if(garcom.getNome() == null || garcom.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}
		if(garcom.getDataNasc() == null || garcom.getDataNasc().isEmpty()) {
			throw new DataNascimentoInvalidaException();
		}
		if(garcom.getSalario() <= 0) {
			throw new SalarioInvalidoException();
		}
		repGarcom.alterar(garcom);
	}
	
	public Garcom garcomRecuperarValidacao(Integer codigo) throws IDRecuperacaoInvalidaException{
		List<Garcom> garcom = new ArrayList<>();
		garcom = this.repGarcom.listarTodos();
		for (int i = 0; i < garcom.size(); i++) {
			if(garcom.get(i).getId_garcom() == codigo) {
				return repGarcom.recuperar(codigo);
			}
		}
		throw new IDRecuperacaoInvalidaException();
	}
	
	public List<Garcom> garcomListarTodosValidacao() throws ListarTodosInvalidoException{
		if (repGarcom.listarTodos() == null) {
			throw new ListarTodosInvalidoException();
		}
		return repGarcom.listarTodos();
	}
	
	public Garcom garcomRecuperarCPFValidacao(String CPF) throws CPFInvalidoException, RecuperarCPFException{
		if(CPF.equals(null) || CPF.isEmpty()) {
			throw new CPFInvalidoException();
		}
		List<Garcom> garcom =  new ArrayList<>();
		garcom = this.repGarcom.listarTodos();
		System.out.println("entrou recuperarcpf");
		for(int i = 0; i < garcom.size(); i++) {
			System.out.println("varreu "+i+" vez");
			if(garcom.get(i).getCpf().equals(CPF)) {
				return repGarcom.recuperar(CPF);
			}
		}
		throw new RecuperarCPFException();
	}

}
