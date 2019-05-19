package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.dados.interfaces.GarcomDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public class GarcomValidacao {
	private GarcomDao repGarcom;
	
	public GarcomValidacao() {
		repGarcom = new RepositorioGarcom();
	}
	
	public void garcomCadastroValidacao(Garcom garcom) throws CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GarcomExistenteException{
		if(garcom.getCpf() == null) {
			throw new CPFInvalidoException();
		}
		if(garcom.getDataNasc() == null || garcom.getDataNasc().isEmpty()) {
			throw new DataNascimentoInvalidaException();
		}
		if(garcom.getEndereco().equals(null)) {
			throw new EnderecoVazioException();
		}
		if(garcom.getNome() == null || garcom.getNome().isEmpty()) {
			throw new NomeInvalidoException();
		}
		//Falta tratar o CPF e a data de Nascimento da forma correta
		//Verificações para saber se o garcom pode ser cadastrado
		//Necessário um método que verifique a já existencia do funcionario
		repGarcom.inserir(garcom);	
	}
	
	public void garcomRemocaoValidacao(Garcom garcom) throws GarcomInexistenteException{
		//Necessário um método que verifique a inexistencia do funcionario
		repGarcom.deletar(garcom);
	}
	
	public void garcomAlteracaoValidacao(Garcom garcom) throws GarcomInexistenteException{
		//Necessário um método que verifique a inexistencia do funcionario
		repGarcom.alterar(garcom);
	}
	
	
	
	

}
