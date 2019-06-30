package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioMesa;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IMesaDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperarMesaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaCadastradaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaDisponibilidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;

public class MesaNegocio {

	private IMesaDao repMesa;

	public MesaNegocio() throws SQLException {
		repMesa = new RepositorioMesa();
	}

	public void cadastrarMesa(Mesa mesa)
			throws MesaCadastradaException, MesaDisponibilidadeInvalidaException, NumeroInvalidoException {
		if (mesa.getNumero() < 1) {
			throw new NumeroInvalidoException();
		}
		if (repMesa.recuperarNumeroMesa(mesa.getNumero()) != null) {
			throw new MesaCadastradaException();
		}
		if (mesa.getDisponibilidade() != 1 && mesa.getDisponibilidade() != 0) {
			throw new MesaDisponibilidadeInvalidaException();
		}
		repMesa.inserir(mesa);
	}

	public void deletarMesa(Mesa mesa) throws MesaInexistenteException {
		if (mesa == null) {
			throw new MesaInexistenteException();
		}
		if (repMesa.recuperar(mesa.getId_mesa()) == null) {
			throw new MesaInexistenteException();
		}
		repMesa.deletar(mesa);
	}

	public void alterarMesa(Mesa mesa, int novoNumero) throws MesaCadastradaException {
		if (novoNumero > 0) {
			if (repMesa.recuperarNumeroMesa(novoNumero) != null) {
				throw new MesaCadastradaException();
			}
			mesa.setNumero(novoNumero);
		}
		repMesa.alterar(mesa);
	}

	public Mesa recuperarMesaID(Integer codigo) throws IDRecuperarMesaException {
		if (repMesa.recuperar(codigo) != null) {
			return repMesa.recuperar(codigo);
		}
		throw new IDRecuperarMesaException();
	}

	public Mesa recuperarMesaPorNumero(Integer numero) throws MesaInexistenteException {

		if (repMesa.recuperarNumeroMesa(numero) != null) {
			return repMesa.recuperarNumeroMesa(numero);

		}
		throw new MesaInexistenteException();
	}

	public void mudarDisponibilidadeMesa(Mesa mesa) throws MesaInexistenteException {
		if (repMesa.recuperar(mesa.getId_mesa()) == null) {
			throw new MesaInexistenteException();
		}

		repMesa.mudarDisponibilidade(mesa);
	}

	public List<Mesa> listarTodasMesas() {
		return repMesa.listarTodos();
	}

}
