package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.ConfiguracoesBanco;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ContaGerarException;
import br.com.ufrpeuag.gastromaster.negocio.fachada.Fachada;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;

public class MainConta {

	public void gerenciarCadastroConta(Garcom garcom, Mesa mesa, Pedido pedido) throws SQLException {
		try {	
		   	LocalDate localDate = LocalDate.now();
		   	Conta conta = new Conta(localDate, pedido, garcom, mesa, pedido.getValor());
		    Fachada.getSingleton().contaCadastroContaValidacao(conta);
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	public void gerenciarRemocaoConta(Integer codigo) throws SQLException {
		try {
			ConfiguracoesBanco.getSingleton().getConnection();	
			Conta conta = new Conta();
			conta = Fachada.getSingleton().contaRecuperarContaValidacao(codigo);
			Fachada.getSingleton().contaRemocaoContaValidacao(conta);
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
	}
	
	public double gerenciarGerarConta(Conta conta) throws SQLException {
		try {
			double valor = Fachada.getSingleton().contaGerarConta(conta);
			return valor;
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return 0;
	}
	
	public List<Conta> gerenciarRetornarTodasContaPorMesa(Integer codigo) throws SQLException, ContaGerarException {
		try {
			List<Conta> contas = new ArrayList<>();
			contas = Fachada.getSingleton().contaRetornarTodasContaPorMesaValidacao(codigo);
			return contas;
		}catch(ContaGerarException ex) {
			System.out.println(ex.getLocalizedMessage());
		}catch(Exception ex) {
			System.out.println("Erro inesperado.");
		}
		return null;
	}
	
	public void gerenciarRemoverTodasConta(Conta conta) throws SQLException{
		try {
			Fachada.getSingleton().contaRemoverTodasContaValidacao(conta);
		}catch(Exception ex) {
			System.out.println("Erro inesperado");
		}
	}
}