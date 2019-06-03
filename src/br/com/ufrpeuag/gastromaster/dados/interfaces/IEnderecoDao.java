package br.com.ufrpeuag.gastromaster.dados.interfaces;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;

public interface IEnderecoDao extends IDao<Endereco> {

	public int recuperarUltimoID();
}
