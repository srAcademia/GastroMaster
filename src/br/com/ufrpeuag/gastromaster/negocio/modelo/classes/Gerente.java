package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Gerente extends Funcionario {

	private int id_gerente;
	private String senha;

	public Gerente(String nome, String cpf, Endereco endereco, String dataNasc, String telefone, String email,
			double salario, String senha) {
		super(nome, cpf, endereco, dataNasc, telefone, email, salario);

		this.senha = senha;
	}

	public int getId_gerente() {
		return id_gerente;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
