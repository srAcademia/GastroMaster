package br.com.ufrpeuag.gastromaster.dados.interfaces;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public interface IMesaDao extends IDao<Mesa>{
	
	public Mesa recuperarNumeroMesa(Integer numero);
	public void mudarDisponibilidade(Mesa mesa);
	
}
