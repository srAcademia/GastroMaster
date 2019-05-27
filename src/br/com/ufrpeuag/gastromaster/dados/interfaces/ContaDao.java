package br.com.ufrpeuag.gastromaster.dados.interfaces;

import java.util.List;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;

public interface ContaDao extends Dao<Conta> {
	
	
	public double fecharConta(Conta conta);
	public List<Conta> recuperarPorMesa(Integer codigo);
	public void concluirPagamento(Conta conta);
	public void deletarTodasContas(Conta conta);

}
