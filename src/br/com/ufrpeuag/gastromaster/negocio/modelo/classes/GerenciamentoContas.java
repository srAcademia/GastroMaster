package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class GerenciamentoContas {
	
	private int id_gerenc;
	private Garcom garcom;
	private Mesa mesa;
	private double valorTotal;
	private LocalDate data;
	
	public GerenciamentoContas(Garcom garcom, Mesa mesa, double valorTotal, LocalDate data) {
		
		this.garcom = garcom;
		this.mesa = mesa;
		this.valorTotal = valorTotal;
		this.data = data;
	}
	public GerenciamentoContas() {
		
	}
	public int getId_gerenc() {
		return id_gerenc;
	}
	public void setId_gerenc(int id_gerenc) {
		this.id_gerenc = id_gerenc;
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
	public double getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}
	public LocalDate getData() {
		return data;
	}
	public void setData(LocalDate data) {
		this.data = data;
	}
	@Override
	public String toString() {
		 java.sql.Date date = java.sql.Date.valueOf(this.data);
		    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
		    String dataFormatada = formatador.format(date);
		  
		return "GerenciamentoContas [id_gerenc=" + id_gerenc + ", garcom=" + garcom + ", mesa=" + mesa + ", valorTotal="
				+ valorTotal + ", data=" + dataFormatada + "]\n";
	}
	
	

}
