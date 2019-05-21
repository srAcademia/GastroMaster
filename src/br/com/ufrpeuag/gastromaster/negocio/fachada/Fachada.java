package br.com.ufrpeuag.gastromaster.negocio.fachada;

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
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NomeInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.NumeroInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.PrecoInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoExistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.ProdutoInexistenteException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.QuantidadeProdutoInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.RuaInvalidaException;
import br.com.ufrpeuag.gastromaster.negocio.excecoes.SalarioInvalidoException;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Endereco;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Garcom;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Gerente;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.EnderecoValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.GarcomValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.GerenteValidacao;
import br.com.ufrpeuag.gastromaster.negocio.validacoes.ProdutoValidacao;

public class Fachada {
	private static Fachada singleton = null;
	private EnderecoValidacao endereco;
	private GarcomValidacao garcom;
	private GerenteValidacao gerente;
	private ProdutoValidacao produto;
	
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
	}
	
	public void enderecoCadastroValidacao(Endereco endereco) throws BairroInvalidoException, CEPInvalidoException, CidadeInvalidaException, NumeroInvalidoException, RuaInvalidaException{
		this.endereco.enderecoCadastroValidacao(endereco);
	}
	
	public void enderecoRemocaoValidacao(Endereco endereco) throws EnderecoInexistenteException{
		this.endereco.enderecoRemocaoValidacao(endereco);
	}
	
	public void enderecoAlteracaoValidacao(Endereco endereco) throws EnderecoInexistenteException{
		this.endereco.enderecoAlteracaoValidacao(endereco);
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
	
	public void garcomAlteracaoValidacao(Garcom garcom) throws GarcomInexistenteException{
		this.garcom.garcomAlteracaoValidacao(garcom);
	}
	
	public Garcom garcomRecuperarValidacao(Integer codigo) throws IDRecuperacaoInvalidaException{
		return this.garcom.garcomRecuperarValidacao(codigo);
	}
	
	public void gerenteCadastroValidacao(Gerente gerente) throws CPFInvalidoException, DataNascimentoInvalidaException, EnderecoVazioException, NomeInvalidoException, GerenteExistenteException{
		this.gerente.gerenteCadastroValidacao(gerente);
	}
	
	public void gerenteRemocaoValidacao(Gerente gerente) throws GerenteInexistenteException{
		this.gerente.gerenteRemocaoValidacao(gerente);
	}
	
	public void gerenteAlteracaoValidacao(Gerente gerente) throws GerenteInexistenteException{
		this.gerente.gerenteAlteracaoValidacao(gerente);
	}
	
	public void produtoCadastroValidacao(Produto produto) throws PrecoInvalidoException, ProdutoExistenteException, QuantidadeProdutoInvalidaException, NomeInvalidoException {
		this.produto.produtoCadastroValidacao(produto);
	}
	
	public void produtoRemocaoValidacao(Produto produto) throws ProdutoInexistenteException{
		this.produto.produtoRemocaoValidacao(produto);
	}
	
	public void produtoAlteracaoValidacao(Produto produto) throws ProdutoInexistenteException{
		this.produto.produtoAlteracaoValidacao(produto);
	}
}
