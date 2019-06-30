package br.com.ufrpeuag.gastromaster.ui;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import br.com.ufrpeuag.gastromaster.negocio.excecoes.BairroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CEPInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CPFInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.CidadeInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ConcluirPagamentoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ContaGerarException;
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
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Conta;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Mesa;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;

public class MainPrincipal {

	public static void main(String[] args) throws SQLException, BairroInvalidoException, CEPInvalidoException,
			CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException, CPFInvalidoException,
			DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GerenteExistenteException,
			IDInexistenteException, SalarioInvalidoException, IDRecuperacaoInvalidaException, LoginInvalidoException,
			SenhaInvalidaException, GerenteInexistenteException, EnderecoInexistenteException, RecuperarCPFException,
			ListarTodosInvalidoException, GarcomExistenteException, GarcomInexistenteException, PrecoInvalidoException,
			ProdutoExistenteException, QuantidadeProdutoInvalidaException, ProdutoInexistenteException,
			IDRecuperacaoItemInvalidoException, QuantidadeInvalidaException, PratoInexistenteException,
			MesaCadastradaException, MesaDisponibilidadeInvalidaException, IDRecuperarMesaException,
			MesaInexistenteException, PratoExistenteException, PedidoInvalidoException, PedidoVazioException,
			PedidoInexistenteException, ContaGerarException, ConcluirPagamentoException, RelatorioVazioException {

		int opcao = -1;
		int opcao1 = -1;
		int opcao11 = -1;
		int opcao12 = -1;
		int opcao2 = -1;
		int opcao22 = -1;
		int opcao3 = -1;
		int opcao4 = -1;
		// int opcao5 = -1;
		
		Gerente gerente = new Gerente();
		MainGerente mainGerente = new MainGerente();
		MainGarcom mainGarcom = new MainGarcom();
		MainProduto mainProduto = new MainProduto();
		MainCardapio mainCardapio = new MainCardapio();
		MainMesa mainMesa = new MainMesa();
		MainPedido mainPedido = new MainPedido();
		MainConta mainConta = new MainConta();
		MainGerenciamento mainGerenciamento = new MainGerenciamento();
		Garcom garcom = new Garcom();
		Pedido pedido = new Pedido();
		Mesa mesa = new Mesa();
		Scanner scan = new Scanner(System.in);
		do {
			System.out.println("Digite 1 para gerenciar Funcionários.");
			System.out.println("Digite 2 para gerenciar Mesas.");
			System.out.println("Digite 3 para gerenciar o Estoque.");
			System.out.println("Digite 4 para gerenciar o Cardápio.");
			System.out.println("Digite 5 para analisar dados do negócio.");
			System.out.println("Digite 0 para sair.");
			opcao = scan.nextInt();

			switch (opcao) {
			case 1:
				System.out.println("Digite seu login:");
				mainGerente.gerenciarCadastroGerente("Garanhuns", "Boa Vista", "Jose Duca da Silva", 9, "553233", "Milena", "13189229414", LocalDate.now(), "87 9953-3012",
				"kelwinjonas@gmail.com", 9999999, "12345", "");
				//gerente = mainGerente.gerenciarVerificarGerente("2cdfc9");
				if (gerente != null) {
					System.out.println("Digite sua senha:");
					gerente = mainGerente.gerenciarLogarGerente("12345");
					if (gerente != null) {
						System.out.println("Digite 1 para gerenciar Gerente.");
						System.out.println("Digite 2 para gerenciar Garçom.");
						System.out.println("Digite 0 sair desta opção.");

						opcao1 = scan.nextInt();

						switch (opcao1) {
						case 1:
							do {
								System.out.println("Digite 1 para cadastrar um novo gerente.");
								System.out.println("Digite 2 para alterar um gerente.");
								System.out.println("Digite 3 para deletar um gerente.");
								System.out.println("Digite 4 para verificar a existência de um gerente.");
								System.out.println("Digite 5 para listar todos os gerentes.");
								System.out.println("Digite 0 sair desta opção.");
								opcao11 = scan.nextInt();

								switch (opcao11) {
								case 1:
									mainGerente.gerenciarCadastroGerente("Garanhuns", "Boa Vista", "Jose Duca da Silva",
											9, "553233", "Milena", "13189229414", LocalDate.now(), "87 9953-3012",
											"kelwinjonas@gmail.com", 9999999, "12345", "");
									break;
								case 2:
									/*mainGerente.gerenciarAlteracaoGerente("", "", "", 0, "", "Milena Macedo", "8998324",
											"", "", "", "", 0, "");*/
									break;
								case 3:
									//mainGerente.gerenciarRemocaoGerente("8998324");
									break;
								case 4:
									mainGerente.gerenciarRecuperarGerente(2);
									break;
								case 5:
									//mainGerente.gerenciarListarGerente();
									break;
								case 0:
									break;
								default:
									System.out.println("Opção Inválida");
								}
							} while (opcao11 != 0);
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

								switch (opcao12) {
								case 1:
									mainGarcom.gerenciarCadastroGarcom("Garanhuns", "Boa Vista", "Jose Duca da Silva",
											9, "553233", "Kelwin", "13189229414", LocalDate.now(), "87 9953-3012",
											"kelwinjonas@gmail.com", 9999999, "");
									break;
								case 2:
									/*mainGarcom.gerenciarAlteracaoGarcom("", "", "", 0, "", "Kelwin Jonas", "131892", "",
											"", "", "", 0);*/
									break;
								case 3:
									mainGarcom.gerenciarRemocaoGarcom("131892");
									break;
								case 4:
									mainGarcom.gerenciarRecuperarGarcom(1);
									break;
								case 5:
									//mainGarcom.gerenciarListarGarcom();
									break;
								case 0:
									break;
								default:
									System.out.println("Opção Inválida");

								}
							} while (opcao12 != 0);
							break;
						case 0:
							break;
						default:
							System.out.println("Opção Inválida");
						}

					}
				}
				break;
			case 2:
				do {
					System.out.println("Digite 1 para cadastrar uma mesa ao restaurante.");
					System.out.println("Digite 2 para alterar o número de uma mesa.");
					System.out.println("Digite 3 para deletar uma mesa.");
					System.out.println("Digite 4 para gerenciar uma mesa específica.");
					System.out.println("Digite 5 para listar todas as mesas do restaurante.");
					System.out.println("Digite 0 sair desta opção.");
					opcao2 = scan.nextInt();

					switch (opcao2) {
					case 1:
						mainMesa.gerenciarCadastroMesa(1, 1);
						break;
					case 2:
						mainMesa.gerenciarAlteracaoMesa(1, 2);
						break;
					case 3:
						mainMesa.gerenciarRemocaoMesa(2);
						break;
					case 4:
						mesa = mainMesa.gerenciarGerenciamentoMesa(1);
						//garcom = mainGarcom.gerenciarVerificarGarcom("1164f4");
						if (mesa != null && garcom != null) {
							do {
								System.out.println("Digite 1 para fazer um pedido à mesa.");
								System.out.println("Digite 2 para deletar um pedido da mesa.");
								System.out.println("Digite 3 para listar todos os pedidos da mesa.");
								System.out.println("Digite 4 para gerar a conta da mesa.");
								System.out.println("Digite 5 para realizar o pagamento dos pedidos efetuados à mesa.");
								System.out.println("Digite 6 para mudar a disponibilidade da mesa.");
								System.out.println("Digite 0 para sair da mesa.");
								opcao22 = scan.nextInt();

								switch (opcao22) {
								case 1:
									Integer id = mainPedido.gerenciarCadastroPedido("Camarão", "Coca-cola 2,5 litros",
											mesa);
									if (id != 0) {
										pedido = mainPedido.gerenciarRecuperarPedido((Integer) id);
										mainConta.gerenciarCadastroConta(garcom, mesa, pedido);
									}
									break;
								case 2:
									Integer id_cardapio = mainCardapio.gerenciarRecuperarIDCardapio("Camarão");
									Integer id_produto = mainProduto.gerenciarRecuperarIDProduto("Coca-cola 2,5 litros");
									pedido = mainPedido.gerencairRecuperarCodigoPedido(id_cardapio, id_produto,
											(Integer) mesa.getId_mesa());
									if (pedido != null) {
										Integer id_conta = pedido.getId_pedido();
										mainProduto.gerenciarAdcionarQuantidadeProduto(pedido.getProduto().getId_produto(), 1);
										mainConta.gerenciarRemocaoConta(id_conta);
										mainPedido.gerenciarRemocaoPedido(pedido);
									}
									break;
								case 3:
									mainPedido.gerenciarListarPedido();
									break;
								case 4:
									List<Conta> contas = new ArrayList<>();
									//contas = mainConta.gerenciarRetornarTodasContaPorMesa((Integer) mesa.getId_mesa());
									if (contas != null) {
										System.out.println(
												"Conta da mesa: " + mainConta.gerenciarGerarConta(contas.get(0)));
									}
									break;
								case 5:
									List<Conta> contas1 = new ArrayList<>();
									//contas1 = mainConta.gerenciarRetornarTodasContaPorMesa((Integer) mesa.getId_mesa());
									if (contas1 != null) {
										mainGerenciamento.gerenciarCadastroGerenciamento(garcom, mesa, mainConta.gerenciarGerarConta(contas1.get(0)), contas1.get(0).getData());
										mainConta.gerenciarRemoverTodasConta(contas1.get(0));
										mainPedido.gerenciarRemoverTodosPedido((Integer) mesa.getId_mesa());
										mainMesa.gerenciarMudarDisponibilidadeMesa(mesa.getNumero());
									}
									break;
								case 6:
									mainMesa.gerenciarMudarDisponibilidadeMesa(mesa.getNumero());
									break;
								case 0:
									break;
								default:
									System.out.println("Opção inválida.");

								}
							} while (opcao22 != 0);
						}
						break;
					case 5:
						//mainMesa.gerenciarListarMesa();
					case 0:
						break;
					default:
						System.out.println("Opção Inválida");
					}
				} while (opcao2 != 0);
				break;
			case 3:
				do {
					System.out.println("Digite 1 para cadastrar um produto no estoque.");
					System.out.println("Digite 2 para alterar um produto.");
					System.out.println("Digite 3 para deletar um produto.");
					System.out.println("Digite 4 para verificar a existência de um produto.");
					System.out.println("Digite 5 para adicionar uma quantidade a algum produto.");
					System.out.println("Digite 6 para saber a quantidade de um produto específico.");
					System.out.println("Digite 7 para listar todos os produtos.");
					System.out.println("Digite 0 sair desta opção.");
					opcao3 = scan.nextInt();

					switch (opcao3) {
					case 1:
						mainProduto.gerenciarCadastroProduto("Coca-cola 2,5 litros", 50, 7);
						break;
					case 2:
						//mainProduto.gerenciarAlteracaoProduto("Coca-cola 2,5 litros", "", 1, 0);
						break;
					case 3:
						mainProduto.gerenciarRemocaoProduto("Coca-cola 2 litros");
						break;
					case 4:
						mainProduto.gerenciarRecuperarProduto(1);
						break;
					case 5:
						mainProduto.gerenciarAdcionarQuantidadeProduto(3, 5);
						break;
					case 6:
						mainProduto.gerenciarVerificarQuantidadeProduto("Coca-cola 2 litros");
						break;
					case 7:
						//mainProduto.gerenciarListarProduto();
					case 0:
						break;
					default:
						System.out.println("Opção Inválida");
					}
				} while (opcao3 != 0);
				break;
			case 4:
				do {
					System.out.println("Digite 1 para cadastrar um prato ao cardápio.");
					System.out.println("Digite 2 para alterar um prato.");
					System.out.println("Digite 3 para deletar um prato.");
					System.out.println("Digite 4 para verificar a existência de um prato.");
					System.out.println("Digite 5 para listar todos os pratos do cardápio.");
					System.out.println("Digite 0 sair desta opção.");
					opcao4 = scan.nextInt();

					switch (opcao4) {
					case 1:
						mainCardapio.gerenciarCadastroCardapio("Camarão", 32);
						break;
					case 2:
						//mainCardapio.gerenciarAlteracaoCardapio("Camarão", "", 30);
						break;
					case 3:
						mainCardapio.gerenciarRemocaoCardapio("Camarão");
						break;
					case 4:
						mainCardapio.gerenciarRecuperarCardapio(2);
						break;
					case 5:
						//mainCardapio.gerenciarListarCardapio();
					case 0:
						break;
					default:
						System.out.println("Opção Inválida");
					}
				} while (opcao4 != 0);
				break;
			case 5:
				System.out.println("Digite seu login:");
				//gerente = mainGerente.gerenciarVerificarGerente("260c75");
				if (gerente != null) {
					System.out.println("Digite sua senha:");
					gerente = mainGerente.gerenciarLogarGerente("12345");
					if (gerente != null) {
						mainGerenciamento.gerenciarListarGerenciamento();
					}
				}
				break;
			case 0:
				System.out.println("Encerrando o sistema...");
				break;
			default:
				System.out.println("Opção Inválida");
			}
		} while (opcao != 0);
	}
}