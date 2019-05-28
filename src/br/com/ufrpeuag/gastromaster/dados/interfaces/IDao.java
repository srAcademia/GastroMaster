package br.com.ufrpeuag.gastromaster.dados.interfaces;

import java.util.List;

public interface IDao<D> {

	public void inserir(D d);

	public D recuperar(Integer codigo);

	public void alterar(D d);

	public void deletar(D d);

	public List<D> listarTodos();
}
