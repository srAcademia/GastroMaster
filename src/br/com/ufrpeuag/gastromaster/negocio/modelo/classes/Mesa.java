package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

import java.util.List;

public class Mesa {

	private int id_mesa;
	private int numero;
	private int disponibilidade;
	private Garcom garcom;
	private List<Pedido> pedido;

	public Mesa(int numero, int disponibilidade, Garcom garcom, List<Pedido> pedido) {
		super();
		this.numero = numero;
		this.disponibilidade = disponibilidade;
		this.garcom = garcom;
		this.pedido = pedido;
	}

	public int getId_mesa() {
		return id_mesa;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int isDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(int disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public Garcom getGarcom() {
		return garcom;
	}

	public void setGarcom(Garcom garcom) {
		this.garcom = garcom;
	}

	public List<Pedido> getPedido() {
		return pedido;
	}

	public void setPedido(List<Pedido> pedido) {
		this.pedido = pedido;
	}

}
