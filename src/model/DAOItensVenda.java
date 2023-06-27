package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author andrelima
 */
public class DAOItensVenda { // data acess object

    DAOProduto daoProduto = new DAOProduto();

    public List<ItensVenda> getListaItensVenda(Integer id) {
        String sql = "select * from itensvenda where venda_idvenda=?";
        List<ItensVenda> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                ItensVenda obj = new ItensVenda();
                System.out.println("IDVENDA="+rs.getInt("venda_idVenda"));
                // obj.getObjVenda().setIdVenda(rs.getInt("venda_idVenda"));
                obj.setIdItensVenda(rs.getInt("iditensvenda"));
                obj.setObjProduto(daoProduto.localizar(rs.getInt("produto_idProduto")));
                obj.setQuantidadeProduto(rs.getInt("quantidade"));
                obj.getObjProduto().setValorProduto(rs.getDouble("valor"));
                lista.add(obj);
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no getListaItensVenda" + e.getMessage());

        }
        return lista;
    }

    public boolean incluir(ItensVenda objItensVenda) {

        String sql = "insert into itensvenda (produto_idProduto,venda_idvenda,quantidade,valor) values (?,?,?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objItensVenda.getObjProduto().getIdProduto());
            pst.setInt(2, objItensVenda.getObjVenda().getIdVenda());
            pst.setInt(3, objItensVenda.getQuantidadeProduto());
            pst.setDouble(4, objItensVenda.getObjProduto().getValorProduto());

            if (pst.executeUpdate() > 0) {

                JOptionPane.showMessageDialog(null, "Item de venda cadastrado com sucesso!");

            } else {
                JOptionPane.showMessageDialog(null, "Item de venda não cadastrado!");

            }
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no getListaItensVenda" + e.getMessage());

        }
        return false;
    }

    public boolean remover(ItensVenda objItensVenda) {
        String sql = "delete from itensvenda where iditensvenda=?";
        
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, objItensVenda.getIdItensVenda());
            if (pst.executeUpdate() > 0) {
                // JOptionPane.showMessageDialog(null, "Item de venda removido com sucesso!");

            } else {
                 JOptionPane.showMessageDialog(null, "Item de venda não removido!");

            }
            pst.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no remover do ItensVenda" + e.getMessage());

        }
        return false;
    }

}
