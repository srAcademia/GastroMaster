package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Endereco {

	private String id_endereco;
	private String cidade;
	private String bairro;
	private String rua;
	private int numero;
	private String cep;

	public Endereco(String cidade, String bairro, String rua, int numero, String cep) {
		
		this.cidade = cidade;
		this.bairro = bairro;
		this.rua = rua;
		this.numero = numero;
		this.cep = cep;
	}

	public String getId_endereco() {
		return id_endereco;
	}
	
	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
}
