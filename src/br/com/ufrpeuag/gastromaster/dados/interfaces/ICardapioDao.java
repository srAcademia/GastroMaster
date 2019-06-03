package br.com.ufrpeuag.gastromaster.dados.interfaces;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;

public interface ICardapioDao extends IDao<Cardapio> {

	public Cardapio recuperarPorNome(String nome);

	public int retornarID(String nome);

}
