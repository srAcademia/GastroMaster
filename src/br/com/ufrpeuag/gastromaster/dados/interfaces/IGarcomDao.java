package br.com.ufrpeuag.gastromaster.dados.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public interface IGarcomDao extends IDao<Garcom> {

	public Garcom verificarIdentificador(String identificador);

	public Garcom recuperarCPF(String cpf);
	
	  public Map<Integer, ArrayList> niverPorMes(LocalDate data);

}
