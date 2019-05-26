package br.com.ufrpeuag.gastromaster.dados.interfaces;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;

public interface CardapioDao extends Dao<Cardapio> {

	public Cardapio recuperar(String nome);
	public int retornarID(String nome);

}
