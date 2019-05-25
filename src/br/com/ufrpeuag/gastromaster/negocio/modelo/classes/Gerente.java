package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Gerente extends Funcionario {

	private int id_gerente;
	private String senha;

	public Gerente() {
	}

	public Gerente(String nome, String cpf,  String dataNasc, String telefone, String email,
			double salario, String senha, String identificador,Endereco endereco) {
		super(nome, cpf,  dataNasc, telefone, email, salario, identificador,endereco);
		this.senha = senha;
	}

	public int getId_gerente() {
		return id_gerente;
	}

	public void setId_gerente(int id_gerente) {
		this.id_gerente = id_gerente;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String gerarIdentificador() {

		String md5 = null;
		char c;
		int num;
		String senha = "";
		String indenGerente = "2";

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

	@Override
	public String toString() {
		return "Gerente [id_gerente=" + id_gerente + ", senha=" + senha + ", getIndentificador()=" + getIdentificador()
				+ ", getNome()=" + getNome() + ", getCpf()=" + getCpf() + ", getEndereco()=" + getEndereco()
				+ ", getDataNasc()=" + getDataNasc() + ", getTelefone()=" + getTelefone() + ", getEmail()=" + getEmail()
				+ ", getSalario()=" + getSalario() + "]\n";
	}

}
