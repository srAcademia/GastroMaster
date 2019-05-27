package br.com.ufrpeuag.gastromaster.dados.interfaces;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;

public interface PedidoDao extends Dao<Pedido> {

	public int retornarId(Integer id_cardapio,Integer id_produto, Integer id_mesa);
	public int recuperarUltimoID();
}
