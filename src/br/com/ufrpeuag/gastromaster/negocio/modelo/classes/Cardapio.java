package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Cardapio {

	private int id_cardapio;
	private String prato;
	private double preco;

	public Cardapio() {

	}

	public Cardapio(String prato, double preco) {

		this.prato = prato;
		this.preco = preco;

	}

	public void setId_cardapio(int id_cardapio) {
		this.id_cardapio = id_cardapio;
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

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public String toString() {
		return  prato + ", Valor = R$" + preco +"\n";
	}

}
