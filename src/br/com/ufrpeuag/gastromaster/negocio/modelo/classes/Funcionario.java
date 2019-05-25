package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Funcionario {

	private String identificador;
	private String nome;
	private String cpf;
	private Endereco endereco;
	private String dataNasc;
	private String telefone;
	private String email;
	private double salario;

	public Funcionario(String nome, String cpf, String dataNasc, String telefone, String email,
			double salario, String identificador, Endereco endereco) {

		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.dataNasc = dataNasc;
		this.telefone = telefone;
		this.email = email;
		this.salario = salario;
		this.identificador = identificador;
	}

	public Funcionario() {

	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String indentificador) {
		this.identificador = indentificador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(String dataNasc) {
		this.dataNasc = dataNasc;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

}
