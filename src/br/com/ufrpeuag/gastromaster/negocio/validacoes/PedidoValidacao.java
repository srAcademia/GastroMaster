package br.com.ufrpeuag.gastromaster.negocio.validacoes;

import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.RepositorioPedido;
import br.com.ufrpeuag.gastromaster.dados.interfaces.PedidoDao;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;

public class PedidoValidacao {
	private PedidoDao repPedido;
	
	public PedidoValidacao() {
		repPedido = new RepositorioPedido();
	}
	
	public void pedidoCadastorValidacao(Pedido pedido) throws PedidoInvalidoException, PedidoVazioException{
		if(pedido.getValor() <= 0) {
			throw new PedidoInvalidoException();
		}
		if(pedido.getCardapio().equals(null) && pedido.getProduto().equals(null)) {
			throw new PedidoVazioException();
		}
		repPedido.inserir(pedido);
	}
	
	public void pedidoRemocaoValidacao(Pedido pedido) throws PedidoInexistenteException {
		List<Pedido> ped = new ArrayList<>();
		ped = this.repPedido.listarTodos();
		for (int i = 0; i < ped.size(); i++) {
			if(ped.get(i).getCardapio().getPrato().equals(pedido.getCardapio().getPrato()) && 
					ped.get(i).getProduto().getNome().equals(pedido.getProduto().getNome())) {
				repPedido.deletar(pedido);;
			}
		}
		throw new PedidoInexistenteException();
	}
	
	public void pedidoAlteracaoValidacao(Pedido pedido) throws PedidoInvalidoException, PedidoVazioException {
		if(pedido.getValor() <= 0) {
			throw new PedidoInvalidoException();
		}
		if(pedido.getCardapio().equals(null) && pedido.getProduto().equals(null)) {
			throw new PedidoVazioException();
		}
		repPedido.alterar(pedido);
	}
	
	public Pedido pedidoRecuperarValidacao(Integer codigo) throws  IDRecuperacaoItemInvalidoException {
		List<Pedido> ped = new ArrayList<>();
		ped = this.repPedido.listarTodos();
		for (int i = 0; i < ped.size(); i++) {
			if(ped.get(i).getId_pedido() == codigo) {
				return repPedido.recuperar(codigo);
			}
		}
		throw new IDRecuperacaoItemInvalidoException();
	}
	
	public List<Pedido> pedidoListarTodosValidacao() throws ListarTodosInvalidoException {
		if(repPedido.listarTodos() == null) {
			throw new ListarTodosInvalidoException();
		}
		return repPedido.listarTodos();
	}

}
