package br.com.ufrpeuag.gastromaster.negocio.fachada;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ConcluirPagamentoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ContaGerarException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ExceptionRecuperarUltimoID;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperarMesaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaCadastradaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaDisponibilidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.MesaInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PedidoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PratoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeProdutoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RecuperarCPFException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RelatorioVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SenhaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.GerenciamentoContas;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.CardapioNegocio;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.ContaNegocio;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.DataNegocio;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.GarcomNegocio;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.GerenciamentoNegocio;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.GerenteNegocio;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.MesaNegocio;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.PedidoNegocio;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.ProdutoNegocio;

public class Fachada {

	private static Fachada singleton = null;
	private GarcomNegocio garcom;
	private GerenteNegocio gerente;
	private ProdutoNegocio produto;
	private CardapioNegocio cardapio;
	private PedidoNegocio pedido;
	private MesaNegocio mesa;
	private ContaNegocio conta;
	private GerenciamentoNegocio gerenciamento;
	private DataNegocio data;

	public static Fachada getSingleton() throws SQLException {
		if (singleton == null) {
			singleton = new Fachada();
		}
		return singleton;
	}

	private Fachada() throws SQLException {
		garcom = new GarcomNegocio();
		gerente = new GerenteNegocio();
		produto = new ProdutoNegocio();
		cardapio = new CardapioNegocio();
		pedido = new PedidoNegocio();
		mesa = new MesaNegocio();
		conta = new ContaNegocio();
		gerenciamento = new GerenciamentoNegocio();
		data = new DataNegocio();
	}

	// Garçom

	public void cadastrarGarcom(Garcom garcom) throws CPFInvalidoException, DataNascimentoInvalidaException,
			NomeInvalidoException, GarcomExistenteException, SalarioInvalidoException, RuaInvalidaException,
			NumeroInvalidoException, CidadeInvalidaException, CEPInvalidoException, BairroInvalidoException {
		this.garcom.cadastrarGarcom(garcom);
	}

	public void deletarGarcom(Garcom garcom) throws GarcomInexistenteException {
		this.garcom.deletarGarcom(garcom);
	}

	public Garcom recuperarGarcomID(Integer codigo) throws IDRecuperacaoInvalidaException {
		return this.garcom.recuperarGarcomID(codigo);
	}

	public List<Garcom> ListarTodosGarcons() {
		return this.garcom.listarTodosGarcons();
	}

	public Garcom recuperarGarcomPorCPF(String CPF) throws CPFInvalidoException, RecuperarCPFException {
		return this.garcom.recuperarPorCpfGarcom(CPF);
	}

	public Garcom verificarIdentificadorGarcom(String identificador) {
		return this.garcom.verificarIndetificadorGarcom(identificador);
	}

	public void alterarGarcom(Garcom garcom) throws CPFInvalidoException, RecuperarCPFException,
			DataNascimentoInvalidaException, GarcomExistenteException {
		this.garcom.alterarGarcom(garcom);
	}

	// Gerente

	public void cadastrarGerente(Gerente gerente)
			throws CPFInvalidoException, DataNascimentoInvalidaException, NomeInvalidoException,
			GerenteExistenteException, SalarioInvalidoException, SenhaInvalidaException, BairroInvalidoException,
			CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException {
		this.gerente.cadastrarGerente(gerente);
	}

	public void deletarGerente(Gerente gerente) throws GerenteInexistenteException {
		this.gerente.deletarGerente(gerente);
	}

	public void alterarGerente(Gerente gerente)
			throws CPFInvalidoException, DataNascimentoInvalidaException, GerenteExistenteException {
		this.gerente.alterarGerente(gerente);
	}

	public Gerente recuperarGerenteID(Integer codigo) throws IDRecuperacaoInvalidaException {
		return this.gerente.recuperarGerenteID(codigo);
	}

