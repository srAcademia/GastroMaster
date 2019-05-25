package br.com.ufrpeuag.gastromaster.dados.interfaces;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public interface MesaDao extends Dao<Mesa>{
	
	public Mesa recuperarNumeroMesa(Integer numero);
	public void mudarDisponibilidade(Mesa mesa);
	
}
