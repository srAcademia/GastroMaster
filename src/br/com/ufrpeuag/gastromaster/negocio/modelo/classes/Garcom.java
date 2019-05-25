    
package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Garcom extends Funcionario {

	private int id_garcom;

	public Garcom() {

	}

	public Garcom(String nome, String cpf,  String dataNasc, String telefone, String email,
			double salario, String identificador,Endereco endereco) {
		super(nome, cpf,  dataNasc, telefone, email, salario, identificador,endereco);

	}

	public int getId_garcom() {
		return id_garcom;
	}

	public void setId_garcom(int id_garcom) {
		this.id_garcom = id_garcom;
	}

	public void aumentarSalarial() {

	}

	@Override
	public String toString() {
		return "Garcom [id_garcom=" + id_garcom + ", getIdentificador()=" + getIdentificador() + ", getNome()="
				+ getNome() + ", getCpf()=" + getCpf() + ", getEndereco()=" + getEndereco() + ", getDataNasc()="
				+ getDataNasc() + ", getTelefone()=" + getTelefone() + ", getEmail()=" + getEmail() + ", getSalario()="
				+ getSalario() + "]\n";
	}

	public String gerarIdentificador() {

		String md5 = null;
		char c;
		int num;
		String senha = "";
		String indenGerente = "1";

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
					indenGerente += "" + c;
				}
			}
			return indenGerente;

		} catch (NoSuchAlgorithmException e) {

			e.printStackTrace();
		}

		return null;
	}

}