	public Gerente recuperarCpfPorGerente(String CPF) throws CPFInvalidoException, RecuperarCPFException {
		return this.gerente.recuperarCpfPorGerente(CPF);
	}

	public List<Gerente> listarTodosGerentes() {
		return this.gerente.listarTodosGerentes();
	}

	public Gerente verificarIdentificadorGerente(String identificador) {
		return this.gerente.verificarIdentificadorGerente(identificador);
	}

	public Gerente logarGerente(String senha) throws SenhaInvalidaException {
		return this.gerente.logarGerente(senha);
	}

	// Mesa

	public void cadastrarMesa(Mesa mesa)
			throws MesaCadastradaException, NumeroInvalidoException, MesaDisponibilidadeInvalidaException {
		this.mesa.cadastrarMesa(mesa);
	}

	public void deletarMesa(Mesa mesa) throws MesaInexistenteException {
		this.mesa.deletarMesa(mesa);
	}

	public void alterarMesa(Mesa mesa, int novoNumero) throws MesaCadastradaException {
		this.mesa.alterarMesa(mesa, novoNumero);
	}

	public Mesa recuperarMesaID(Integer codigo) throws IDRecuperarMesaException {
		return this.mesa.recuperarMesaID(codigo);
	}

	public Mesa recuperarMesaPorNumero(Integer numero) throws MesaInexistenteException {
		return this.mesa.recuperarMesaPorNumero(numero);
	}

	public void mudarDisponibilidadeMesa(Mesa mesa) throws MesaInexistenteException {
		this.mesa.mudarDisponibilidadeMesa(mesa);
	}

	public List<Mesa> listarTodasMesas() throws ListarTodosInvalidoException {
		return this.mesa.listarTodasMesas();
	}

	// Produto

	public void cadastrarProduto(Produto produto) throws PrecoInvalidoException, ProdutoExistenteException,
			QuantidadeProdutoInvalidaException, NomeInvalidoException {
		this.produto.cadastrarProduto(produto);
	}

	public void deletarProduto(Produto produto) throws ProdutoInexistenteException {
		this.produto.deletarProduto(produto);
	}

	public void alterarProduto(Produto produto, String nome, String novoNome, int quantidade, double preco)
			throws ProdutoExistenteException {
		this.produto.alterarProduto(produto, nome, novoNome, quantidade, preco);
	}

	public Produto recuperarProdutoNome(String nome) throws ProdutoInexistenteException, NomeInvalidoException {
		return this.produto.recuperarProdutoNome(nome);
	}

	public Produto recuperarProdutoPorID(Integer codigo) throws IDRecuperacaoItemInvalidoException {
		return this.produto.recuperarProdutoPorID(codigo);
	}

	public int recuperarQuantidadeProduto(Produto produto) throws ProdutoInexistenteException {
		return this.produto.recuperarQuantidadeProduto(produto);
	}

	public void removerQuantidadeProduto(Produto produto, Integer quantidade)
			throws QuantidadeInvalidaException, QuantidadeProdutoInvalidaException {
		this.produto.removerQuantidadeProduto(produto, quantidade);
	}

	public void adicionarQuantidadeProduto(Produto produto, Integer quantidade)
			throws QuantidadeProdutoInvalidaException {
		this.produto.adicionarQuantidadeProduto(produto, quantidade);
	}

	public List<Produto> listarTodosProdutos() throws ListarTodosInvalidoException {
		return this.produto.listarTodosProdutos();
	}

	public Integer recuperarIDPeloNomeProduto(String nome) throws NomeInvalidoException, ProdutoInexistenteException {
		return this.produto.recuperarIDPeloNome(nome);
	}

	// CARDAPIO

	public void cadastrarCardapio(Cardapio cardapio)
			throws PratoExistenteException, NomeInvalidoException, PrecoInvalidoException {
		this.cardapio.cadastrarCardapio(cardapio);
	}

