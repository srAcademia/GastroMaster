package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Pedido {

	private int id_pedido;
	private Cardapio cardapio;
	private Mesa mesa;
	private double valor;

	public Pedido(Cardapio cardapio, Mesa mesa, double valor) {
		this.cardapio = cardapio;
		this.mesa = mesa;
		this.valor = valor;
	}

	public int getId_pedido() {
		return id_pedido;
	}

	public Cardapio getCardapio() {
		return cardapio;
	}

	public void setCardapio(Cardapio cardapio) {
		this.cardapio = cardapio;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
