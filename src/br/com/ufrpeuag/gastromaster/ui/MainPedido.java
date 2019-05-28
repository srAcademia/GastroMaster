package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.ConcluirPagamentoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeProdutoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class MainPedido {
	
	//INSERCAO
	public int gerenciarCadastroPedido(String nomeCard, String nomeProduto, Mesa mesa) throws SQLException, PratoInexistenteException, NomeInvalidoException, PedidoInvalidoException, PedidoVazioException, ProdutoInexistenteException {
		try {
			Cardapio cardapio = new Cardapio();
			Produto produto = new Produto();
			if(nomeCard.isEmpty() == false) {
				cardapio = Fachada.getSingleton().cardapioRecuperarValidacao(nomeCard);
			}
			if(nomeProduto.isEmpty() == false) {
				produto = Fachada.getSingleton().produtoRetornarProdutoValidacao(nomeProduto);
				Fachada.getSingleton().produtoRemoverQuantProdutoValidacao(produto, 1);
			}
			Pedido pedido = new Pedido(cardapio, produto, 0, mesa);
			Fachada.getSingleton().pedidoCadastroValidacao(pedido);
			int id = 0;
			id = Fachada.getSingleton().pedidoRecuperarUltimoIDValidacao();
			return id;
		}catch(PedidoInvalidoException | PedidoVazioException | PratoInexistenteException | NomeInvalidoException | ProdutoInexistenteException | QuantidadeInvalidaException | QuantidadeProdutoInvalidaException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return 0;
			
	}
	
	//REMOCAO
	public void gerenciarRemocaoPedido(Pedido pedido) throws SQLException, PedidoInexistenteException {
		try {
			Fachada.getSingleton().pedidoRemocaoValidacao(pedido);
		}catch(PedidoInexistenteException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//RETORNAR PEDIDO POR CODIGO
	public Pedido gerenciarRecuperarPedido(Integer codigo) throws SQLException, IDRecuperacaoItemInvalidoException{
		try {
			Pedido pedido = new Pedido();
			pedido = Fachada.getSingleton().pedidoRecuperarValidacao(codigo);
			return pedido;
		}catch(IDRecuperacaoItemInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return null;
	}
	
	//RETORNAR O CODIGO DO PEDIDO
	public Pedido gerencairRecuperarCodigoPedido(Integer id_cardapio, Integer id_produto, Integer id_mesa) throws SQLException, IDRecuperacaoItemInvalidoException{
		try {
			Integer id = Fachada.getSingleton().pedidoRecuperarCodigosValidacao(id_cardapio, id_produto, id_mesa);
			Pedido pedido = new Pedido();
			pedido = Fachada.getSingleton().pedidoRecuperarValidacao(id);
			return pedido;
		}catch(IDRecuperacaoItemInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return null;
	}
	
	//ALTERACAO
	public void gerenciarAlteracaoPedido(Integer codigo, String novoCardapio, String novoProduto) throws SQLException{
		try {
			Cardapio cardapio = new Cardapio();
			Produto produto = new Produto();
			Pedido pedido = new Pedido();
			pedido = Fachada.getSingleton().pedidoRecuperarValidacao(codigo);
			if(novoCardapio.isEmpty() == false) {
				cardapio = Fachada.getSingleton().cardapioRecuperarValidacao(novoCardapio);
				pedido.setCardapio(cardapio);
			}
			if(novoProduto.isEmpty() == false) {
				produto = Fachada.getSingleton().produtoRetornarProdutoValidacao(novoProduto);
				pedido.setProduto(produto);
			}
			Fachada.getSingleton().pedidoAlteracaoValidacao(pedido);
		}catch(IDRecuperacaoItemInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//LISTAR TODOS
	public void gerenciarListarPedido() throws SQLException, ListarTodosInvalidoException {
		try {
			System.out.println(Fachada.getSingleton().pedidoListarTodosValidacao());
		}catch(ListarTodosInvalidoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	//LISTAR POR MESA
	public List<Pedido> gerenciarListarPorMesa(Integer codigo) throws SQLException, ConcluirPagamentoException{
		try {
			List<Pedido> pedidos = new ArrayList<>();
			pedidos = Fachada.getSingleton().pedidoListarPorMesaValidacao(codigo);
			return pedidos;
		}catch(ConcluirPagamentoException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return null;
	}
	
	//REMOVER TODOS PEDIDOS
	public void gerenciarRemoverTodosPedido(Integer codigo) throws SQLException {
		try {
			Fachada.getSingleton().pedidoRemoverTodosPedidosValidacao(codigo);
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
}