	public void deletarCardapio(Cardapio cardapio) throws PratoInexistenteException {
		this.cardapio.deletarCardapio(cardapio);
	}

	public void alterarCardapio(Cardapio cardapio, String nome, String novoNome, double preco)
			throws PratoExistenteException {
		this.cardapio.alterarCardapio(cardapio, nome, novoNome, preco);
	}

	public Cardapio recuperarCardapioPeloNome(String nome) throws PratoInexistenteException, NomeInvalidoException {
		return this.cardapio.recuperarCardapioPeloNome(nome);
	}

	public Cardapio recuperarCardapioPorID(Integer codigo) throws PratoInexistenteException {
		return this.cardapio.recuperarCardapioPorID(codigo);
	}

	public List<Cardapio> listarTodosCardapios() throws ListarTodosInvalidoException {
		return this.cardapio.listarTodosCardapios();
	}

	public Integer recuperarIDPeloNomeCardapio(String nome) throws NomeInvalidoException, PratoInexistenteException {
		return this.cardapio.recuperarIDPeloNomeCardapio(nome);
	}

	// Pedido

	public void cadastrarPedido(Pedido pedido) throws PedidoInvalidoException, PedidoVazioException {
		this.pedido.cadastrarPedido(pedido);
	}

	public void deletarPedido(Pedido pedido) throws PedidoInexistenteException {
		this.pedido.deletarPedido(pedido);
	}

	public void alterarPedido(Pedido pedido) throws PedidoInvalidoException, PedidoVazioException {
		this.pedido.alterarPedido(pedido);
	}

	public Pedido recuperarPedidoPorID(Integer codigo) throws IDRecuperacaoItemInvalidoException {
		return this.pedido.recuperarPedidoPorID(codigo);
	}

	public List<Pedido> listarTodosPedidos() throws ListarTodosInvalidoException {
		return this.pedido.listarTodosPedidos();
	}

	public Integer retornarIDPedido(Integer id_cardapio, Integer id_produto, Integer id_mesa)
			throws PedidoInexistenteException {
		return this.pedido.retornarIDPedido(id_cardapio, id_produto, id_mesa);
	}

	public int recuperarUltimoIDPedido() throws ExceptionRecuperarUltimoID {
		return this.pedido.recuperarUltimoIDPedido();
	}

	public List<Pedido> listarPedidosPorMesa(Integer codigo) throws ConcluirPagamentoException {
		return this.pedido.listarPedidosPorMesa(codigo);
	}

	public void deletarTodosPedidosPelaMesa(Integer codigo) throws PedidoInexistenteException {
		this.pedido.deletarTodosPedidosPelaMesa(codigo);
	}

	// Conta

	public void cadastrarConta(Conta conta) {
		this.conta.cadastrarConta(conta);
	}

	public void deletarConta(Conta conta) {
		this.conta.deletarConta(conta);
	}

	public Conta recuperarContaID(Integer codigo) {
		return this.conta.recuperarContaID(codigo);
	}

	public double mostrarValorConta(Conta conta) {
		return this.conta.mostrarValorConta(conta);
	}

	public List<Conta> recuperarContaPorMesa(Integer codigo) throws ContaGerarException {
		return this.conta.recuperarContaPorMesa(codigo);
	}

	public void deletarTodasContasPorMesa(Conta conta) {
		this.conta.deletarTodasContasPorMesa(conta);
	}
	// Gerenciamento Conta

	public void cadastrarGerenciamentoContas(GerenciamentoContas gerenciamento) {
		this.gerenciamento.cadastrarGerenciamentoContas(gerenciamento);
	}

	public List<GerenciamentoContas> listarTodosGerenciamentoContas() throws RelatorioVazioException {
		return this.gerenciamento.listarTodosGerenciamentoContas();
	}
	
	public LocalDate ValidarData(String data) throws DataNascimentoInvalidaException {
		return this.data.ValidarData(data);
	}
}
