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
		if(repMesa.recuperarNumeroMesa(mesa.getNumero()) != null) {
			throw new MesaCadastradaException();
		}
		if(mesa.getDisponibilidade() != 1 && mesa.getDisponibilidade() != 0) {
			throw new MesaDisponibilidadeInvalidaException();
		}
		repMesa.inserir(mesa);
	}
	
	public void mesaRemocaoValidacao(Mesa mesa){
		repMesa.deletar(mesa);
	}
	
	public void mesaAlteracaoValidacao(Mesa mesa, int novoNumero) throws MesaCadastradaException {
		if(novoNumero > 0) {
			if(repMesa.recuperarNumeroMesa(novoNumero) != null) {
				throw new MesaCadastradaException();
			}
			mesa.setNumero(novoNumero);
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
	
	public Mesa mesaRecuperarNumeroValidacao(Integer numero) throws MesaInexistenteException{
		List<Mesa> m = new ArrayList<>();
		m = this.repMesa.listarTodos();
		for (int i = 0; i < m.size(); i++) {
			if(m.get(i).getNumero() == numero) {
				return repMesa.recuperarNumeroMesa(numero);
			}
		}
		throw new MesaInexistenteException();
	}
	
	public void mesaMudarDisponibilidadeValidacao(Mesa mesa) {
		repMesa.mudarDisponibilidade(mesa);
	}
	
	public List<Mesa> mesaListarTodosValidacao() throws ListarTodosInvalidoException {
		if(repMesa.listarTodos() == null) {
			throw new ListarTodosInvalidoException();
		}
		return repMesa.listarTodos();
	}

}
