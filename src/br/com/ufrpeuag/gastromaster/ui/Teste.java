/*package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;
import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioCardapio;
import br.com.ufrpeuag.gastromaster.dados.RepositorioConta;
import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGerenciamentoContas;
import br.com.ufrpeuag.gastromaster.dados.RepositorioMesa;
import br.com.ufrpeuag.gastromaster.dados.RepositorioPedido;
import br.com.ufrpeuag.gastromaster.dados.RepositorioProduto;
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
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RelatorioVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SenhaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.GerenciamentoContas;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
import javafx.scene.chart.XYChart;

public class Teste {

	public static void main(String[] args) throws SQLException, CPFInvalidoException, DataNascimentoInvalidaException,
			NomeInvalidoException, GarcomExistenteException, SalarioInvalidoException, RuaInvalidaException,
			NumeroInvalidoException, CidadeInvalidaException, CEPInvalidoException, BairroInvalidoException,
			IDRecuperacaoInvalidaException, GarcomInexistenteException, RecuperarCPFException, LoginInvalidoException,
			ListarTodosInvalidoException, GerenteExistenteException, SenhaInvalidaException, MesaCadastradaException,
			MesaDisponibilidadeInvalidaException, IDRecuperarMesaException, MesaInexistenteException, PratoExistenteException, PrecoInvalidoException, IDRecuperacaoItemInvalidoException, PratoInexistenteException, ProdutoInexistenteException, ProdutoExistenteException, QuantidadeProdutoInvalidaException, QuantidadeInvalidaException, PedidoInvalidoException, PedidoVazioException, PedidoInexistenteException, ConcluirPagamentoException, ExceptionRecuperarUltimoID, ContaGerarException, RelatorioVazioException {

		RepositorioGerenciamentoContas rg =new RepositorioGerenciamentoContas();
		RepositorioGerenciamentoContas gn = new RepositorioGerenciamentoContas();
		LocalDate data = LocalDate.now();  
		
		try {
		Map<String, Integer> example=null;
		example = gn.reucuperarPorMes(data);
		for (String key : example.keySet()) {
			Integer value = example.get(key);
			System.out.println("CHave "+ key+" Valor "+value);
           }
		}catch(ArrayIndexOutOfBoundsException a) {
			System.out.println("Lista Vazia");
			a.printStackTrace();
		}
		// Garcom e endereco cadastrar

		/*
		  Endereco end = new Endereco("Garanhuns", "sitio", "Mochila", 30, "55555");
		  Garcom g = new
		  Garcom("Milena","11563286408",LocalDate.now(),"88888","milly",220,null,end);
		  g.setIdentificador(g.gerarIdentificador());
		  Fachada.getSingleton().cadastrarGarcom(g);
		  Garcom g1 = new
				  Garcom("Papai","75008513400",LocalDate.now(),"88888","milly",220,null,end);
				  g1.setIdentificador(g1.gerarIdentificador());
				  Fachada.getSingleton().cadastrarGarcom(g1);*/
				  
		/*
		 * LocalDate data = LocalDate.of(2010, 3, 7); Endereco end = new
		 * Endereco("Garanhuns", "sitio", "Mochila", 30, "55555"); Garcom g = new
		 * Garcom("David","05842364417",data,"88888","milly",220,null,end);
		 * g.setIdentificador(g.gerarIdentificador());
		 * Fachada.getSingleton().cadastrarGarcom(g);
		 */
		/*
		 * String data ="14/07/1998"; String a =""+ data.charAt(6)+ data.charAt(7)+
		 * data.charAt(8)+ data.charAt(9); System.out.println(a);
		 */
		  /* //Recuperar: Garcom g = null; g =
		  Fachada.getSingleton().recuperarGarcomID(1);
		  System.out.println("Recuperar por ID: "+g); g =
		 Fachada.getSingleton().recuperarGarcomPorCPF("11563286408");
		  System.out.println("\nRecuperar por CPF: "+g); g =
		  Fachada.getSingleton().verificarIdentificadorGarcom("1c1d12");
		  System.out.println("\nRecuperar pelo Indentificador: "+g);
		  System.out.println("\nListar todos: "+Fachada.getSingleton().
		  ListarTodosGarcons());
		
		 * //Deletar: g = Fachada.getSingleton().recuperarGarcomID(1);
		 * Fachada.getSingleton().deletarGarcom(g);
		 * 
		 * //Alterar Garcom g = null; g = Fachada.getSingleton().recuperarGarcomID(1);
		 * g.setNome("Maria"); g.getEndereco().setBairro("Jucati");
		 * Fachada.getSingleton().alterarGarcom(g);
		 * 
		 */

		// Gerente Cadastrar
		/*
		 * Endereco end = new Endereco("Caetes", "sitio", "Mulambu", 30, "55555");
		 * Gerente g = new Gerente("Joao", "11563286408", LocalDate.now(), "88888",
		 * "milly", 350, "12345", null, end);
		 * g.setIdentificador(g.gerarIdentificador());
		 * Fachada.getSingleton().cadastrarGerente(g);
		 * 
		 * 
		 * // Recuperar:
		 * 
		 * Gerente g = null; g = Fachada.getSingleton().recuperarGerenteID(1);
		 * System.out.println("Recuperar por ID: " + g); g =
		 * Fachada.getSingleton().recuperarCpfPorGerente("11563286408");
		 * System.out.println("\nRecuperar por CPF: " + g); g =
		 * Fachada.getSingleton().verificarIdentificadorGerente("2fb2ec");
		 * System.out.println("\nRecuperar pelo Indentificador: " + g); g =
		 * Fachada.getSingleton().logarGerente("12345");
		 * System.out.println("\nRecuperar Por senha: " + g);
		 * System.out.println("\nListar todos: " +
		 * Fachada.getSingleton().ListarTodosGarcons());
		 * 
		 * // Deletar: g = Fachada.getSingleton().recuperarGarcomID(1);
		 * Fachada.getSingleton().deletarGerente(g);
		 * 
		 * // Alterar Gerente g = null; g =
		 * Fachada.getSingleton().recuperarGerenteID(1); g.setNome("Paulo");
		 * g.getEndereco().setBairro("Boa vista"); g.getEndereco().setCidade("Jupi");
		 * Fachada.getSingleton().alterarGerente(g);
		 */

		// Mesa Cadastrar
		  /*

		  Mesa m = new Mesa(6,1); 
		  Fachada.getSingleton().cadastrarMesa(m);
				// Recuperar
		Mesa m = null;
		m = Fachada.getSingleton().recuperarMesaID(1);
		System.out.println("Recuperar ID: " + m);
		m = Fachada.getSingleton().recuperarMesaPorNumero(4);
		System.out.println("Recuperar Numeror: " + m);
		System.out.println("Listar Todas: " + Fachada.getSingleton().listarTodasMesas());
		 
		//Alterar
		Mesa m = null;
		m = Fachada.getSingleton().recuperarMesaID(1);
		m.setDisponibilidade(0);
		m.setNumero(2);
		Fachada.getSingleton().alterarMesa(m);
		

		//Mudar Disponibilidade
		Mesa m = null;	
		m = Fachada.getSingleton().recuperarMesaID(1);
		Fachada.getSingleton().mudarDisponibilidadeMesa(m);
		System.out.println("Antes: "+m);
		m = Fachada.getSingleton().recuperarMesaID(1);
		System.out.println("Depois: "+m);
		
		//Deletar
		Mesa m = null;
		m = Fachada.getSingleton().recuperarMesaID(1);
		Fachada.getSingleton().deletarMesa(m);*/
		
		//Cardapio
		
		//Cadastrar
		
		
		/*
		Cardapio card = new Cardapio("Pizza",30);
		Cardapio card2 = new Cardapio("Lasanha",20);
		Fachada.getSingleton().cadastrarCardapio(card);
		Fachada.getSingleton().cadastrarCardapio(card2);
		
		
		//Alterar
		 


		Cardapio c =null;
		c = Fachada.getSingleton().recuperarCardapioPorID(1);
		c.setPreco(55);
		Fachada.getSingleton().alterarCardapio(c);
		*/
		
		//Deletar e recuperar por nome e ID, listar Todos
		
		/*
		Cardapio c =null;
		
		int id = Fachada.getSingleton().recuperarIDPeloNomeCardapio("Pizza");
		System.out.println("Mostrar ID :"+id);
		
		System.out.println("Listar todos cardapios: "+Fachada.getSingleton().listarTodosCardapios());
		
		c= Fachada.getSingleton().recuperarCardapioPeloNome("Lasanha");
		System.out.println("Recuperar Pelo Nome cardapio : "+c);
		
		Fachada.getSingleton().deletarCardapio(c);
		
		c = Fachada.getSingleton().recuperarCardapioPorID(2);
		System.out.println("Recuperar pelo ID: "+c);
		
		Fachada.getSingleton().deletarCardapio(c);
		*/
		
		//Produto 
		
		//Cadastrar
		/*
		Produto p = new Produto("Fanta",20,3);
		Produto p2 = new Produto("Coca-cola",10,4);
		Fachada.getSingleton().cadastrarProduto(p);
		Fachada.getSingleton().cadastrarProduto(p2);
		*/
		
		//Deletar
		
		/*
		Produto p = null;
		p = Fachada.getSingleton().recuperarProdutoPorID(2);
		Fachada.getSingleton().deletarProduto(p);
		*/
		
		//Recuperar
		
	/*	Produto p = null;
		
		p = Fachada.getSingleton().recuperarProdutoNome("Fanta");
		System.out.println("Recuperar Por nome: "+p);
		
		p = Fachada.getSingleton().recuperarProdutoPorID(1);
		System.out.println("Recuperar por ID: "+p);
		
		int quant = Fachada.getSingleton().recuperarQuantidadeProduto(p);
		System.out.println("Quantidade de produtos: "+quant);
		
		Fachada.getSingleton().adicionarQuantidadeProduto(p, 5);
		int quant2 = Fachada.getSingleton().recuperarQuantidadeProduto(p);
		System.out.println("Adicionar quantidade : "+quant2);
		
		Fachada.getSingleton().removerQuantidadeProduto(p, 10);
		int quant3 = Fachada.getSingleton().recuperarQuantidadeProduto(p);
		System.out.println("Remover quantidade : "+quant3);
		
		System.out.println("Listar Todos: "+Fachada.getSingleton().listarTodosProdutos());
		
		System.out.println("Recuperar id Pelo nome: "+ Fachada.getSingleton().recuperarIDPeloNomeProduto("Fanta"));
		
		//Alterar
		
		Produto pp =null;
		pp = Fachada.getSingleton().recuperarProdutoPorID(1);
		pp.setNome("Coca");
		Fachada.getSingleton().alterarProduto(pp);
		System.out.println("Apos alteracao: "+Fachada.getSingleton().recuperarProdutoPorID(1));
		*/
		
		//Pedido 
		
		/*
		//Cadastrar Pedido
		Produto p = null;
		Cardapio c = null;
		Mesa mesa = null;
		mesa = Fachada.getSingleton().recuperarMesaID(1);
		//p = Fachada.getSingleton().recuperarProdutoPorID(1);
	    c= Fachada.getSingleton().recuperarCardapioPorID(1);
		Pedido pedido = new Pedido(c,p,0,mesa);
		pedido.getCardapio().setId_cardapio(1);
		//pedido.getProduto().setId_produto(1);
		pedido.getMesa().setId_mesa(1);
		Fachada.getSingleton().cadastrarPedido(pedido);
		
		Pedido p = null;
		Cardapio card =null;
		
		//Listar Todos
		System.out.println( Fachada.getSingleton().listarTodosPedidos());
		
		//Deletar Pedido por mesa
		Fachada.getSingleton().deletarTodosPedidosPelaMesa(2);
		
		//Recuperar ultimo ID
		int a =Fachada.getSingleton().recuperarUltimoIDPedido();
		System.out.println(a);
		
		//Recuperar por ID
		System.out.println(Fachada.getSingleton().recuperarPedidoPorID(5));
		
		//Alterar
		p = Fachada.getSingleton().recuperarPedidoPorID(6);
		card = Fachada.getSingleton().recuperarCardapioPorID(4);
		p.setCardapio(card);
		Fachada.getSingleton().alterarPedido(p);
		
		//Retornar ID de pedido
		System.out.println(Fachada.getSingleton().retornarIDPedido(4, 1, 2));
		
		//Deletar pedido
		Fachada.getSingleton().deletarPedido(Fachada.getSingleton().recuperarPedidoPorID(7));
	
		//Listar Por mesa 
		
		*/
		/*
		// Rcuperar e Mostrar
		
				System.out.println(Fachada.getSingleton().recuperarContaID(1));
				System.out.println(Fachada.getSingleton().mostrarValorConta(Fachada.getSingleton().recuperarContaID(1)));
				System.out.println(Fachada.getSingleton().recuperarContaPorMesa(2));

		//CONTA
		
		//Cadastrar Conta

		
		 * Pedido ped = Fachada.getSingleton().recuperarPedidoPorID(9); Conta conta =
		 * null; Garcom g = Fachada.getSingleton().recuperarGarcomID(1); Mesa m =
		 * Fachada.getSingleton().recuperarMesaID(1); conta = new Conta(LocalDate.now(),
		 * ped, g, m, ped.getValor()); Fachada.getSingleton().cadastrarConta(conta);
		
		// Rcuperar e Mostrar
		System.out.println(Fachada.getSingleton().recuperarContaID(1));
		System.out.println(Fachada.getSingleton().mostrarValorConta(Fachada.getSingleton().recuperarContaID(1)));
		System.out.println(Fachada.getSingleton().recuperarContaPorMesa(2));

		// Deletar uma conta

		Fachada.getSingleton().deletarConta(Fachada.getSingleton().recuperarContaID(2));

		// Deletar conta por Mesa

		Fachada.getSingleton().deletarTodasContasPorMesa(Fachada.getSingleton().recuperarContaID(1));

		// GERENCIAMENTO DE CONTA

		Garcom g = Fachada.getSingleton().recuperarGarcomID(1);
		Mesa m = Fachada.getSingleton().recuperarMesaID(1);
		Conta c = Fachada.getSingleton().recuperarContaID(1);

		// CADASTRAR

		GerenciamentoContas gc = new GerenciamentoContas(g, m, Fachada.getSingleton().mostrarValorConta(c),
				LocalDate.now());
		Fachada.getSingleton().cadastrarGerenciamentoContas(gc);

		// LISTAR TODOS

		System.out.println(Fachada.getSingleton().listarTodosGerenciamentoContas());
*/
		/*
		 * System.out.println(Fachada.getSingleton().listarTodosPedidos());
		 * System.out.println(Fachada.getSingleton().listarPedidosPorMesa(1));
		 */	//}
	
//}
