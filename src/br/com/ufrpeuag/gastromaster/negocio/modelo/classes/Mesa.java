package br.com.ufrpeuag.gastromaster.negocio.modelo.classes;

public class Mesa {

	public static final int OCUPADO = 0;
	public static final int LIVRE = 1;

	private int id_mesa;
	private int numero;
	private int disponibilidade;

	public Mesa() {

	}

	public Mesa(int numero, int disponibilidade) {

		this.numero = numero;
		this.disponibilidade = disponibilidade;
	}

	public int getId_mesa() {
		return id_mesa;
	}

	public void setId_mesa(int id_mesa) {
		this.id_mesa = id_mesa;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public int getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(int disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	@Override
	public String toString() {
		return "Mesa [id_mesa=" + id_mesa + ", numero=" + numero + ", disponibilidade=" + disponibilidade + "]\n";
	}

}
