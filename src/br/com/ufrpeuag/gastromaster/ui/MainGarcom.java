package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public class MainGarcom {

	public static void main(String args[]) throws SQLException, BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException, CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GarcomExistenteException, IDInexistenteException, SalarioInvalidoException{
			
		ConfiguracoesBanco.getSingleton().getConnection();
		RepositorioGarcom repGarcom = new  RepositorioGarcom();
		int id = 0;
		Endereco end  = new Endereco("Garanhuns","sitio","Mochila",30,"55555");
		Garcom garcom = new Garcom("Allan","767376728","14/07/1198","88888","milly",220,end);
		/*try {
			Fachada.getSingleton().enderecoCadastroValidacao(end);
			id = Fachada.getSingleton().enderecoRecuperarUltimoIDValidacao();
			garcom.getEndereco().setId_endereco(id);
			Fachada.getSingleton().garcomCadastroValidacao(garcom);
		}catch(BairroInvalidoException | CEPInvalidoException | CidadeInvalidaException | NumeroInvalidoException | RuaInvalidaException | CPFInvalidoException | DataNascimentoInvalidaException | EnderecoVazioException | NomeInvalidoException | GarcomExistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		//System.out.println(id);
		//System.out.println(garcom.getId_garcom());
		//System.out.println(repGarcom.listarTodos());
		try{
			garcom = Fachada.getSingleton().garcomRecuperarValidacao(garcom.getId_garcom());
		}catch(IDRecuperacaoInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}
		System.out.println(garcom);
		System.out.println(repGarcom.listarTodos());*/
	}
}

		/*//Inserir Endereco
		repEndereco.inserir(end);
		
		//Recuperar por ID ultimo endereco adicionado
		int id = repEndereco.recuperarUltimoID();
		System.out.println(id);
		
		//Setar o ID de endereco de Garcom
		garcom.getEndereco().setId_endereco(id);
		
		//Inserir Garcom
		repGarcom.inserir(garcom);
		
		//Recuperar Garcom pelo ID
		garcom = repGarcom.recuperar(1);
		System.out.println(garcom);
		
		//Deletar endereço e Garcom
		end = repEndereco.recuperar(garcom.getEndereco().getId_endereco());
		repGarcom.deletar(garcom);
		repEndereco.deletar(end);
		
		//Alterar garcom e endereco
		garcom = repGarcom.recuperar(2);
		end =repEndereco.recuperar(garcom.getEndereco().getId_endereco());
		end.setCidade("Jucati");
		garcom.setNome("Joana");
		repGarcom.alterar(garcom);
		repEndereco.alterar(end);
		
		//Listar Todos
		System.out.println(repGarcom.listarTodos());
		
		//Recuperar pelo CPF:
		System.out.println(repGarcom.recuperar("11536598701"));
			
		
	}
}
*/