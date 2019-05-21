package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Garcom extends Funcionario {

	private int id_garcom;

	public Garcom(String nome, String cpf, String dataNasc, String telefone, String email,
			double salario, Endereco endereco) {
		super(nome, cpf, dataNasc, telefone, email, salario, endereco);
		// TODO Auto-generated constructor stub
	}
	
	public Garcom() {
		
	}

	public void setId_garcom(int id_garcom) {
		this.id_garcom = id_garcom;
	}

	public int getId_garcom() {
		return id_garcom;
	}

	public void aumentarSalarial() {

	}

	@Override
	public String toString() {
		return "Garcom [id_garcom=" + id_garcom + ", getId_garcom()=" + getId_garcom() + 
				 ", getNome()=" + getNome() + ", getCpf()=" + getCpf() + ", getEndereco()="
				+ getEndereco()+ ", getDataNasc()=" + getDataNasc() + ", getTelefone()=" + getTelefone()
				+ ", getEmail()=" + getEmail() + ", getSalario()=" + getSalario() + "]\n";
	}
	
}
