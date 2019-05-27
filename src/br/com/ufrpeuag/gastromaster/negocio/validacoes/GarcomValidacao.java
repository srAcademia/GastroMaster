package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.dados.interfaces.EnderecoDao;
import br.com.ufrpeuag.gastromaster.dados.interfaces.GarcomDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.LoginInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RecuperarCPFException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public class GarcomValidacao {
	private GarcomDao repGarcom;
	private EnderecoDao repEndereco;
	Endereco end = new Endereco();
	int id;
	
	public GarcomValidacao() {
		repGarcom = new RepositorioGarcom();
		repEndereco = new RepositorioEndereco();
	}
	
	public void garcomCadastroValidacao(Garcom garcom) throws CPFInvalidoException, DataNascimentoInvalidaException, NomeInvalidoException, SalarioInvalidoException, GarcomExistenteException{
		if(garcom.getCpf() == null) {
			id = repEndereco.recuperarUltimoID();
			end = repEndereco.recuperar(id);
			repEndereco.deletar(end);
			throw new CPFInvalidoException();
		}
		if(repGarcom.recuperar(garcom.getCpf()) != null) {
			id = repEndereco.recuperarUltimoID();
			end = repEndereco.recuperar(id);
			repEndereco.deletar(end);
			throw new GarcomExistenteException();
		}
		if(garcom.getNome() == null || garcom.getNome().isEmpty()) {
			id = repEndereco.recuperarUltimoID();
			end = repEndereco.recuperar(id);
			repEndereco.deletar(end);
			throw new NomeInvalidoException();
		}
		if(garcom.getDataNasc() == null || garcom.getDataNasc().isEmpty()) {
			id = repEndereco.recuperarUltimoID();
			end = repEndereco.recuperar(id);
			repEndereco.deletar(end);
			throw new DataNascimentoInvalidaException();
		}
		if(garcom.getSalario() <= 0) {
			id = repEndereco.recuperarUltimoID();
			end = repEndereco.recuperar(id);
			repEndereco.deletar(end);
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
	
	public void garcomAlteracaoValidacao(Garcom garcom, String nome, String cpf, String novoCPF, String dataNasc, String telefone, String email, double salario) throws CPFInvalidoException, DataNascimentoInvalidaException, GarcomExistenteException{
		if(nome.isEmpty() == false) {
			garcom.setNome(nome);
		}
		if(novoCPF.isEmpty() == false) {
			if(repGarcom.recuperar(novoCPF) != null) {
				throw new GarcomExistenteException(); 
			}
			garcom.setCpf(novoCPF);
		}
		if(dataNasc.isEmpty() == false) {
			garcom.setDataNasc(dataNasc);
		}
		if(telefone.isEmpty() == false) {
			garcom.setTelefone(telefone);
		}
		if(email.isEmpty() == false) {
			garcom.setEmail(email);
		}
		if(salario > 0 ) {
			garcom.setSalario(salario);
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
		if (repGarcom.listarTodos() == null || repGarcom.listarTodos().isEmpty()) {
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
		for(int i = 0; i < garcom.size(); i++) {
			if(garcom.get(i).getCpf().equals(CPF)) {
				return repGarcom.recuperar(CPF);
			}
		}
		throw new RecuperarCPFException();
	}
	
	public Garcom garcomVerificarValidacao(String identificador) throws LoginInvalidoException {
		if(identificador.equals(null) || identificador.isEmpty()) {
			throw new LoginInvalidoException();
		}
		if(repGarcom.verificar(identificador) == null) {
			throw new LoginInvalidoException();
		}
		return repGarcom.verificar(identificador);
	}

}
