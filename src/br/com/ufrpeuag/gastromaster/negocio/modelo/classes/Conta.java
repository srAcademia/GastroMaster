package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class Conta {

	public static final int NAO_PAGO = 0;
	public static final int PAGO = 1;

	private int id_conta;
	private int pagamento;
	private LocalDate data;
	private Pedido pedido;
	private Garcom garcom;
	private Mesa mesa;
	private double valor;
	
	public Conta() {

	}
	
	public Conta(int pagamento, LocalDate data, Pedido pedido, Garcom garcom, Mesa mesa, double valor) {
		super();
		this.pagamento = pagamento;
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

	public int getPagamento() {
		return pagamento;
	}

	public void setPagamento(int pagamento) {
		this.pagamento = pagamento;
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
	  
		return "Conta [id_conta=" + id_conta + ", pagamento=" + pagamento + ", data=" + dataFormatada + ", pedido=" + pedido
				+ ", garcom=" + garcom + ", mesa=" + mesa + ", valor=" + valor + "]\n";
	}
	
}
