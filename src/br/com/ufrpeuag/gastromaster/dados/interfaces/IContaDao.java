package br.com.ufrpeuag.gastromaster.dados.interfaces;

import java.util.List;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;

public interface IContaDao extends IDao<Conta> {

	public double mostrarValorConta(Conta conta);

	public List<Conta> recuperarPorMesa(Integer codigo);

	public void deletarTodasContas(Conta conta);

}
