package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Pedido {

	private int id_pedido;
	private Cardapio cardapio;
	private Produto produto;
	private double valor;

	public Pedido() {

	}

	public Pedido(Cardapio cardapio, Produto produto,double valor) {

		this.cardapio = cardapio;
		this.produto = produto;
		this.valor=valor;
		
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public void setId_pedido(int id_pedido) {
		this.id_pedido = id_pedido;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}
	public double calcularValorPedido(double precoProd , double precoCard) {
		return precoProd + precoCard;
	}

	@Override
	public String toString() {
		return "Pedido [id_pedido=" + id_pedido + ", cardapio=" + cardapio + ", produto=" + produto + ", valor=" + valor
				+ "]\n";
	}

}
