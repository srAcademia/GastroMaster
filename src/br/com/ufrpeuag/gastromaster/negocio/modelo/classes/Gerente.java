package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Gerente extends Funcionario {

	private int id_gerente;
	private String senha;

	public Gerente(String nome, String cpf, String dataNasc, String telefone, String email,
			double salario, String senha,Endereco endereco) {
		super(nome, cpf, dataNasc, telefone, email, salario, endereco);

		this.senha = senha;
	}
	public Gerente() {
		
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

	@Override
	public String toString() {
		return "Gerente [id_gerente=" + id_gerente + ", senha=" + senha + ", getId_gerente()=" + getId_gerente()
				+ ", getSenha()=" + getSenha() + ", getNome()=" + getNome() + ", getCpf()=" + getCpf()
				+ ", getEndereco()=" + getEndereco() + ", getDataNasc()=" + getDataNasc() + ", getTelefone()="
				+ getTelefone() + ", getEmail()=" + getEmail() + ", getSalario()=" + getSalario() + ", toString()="
				+ super.toString() + "]"+"\n";
	}

}
