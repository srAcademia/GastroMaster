package br.com.ufrpeuag.gastromaster.ui;
/*
import java.sql.SQLException;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public class MainGarcom {

	public static void main(String args[]) throws SQLException{
	
		
		ConfiguracoesBanco.getSingleton().getConnection();
		
		RepositorioGarcom repGarcom = new  RepositorioGarcom();
		RepositorioEndereco repEndereco = new RepositorioEndereco();
		
		Endereco end  = new Endereco("Garanhuns","sitio","Mochila",30,"55555");
		Garcom garcom = new Garcom("Milena","11536598701","14/07/1198","88888","milly",220,end);
	
		//Inserir Endereco
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