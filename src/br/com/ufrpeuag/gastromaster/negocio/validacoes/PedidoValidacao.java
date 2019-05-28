package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioPedido;
import br.com.ufrpeuag.gastromaster.dados.interfaces.IPedidoDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ConcluirPagamentoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;

public class PedidoValidacao {
	private IPedidoDao repPedido;
	
	public PedidoValidacao() {
		repPedido = new RepositorioPedido();
	}
	
	public void pedidoCadastroValidacao(Pedido pedido) throws PedidoInvalidoException, PedidoVazioException{
		pedido.setValor(pedido.calcularValorPedido(pedido.getCardapio().getPreco(), pedido.getProduto().getPreco()));
		if(pedido.getValor() <= 0) {
			throw new PedidoInvalidoException();
		}
		if(pedido.getCardapio().equals(null) && pedido.getProduto().equals(null)) {
			throw new PedidoVazioException();
		}
		repPedido.inserir(pedido);
	}
	
	public void pedidoRemocaoValidacao(Pedido pedido) throws PedidoInexistenteException {
		int id = repPedido.retornarId(pedido.getCardapio().getId_cardapio(), pedido.getProduto().getId_produto(), pedido.getMesa().getId_mesa());
		if( id != 0) {
			repPedido.deletar(pedido);
		}
		else {
			throw new PedidoInexistenteException();
		}
	}
	
	public void pedidoAlteracaoValidacao(Pedido pedido) throws PedidoInvalidoException, PedidoVazioException {
		pedido.setValor(pedido.calcularValorPedido(pedido.getCardapio().getPreco(), pedido.getProduto().getPreco()));
		if(pedido.getValor() <= 0) {
			throw new PedidoInvalidoException();
		}
		if(pedido.getCardapio().equals(null) && pedido.getProduto().equals(null)) {
			throw new PedidoVazioException();
		}
		repPedido.alterar(pedido);
	}
	
	public Pedido pedidoRecuperarValidacao(Integer codigo)throws IDRecuperacaoItemInvalidoException {
		if(repPedido.recuperar(codigo) == null) {
			throw new IDRecuperacaoItemInvalidoException();
		}
		return repPedido.recuperar(codigo);
	}
	
	public Integer pedidoRecuperarCodigosValidacao(Integer id_cardapio, Integer id_produto, Integer id_mesa) throws PedidoInexistenteException {
		Integer id = repPedido.retornarId(id_cardapio, id_produto, id_mesa);
		if(id == 0) {
			throw new PedidoInexistenteException();
		}
		return id;
	}
	
	public List<Pedido> pedidoListarTodosValidacao() throws ListarTodosInvalidoException {
		if(repPedido.listarTodos() == null || repPedido.listarTodos().isEmpty()) {
			throw new ListarTodosInvalidoException();
		}
		return repPedido.listarTodos();
	}
	
	public int pedidoRecuperarUltimoIDValidacao() {
		return repPedido.recuperarUltimoID();
	}
	
	public void pedidoRemoverTodosPedidosValidacao(Integer codigo) {
		repPedido.deletarPedidosPelaMesa(codigo);
	}
	
	public List<Pedido> pedidoListarPorMesaValidacao(Integer codigo)throws ConcluirPagamentoException{
		if(repPedido.listarPorMesa(codigo) == null || repPedido.listarPorMesa(codigo).isEmpty()) {
			throw new ConcluirPagamentoException();
		}
		return repPedido.listarPorMesa(codigo);
	}

}
