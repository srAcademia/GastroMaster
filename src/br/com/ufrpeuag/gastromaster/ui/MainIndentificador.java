/*package br.com.ufrpeuag.gastromaster.ui;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGerente;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;

public class MainIndentificador {

	public static void main(String[] args) throws SQLException {
		
		 * String md5 = null; char c; int num; String senha = ""; String gerente = "2";
		 * Random r = new Random(); for (int i = 0; i < 5; i++) { num = r.nextInt(5);
		 * senha += "" + num; }
		 * 
		 * try {
		 * 
		 * 
		 * MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		 * 
		 * messageDigest.update(senha.getBytes(), 0, senha.length());
		 * 
		 * md5 = new BigInteger(1, messageDigest.digest()).toString(16); for (int i = 0;
		 * i < md5.length(); i++) { if (i < 5) { c = md5.charAt(i); gerente += "" + c; }
		 * } System.out.println(gerente);
		 * 
		 * } catch (NoSuchAlgorithmException e) {
		 * 
		 * e.printStackTrace(); }
		 
		//ConfiguracoesBanco.getSingleton().getConnection();

		RepositorioGerente rg = new RepositorioGerente();
		RepositorioEndereco re = new RepositorioEndereco();
		Endereco end = new Endereco("Garanhuns", "sitio", "Mochila", 30, "55555");
		Gerente gerente = null;
		String a = "";
		gerente = new Gerente("Allan", "878742814", "14/07/1198", "88888", "milly", 220, "12345", a, end);
		// Inserir
		re.inserir(end);
		gerente.setIdentificador(gerente.gerarIdentificador());
		gerente.getEndereco().setId_endereco(re.recuperarUltimoID());
		rg.inserir(gerente);
		
		gerente = rg.verificar("2db5c5");
		gerente = rg.logar("12345");
		System.out.println(gerente);
		
		// Recuperar
		gerente = rg.recuperar(1);
		System.out.println(gerente);
		//Alterar
		gerente.setIdentificador(gerente.gerarIdentificador());
		//rg.alterar(gerente);
		System.out.println(gerente);
		// Listar todos
		System.out.println(rg.listarTodos());
		gerente = rg.recuperarCPF("878742814");
		System.out.println(gerente);
		
		ConfiguracoesBanco.getSingleton().getConnection();
		String a = "";
		RepositorioGarcom repGarcom = new  RepositorioGarcom();
		RepositorioEndereco repEndereco = new RepositorioEndereco();
		
		Endereco end  = new Endereco("Garanhuns","sitio","Mochila",30,"55555");
		Garcom garcom = new Garcom("Milena","11536598701","14/07/1198","88888","milly",220,a,end);
	
		//Inserir Endereco
		repEndereco.inserir(end);
		
		//Recuperar por ID ultimo endereco adicionado
		int id = repEndereco.recuperarUltimoID();
		System.out.println(id);
		
		//Setar o ID de endereco de Garcom
		garcom.getEndereco().setId_endereco(id);
		garcom.setIdentificador(garcom.gerarIdentificador());
		//Inserir Garcom
		repGarcom.inserir(garcom);
		
		//Recuperar Garcom pelo ID
		garcom = repGarcom.recuperar(1);
		System.out.println(garcom);
		
		//Deletar endereço e Garcom
		garcom = repGarcom.recuperar(1);
		end = repEndereco.recuperar(garcom.getEndereco().getId_endereco());
		repGarcom.deletar(garcom);
		repEndereco.deletar(end);
		
		//Alterar garcom e endereco
		garcom = repGarcom.recuperar(1);
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