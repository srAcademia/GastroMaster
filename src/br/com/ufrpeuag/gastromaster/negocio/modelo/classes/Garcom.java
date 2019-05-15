package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Garcom extends Funcionario {

	private int id_garcom;

	public Garcom(String nome, String cpf, Endereco endereco, String dataNasc, String telefone, String email,
			double salario) {
		super(nome, cpf, endereco, dataNasc, telefone, email, salario);
		// TODO Auto-generated constructor stub
	}

	public int getId_garcom() {
		return id_garcom;
	}

	public void aumentarSalarial() {

	}
}
