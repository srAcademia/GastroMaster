package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

import java.time.LocalDate;

public class Conta {

	private int id_conta;
	private LocalDate dataConta;
	private Pedido pedido;
	private Garcom garcom;
	
	public Conta(LocalDate dataConta, Pedido pedido, Garcom garcom) {
		this.dataConta = dataConta;
		this.pedido = pedido;
		this.garcom = garcom;
	}
	public int getId_conta() {
		return id_conta;
	}
	public LocalDate getDataConta() {
		return dataConta;
	}
	public void setDataConta(LocalDate dataConta) {
		this.dataConta = dataConta;
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
	
	
	
	
}
