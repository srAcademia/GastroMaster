package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import java.util.Scanner;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.DataNascimentoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.EnderecoVazioException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.GerenteExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.IDRecuperacaoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;

public class MainPrincipal extends MainGerente{
	public static void main(String[] args) throws SQLException, BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException, CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GerenteExistenteException, IDInexistenteException, SalarioInvalidoException, IDRecuperacaoInvalidaException {
		int opcao = -1;
		Scanner scan = new Scanner(System.in);
		do{
			System.out.println("Digite 1 para gerenciar Funcionários.");
			System.out.println("Digite 2 para gerenciar Mesas.");
			System.out.println("Digite 3 para gerenciar o Estoque.");
			System.out.println("Digite 4 para gerenciar o Cardápio.");
			System.out.println("Digite 5 para analisar dados do negócio.");
			System.out.println("Digite 0 para sair.");
			opcao = scan.nextInt();
			
			switch(opcao) {
				case 1:
					System.out.println("entrou 1");
					gerenciarCadastroGerente("Garanhuns", "Boa Vista", "Jose Duca da Silva", 9, "553233", "Kelwin", "131.892.294-14", "30/01/2001", "87 9953-3012", "kelwinjonas@gmail.com", "12345", 9999999);
					break;
				case 2:
					System.out.println("entrou 2");
					break;
				case 3:
					System.out.println("entrou 3");
					break;
				case 4:
					System.out.println("entrou 4");
					break;
				case 5:
					System.out.println("entrou 5");
					break;
				case 0:
					System.out.println("Encerrando o sistema...");
					break;
				default:
					System.out.println("Opcao Invalida");
			}
		}while(opcao != 0);
	}	
}
