package br.com.ufrpeuag.gastromaster.dados.interfaces;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;

public interface IGarcomDao extends IDao<Garcom> {

	public Garcom verificarIdentificador(String identificador);

	public Garcom recuperarCPF(String cpf);

}
