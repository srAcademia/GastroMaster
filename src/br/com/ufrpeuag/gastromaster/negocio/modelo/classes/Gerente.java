package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Gerente extends Funcionario {

	private int id_gerente;
	private String senha;

	public Gerente(String nome, String cpf, String dataNasc, String telefone, String email,
			double salario, String senha,Endereco endereco) {
		super(nome, cpf, dataNasc, telefone, email, salario, endereco);

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
