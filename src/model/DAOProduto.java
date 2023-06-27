package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author andrelima
 */
public class DAOProduto {
    public List<Produto> getLista(){
        String sql = "select * from produto";
        List<Produto> lista = new ArrayList<>();
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Produto obj = new Produto();
                obj.setIdProduto(rs.getInt("idProduto"));
                obj.setNomeProduto(rs.getString("nomeProduto"));
                obj.setValorProduto(rs.getDouble("valorProduto"));
                lista.add(obj);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no getLista()" + e.getMessage());
        }
        return lista;
    }
    
    public boolean salvar(Produto obj) {
        if (obj.getIdProduto()== null) {
            return incluir(obj);
        } else {
            return alterar(obj);
        }

    }

    public boolean incluir(Produto obj) {
        String sql = "insert into produto (nomeProduto,valorProduto) values(?,?)";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeProduto());
            pst.setDouble(2, obj.getValorProduto());
            
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto incluido");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Produto não incluido");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no incluir do DAOProduto" + e.getMessage());
        }
        return false;
    }

    public boolean alterar(Produto obj) {
        String sql = "update produto set nomeProduto=?, valorProduto=? where idProduto=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setString(1, obj.getNomeProduto());
            pst.setDouble(2, obj.getValorProduto());
            pst.setInt(3, obj.getIdProduto());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto alterado");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Produto não alterado");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no alterar do DAOProduto" + e.getMessage());

        }
        return false;
    }
    public boolean remover(Produto obj) {
        String sql = "delete from produto where idProduto=?";
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, obj.getIdProduto());
            if (pst.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "Produto excluído");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Produto não excluído");
                return false;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro de SQL no excluir do DAOProduto" + e.getMessage());

        }
        return false;
    }
    
    public Produto localizar(Integer id){
        String sql = "select * from produto where idProduto=?";
        Produto obj = new Produto();
        try{
            PreparedStatement pst = Conexao.getPreparedStatement(sql);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                obj.setIdProduto(rs.getInt("idProduto"));
                obj.setNomeProduto(rs.getString("nomeProduto"));
                obj.setValorProduto(rs.getDouble("valorProduto"));
                return obj;
            }
        }catch(SQLException e){
            JOptionPane.showMessageDialog
        (null,"Erro de SQL Localizar" + e.getMessage());
    }
        return null;
    }
}
