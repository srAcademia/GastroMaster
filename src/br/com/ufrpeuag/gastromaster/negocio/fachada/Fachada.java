package br.com.ufrpeuag.gastromaster.negocio.fachada;

import java.util.List;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
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
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.CardapioValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.EnderecoValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.GarcomValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.GerenteValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.PedidoValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.ProdutoValidacao;

public class Fachada {
	private static Fachada singleton = null;
	private EnderecoValidacao endereco;
	private GarcomValidacao garcom;
	private GerenteValidacao gerente;
	private ProdutoValidacao produto;
	private CardapioValidacao cardapio;
	private PedidoValidacao pedido;
	
	public static Fachada getSingleton() {
		if (singleton == null) {
			singleton = new Fachada();
		}
		return singleton;
	}
	
	private Fachada () {
		endereco = new EnderecoValidacao();
		garcom = new GarcomValidacao();
		gerente = new GerenteValidacao();
		produto = new ProdutoValidacao();
		cardapio = new CardapioValidacao();
		pedido = new PedidoValidacao();
	}
	
	public void enderecoCadastroValidacao(Endereco endereco) throws BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException{
		this.endereco.enderecoCadastroValidacao(endereco);
	}
	
	public void enderecoRemocaoValidacao(Endereco endereco) throws EnderecoInexistenteException{
		this.endereco.enderecoRemocaoValidacao(endereco);
	}
	
	public void enderecoAlteracaoValidacao(Endereco endereco) throws BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException{
		this.endereco.enderecoAlteracaoValidacao(endereco);
	}
	
	public Endereco enderecoRecuperarValidacao(Integer codigo) throws EnderecoInexistenteException{
		return this.endereco.enderecoRecuperarValidacao(codigo);
	}
	
	public int enderecoRecuperarUltimoIDValidacao() throws IDInexistenteException{
		return this.endereco.enderecoRecuperarUltimoIDValidacao();
	}
	
	public void garcomCadastroValidacao(Garcom garcom) throws CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GarcomExistenteException, SalarioInvalidoException{
		this.garcom.garcomCadastroValidacao(garcom);
	}
	
	public void garcomRemocaoValidacao(Garcom garcom) throws GarcomInexistenteException{
		this.garcom.garcomRemocaoValidacao(garcom);
	}
	
	public void garcomAlteracaoValidacao(Garcom garcom) throws CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, SalarioInvalidoException {
		this.garcom.garcomAlteracaoValidacao(garcom);
	}
	
	public Garcom garcomRecuperarValidacao(Integer codigo) throws IDRecuperacaoInvalidaException{
		return this.garcom.garcomRecuperarValidacao(codigo);
	}
	
	public List<Garcom> garcomListarTodosValidacao() throws ListarTodosInvalidoException{
		return this.garcom.garcomListarTodosValidacao();
	}
	
	public Garcom garcomRecuperarCPFValidacao(String CPF) throws CPFInvalidoException, RecuperarCPFException{
		return this.garcom.garcomRecuperarCPFValidacao(CPF);
	}
	
	public void gerenteCadastroValidacao(Gerente gerente) throws CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GerenteExistenteException, SalarioInvalidoException{
		this.gerente.gerenteCadastroValidacao(gerente);
	}
	
	public void gerenteRemocaoValidacao(Gerente gerente) throws GerenteInexistenteException{
		this.gerente.gerenteRemocaoValidacao(gerente);
	}
	
	public void gerenteAlteracaoValidacao(Gerente gerente) throws CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GerenteExistenteException, SalarioInvalidoException{
		this.gerente.gerenteAlteracaoValidacao(gerente);
	}
	
	public Gerente gerenteRecuperarValidacao(Integer codigo) throws IDRecuperacaoInvalidaException{
		return this.gerente.gerenteRecuperarValidacao(codigo);
	}
	
	public List<Gerente> gerenteListarTodosValidacao() throws ListarTodosInvalidoException{
		return this.gerente.gerenteListarTodosValidacao();
	}
	
