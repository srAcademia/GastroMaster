package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import java.util.Scanner;

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
import br.com.ufrpeuag.gastromaster.negocio.excecoes.LoginInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeProdutoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RecuperarCPFException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SenhaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;

public class MainPrincipal{
	
	public static void main(String[] args) throws SQLException, BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException, CPFInvalidoException, 
	DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GerenteExistenteException, IDInexistenteException, SalarioInvalidoException, IDRecuperacaoInvalidaException, LoginInvalidoException, 
	SenhaInvalidaException, GerenteInexistenteException, EnderecoInexistenteException, RecuperarCPFException, ListarTodosInvalidoException, GarcomExistenteException, GarcomInexistenteException, PrecoInvalidoException, 
	ProdutoExistenteException, QuantidadeProdutoInvalidaException, ProdutoInexistenteException, IDRecuperacaoItemInvalidoException, QuantidadeInvalidaException {
		
		int opcao = -1;
		int opcao1 = -1; int opcao11 = -1; int opcao12 = -1;
		int opcao2 = -1;
		Gerente gerente = new Gerente();
		MainGerente mainGerente = new MainGerente();
		MainGarcom mainGarcom = new MainGarcom();
		MainProduto mainProduto = new MainProduto();
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
					System.out.println("Digite seu login:");
					//gerenciarCadastroGerente("Garanhuns", "Boa Vista", "Jose Duca da Silva", 9, "553233", "Milena", "8998324", "30/01/2001", "87 9953-3012", "kelwinjonas@gmail.com", 9999999, "12345", "");
					gerente = mainGerente.gerenciarVerificarGerente("2fe374");
					if(gerente != null) {
						System.out.println("Digite sua senha:");
						gerente = mainGerente.gerenciarLogarGerente("12345");
						if(gerente != null) {
							System.out.println("Digite 1 para gerenciar Gerente.");
							System.out.println("Digite 2 para gerenciar Garçom.");
							opcao1 = scan.nextInt();
							
							switch(opcao1) {
								case 1:
									do {
										System.out.println("Digite 1 para cadastrar um novo gerente.");
										System.out.println("Digite 2 para alterar um gerente.");
										System.out.println("Digite 3 para deletar um gerente.");
										System.out.println("Digite 4 para verificar a existência de um gerente.");
										System.out.println("Digite 5 para listar todos os gerentes.");
										System.out.println("Digite 0 sair desta opção.");
										opcao11 = scan.nextInt();
										
										switch(opcao11) {
											case 1:
												mainGerente.gerenciarCadastroGerente("Garanhuns", "Boa Vista", "Jose Duca da Silva", 9, "553233", "Milena", "8998324", "30/01/2001", "87 9953-3012", "kelwinjonas@gmail.com", 9999999, "12345", "");
												break;
											case 2:
												//ELE TA COLOCANDO A SENHA NULA; ERRO NO BANCO EM RECUPERAR PELO CPF
												mainGerente.gerenciarAlteracaoGerente("", "", "", 0, "", "Milena Macedo", "8998324", "", "", "", "", 0, "");
												break;
											case 3:
												mainGerente.gerenciarRemocaoGerente("8998324");
												break;
											case 4:
												mainGerente.gerenciarRecuperarGerente(2);
												break;
											case 5:
												mainGerente.gerenciarListarGerente();
												break;
											case 0:
												break;
											default:
												System.out.println("Opção Inválida");
										}
									}while(opcao11 != 0);
									break;
								case 2:
									do {
										System.out.println("Digite 1 para cadastrar um novo garçom.");
										System.out.println("Digite 2 para alterar um garçom.");
										System.out.println("Digite 3 para deletar um garçom.");
										System.out.println("Digite 4 para verificar a existência de um garçom.");
										System.out.println("Digite 5 para listar todos os garçons.");
										System.out.println("Digite 0 sair desta opção.");
										opcao12 = scan.nextInt();
										
										switch(opcao12) {
											case 1:
												mainGarcom.gerenciarCadastroGarcom("Garanhuns", "Boa Vista", "Jose Duca da Silva", 9, "553233", "Kelwin", "12312", "30/01/2001", "87 9953-3012", "kelwinjonas@gmail.com", 9999999, "");
												break;
											case 2:
												mainGarcom.gerenciarAlteracaoGarcom("", "", "", 0, "", "Kelwin Jonas", "131892", "", "", "", "", 0);
												break;
											case 3:
												mainGarcom.gerenciarRemocaoGarcom("131892");
												break;
											case 4:
												mainGarcom.gerenciarRecuperarGarcom(1);
												break;
											case 5:
												mainGarcom.gerenciarListarGarcom();
												break;
											case 0:
												break;
											default:
												System.out.println("Opção Inválida");
										
										}
									}while(opcao12 != 0);
									break;
								default:
									System.out.println("Opção Inválida");									
							}
							
						}
					}
					break;
				case 2:
					System.out.println("entrou 2");
					break;
				case 3:
					do {
						System.out.println("Digite 1 para cadastrar um prduto no estoque.");
						System.out.println("Digite 2 para alterar um produto.");
						System.out.println("Digite 3 para deletar um produto.");
						System.out.println("Digite 4 para verificar a existência de um produto.");
						System.out.println("Digite 5 para listar todos os produtos.");
						System.out.println("Digite 6 para saber a quantidade de um produto específico.");
						System.out.println("Digite 7 para adicionar uma quantidade a algum produto.");
						System.out.println("Digite 0 sair desta opção.");
						opcao2 = scan.nextInt();
						
						switch(opcao2) {
							case 1:
								mainProduto.gerenciarCadastroProduto("Coca-cola 2 litros", 50, 7);
								break;
							case 2:
								mainProduto.gerenciarAlteracaoProduto("Coca-cola 2,5 litros", "", 50, 0);
								break;
							case 3:
								mainProduto.gerenciarRemocaoProduto("Coca-cola 2 litros");
								break;
							case 4:
								mainProduto.gerenciarRecuperarProduto(3);
								break;
							case 5:
								mainProduto.gerenciarAdcionarQuantidadeProduto(3, 5);
								break;
							case 6:
								mainProduto.gerenciarVerificarQuantidadeProduto("Coca-cola 2 litros");
								break;
							case 7:
								mainProduto.gerenciarListarProduto();
							case 0:
								break;
							default:
								System.out.println("Opção Inválida");
						}
					}while(opcao2 != 0);
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
					System.out.println("Opção Inválida");
			}
		}while(opcao != 0);
	}	
}
