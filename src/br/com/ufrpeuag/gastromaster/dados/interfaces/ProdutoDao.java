package br.com.ufrpeuag.gastromaster.dados.interfaces;

import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Produto;

public interface ProdutoDao extends Dao<Produto> {
	
	public void removerQuantProduto(Produto p,Integer quantidade);
	public int retonarQuantidadeProduto(Produto produto);
	

}