	public void produtoCadastroValidacao(Produto produto) throws PrecoInvalidoException, ProdutoExistenteException, QuantidadeProdutoInvalidaException, NomeInvalidoException {
		this.produto.produtoCadastroValidacao(produto);
	}
	
	public void produtoRemocaoValidacao(Produto produto) throws ProdutoInexistenteException{
		this.produto.produtoRemocaoValidacao(produto);
	}
	
	public void produtoAlteracaoValidacao(Produto produto) throws ProdutoInexistenteException, NomeInvalidoException, QuantidadeProdutoInvalidaException, PrecoInvalidoException{
		this.produto.produtoAlteracaoValidacao(produto);
	}
	
	public Produto produtoRetornarProdutoValidacao(String nome) throws ProdutoInexistenteException, NomeInvalidoException {
		return this.produto.produtoRetornarProdutoValidacao(nome);
	}
	
	public Produto produtoRecuperarValidacao(Integer codigo) throws IDRecuperacaoItemInvalidoException{
		return this.produto.produtoRecuperarValidacao(codigo);
	}
	
	public int produtoRetornarQuantidadeProdutoValidacao(Produto produto) throws ProdutoInexistenteException{
		return this.produto.produtoRetornarQuantidadeProdutoValidacao(produto);
	}
	
	public void produtoRemoverQuantProdutoValidacao(Produto produto, Integer quantidade) throws ProdutoInexistenteException, QuantidadeInvalidaException, QuantidadeProdutoInvalidaException {
		this.produto.produtoRemoverQuantProdutoValidacao(produto, quantidade);
	}
	
	public List<Produto> produtoListarTodosValidacao() throws ListarTodosInvalidoException{
		return this.produto.produtoListarTodosValidacao();
	}
	
	public void cardapioCadastroValidacao(Cardapio cardapio) throws PratoExistenteException, NomeInvalidoException, PrecoInvalidoException{
		this.cardapio.cardapioCadastroValidacao(cardapio);
	}
	
	public void cardapioRemocaoValidacao(Cardapio cardapio) throws PratoInexistenteException {
		this.cardapio.cardapioRemocaoValidacao(cardapio);
	}
	
	public void cardapioAlteracaoValidacao(Cardapio cardapio) throws NomeInvalidoException, PrecoInvalidoException {
		this.cardapio.cardapioAlteracaoValidacao(cardapio);
	}
	
	public Cardapio cardapioRecuperarValidacao(String nome) throws PratoInexistenteException, NomeInvalidoException {
		return this.cardapio.cardapioRecuperarValidacao(nome);
	}
	
	public Cardapio cardapioRecuperarValidacao(Integer codigo) throws IDRecuperacaoItemInvalidoException{
		return this.cardapio.cardapioRecuperarValidacao(codigo);
	}
	
	public List<Cardapio> cardapioListarTodosValidacao() throws ListarTodosInvalidoException {
		return this.cardapio.cardapioListarTodosValidacao();
	}
	
	public void pedidoCadastorValidacao(Pedido pedido) throws PedidoInvalidoException, PedidoVazioException{
		this.pedido.pedidoCadastorValidacao(pedido);
	}
	
	public void pedidoRemocaoValidacao(Pedido pedido) throws PedidoInexistenteException {
		this.pedido.pedidoRemocaoValidacao(pedido);
	}
	
	public void pedidoAlteracaoValidacao(Pedido pedido) throws PedidoInvalidoException, PedidoVazioException {
		this.pedido.pedidoAlteracaoValidacao(pedido);
	}
	
	public Pedido pedidoRecuperarValidacao(Integer codigo) throws  IDRecuperacaoItemInvalidoException {
		return this.pedido.pedidoRecuperarValidacao(codigo);
	}
	
	public List<Pedido> pedidoListarTodosValidacao() throws ListarTodosInvalidoException {
		return this.pedido.pedidoListarTodosValidacao();
	}
}
