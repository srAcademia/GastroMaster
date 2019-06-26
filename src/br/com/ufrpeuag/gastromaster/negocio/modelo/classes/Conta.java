package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Conta {

	private int id_conta;
	private LocalDate data;
	private Pedido pedido;
	private Garcom garcom;
	private Mesa mesa;
	private double valor;

	public Conta() {

	}

	public Conta(LocalDate data, Pedido pedido, Garcom garcom, Mesa mesa, double valor) {
		super();
		this.data = data;
		this.pedido = pedido;
		this.garcom = garcom;
		this.mesa = mesa;
		this.valor = valor;
	}

	public int getId_conta() {
		return id_conta;
	}

	public void setId_conta(int id_conta) {
		this.id_conta = id_conta;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Garcom getGarcom() {
		return garcom;
	}

	public void setGarcom(Garcom garcom) {
		this.garcom = garcom;
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

	@Override
	public String toString() {

		java.sql.Date date = java.sql.Date.valueOf(this.data);
		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = formatador.format(date);

		return "Conta [id_conta=" + id_conta + ", data=" + dataFormatada + ", pedido=" + pedido + ", garcom=" + garcom
				+ ", mesa=" + mesa + ", valor=" + valor + "]\n";
	}

}
