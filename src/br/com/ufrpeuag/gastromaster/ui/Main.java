package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public class Main {

	public static void main(String args[]) throws SQLException, ClassNotFoundException {
	
		
		Endereco end  = new Endereco("Garanhuns","sitio","Mochila",30,"55555");
		
		 ConfiguracoesBanco.getSingleton().getConnection();
		
		RepositorioGarcom rg = new  RepositorioGarcom();
		RepositorioEndereco e = new RepositorioEndereco();
		
		e.inserir(end);
		int a = e.recuperarUltimoID();
		System.out.println(a);
		Garcom g = new Garcom("Milena","11536598701","14/07/1198","88888","milly",220,end);
		g.getEndereco().setId_endereco(a);
		rg.inserir(g);
		//g = rg.recuperar("1111");
		//rg.deletar(g);
		/*g.setCpf("1111");
		g.setDataNasc("20/07/1998");
		g.getEndereco().setCidade("Jupi");
		System.out.println(g);
		rg.alterar(g);*/
		
		System.out.println(rg.listarTodos());
		
	}
}
