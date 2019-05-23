/*package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomExistenteException;
<<<<<<< HEAD
=======
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomInexistenteException;
>>>>>>> b01750888d483d95cca928715b4022e99e98ec95
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RecuperarCPFException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public class MainGarcom {

	public static void main(String args[]) throws SQLException, BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException, CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GarcomExistenteException, IDInexistenteException, SalarioInvalidoException, IDRecuperacaoInvalidaException{
			
		ConfiguracoesBanco.getSingleton().getConnection();
		RepositorioGarcom repGarcom = new  RepositorioGarcom();
<<<<<<< HEAD
		//int id = 0;
=======
		int id = 0;
>>>>>>> b01750888d483d95cca928715b4022e99e98ec95
		//Endereco end  = new Endereco("Garanhuns","sitio","Mochila",30,"55555");
		//Garcom garcom = new Garcom("Allan","767376728","14/07/1198","88888","milly",220,end);
		Endereco end = new Endereco();
		Garcom garcom = new Garcom();
		//TESTE DE CADASTRO
		/*
		try {
			Fachada.getSingleton().enderecoCadastroValidacao(end);
			id = Fachada.getSingleton().enderecoRecuperarUltimoIDValidacao();
			garcom.getEndereco().setId_endereco(id);
			Fachada.getSingleton().garcomCadastroValidacao(garcom);
		}catch(BairroInvalidoException | CEPInvalidoException | CidadeInvalidaException | NumeroInvalidoException | RuaInvalidaException | CPFInvalidoException | DataNascimentoInvalidaException | EnderecoVazioException | NomeInvalidoException | GarcomExistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//System.out.println(id);
		//System.out.println(garcom.getId_garcom());
<<<<<<< HEAD
		//System.out.println(repGarcom.listarTodos());*/
		//TESTE DE RECUPERACAO
		/*
		try{
			garcom = Fachada.getSingleton().garcomRecuperarValidacao(2);
=======
		//System.out.println(repGarcom.listarTodos());
		//TESTE DE RECUPERACAO
		
		try{
			garcom = Fachada.getSingleton().garcomRecuperarValidacao(1);
>>>>>>> b01750888d483d95cca928715b4022e99e98ec95
			end = Fachada.getSingleton().enderecoRecuperarValidacao(41);
		}catch(IDRecuperacaoInvalidaException | EnderecoInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		System.out.println(garcom);
		System.out.println(end);
		//System.out.println(repGarcom.listarTodos());
<<<<<<< HEAD
		/*/
		//
		//TESTE DE REMOCAO
		/*try {
			garcom = Fachada.getSingleton().garcomRecuperarValidacao(2);
			end = Fachada.getSingleton().enderecoRecuperarValidacao(garcom.getEndereco().getId_endereco());
			Fachada.getSingleton().garcomRemocaoValidacao(garcom);
			Fachada.getSingleton().enderecoRemocaoValidacao(end);
		}catch(IDRecuperacaoInvalidaException | GarcomInexistenteException | EnderecoInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		
		System.out.println(repGarcom.listarTodos());*/
		//TESTE ALTERACAO
		
=======
		/
		//S
		//TESTE DE REMOCAO
		
		try {
			garcom = Fachada.getSingleton().garcomRecuperarValidacao(2);
			end = Fachada.getSingleton().enderecoRecuperarValidacao(garcom.getEndereco().getId_endereco());
			Fachada.getSingleton().garcomRemocaoValidacao(garcom);
			Fachada.getSingleton().enderecoRemocaoValidacao(end);
		}catch(IDRecuperacaoInvalidaException | GarcomInexistenteException | EnderecoInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		
		System.out.println(repGarcom.listarTodos());
		//TESTE ALTERACAO
		
>>>>>>> b01750888d483d95cca928715b4022e99e98ec95
		/*
		try {
			garcom = Fachada.getSingleton().garcomRecuperarValidacao(3);
			end = Fachada.getSingleton().enderecoRecuperarValidacao(garcom.getEndereco().getId_endereco());
			end.setCidade("Jucati");
			garcom.setNome("Kel");			
			Fachada.getSingleton().enderecoAlteracaoValidacao(end);
			Fachada.getSingleton().garcomAlteracaoValidacao(garcom);
		}catch(BairroInvalidoException | CEPInvalidoException | CidadeInvalidaException | NumeroInvalidoException | RuaInvalidaException | CPFInvalidoException | DataNascimentoInvalidaException | EnderecoVazioException | NomeInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		
		System.out.println(repGarcom.listarTodos());
<<<<<<< HEAD
		//*/
		
		//TESTE LISTAR TODOS
		/*
=======
		//
		
		//TESTE LISTAR TODOS
		
>>>>>>> b01750888d483d95cca928715b4022e99e98ec95
		try {
			System.out.println(Fachada.getSingleton().garcomListarTodosValidacao());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
<<<<<<< HEAD
		//*/
		//TESTE RECUPERAR CPF 
		/*try {
=======
		//
		//TESTE RECUPERAR CPF 
		try {
>>>>>>> b01750888d483d95cca928715b4022e99e98ec95
			garcom = Fachada.getSingleton().garcomRecuperarCPFValidacao("31892292414");
		}catch(CPFInvalidoException | RecuperarCPFException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		System.out.println(garcom);	
	}
}

		/*
		//Recuperar pelo CPF:
		System.out.println(repGarcom.recuperar("11536598701"));
			
		
	}
}*/