/*package br.com.ufrpeuag.gastromaster.ui;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.GerenciamentoContas;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.CpfValidacao;
public class Teste {
	private static Scanner src;
	public static void main(String[] args) throws SQLException {
		src = new Scanner(System.in);
		
	ConfiguracoesBanco.getSingleton().getConnection();
	String data = "25/01/2016";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDate date = LocalDate.parse(data,formatter);
	//System.out.println(date);
	
	 // Data atual
    
    // A classe do java.sql.Date converte o localDate
	LocalDate localDate = LocalDate.now();
    java.sql.Date date = java.sql.Date.valueOf(localDate);
    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    String dataFormatada = formatador.format(date);
  
    System.out.println("Data em String:  "+dataFormatada);
	String data1 =dataFormatada;
	
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDate date1 = LocalDate.parse(data1,formatter);
	System.out.println("LocalDate : "+date1);
	
	java.sql.Date dat = java.sql.Date.valueOf(date1);
	SimpleDateFormat formatador2 = new SimpleDateFormat("dd/MM/yyyy");
	String dataFormatada2 = formatador2.format(dat);
	System.out.println("Strng da localdate "+dataFormatada2);
		
		
	    
		
		
		//Mesa Todos Métodos
		 RepositorioMesa rm = new RepositorioMesa();
		Mesa m = new Mesa(2, 0);
		rm.inserir(m);
		System.out.println(rm.recuperar(1));
		System.out.println(rm.listarTodos());
		m = rm.recuperar(1);
		m.setDisponibilidade(1);
		rm.alterar(m);
		System.out.println(rm.recuperarNumeroMesa(2));
		rm.deletar(rm.recuperar(1));
		 
		//Garcom e endereco
		Endereco end  = new Endereco("Garanhuns","sitio","Mochila",30,"55555");
		
		ConfiguracoesBanco.getSingleton().getConnection();
		
		RepositorioGarcom rg = new  RepositorioGarcom();
		RepositorioEndereco e = new RepositorioEndereco();
		
		e.inserir(end);
		int a = e.recuperarUltimoID();
		System.out.println(a);
		Garcom g = new Garcom("Milena","11536598701","14/07/1198","88888","milly",220,null,end);
		g.getEndereco().setId_endereco(a);
		g.setIdentificador(g.gerarIdentificador());
		rg.inserir(g);
		
		
		//Produto
		 RepositorioProduto rp= new RepositorioProduto();
		
		Produto p = new Produto("Vinho ", 3, 2.5);
		Produto p2 = new Produto("Fanta 500 ml", 3, 2.5);
		rp.inserir(p);
		rp.inserir(p2);
		 p = rp.recuperar(4);
		 p.setNome("Pitu");
		 rp.alterar(p);
		System.out.println(p);
		///Cardapio
		RepositorioCardapio repCardapio = new RepositorioCardapio();
		
		Cardapio cardapio = new Cardapio("Bife", 20);
		Cardapio cardapio2 = new Cardapio("Camarão", 30);
		repCardapio.inserir(cardapio);
		repCardapio.inserir(cardapio2);
		//repCardapio.deletar(repCardapio.recuperar(5));
		System.out.println(repCardapio.listarTodos());
		System.out.println(repCardapio.recuperar("Camarão"));
		System.out.println(repCardapio.retornarID("Camarão"));
		//System.out.println(repCardapio.retornarID("Pizza"));
		//System.out.println(rp.retornarID("Vinho "));
		
		 //Pedido
		RepositorioPedido rpedido = new RepositorioPedido();
		RepositorioCardapio repCardapio = new RepositorioCardapio();
		RepositorioProduto rp= new RepositorioProduto();
		
		Cardapio card = new Cardapio();
		Produto prod = new Produto();
		Pedido pedido = null;
		
		
		double valor = 0;
		Mesa m = new Mesa();
		
		pedido = new Pedido(card, prod, valor,m);
		
		pedido.setValor(pedido.calcularValorPedido(repCardapio.recuperar(1).getPreco(),rp.recuperar(1).getPreco()));
		pedido.getCardapio().setId_cardapio(1);
		pedido.getProduto().setId_produto(1);
		pedido.getMesa().setId_mesa(1);
		rpedido.inserir(pedido);
		System.out.println(rpedido.recuperar(1));
		System.out.println(rpedido.listarTodos());
		//System.out.println(rpedido.retornarId(2,1,1));
		
		//Conta
		
		Pedido pedido = null;
		Garcom g = null;
		RepositorioPedido rpedido = new RepositorioPedido();
		RepositorioGarcom rg = new  RepositorioGarcom();
		RepositorioMesa rm = new RepositorioMesa();
		RepositorioConta rc = new RepositorioConta();
		Mesa m =null; 
		Conta c = null;
		//Conta c = new Conta(1,localDate,pedido,g,m,50);
		//pedido = rpedido.recuperar(4);
		g = rg.recuperar(1);
		m = rm.recuperar(3);
		System.out.println(g);
		System.out.println(m);
		System.out.println(pedido);
		c.setPedido(pedido);
		c.setGarcom(g);
		c.setMesa(m);
	//	rc.inserir(c);
		c = rc.recuperar(1);
		//System.out.println(c);

		//REPOSITORIO GErencoam
		RepositorioGerenciamentoContas r = new RepositorioGerenciamentoContas();
		GerenciamentoContas gc = null;
		double a = rc.fecharConta(c);
		//System.out.println(a);
		LocalDate l = c.getData();
		gc = new GerenciamentoContas(g,m,a,l);
		//System.out.println(gc);
		//r.inserir(gc);
		System.out.println(r.recuperar(1));
		System.out.println(r.listarTodos());
		gc = r.recuperar(1);
		gc.setMesa(rm.recuperar(4));
		r.alterar(gc);
		System.out.println(r.listarTodos());
		
		RepositorioPedido rpedido = new RepositorioPedido();
		RepositorioMesa rm = new RepositorioMesa();
		RepositorioConta rc = new RepositorioConta();
		System.out.println(rc.recuperar(1));
		
		CpfValidacao cpf = new CpfValidacao();
		if (cpf.isCPF("11563286408")){
			System.out.println(cpf.imprimeCPF("11563286408"));
		}
		Conta c = null;
		System.out.println(rm.listarTodos());
		System.out.println("Digite id da mesa:");
		int cod_mesa = src.nextInt();
		rc.recuperarPorMesa(cod_mesa);
		System.out.println(c);
		System.out.println(rc.fecharConta(c));
		rc.deletar(c);
		
		
		System.out.println(rc.listarTodos());
		 
		 
	
	}
}
*/