package br.com.ufrpeuag.gastromaster.ui;

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
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Cardapio;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public class MainConta {

	public static void gerenciarCadastroConta(Garcom garcom, Mesa mesa, Pedido pedido) throws SQLException {
	try {	
		ConfiguracoesBanco.getSingleton().getConnection();	
	   	LocalDate localDate = LocalDate.now();
	    //java.sql.Date date = java.sql.Date.valueOf(localDate);
	    //SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
	    Conta conta = new Conta(0, localDate, pedido, garcom, mesa, pedido.getValor());
	    Fachada.getSingleton().contaCadastroConta(conta);
	}catch(Exception ex) {
		System.out.println("Erro Inesperado.");
	}
	}
		
	/*
		//Conta
		
		RepositorioConta rc = new RepositorioConta();
		Pedido pedido = null;
		Garcom g = null;
		RepositorioPedido rpedido = new RepositorioPedido();
		RepositorioGarcom rg = new  RepositorioGarcom();
		RepositorioMesa rm = new RepositorioMesa();
		Mesa m =null; 
		Conta c = null;
		
		 c = new Conta(1,date,pedido,g,m,50);
		
		
		pedido = rpedido.recuperar(2);
		
		RepositorioGarcom rg = new  RepositorioGarcom();
		//Recuperar pelo identificador do garcom
		g = rg.verificar("243e75");
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
		c = rc.recuperarPorMesa(1);
		System.out.println(c);
		rc.concluirPagamento(c);
		pedido = rpedido.recuperar(4);
		c.setPedido(pedido);
		rc.alterar(c);
		System.out.println(rc.fecharConta(c));
	}*/
}