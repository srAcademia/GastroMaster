package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
/*
import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGerente;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
*/
public class Main {

	public static void main(String args[]) throws SQLException{
	
		/* Garcom
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
		g = rg.recuperar(6);
		end = e.recuperar(g.getEndereco().getId_endereco());
		rg.deletar(g);
		e.deletar(end);
	
		g = rg.recuperar(4);
		end =e.recuperar(g.getEndereco().getId_endereco());
		end.setCidade("Jucati");
		g.setNome("Joana");
		rg.alterar(g);
		e.alterar(end);
		 */		
		/*Gerente
		ConfiguracoesBanco.getSingleton().getConnection();
		RepositorioEndereco repEndereco = new RepositorioEndereco();
		RepositorioGerente repGerente = new RepositorioGerente();
		
		Endereco end  = new Endereco("Garanhuns","sitio","Mochila",30,"55555");
		Gerente gerente = new Gerente("Milena","11536598701","14/07/1198","88888","milly",220,"12345",end);
		
		repEndereco.inserir(end);
		int idEndereco = repEndereco.recuperarUltimoID();
		gerente.getEndereco().setId_endereco(idEndereco);
		//INSERIR
		repGerente.inserir(gerente);
		//Recuperar
		gerente = repGerente.recuperar(3);
		System.out.println(gerente);
		//Deletar
		end = repEndereco.recuperar(gerente.getEndereco().getId_endereco());
		repEndereco.deletar(end);
		repGerente.deletar(gerente);
		//Alterar
		gerente = repGerente.recuperar(2);
		System.out.println(gerente);
		end = repEndereco.recuperar(gerente.getEndereco().getId_endereco());
		gerente.setNome("Kelwin");
		end.setCidade("Águas Belas");
		repEndereco.alterar(end);
		repGerente.alterar(gerente);
		//ListarTodos
		System.out.println(repGerente.listarTodos());
		*/
		
	}
}
