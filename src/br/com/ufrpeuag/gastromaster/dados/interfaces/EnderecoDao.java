package br.com.ufrpeuag.gastromaster.dados.interfaces;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;

public interface EnderecoDao extends Dao<Endereco>{

	public int recuperarUltimoID();
}
