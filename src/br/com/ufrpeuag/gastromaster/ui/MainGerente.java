/*package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGerente;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;

public class MainGerente {

	public static void main(String[] args) throws SQLException, BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException, CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GerenteExistenteException, IDInexistenteException, SalarioInvalidoException, IDRecuperacaoInvalidaException{
		
		ConfiguracoesBanco.getSingleton().getConnection();
		RepositorioGerente repGerente = new RepositorioGerente();
		
		//Endereco end  = new Endereco("Garanhuns","sitio","Mochila",30,"55555");
		//Gerente gerente = new Gerente("Allan","878742814","14/07/1198","88888","milly",220,"12345",end);
		Endereco end = new Endereco();
		Gerente gerente = new Gerente();
		//TESTE DE CADASTRO
		/*
		try {
			Fachada.getSingleton().enderecoCadastroValidacao(end);
			int id = Fachada.getSingleton().enderecoRecuperarUltimoIDValidacao();
			gerente.getEndereco().setId_endereco(id);
			Fachada.getSingleton().gerenteCadastroValidacao(gerente);
		}catch(BairroInvalidoException | CEPInvalidoException | CidadeInvalidaException | NumeroInvalidoException | RuaInvalidaException | CPFInvalidoException | DataNascimentoInvalidaException | EnderecoVazioException | NomeInvalidoException | GerenteExistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//System.out.println(id);
		//System.out.println(garcom.getId_garcom());
		//System.out.println(repGarcom.listarTodos());*/
		//TESTE DE RECUPERACAO
		/*
		try{
			gerente = Fachada.getSingleton().gerenteRecuperarValidacao(5);
			end = Fachada.getSingleton().enderecoRecuperarValidacao(13);
		}catch(IDRecuperacaoInvalidaException | EnderecoInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		System.out.println(gerente);
		System.out.println(end);
		//System.out.println(repGerente.listarTodos());
		/*/
		
		//TESTE DE REMOCAO
		/*
		try {
			gerente = Fachada.getSingleton().gerenteRecuperarValidacao(2);
			end = Fachada.getSingleton().enderecoRecuperarValidacao(gerente.getEndereco().getId_endereco());
			Fachada.getSingleton().gerenteRemocaoValidacao(gerente);
			Fachada.getSingleton().enderecoRemocaoValidacao(end);
		}catch(IDRecuperacaoInvalidaException | GerenteInexistenteException | EnderecoInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
				
		System.out.println(repGerente.listarTodos());
		/*/
		//TESTE ALTERACAO
		/*
		try {
			gerente = Fachada.getSingleton().gerenteRecuperarValidacao(3);
			end = Fachada.getSingleton().enderecoRecuperarValidacao(gerente.getEndereco().getId_endereco());
			end.setCidade("Jucati");
			gerente.setNome("Kel");			
			Fachada.getSingleton().enderecoAlteracaoValidacao(end);
			Fachada.getSingleton().gerenteAlteracaoValidacao(gerente);
		}catch(BairroInvalidoException | CEPInvalidoException | CidadeInvalidaException | NumeroInvalidoException | RuaInvalidaException | CPFInvalidoException | DataNascimentoInvalidaException | EnderecoVazioException | NomeInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		
		System.out.println(repGerente.listarTodos());
		//*/
		
		//TESTE LISTAR TODOS
		/*
		try {
			System.out.println(Fachada.getSingleton().gerenteListarTodosValidacao());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//

	}

}*/