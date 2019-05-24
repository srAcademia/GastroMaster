package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioMesa;
import br.com.ufrpeuag.gastromaster.dados.interfaces.MesaDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperarMesaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaCadastradaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaDisponibilidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public class MesaValidacao {
	private MesaDao repMesa;
	
	public MesaValidacao() {
		repMesa = new RepositorioMesa();
	}
	
	public void mesaCadastroMesaValidacao(Mesa mesa) throws MesaCadastradaException, MesaDisponibilidadeInvalidaException, NumeroInvalidoException{
		if(mesa.getNumero() < 1) {
			throw new NumeroInvalidoException();
		}
		List<Mesa> m = new ArrayList<>();
		m = this.repMesa.listarTodos();
		for (int i = 0; i < m.size(); i++) {
			if(m.get(i).getNumero() == (mesa.getNumero())) {
				throw new MesaCadastradaException();
			}
		}
		if(mesa.getDisponibilidade() != 1 && mesa.getDisponibilidade() != 0) {
			throw new MesaDisponibilidadeInvalidaException();
		}
		repMesa.inserir(mesa);
	}
	
	public void mesaRemocaoValidacao(Mesa mesa) throws MesaInexistenteException{
		List<Mesa> m = new ArrayList<>();
		m = this.repMesa.listarTodos();
		for (int i = 0; i < m.size(); i++) {
			if(m.get(i).getNumero() == (mesa.getNumero())) {
				repMesa.deletar(mesa);
			}
		}
		throw new MesaInexistenteException();
	}
	
	public void mesaAlteracaoValidacao(Mesa mesa) throws MesaDisponibilidadeInvalidaException {
		if(mesa.getDisponibilidade() != 1 && mesa.getDisponibilidade() != 0) {
			throw new MesaDisponibilidadeInvalidaException();
		}
		repMesa.alterar(mesa);
	}
	
	public Mesa mesaRecuperarValidacao(Integer codigo) throws IDRecuperarMesaException {
		List<Mesa> m = new ArrayList<>();
		m = this.repMesa.listarTodos();
		for (int i = 0; i < m.size(); i++) {
			if(m.get(i).getId_mesa() == codigo) {
				return repMesa.recuperar(codigo);
			}
		}
		throw new IDRecuperarMesaException();
	}
	
	public List<Mesa> mesaListarTodosValidacao() throws ListarTodosInvalidoException {
		if(repMesa.listarTodos() == null) {
			throw new ListarTodosInvalidoException();
		}
		return repMesa.listarTodos();
	}

}
