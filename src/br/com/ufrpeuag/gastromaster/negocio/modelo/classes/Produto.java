package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Produto {
	
	private int id_produto;
	private String nome;
	private int quantidade;

	public Produto() {
		
	}

	public Produto(String nome, int quantidade) {
		this.nome = nome;
		this.quantidade = quantidade;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	public int getId_produto() {
		return id_produto;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Produto [id_produto=" + id_produto + ", nome=" + nome + ", quantidade=" + quantidade + "]";
	}
	
	

}
