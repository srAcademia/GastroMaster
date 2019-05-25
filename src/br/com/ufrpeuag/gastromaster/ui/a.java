package br.com.ufrpeuag.gastromaster.ui;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Random;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGerente;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;

public class a {

	public static void main(String[] args) throws SQLException {
		/*String md5 = null;
		char c;
		int num;
		String senha = "";
		String gerente = "2";
		Random r = new Random();
		for (int i = 0; i < 5; i++) {
			num = r.nextInt(5);
			senha += "" + num;
		}

		try {
			

			MessageDigest messageDigest = MessageDigest.getInstance("MD5");

			messageDigest.update(senha.getBytes(), 0, senha.length());
			
				md5 = new BigInteger(1, messageDigest.digest()).toString(16);
				for (int i = 0; i < md5.length(); i++) {
					if (i < 5) {
						c = md5.charAt(i);
						gerente += "" + c;
					}
				}
				System.out.println(gerente);
			
		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}
*/
		ConfiguracoesBanco.getSingleton().getConnection();
		
		
		RepositorioGerente rg = new RepositorioGerente();
		RepositorioEndereco re = new RepositorioEndereco();
		Endereco end  = new Endereco("Garanhuns","sitio","Mochila",30,"55555");
		Gerente gerente = null;
		String a = "";
		/*gerente = new Gerente("Allan","878742814","14/07/1198","88888","milly",220,"12345",a,end);
		gerente.setIdentificador(gerente.gerarIdentificador());
		gerente.getEndereco().setId_endereco(re.recuperarUltimoID());
		rg.inserir(gerente);*/
		System.out.println( rg.recuperar(6));
		System.out.println(gerente);
		System.out.println(rg.listarTodos());
	}

}
