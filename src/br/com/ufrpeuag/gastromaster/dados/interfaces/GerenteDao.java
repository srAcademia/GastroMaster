package br.com.ufrpeuag.gastromaster.dados.interfaces;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;

public interface GerenteDao extends Dao<Gerente> {
	
	public Gerente verificar(String identificador);
	public Gerente logar(String senha);
	public Gerente recuperarCPF(String cpf);
	

}
