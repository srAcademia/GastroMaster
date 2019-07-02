package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.sql.SQLException;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioPedido;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IPedidoDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ExceptionRecuperarUltimoID;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;

public class PedidoNegocio {
	private IPedidoDao repPedido;

	public PedidoNegocio() throws SQLException {
		repPedido = new RepositorioPedido();
	}

	public void cadastrarPedido(Pedido pedido) throws PedidoInvalidoException, PedidoVazioException {

		if (pedido.getCardapio() == null && pedido.getProduto() == null) {
			throw new PedidoVazioException();

		}

		if (pedido.getProduto() == null) {
			pedido.setValor(pedido.calcularValorPedido(pedido.getCardapio().getPreco(), 0));
		} else if (pedido.getCardapio() == null) {
			pedido.setValor(pedido.calcularValorPedido(0, pedido.getProduto().getPreco()));
		} else {
			pedido.setValor(
					pedido.calcularValorPedido(pedido.getCardapio().getPreco(), pedido.getProduto().getPreco()));
		}
		if (pedido.getValor() <= 0) {
			throw new PedidoInvalidoException();
		}

		repPedido.inserir(pedido);
	}

	public void deletarPedido(Pedido pedido) throws PedidoInexistenteException {
		repPedido.deletar(pedido);
	}

	public void alterarPedido(Pedido pedido) throws PedidoInvalidoException, PedidoVazioException {
		pedido.setValor(pedido.calcularValorPedido(pedido.getCardapio().getPreco(), pedido.getProduto().getPreco()));

		if (pedido.getValor() <= 0) {
			throw new PedidoInvalidoException();
		}
		if (pedido.getCardapio().equals(null) && pedido.getProduto().equals(null)) {
			throw new PedidoVazioException();
		}
		repPedido.alterar(pedido);
	}

	public Pedido recuperarPedidoPorID(Integer codigo) throws IDRecuperacaoItemInvalidoException {
		if (repPedido.recuperar(codigo) != (null)) {
			return repPedido.recuperar(codigo);

		}
		throw new IDRecuperacaoItemInvalidoException();
	}

	public Integer retornarIDPedido(Integer id_cardapio, Integer id_produto, Integer id_mesa)
			throws PedidoInexistenteException {

		if (repPedido.retornarId(id_cardapio, id_produto, id_mesa) == 0) {
			throw new PedidoInexistenteException();
		}
		return repPedido.retornarId(id_cardapio, id_produto, id_mesa);
	}

	public List<Pedido> listarTodosPedidos() throws ListarTodosInvalidoException {

		if (repPedido.listarTodos() == null || repPedido.listarTodos().isEmpty()) {
			throw new ListarTodosInvalidoException();
		}
		return repPedido.listarTodos();
	}

	public int recuperarUltimoIDPedido() throws ExceptionRecuperarUltimoID {
		if (repPedido.recuperarUltimoID() == 0) {
			throw new ExceptionRecuperarUltimoID();
		}
		return repPedido.recuperarUltimoID();
	}

	public void deletarTodosPedidosPelaMesa(Integer codigo) {
		repPedido.deletarPedidosPelaMesa(codigo);
	}

	public List<Pedido> listarPedidosPorMesa(Integer codigo) {
		return repPedido.listarPorMesa(codigo);
	}

}
