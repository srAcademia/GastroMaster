package br.com.ufrpeuag.gastromaster.negocio.fachada;

import java.sql.SQLException;
import java.util.List;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GarcomInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoItemInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperarMesaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ListarTodosInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.LoginInvalidoException;
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
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SenhaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.CardapioValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.ContaValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.EnderecoValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.GarcomValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.GerenteValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.MesaValidacao;
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
	private MesaValidacao mesa;
	private ContaValidacao conta;
	
	public static Fachada getSingleton() throws SQLException {
		if (singleton == null) {
			singleton = new Fachada();
		}
		return singleton;
	}
	
	private Fachada () throws SQLException {
		endereco = new EnderecoValidacao();
		garcom = new GarcomValidacao();
		gerente = new GerenteValidacao();
		produto = new ProdutoValidacao();
		cardapio = new CardapioValidacao();
		pedido = new PedidoValidacao();
		mesa = new MesaValidacao();
		conta = new ContaValidacao();
	}
	
	public void enderecoCadastroValidacao(Endereco endereco) throws BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException{
		this.endereco.enderecoCadastroValidacao(endereco);
	}
	
	public void enderecoRemocaoValidacao(Endereco endereco) throws EnderecoInexistenteException{
		this.endereco.enderecoRemocaoValidacao(endereco);
	}
	
	public void enderecoAlteracaoValidacao(Endereco endereco, String cidade, String bairro, String rua, int numero, String cep){
		this.endereco.enderecoAlteracaoValidacao(endereco, cidade, bairro, rua, numero, cep);
	}
	
	public Endereco enderecoRecuperarValidacao(Integer codigo) throws EnderecoInexistenteException{
		return this.endereco.enderecoRecuperarValidacao(codigo);
	}
	
	public int enderecoRecuperarUltimoIDValidacao() throws IDInexistenteException{
		return this.endereco.enderecoRecuperarUltimoIDValidacao();
	}
	
	public void garcomCadastroValidacao(Garcom garcom) throws CPFInvalidoException, DataNascimentoInvalidaException, NomeInvalidoException, GarcomExistenteException, SalarioInvalidoException{
		this.garcom.garcomCadastroValidacao(garcom);
	}
	
	public void garcomRemocaoValidacao(Garcom garcom) throws GarcomInexistenteException{
		this.garcom.garcomRemocaoValidacao(garcom);
	}
	
	public void garcomAlteracaoValidacao(Garcom garcom, String nome, String cpf, String novoCPF, String dataNasc, String telefone, String email, double salario) throws CPFInvalidoException, RecuperarCPFException, DataNascimentoInvalidaException, GarcomExistenteException {
		this.garcom.garcomAlteracaoValidacao(garcom, nome, cpf, novoCPF, dataNasc, telefone, email, salario);
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
	
	public Garcom garcomVerificarValidacao(String identificador) throws LoginInvalidoException {
		return this.garcom.garcomVerificarValidacao(identificador);
	}
	
	public void gerenteCadastroValidacao(Gerente gerente) throws CPFInvalidoException, DataNascimentoInvalidaException, NomeInvalidoException, GerenteExistenteException, SalarioInvalidoException{
		this.gerente.gerenteCadastroValidacao(gerente);
	}
	
	public void gerenteRemocaoValidacao(Gerente gerente) throws GerenteInexistenteException{
		this.gerente.gerenteRemocaoValidacao(gerente);
	}
	
	public void gerenteAlteracaoValidacao(Gerente gerente, String nome, String cpf, String novoCPF, String dataNasc, String telefone, String email, double salario, String senha) throws CPFInvalidoException, DataNascimentoInvalidaException, GerenteExistenteException{
		this.gerente.gerenteAlteracaoValidacao(gerente, nome, cpf, novoCPF, dataNasc, telefone, email, salario, senha);
	}
	
	public Gerente gerenteRecuperarValidacao(Integer codigo) throws IDRecuperacaoInvalidaException{
		return this.gerente.gerenteRecuperarValidacao(codigo);
	}
	
	public Gerente gerenteRecuperarCPFValidacao(String CPF) throws CPFInvalidoException, RecuperarCPFException{
		return this.gerente.gerenteRecuperarCPFValidacao(CPF);
	}
	
	public List<Gerente> gerenteListarTodosValidacao() throws ListarTodosInvalidoException{
		return this.gerente.gerenteListarTodosValidacao();
	}
	
	public Gerente gerenteVerificarValidacao(String identificador) throws LoginInvalidoException {
		return this.gerente.gerenteVerificarValidacao(identificador);
	}
	
	public Gerente gerenteLogarValidacao(String senha) throws SenhaInvalidaException {
		return this.gerente.gerenteLogarValidacao(senha);
	}
	
	public void produtoCadastroValidacao(Produto produto) throws PrecoInvalidoException, ProdutoExistenteException, QuantidadeProdutoInvalidaException, NomeInvalidoException {
		this.produto.produtoCadastroValidacao(produto);
	}
	
	public void produtoRemocaoValidacao(Produto produto) throws ProdutoInexistenteException{
		this.produto.produtoRemocaoValidacao(produto);
	}
	
	public void produtoAlteracaoValidacao(Produto produto, String nome, String novoNome, int quantidade, double preco)throws ProdutoExistenteException{
		this.produto.produtoAlteracaoValidacao(produto, nome, novoNome, quantidade, preco);
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
	
	public void produtoRemoverQuantProdutoValidacao(Produto produto, Integer quantidade) throws QuantidadeInvalidaException, QuantidadeProdutoInvalidaException {
		this.produto.produtoRemoverQuantProdutoValidacao(produto, quantidade);
	}
	public void produtoAdicionarQuantProdutoValidacao(Produto produto, Integer quantidade) throws QuantidadeProdutoInvalidaException {
		this.produto.produtoAdicionarQuantProdutoValidacao(produto, quantidade);
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
	
	public void cardapioAlteracaoValidacao(Cardapio cardapio, String nome, String novoNome, double preco) throws PratoExistenteException {
		this.cardapio.cardapioAlteracaoValidacao(cardapio, nome, novoNome, preco);
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
	
	public void pedidoCadastroValidacao(Pedido pedido) throws PedidoInvalidoException, PedidoVazioException{
		this.pedido.pedidoCadastroValidacao(pedido);
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
	
	public void mesaCadastroMesaValidacao(Mesa mesa) throws MesaCadastradaException, NumeroInvalidoException, MesaDisponibilidadeInvalidaException{
		this.mesa.mesaCadastroMesaValidacao(mesa);
	}
	
	public void mesaRemocaoValidacao(Mesa mesa){
		this.mesa.mesaRemocaoValidacao(mesa);
	}
	
	public void mesaAlteracaoValidacao(Mesa mesa, int novoNumero) throws MesaCadastradaException{
		this.mesa.mesaAlteracaoValidacao(mesa, novoNumero);
	}
	
	public Mesa mesaRecuperarValidacao(Integer codigo) throws IDRecuperarMesaException {
		return this.mesa.mesaRecuperarValidacao(codigo);
	}
	
	public Mesa mesaRecuperarNumeroValidacao(Integer numero) throws MesaInexistenteException {
		return this.mesa.mesaRecuperarNumeroValidacao(numero);
	}
	
	public void mesaMudarDisponibilidadeValidacao(Mesa mesa) {
		this.mesa.mesaMudarDisponibilidadeValidacao(mesa);
	}
	
	public List<Mesa> mesaListarTodosValidacao() throws ListarTodosInvalidoException {
		return this.mesa.mesaListarTodosValidacao();
	}
	
	public void contaCadastroContaValidacao(Conta conta) {
		this.conta.contaCadastroContaValidacao(conta);
	}
}
