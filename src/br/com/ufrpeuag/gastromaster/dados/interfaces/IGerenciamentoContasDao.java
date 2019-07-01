package br.com.ufrpeuag.gastromaster.dados.interfaces;

import java.time.LocalDate;
import java.util.Map;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.GerenciamentoContas;

public interface IGerenciamentoContasDao extends IDao<GerenciamentoContas> {
	
	public Map<String,Integer> recuperarPorDia(LocalDate data);
	public Map<String,Integer> recuperarPorAno();
	public Map<String,Integer> recuperarPorMes(LocalDate data);

}
