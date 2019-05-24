/*package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioCardapio;
import br.com.ufrpeuag.gastromaster.dados.RepositorioConta;
import br.com.ufrpeuag.gastromaster.dados.RepositorioEndereco;
import br.com.ufrpeuag.gastromaster.dados.RepositorioGarcom;
import br.com.ufrpeuag.gastromaster.dados.RepositorioMesa;
import br.com.ufrpeuag.gastromaster.dados.RepositorioPedido;
import br.com.ufrpeuag.gastromaster.dados.RepositorioProduto;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class MainConta {
	private static Scanner src;
	public static void main(String[] args) throws SQLException {
		src = new Scanner(System.in);
		
	ConfiguracoesBanco.getSingleton().getConnection();
	String data = "25/01/2016";
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	LocalDate date = LocalDate.parse(data,formatter);
	//System.out.println(date);
	
	LocalDate localDate = LocalDate.now(); // Data atual
    
    // A classe do java.sql.Date converte o localDate

    java.sql.Date date = java.sql.Date.valueOf(localDate);
    // o formatador
    SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
    String dataFormatada = formatador.format(date);
    System.out.println(dataFormatada);
		
		
		
		//Mesa
		RepositorioMesa rm = new RepositorioMesa();

		Mesa m = new Mesa(2, 0);
		rm.inserir(m);
		
		
		//Garcom e endereco
		Endereco end  = new Endereco("Garanhuns","sitio","Mochila",30,"55555");
		
		ConfiguracoesBanco.getSingleton().getConnection();
		
		RepositorioGarcom rg = new  RepositorioGarcom();
		RepositorioEndereco e = new RepositorioEndereco();
		
		e.inserir(end);
		int a = e.recuperarUltimoID();
		System.out.println(a);
		Garcom g = new Garcom("Milena","11536598701","14/07/1198","88888","milly",220,end);
		g.getEndereco().setId_endereco(a);
		rg.inserir(g);
		
		
		//Produto
		RepositorioProduto rp= new RepositorioProduto();
		
		Produto p = new Produto("Vinho ", 3, 2.5);
		Produto p2 = new Produto("Fanta 500 ml", 3, 2.5);
		rp.inserir(p);
		rp.inserir(p2);
		
		//Cardapio
		RepositorioCardapio repCardapio = new RepositorioCardapio();
		
		Cardapio cardapio = new Cardapio("Pizza", 50);
		Cardapio cardapio2 = new Cardapio("Lasanha", 50);
		repCardapio.inserir(cardapio);
		repCardapio.inserir(cardapio2);
		
		
		//Pedido
		RepositorioPedido rpedido = new RepositorioPedido();
		RepositorioCardapio repCardapio = new RepositorioCardapio();
		RepositorioProduto rp= new RepositorioProduto();
		
		Cardapio card = new Cardapio();
		Produto prod = new Produto();
		Pedido pedido = null;
		
		
		System.out.println(repCardapio.listarTodos());
		System.out.println("Digite um  cardapio:");
		int cod_produto = src.nextInt();
		
		System.out.println(rp.listarTodos());
		System.out.println("Digite um produto:");
		int cod_cardapio = src.nextInt();
		
		
		card = repCardapio.recuperar(cod_cardapio);
		prod = rp.recuperar(cod_produto);
		
		double valor = 0;
		pedido = new Pedido(card, prod, valor);
		pedido.setValor(pedido.calcularValorPedido(card.getPreco(), prod.getPreco()));
		pedido.getCardapio().setId_cardapio(cod_cardapio);
		pedido.getProduto().setId_produto(cod_produto);
		rpedido.inserir(pedido);
		
		//Conta
		RepositorioConta rc = new RepositorioConta();
		Pedido pedido = null;
		Garcom g = null;
		RepositorioPedido rpedido = new RepositorioPedido();
		RepositorioGarcom rg = new  RepositorioGarcom();
		RepositorioMesa rm = new RepositorioMesa();
		Mesa m =null; 
		Conta c = null;
		Conta c = new Conta(1,date,pedido,g,m,50);
		pedido = rpedido.recuperar(2);
		g = rg.recuperar(1);
		m = rm.recuperar(1);
		System.out.println(g);
		System.out.println(m);
		System.out.println(pedido);
		c.setPedido(pedido);
		c.setGarcom(g);
		c.setMesa(m);
		rc.inserir(c);
		System.out.println(rm.listarTodos());
		System.out.println("Digite id da mesa:");
		int cod_mesa = src.nextInt();
		c = rc.recuperarPorMesa(cod_mesa);
		System.out.println(c);
		System.out.println(rc.fecharConta(c));
		rc.deletar(c);
	}

}
*/