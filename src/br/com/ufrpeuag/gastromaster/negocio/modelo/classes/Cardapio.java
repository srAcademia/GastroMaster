package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

import java.util.List;

public class Cardapio {

	private int id_cardapio;
	private String prato;
	private double valores;
	private List<Produto> produtos;

	public Cardapio(String prato, double valores, List<Produto> produtos) {

		this.prato = prato;
		this.valores = valores;
		this.produtos = produtos;
	}

	public int getId_cardapio() {
		return id_cardapio;
	}

	public String getPrato() {
		return prato;
	}

	public void setPrato(String prato) {
		this.prato = prato;
	}

	public double getValores() {
		return valores;
	}

	public void setValores(double valores) {
		this.valores = valores;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

}
