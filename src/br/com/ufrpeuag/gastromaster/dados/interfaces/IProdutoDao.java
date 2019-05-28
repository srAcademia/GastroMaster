package br.com.ufrpeuag.gastromaster.dados.interfaces;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public interface IProdutoDao extends IDao<Produto> {
	
	public void removerQuantProduto(Produto produto,Integer quantidade);
	public void adicionarQuantProduto(Produto produto,Integer quantidade);
	public int retornarQuantidadeProduto(Produto produto);
	public Produto retornarProduto(String nome);
	public int retornarID(String nome);
	

}
