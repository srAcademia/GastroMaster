package br.com.ufrpeuag.gastromaster.ui;
/*
import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGerente;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;

public class MainGerente {

	public static void main(String[] args) throws SQLException {
		
		ConfiguracoesBanco.getSingleton().getConnection();
		RepositorioEndereco repEndereco = new RepositorioEndereco();
		RepositorioGerente repGerente = new RepositorioGerente();
		
		Endereco end  = new Endereco("Garanhuns","sitio","Mochila",30,"55555");
		Gerente gerente = new Gerente("Milena","11536598701","14/07/1198","88888","milly",220,"12345",end);
		
		//Inserir em endereço
		repEndereco.inserir(end);
		
		//Recuperar Ultimo endereço
		int idEndereco = repEndereco.recuperarUltimoID();
		
		//Setar o ID de endereco do gerente
		gerente.getEndereco().setId_endereco(idEndereco);
		
		//INSERIR Gerente
		repGerente.inserir(gerente);
		
		//Recuperar
		gerente = repGerente.recuperar(1);
		System.out.println(gerente);
		
		//Deletar endereco e gerente
		end = repEndereco.recuperar(gerente.getEndereco().getId_endereco());
		repEndereco.deletar(end);
		repGerente.deletar(gerente);
		
		//Alterar endereco  e gerente
		gerente = repGerente.recuperar(2);
		System.out.println(gerente);
		end = repEndereco.recuperar(gerente.getEndereco().getId_endereco());
		gerente.setNome("Kelwin");
		end.setCidade("Águas Belas");
		repEndereco.alterar(end);
		repGerente.alterar(gerente);
		
		//ListarTodos
		System.out.println(repGerente.listarTodos());
		

	}

}
*/