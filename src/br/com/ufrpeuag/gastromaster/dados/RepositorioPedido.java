package br.com.ufrpeuag.gastromaster.dados;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import br.com.ufrpeuag.gastromaster.dados.interfaces.PedidoDao;
import br.com.ufrpeuag.gastromaster.negocio.modelo.classes.Pedido;

public class RepositorioPedido implements PedidoDao {
	
	@Override
	public void inserir(Pedido pedido) {
		PreparedStatement pstmt = null;
		try {
			Connection conn = ConfiguracoesBanco.getSingleton().getConnection();
			String inserirSql = "INSERT INTO Pedido (cod_produto, cod_cardapio, valor) VALUES(?,?,?)";
			pstmt = conn.prepareStatement(inserirSql);

			pstmt.setInt(1, pedido.getProduto().getId_produto());
			pstmt.setInt(2, pedido.getCardapio().getId_cardapio());
			pstmt.setDouble(3, pedido.getValor());
			pstmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());
		} finally {

			try {
				pstmt.close();
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	@Override
	public Pedido recuperar(Integer codigo) {

		/*select *
from Pedido pe join Produto pr on (pe.cod_produto = pr.id_produto) JOIN Cardapio c on (pe.cod_cardapio= c.id_cardapio)
 where id_pedido = 1
		*/
		return null;
	}

	@Override
	public void alterar(Pedido d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deletar(Pedido d) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Pedido> listarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
